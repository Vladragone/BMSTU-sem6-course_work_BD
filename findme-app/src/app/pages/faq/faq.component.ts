import { Component, OnInit } from '@angular/core';
import { FaqService, Faq } from '../../services/faq.service';
import { MatExpansionModule } from '@angular/material/expansion';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.scss'],
    imports: [CommonModule, MatExpansionModule, HttpClientModule],

})
export class FaqComponent implements OnInit {
  faqs: Faq[] = [];

  constructor(private faqService: FaqService, private router: Router) {}

  ngOnInit(): void {
    this.faqService.getFaqs().subscribe((data) => {
      this.faqs = data;
    });
  }

  onBack(): void {
    this.router.navigate(['']);
  }
}
