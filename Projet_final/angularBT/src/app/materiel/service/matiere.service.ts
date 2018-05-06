import { Matiere } from '../../model/matiere';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class MatiereService {

 private baseUrl:string='http://localhost:8080/projetfinal/matiere';

  constructor(private http: HttpClient) { }
  
  list() :Observable<Matiere[]>{
    return this.http.get<Matiere[]>(this.baseUrl);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<Matiere>{
    return this.http.get<Matiere>(`${this.baseUrl}/${id}`);
  }

  public update(matiere: Matiere):Observable<any>{
    return this.http.put(this.baseUrl, matiere);
  }
  
  public create(matiere: Matiere): Observable<any>{
    const obj={
      id:matiere.id,
      titre:matiere.titre,
      nbHeure:matiere.nbHeure,
      objectifs: matiere.objectifs,
      contenu: matiere.contenu,
    };
    return this.http.post(this.baseUrl, obj);
  }

}
