import { Cursus } from './cursus';
import { Formateur } from './formateur';
import { Matiere } from './matiere';
export class Module {
  
   constructor(private _id:string='',
  private _dates: Date=null,
  private _matiere:Matiere=null,
  private _formateur:Formateur=null,
  private _cursus:Cursus=null){
    
  }
  
  public get id(): string {
    return this._id;
  }
  
  public set id(_id: string) {
    this._id =_id;
  }
  
  public get dates(): Date {
    return this._dates;
  }
  
  public set dates(_dates: Date) {
    this._dates =_dates;
  }
  
  public get matiere(): Matiere {
    return this._matiere;
  }
  
  public set matiere(_matiere: Matiere) {
    this._matiere =_matiere;
  }
  
  public get formateur(): Formateur {
    return this._formateur;
  }
  
  public set formateur(_formateur: Formateur) {
    this._formateur =_formateur;
  }
  
  public get cursus(): Cursus {
    return this._cursus;
  }
  
  public set cursus(_cursus: Cursus) {
    this._cursus =_cursus;
  }
}