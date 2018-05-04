import { Module } from '../model/module';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ModuleService {
  
private baseUrl:string='http://localhost:8080/projetfinal/module';
  
  constructor(private http: HttpClient) { }

  list() :Observable<Module[]>{
    return this.http.get<Module[]>(this.baseUrl);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<Module>{
    return this.http.get<Module>(`${this.baseUrl}/${id}`);
  }

  public update(module: Module):Observable<any>{
    return this.http.put(this.baseUrl, module);
  }
  
  public create(module: Module): Observable<any>{
    const obj={
      id:module.id,
      dates:module.dates,
//      adresse:{
//        numero:salle.adresse.numero,
//        rue:salle.adresse.rue,
//        codePostal:salle.adresse.codePostal,
//        ville:salle.adresse.ville,
//        pays:salle.adresse.pays
//      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}


