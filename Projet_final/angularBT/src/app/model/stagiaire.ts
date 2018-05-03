import { Cursus } from './cursus';
import { Ordinateur } from './ordinateur';
import { User } from './user';
export class Stagiaire extends User{
  
   constructor(private _ordinateur:Ordinateur=null,
  private _cursus:Cursus=null) {
     super();

  }
  
  public get ordinateur(): Ordinateur {
    return this._ordinateur;
  }
  
  public set ordinateur(_ordinateur: Ordinateur) {
    this._ordinateur =_ordinateur;
  }
  
  public get cursus(): Cursus {
    return this._cursus;
  }
  
  public set cursus(_cursus: Cursus) {
    this._cursus =_cursus;
  }
  
}