import { Cursus } from '../model/cursus';
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
    return this.http.get<Cursus>(`${this.baseUrl}/${id}`);
  }

  public update(cursus: Cursus):Observable<any>{
    return this.http.put(this.baseUrl, cursus);
  }
  
  public create(cursus: Cursus): Observable<any>{
    const obj={
      nom:cursus.nom,
      date:cursus.date,
      gestionnaire: {
          id: 102,
          nom: "ZANARELLI",
          prenom: "Johanna",
          adresse: {
              numero: 6,
              rue: "Rue Rougemont",
              codePostal: "75009",
              ville: "Paris",
              pays: "FRANCE"
          },
          coordonnees: null
      },
      referent: {
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
      modules: [
          {
              id: 105,
              dates: null
          }
      ],
      projecteur: {
          code: 101,
          cout: 10,
          disponible: null
      },
      stagiaires: [
          {
              id: 103,
              nom: "RAZAFINDRAKOTO",
              prenom: "Mirijason",
              adresse: null,
              coordonnees: {
                  telephone: "+33192638574",
                  email: "john.doe@gmail.com"
              }
          }
      ],
      salle: {
          id: 107,
          capacite: 60,
          numero: "F345",
          adresse: {
              numero: 6,
              rue: "rue rougemont",
              codePostal: "75009",
              ville: "Paris",
              pays: "France"
          }
      }
    };
    return this.http.post(this.baseUrl, obj);
  }


}
