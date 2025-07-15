import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-tag',
  imports: [CommonModule],
  template: `<h2>Tag Component</h2>`
})
export class TagComponent {}