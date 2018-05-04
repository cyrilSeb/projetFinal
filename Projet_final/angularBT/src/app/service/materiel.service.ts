import { Materiel } from '../model/materiel';
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
    return this.http.get<Materiel>(`${this.baseUrl}/${code}`);
  }

  public update(materiel: Materiel):Observable<any>{
    return this.http.put(this.baseUrl, materiel);
  }
  
  public create(materiel: Materiel): Observable<any>{
    const obj={
      code:materiel.code,
      cout:materiel.cout,
      disponible:materiel.disponible,
    };
    return this.http.post(this.baseUrl, obj);
  }
}
