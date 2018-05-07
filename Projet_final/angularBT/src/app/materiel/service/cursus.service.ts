import { Cursus } from '../../model/cursus';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class CursusService {
  
  private baseUrl:string='http://localhost:8080/projetfinal/cursus';

  constructor(private http: HttpClient) { }
  
  list() :Observable<Cursus[]>{
    return this.http.get<Cursus[]>(`${this.baseUrl}/infos`);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<Cursus>{
    return this.http.get<Cursus>(`${this.baseUrl}/${id}/infos`);
  }

  public update(cursus: Cursus):Observable<any>{
    return this.http.put(`${this.baseUrl}/infos`, cursus);
  }
  
  public create(cursus: Cursus): Observable<any>{
    const obj={
      nom:cursus.nom,
      date:cursus.date,
      gestionnaire: {
          id: cursus.gestionnaire.id,
          nom: cursus.gestionnaire.nom,
          prenom: cursus.gestionnaire.prenom,
          adresse: {
              numero: cursus.gestionnaire.adresse.numero,
              rue: cursus.gestionnaire.adresse.rue,
              codePostal: cursus.gestionnaire.adresse.codePostal,
              ville: cursus.gestionnaire.adresse.ville,
              pays: cursus.gestionnaire.adresse.pays
          },
          coordonnees:{
              telephone: cursus.gestionnaire.coordonnees.telephone,
              email: cursus.gestionnaire.coordonnees.email
          } 
      },
      referent: {
          id: cursus.referent.id,
          nom: cursus.referent.nom,
          prenom: cursus.referent.prenom,
          adresse: {
              numero: cursus.referent.adresse.numero,
              rue: cursus.referent.adresse.rue,
              codePostal: cursus.referent.adresse.codePostal,
              ville: cursus.referent.adresse.ville,
              pays: cursus.referent.adresse.pays
          },
          coordonnees: {
              telephone: cursus.referent.coordonnees.telephone,
              email: cursus.referent.coordonnees.email
          },
          disponibilites: cursus.referent.disponnibilite
      },
      modules: [
          {
              id: cursus.module.id,
              dates: cursus.module.dates
          }
      ],
      projecteur: {
          code: cursus.projecteur.code,
          cout: cursus.projecteur.cout,
          disponible: cursus.projecteur.disponible
      },
      stagiaires: [
          {
              id: cursus.stagiaire.id,
              nom: cursus.stagiaire.nom,
              prenom: cursus.stagiaire.prenom,
              adresse: {
                  numero: cursus.stagiaire.adresse.numero,
                  rue: cursus.stagiaire.adresse.rue,
                  codePostal: cursus.stagiaire.adresse.codePostal,
                  ville: cursus.stagiaire.adresse.ville,
                  pays: cursus.stagiaire.adresse.pays
              },
              coordonnees: {
                  telephone: cursus.stagiaire.coordonnees.telephone,
                  email: cursus.stagiaire.coordonnees.email
              }
          }
      ],
      salle: {
          id: cursus.salle.id,
          capacite: cursus.salle.capacite,
          numero: cursus.salle.numero,
          adresse: {
              numero: cursus.salle.adresse.numero,
              rue: cursus.salle.adresse.rue,
              codePostal: cursus.salle.adresse.codePostal,
              ville: cursus.salle.adresse.ville,
              pays: cursus.salle.adresse.pays
          }
      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}
