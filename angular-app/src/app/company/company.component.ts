import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-company',
  imports: [CommonModule],
  template: `<h2>Company Page (Standalone)</h2>`
})
export class CompanyComponent {}