import { Adresse } from './adresse';
import { Cursus } from './cursus';
export class Salle {
  
  constructor(private _id:string='',
    private _numero:string='',
    private _adresse:Adresse=null,
    private _cursus:Cursus=null,
  private _capacite:string=''){
    
  }
  
  public get id(): string {
    return this._id;
  }
  
  public set id(_id: string) {
    this._id =_id;
  }
  
  public get numero(): string {
    return this._numero;
  }
  
  public set numero(_numero: string) {
    this._numero =_numero;
  }
  
  public get adresse(): Adresse {
    return this._adresse;
  }
  
  public set adresse(_adresse: Adresse) {
    this._adresse =_adresse;
  }
  
  public get capacite(): string {
    return this._capacite;
  }
  
  public set capacite(_capacite: string) {
    this._capacite =_capacite;
  }
  
  public get cursus(): Cursus {
    return this._cursus;
  }
  
  public set cursus(_cursus: Cursus) {
    this._cursus =_cursus;
  }

}