import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileService } from '../../services/profile.service';
import { Profile } from '../../model/profile';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profile: Profile | null = null;
  error: string | null = null;

  lastSessions: any[] = [];
  isLoadingSessions = false;

  constructor(
    private profileService: ProfileService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.profileService.getProfile().subscribe({
      next: (data: Profile) => {
        this.profile = data;
        this.loadLastSessions();
      },
      error: (err) => {
        console.error(err);
        this.error = 'Ошибка загрузки профиля';
      }
    });
  }

  private loadLastSessions(): void {
    this.isLoadingSessions = true;
    this.profileService.getLast5Sessions().subscribe({
      next: (sessions: any[]) => {
        this.lastSessions = sessions;
        this.isLoadingSessions = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Не удалось загрузить последние игры';
        this.isLoadingSessions = false;
      }
    });
  }

  startGame(): void {
    this.router.navigate(['/game-settings']);
  }

  goToRating(): void {
    this.router.navigate(['/rating']);
  }

  goToFaq(): void {
    this.router.navigate(['/faq']);
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}