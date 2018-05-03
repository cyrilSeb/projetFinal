import { Salle } from '../model/salle';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class SalleService {

  private baseUrl:string='http://localhost:8080/projetfinal/salle';

  constructor(private http: HttpClient) { }
  
  list() :Observable<Salle[]>{
    return this.http.get<Salle[]>(this.baseUrl);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<Salle>{
    return this.http.get<Salle>(`${this.baseUrl}/${id}`);
  }

  public update(salle: Salle):Observable<any>{
    return this.http.put(this.baseUrl, salle);
  }
  
  public create(salle: Salle): Observable<any>{
    const obj={
      prenom:salle.numero,
      nom:salle.capacite,
      adresse:{
        numero:salle.adresse.numero,
        rue:salle.adresse.rue,
        codePostal:salle.adresse.codePostal,
        ville:salle.adresse.ville,
        pays:salle.adresse.pays
      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}
