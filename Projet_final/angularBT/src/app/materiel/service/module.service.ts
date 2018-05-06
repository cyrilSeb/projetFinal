import { Module } from '../../model/module';
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
        id: module.matiere.id,
        titre: module.matiere.titre,
        nbHeure: module.matiere.nbHeure,
        objectifs: module.matiere.objectifs,
        contenu: module.matiere.contenu
      },
      formateur: {
          id: module.formateur.id,
          nom: module.formateur.nom,
          prenom: module.formateur.prenom,
          adresse: {
              numero: module.formateur.adresse.numero,
              rue: module.formateur.adresse.rue,
              codePostal: module.formateur.adresse.codePostal,
              ville: module.formateur.adresse.ville,
              pays: module.formateur.adresse.pays
          },
          coordonnees: {
              telephone: module.formateur.coordonnees.telephone,
              email: module.formateur.coordonnees.email
          },
          disponibilites: module.formateur.disponnibilite
      },
      cursus: {
          id: module.cursus.id,
          nom: module.cursus.nom,
          dates: module.cursus.date
      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}


