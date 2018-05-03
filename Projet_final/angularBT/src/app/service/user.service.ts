import { User } from '../model/user';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  private baseUrl:string='http://localhost:8080/projetfinal';
  
  constructor(private http: HttpClient) { 
    
  }
  
  login(username: string, password: string) {
      if (this.findByUsername(username, password)==null){
        return this.http.get<User>(this.baseUrl);
      }else{
        return this.http.get<User>(`${this.baseUrl}/${'home'}`);
      }
        
  }


   public list() :Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl);
  }
  
  public delete(id:number):Observable<any>{
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
  
  public findById(id):Observable<User>{
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }
  
  public findByUsername(username, password):Observable<User>{
    return this.http.get<User>(`${this.baseUrl}/${username}`);
  }

  public update(user: User):Observable<any>{
    return this.http.put(this.baseUrl, user);
  }
  
  public create(user: User): Observable<any>{
    const obj={
      prenom:user.prenom,
      nom:user.nom,
      
    };
    return this.http.post(this.baseUrl, obj);
  }

}

  