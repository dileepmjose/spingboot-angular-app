import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Person } from '../models/person.model';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-create-person',
  template: `
    <h2>Create Person</h2>
    <form (ngSubmit)="savePerson()">
      <input [(ngModel)]="person.name" name="name" placeholder="Name" required>
      <input [(ngModel)]="person.age" name="age" type="number" placeholder="Age">
      <input [(ngModel)]="person.gender" name="gender" placeholder="Gender">
      <input [(ngModel)]="person.eyeColor" name="eyeColor" placeholder="Eye Color">
      <input [(ngModel)]="person.favoriteFruit" name="favoriteFruit" placeholder="Favorite Fruit">

      <h3>Company</h3>
      <input [(ngModel)]="person.company.title" name="companyTitle" placeholder="Company Title">
      <input [(ngModel)]="person.company.email" name="companyEmail" placeholder="Email">
      <input [(ngModel)]="person.company.phone" name="companyPhone" placeholder="Phone">
      <input [(ngModel)]="person.company.location.country" name="companyCountry" placeholder="Country">
      <input [(ngModel)]="person.company.location.address" name="companyAddress" placeholder="Address">

      <h3>Tags (comma separated)</h3>
      <input [(ngModel)]="tagInput" name="tags" placeholder="e.g. enim,id,velit">
      
      <button type="submit">Save</button>
    </form>
  `,
})
export class CreatePersonComponent {
  tagInput = '';
  person: Person = {
    index: 0,
    name: '',
    isActive: true,
    registered: new Date().toISOString(),
    age: 0,
    gender: '',
    eyeColor: '',
    favoriteFruit: '',
    company: {
      title: '',
      email: '',
      phone: '',
      location: {
        country: '',
        address: ''
      }
    },
    tags: []
  };

  savePerson() {
    this.person.tags = this.tagInput.split(',').map(t => t.trim());
    localStorage.setItem('person', JSON.stringify(this.person));
    alert('Person saved!');
  }
}