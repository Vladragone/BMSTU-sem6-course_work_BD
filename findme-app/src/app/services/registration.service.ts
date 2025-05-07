import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../env/env';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private apiUrl = `${environment.apiBaseUrl}/api/register`;

  constructor(private http: HttpClient) { }

  register(username: string, email: string, password: string): Observable<any> {
    const registrationData = { username, email, password };
    return this.http.post(this.apiUrl, registrationData).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    let errorMessage = 'Произошла ошибка при регистрации. Пожалуйста, попробуйте снова.';
    if (error.status === 400) {
      errorMessage = 'Логин или почта уже используются';
    }
    return throwError(() => new Error(errorMessage));
  }
}
