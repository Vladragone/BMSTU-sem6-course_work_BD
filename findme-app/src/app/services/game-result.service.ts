import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../env/env';

@Injectable({
  providedIn: 'root'
})
export class GameResultService {
  private apiUrl = `${environment.apiBaseUrl}/api/profile/score`;

  constructor(private http: HttpClient) {}

  updateScore(score: number) {
    const token = localStorage.getItem('token');
    if (!token) return;

    const headers = new HttpHeaders().set('Authorization', token);
    const body = { score };

    return this.http.put(this.apiUrl, body, { headers });
  }
}
