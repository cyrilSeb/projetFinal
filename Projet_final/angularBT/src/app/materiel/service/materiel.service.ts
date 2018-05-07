import { Materiel } from '../../model/materiel';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class MaterielService {
  private baseUrl: string = 'http://localhost:8080/projetfinal/materiel';
  constructor(private http: HttpClient) { }

  public list(): Observable<Materiel[]> {
    return this.http.get<Materiel[]>(`${this.baseUrl}/infos`);
  }
  public delete(code: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${code}`);
  }
  public findById(code):Observable<Materiel>{
    return this.http.get<Materiel>(`${this.baseUrl}/${code}/infos`);
  }

  public update(materiel: Materiel):Observable<any>{
    return this.http.put(this.baseUrl, materiel);
  }
  
  public create(materiel: Materiel): Observable<any>{
    const obj={
      code:materiel.code,
      cout:materiel.cout,
      disponible:materiel.disponible,
//      processeur: materiel.processeur,
//      ram: materiel.ram,
//      DD: materiel.DD,
//      anneeAchat: materiel.anneeAchat,
//      stagiaire: {
//            id: materiel.stagiaire.id,
//            nom: materiel.stagiaire.nom,
//            prenom: materiel.stagiaire.prenom,
//            adresse:{
//              numero:materiel.stagiaire.adresse.numero,
//              rue:materiel.stagiaire.adresse.rue,
//              codePostal:materiel.stagiaire.adresse.codePostal,
//              ville:materiel.stagiaire.adresse.ville,
//              pays:materiel.stagiaire.adresse.pays
//            },
//            coordonnees: {
//                telephone: materiel.stagiaire.coordonnees.telephone,
//                email: materiel.stagiaire.coordonnees.email,
//            }
//        },
//      cursus: {
//            id: materiel.cursus.id,
//            nom: materiel.cursus.nom,
//            dates: materiel.cursus.date
//        }
   
    };
    return this.http.post(this.baseUrl, obj);
  }
}
