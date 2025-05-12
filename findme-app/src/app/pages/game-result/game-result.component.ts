import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DecimalPipe } from '@angular/common';
import { GameResultService } from '../../services/game-result.service';
import { HttpClientModule } from '@angular/common/http';

declare const ymaps: any;

@Component({
  selector: 'app-game-result',
  templateUrl: './game-result.component.html',
  styleUrls: ['./game-result.component.scss'],
  standalone: true,
  imports: [CommonModule, DecimalPipe, HttpClientModule]
})
export class GameResultComponent implements OnInit {
  trueLat!: number;
  trueLng!: number;
  userGuessLat!: number;
  userGuessLng!: number;
  score: number = 0;
  distance: number = 0;
  guessMap: any;
  originPlacemark: any;
  guessPlacemark: any;
  line: any;
  userId!: number;

  constructor(private route: ActivatedRoute, private router: Router, private gameResultService: GameResultService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.trueLat = +params['trueLat'];
      this.trueLng = +params['trueLng'];
      this.userGuessLat = +params['userGuessLat'];
      this.userGuessLng = +params['userGuessLng'];
      const token = localStorage.getItem('token');
      if (token) {
        this.userId = this.getUserIdFromToken(token);
      }
      if (this.trueLat && this.trueLng && this.userGuessLat && this.userGuessLng) {
        this.calculateScore();
        this.loadMap();
      }
    });
  }

  private getUserIdFromToken(token: string): number {
    const payload = this.decodeJWT(token);
    return payload.user_id; // Имя поля в payload может быть разным, у вас может быть 'userId' или 'id'
  }

  private decodeJWT(token: string): any {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Invalid token format');
    }
    const payload = parts[1];
    const decodedPayload = atob(payload);
    return JSON.parse(decodedPayload);
  }

  private loadMap(): void {
    const waitForYMaps = () => {
      if (typeof ymaps === 'undefined') {
        setTimeout(waitForYMaps, 100);
      } else {
        ymaps.ready(() => {
          this.guessMap = new ymaps.Map('guess-map', {
            center: [this.trueLat, this.trueLng],
            zoom: 12,
            controls: ['zoomControl']
          });

          this.originPlacemark = new ymaps.Placemark([this.trueLat, this.trueLng], {
            balloonContent: 'Исходная точка'
          }, {
            iconColor: 'red'
          });

          this.guessPlacemark = new ymaps.Placemark([this.userGuessLat, this.userGuessLng], {
            balloonContent: 'Точка пользователя'
          }, {
            iconColor: 'green'
          });

          this.line = new ymaps.GeoObject({
            geometry: {
              type: 'LineString',
              coordinates: [
                [this.trueLat, this.trueLng],
                [this.userGuessLat, this.userGuessLng]
              ]
            },
            properties: {
              balloonContent: 'Линия между точками'
            }
          });

          this.guessMap.geoObjects.add(this.originPlacemark);
          this.guessMap.geoObjects.add(this.guessPlacemark);
          this.guessMap.geoObjects.add(this.line);

          this.guessMap.setBounds(this.guessMap.geoObjects.getBounds(), { checkZoomRange: true });
        });
      }
    };

    waitForYMaps();
  }

  private calculateScore(): void {
    const R = 6371;
    const dLat = this.degreesToRadians(this.userGuessLat - this.trueLat);
    const dLng = this.degreesToRadians(this.userGuessLng - this.trueLng);
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(this.degreesToRadians(this.trueLat)) * Math.cos(this.degreesToRadians(this.userGuessLat)) *
      Math.sin(dLng / 2) * Math.sin(dLng / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distanceInKm = R * c;

    this.distance = distanceInKm;
    this.score = Math.max(0, Math.round(5000 - (distanceInKm * 1000)));
    this.gameResultService.updateScore(this.score)?.subscribe();
    this.gameResultService.saveGame(this.userId, this.userGuessLat, this.userGuessLng, this.trueLat, this.trueLng, this.score)?.subscribe();
  }

  private degreesToRadians(degrees: number): number {
    return degrees * (Math.PI / 180);
  }

  onPlayAgain(): void {
    this.router.navigate(['/game-settings']);
  }

  onProfile(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.router.navigate(['/profile']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
