import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { gameService, gameQuestion, gameAnswerResponse } from '../../services/game.service';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class gameComponent implements OnInit {
  question!: gameQuestion;
  answerForm!: FormGroup;
  feedback!: string;
  confirmEnabled: boolean = true;
  errorMsg: string = '';

  constructor(
    private gameService: gameService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadQuestion();
    this.answerForm = this.fb.group({
      answer: ['', Validators.required]
    });
  }

  loadQuestion(): void {
    this.gameService.getQuestion().subscribe({
      next: (q) => this.question = q,
      error: (err) => {
        console.error('Ошибка загрузки вопроса:', err);
        this.errorMsg = 'Ошибка загрузки вопроса';
      }
    });
  }

  submitAnswer(): void {
    if (this.answerForm.valid && this.question) {
      const answer = this.answerForm.value.answer;
      this.gameService.submitAnswer(this.question.id, answer).subscribe({
        next: (response: gameAnswerResponse) => {
          this.feedback = response.message;
        },
        error: (err) => {
          console.error('Ошибка отправки ответа:', err);
          this.errorMsg = 'Ошибка отправки ответа';
        }
      });
    }
  }

  confirmMiniMap(): void {
    console.log('Mini map confirmed');
  }
}
