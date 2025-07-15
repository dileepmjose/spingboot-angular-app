import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Person } from '../../models/person.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: 'create-person.component.html',
  styleUrl : 'create-person.component.css', 
})
export class CreatePersonComponent {
  person: Person | undefined;

  newTag = '';

  ngOnInit() {
    this.resetForm();
  }

  constructor(private http: HttpClient) {}
 
  addTag() {
    if (this.newTag.trim()) {
      this.person.tags.push(this.newTag.trim());
      this.newTag = '';
    }
  }

  submitForm() {
    this.http.post('http://localhost:8080/api/persons/create', this.person)
      .subscribe({
        next: (res) => console.log('Saved', res),
        error: (err) => console.error('Error:', err)
        
      });
      this.resetForm();
  }

    resetForm() {
      this.person = {
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
  this.newTag = '';
    }

}