import { Formateur } from '../model/formateur';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class FormateurService {

  private baseUrl:string='http://localhost:8080/projetfinal/formateur';

  constructor(private http: HttpClient) { }
  
  list() :Observable<Formateur[]>{
    return this.http.get<Formateur[]>(this.baseUrl);
  }
}
