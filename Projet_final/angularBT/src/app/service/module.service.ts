import { Module } from '../model/module';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ModuleService {
  
private baseUrl:string='http://localhost:8080/projetfinal/module';
  
  constructor(private http: HttpClient) { }

  list() :Observable<Module[]>{
    return this.http.get<Module[]>(`${this.baseUrl}/infos`);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<Module>{
    return this.http.get<Module>(`${this.baseUrl}/${id}/infos`);
  }

  public update(module: Module):Observable<any>{
    return this.http.put(this.baseUrl, module);
  }
  
  public create(module: Module): Observable<any>{
    const obj={
      id:module.id,
      dates:module.dates,
      matiere: {
        id: 104,
        titre: "JAVA/JEE",
        nbHeure: 16,
        objectifs: "Devenir Dieu ",
        contenu: "JAVA/JEE"
      },
      formateur: {
          id: 101,
          nom: "MELLOUL",
          prenom: "Jacky",
          adresse: {
              numero: 6,
              rue: "Rue Rougemont",
              codePostal: "75009",
              ville: "Paris",
              pays: "FRANCE"
          },
          coordonnees: {
              telephone: "+33192638574",
              email: "john.doe@gmail.com"
          },
          disponibilites: null
      },
      cursus: {
          id: 101,
          nom: "Mathematiques, Physique et Informatiques",
          dates: null
      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}


