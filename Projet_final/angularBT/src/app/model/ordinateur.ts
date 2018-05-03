import { Materiel } from './materiel';
import { Stagiaire } from './stagiaire';
export class Ordinateur extends Materiel {
  constructor(private _processeur: string = '', private _ram: number = null, private _DD: number = null, private _anneeAchat: Date = null, private _stagiaire: Stagiaire = '') {
    super();
  }
  public get processeur(): string {
    return this._processeur;
  }
  
  public set processeur(_processeur: string) {
    this._processeur = _processeur;
  }
  
   public get ram(): number {
    return this._ram;
  }
  public set ram(_ram: number) {
    this._ram = _ram;
  }
   public get DD(): number {
    return this._DD;
  }
   public set DD(_DD: number) {
    this._DD = _DD;
  }
   public get anneeAchat(): Date {
    return this._anneeAchat;
  }
    public set anneeAchat(_anneeAchat: Date) {
    this._anneeAchat = _anneeAchat;
  }
   public get stagiaire(): Stagiaire {
    return this._stagiaire;
  }
   public set stagiaire(_stagiaire: Stagiaire) {
    this._stagiaire = _stagiaire;
  }
}