import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RegistrationService } from '../../services/registration.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  imports: [ReactiveFormsModule, CommonModule]
})
export class RegisterComponent {

  registerForm: FormGroup;
  isSubmitting = false;
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private registrationService: RegistrationService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      return;
    }
    const { username, email, password } = this.registerForm.value;
  
    this.registrationService.register(username, email, password)
      .subscribe({
        next: () => {
          this.router.navigate(['/login']);
        },
        error: (err: any) => {
          this.errorMessage = err.message;
          console.error(err);
        },
      });
  }

  goBack(): void {
    this.router.navigate(['/']);
  }
}
