import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PersonService } from '../../services/person.service';


@Component({
  selector: 'app-display-person',
  standalone: true,
  imports: [CommonModule, HttpClientModule, FormsModule],
  templateUrl: './display-person.component.html',
  styleUrl : './display-person.component.css', 
})
export class DisplayPersonComponent implements OnInit {

  response: any[] = [];
  selectedEyeColor: string = '';
  selectedCountry: string = '';
  selectedName: string = '';

  uniqueEyeColors: string[] = [''];
  uniqueCountry: string[] = [''];

  constructor(private personService: PersonService) {}

  ngOnInit() {
    this.loadAllPersons();
    this.loadEyeColors()
    this.loadCountry();
     
  }

  loadAllPersons() {
    this.personService.loadAllPersons()
      .subscribe({
        next: data => this.response = data,
        error: err => console.error('Failed to load persons:', err)
      });
  }

  loadEyeColors() {
    this.personService.loadEyeColors()
      .subscribe({
        next: data => this.uniqueEyeColors = data,
        error: err => console.error('Failed to load eye colors:', err)
      });
  }

  loadCountry() {
    this.personService.loadCountry()
      .subscribe({
        next: data => this.uniqueCountry = data,
        error: err => console.error('Failed to load country:', err)
      });
  }

  
  onChangeSearch() {
      this.personService.onChangeSearch(this.selectedEyeColor,this.selectedCountry,this.selectedName )
        .subscribe({
          next: data => this.response = data,
          error: err => console.error('Failed to search by country:', err)
        });
    
  }
}