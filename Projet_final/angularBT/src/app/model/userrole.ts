import { Role } from './role';
import { User } from './user';
export class UserRole {
  
   constructor(private _id:string='',
     private _role:Role=null,
  private _user:User=null){
    
  }
  
  public get id(): string {
    return this._id;
  }
  
  public set id(_id: string) {
    this._id =_id;
  }
  
  public get role(): Role {
    return this._role;
  }
  
  public set role(_role: Role) {
    this._role =_role;
  }
  
  public get user(): User {
    return this._user;
  }
  
  public set user(_user: User) {
    this._user =_user;
  }
}