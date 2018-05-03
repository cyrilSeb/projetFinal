import { Formateur } from './formateur';
import { Gestionnaire } from './gestionnaire';
import { Module } from './module';
import { Projecteur } from './projecteur';
import { Salle } from './salle';
import { Stagiaire } from './stagiaire';
export class Cursus {
  constructor (private _nom: string ='', private _date: Date=null, private _gestionnaire : Gestionnaire, private _referent : Formateur,
  private _module :Module, private _projecteur: Projecteur=null, private _stagiaire : Stagiaire='',private _salle : Salle){  
  }
  
  public get nom(): string {
    return this._nom;
  } 
  public set  nom(_nom: string) {
    this._nom = _nom;
  }
  public get date(): Date {
    return this._date;
  }
  public set date(_date: Date) {
    this._date = _date;
  }
public get gestionnaire(): Gestionnaire {
    return this._gestionnaire;
  }
  public set gestionnaire(_gestionnaire: Gestionnaire) {
    this._gestionnaire = _gestionnaire;
  }
  public get referent(): Formateur {
    return this._referent;
  }
  public set referent(_referent: Formateur) {
    this._referent = _referent;
}
   public get module (): Module {
    return this._module;
  }
   public set module(_module: Module) {
    this._module = _module;
}
  public get projecteur (): Projecteur {
    return this._projecteur;
  }
  
  public set projecteur(_projecteur: Projecteur) {
    this._projecteur = _projecteur;
}
   public get stagiaire(): Stagiaire {
    return this._stagiaire;
  }
    public set stagiaire(_stagiaire: Stagiaire) {
    this._stagiaire = _stagiaire;
}
  public get salle(): Salle {
    return this._salle;
  }
      public set salle(_salle: Salle) {
    this._salle = _salle;
}
  }