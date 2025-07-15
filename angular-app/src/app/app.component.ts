import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, CommonModule],
  template: `
    <h1>Angular 20.1.0 - Person Manager</h1>
    <nav>
      <a routerLink="/create-person">Create Person</a> |
      <a routerLink="/display-person">Display Person</a> |
      <a routerLink="/company">Company</a> |
      <a routerLink="/tags">Tags</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}