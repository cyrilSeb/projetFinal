export class Adresse {
  
    constructor(private _numero:string='',
  private _rue:string='',
  private _codePostal:string='',
  private _ville:string='',
  private _pays:string=''){
    
  }
  
  public get rue(): string {
    return this._rue;
  }
  
  public set rue(_rue: string) {
    this._rue =_rue;
  }
  
  public get numero(): string {
    return this._numero;
  }
  
  public set numero(_numero: string) {
    this._numero =_numero;
  }
  
  public get codePostal(): string {
    return this._codePostal;
  }
  
  public set codePostal(_codePostal: string) {
    this._codePostal =_codePostal;
  }
  
  public get ville(): string {
    return this._ville;
  }
  
  public set pays(_pays: string) {
    this._pays =_pays;
  }
  
  public get pays(): string {
    return this._pays;
  }
  
  public set ville(_ville: string) {
    this._ville =_ville;
  }

}