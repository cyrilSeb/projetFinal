import { Competences } from './competences';
import { Cursus } from './cursus';
import { Module } from './module';
import { User } from './user';
export class Formateur extends User{
  constructor(private _disponnibilite: Date[] = null, private _competences: Competences=null, private _module: Module=null, private _cursus: Cursus=null) {
    super();
  }
  
   public get disponnibilite(): Date[] {
    return this._disponnibilite;
  }
  public set disponnibilite(_disponnibilite: Date[]) {
    this._disponnibilite = _disponnibilite;
  }
 public get competences(): Competences {
    return this._competences;
  }
  public set competences(_competences: Competences) {
    this._competences = _competences;
  }
   public get module():  Module {
    return this._module;
  }
   public set module(_module: Module) {
    this._module = _module;
  } 
   public get cursus():  Cursus {
    return this._cursus;
  }
}