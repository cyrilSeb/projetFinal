import { Competences } from './competences';
import { Cursus } from './cursus';
import { Module } from './module';
export class Formateur {
  constructor (private _disponnibilite: Date[]=null, private _competences : Competences, private _module : Module, private _cursus: Cursus ){  
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