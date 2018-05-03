export class Coordonnees {
  
    constructor(private _telephone:string='',
  private _email:string=''){
    
  }
  
  public get telephone(): string {
    return this._telephone;
  }
  
  public set telephone(_telephone: string) {
    this._telephone =_telephone;
  }
  
  public get email(): string {
    return this._email;
  }
  
  public set email(_email: string) {
    this._email =_email;
  }

}