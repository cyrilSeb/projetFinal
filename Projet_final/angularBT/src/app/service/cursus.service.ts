import { Cursus } from '../model/cursus';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class CursusService {
  
  private baseUrl:string='http://localhost:8080/projetfinal/cursus';

  constructor(private http: HttpClient) { }
  
  list() :Observable<Cursus[]>{
    return this.http.get<Cursus[]>(this.baseUrl);
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
    };
    return this.http.post(this.baseUrl, obj);
  }


}
