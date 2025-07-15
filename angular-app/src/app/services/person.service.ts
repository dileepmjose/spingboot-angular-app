import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class PersonService {
    
    private baseUrl = 'http://localhost:8080/api/persons';

     constructor(private http: HttpClient) {}

    loadAllPersons(): Observable<string[]> {
       return this.http.get<any[]>(`${this.baseUrl}/get-all`);
    }



   loadEyeColors(): Observable<string[]>  {
   return this.http.get<any[]>(`${this.baseUrl}/eyecolors`);
   }

  loadCountry(): Observable<string[]> {
    return this.http.get<any[]>(`${this.baseUrl}/countries`);

  }
  
   onChangeSearch(eyeColor: string, country: string, name: string): Observable<string[]> {
    return this.http.get<any[]>(`${this.baseUrl}/search?eyeColor=${eyeColor}&country=${country}&name=${name}`);
    }

}