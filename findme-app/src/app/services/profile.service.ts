import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { jwtDecode } from 'jwt-decode';
import { Profile } from '../model/profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getProfile(): Observable<Profile> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': token ?? ''
    });
    return this.http.get<Profile>(`${this.apiUrl}/profile`, { headers }).pipe(
      catchError((err: HttpErrorResponse) => {
        console.error('Ошибка при загрузке профиля', err);
        return throwError(() => new Error('Ошибка загрузки профиля'));
      })
    );
  }

  getLast5Sessions(): Observable<any[]> {
    const token = localStorage.getItem('token');
    if (!token) {
      return of([]);
    }

    let userId: number | null = null;
    try {
      const decoded: any = jwtDecode(token);
      userId = decoded?.user_id || null;
    } catch (e) {
      console.error('Ошибка декодирования JWT:', e);
      userId = null;
    }

    if (userId === null) {
      return of([]);
    }

    const headers = new HttpHeaders({
      'Authorization': token
    });

    return this.http.get<any[]>(`${this.apiUrl}/user/${userId}/sessions`, { headers }).pipe(
      catchError((err: HttpErrorResponse) => {
        console.error('Ошибка при загрузке последних игр', err);
        return throwError(() => new Error('Не удалось загрузить последние игры'));
      })
    );
  }
}