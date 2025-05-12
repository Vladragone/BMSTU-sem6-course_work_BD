import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../env/env';

@Injectable({
  providedIn: 'root'
})
export class GameResultService {
  private apiUrl = `${environment.apiBaseUrl}/api/profile/score`;
  private saveGameUrl = `${environment.apiBaseUrl}/api/save-game`;

  constructor(private http: HttpClient) {}

  updateScore(score: number) {
    const token = localStorage.getItem('token');
    if (!token) return;

    const headers = new HttpHeaders().set('Authorization', token);
    const body = { score };

    return this.http.put(this.apiUrl, body, { headers });
  }

  saveGame(userId: number, userLat: number, userLng: number, correctLat: number, correctLng: number, earnedScore: number) {
    const token = localStorage.getItem('token');
    if (!token) return;

    const headers = new HttpHeaders().set('Authorization', token);
    const body = { userId, userLat, userLng, correctLat, correctLng, earnedScore };
    console.log(body);
    return this.http.post(this.saveGameUrl, body, { headers });
  }
}
