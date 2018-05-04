

export class Materiel  {
   constructor (private _code:string='', private _cout:string ='', private _disponible :boolean =  null ){
     
   }


 public get code(): string {
    return this._code;
  }

public set code(_code: string) {
    this._code = _code;
  }

 public get cout(): string {
    return this._cout;
  }
public set cout(_cout: string) {
    this._cout = _cout;
  }

public get disponible(): boolean {
    return this._disponible;
  }
public set disponible(_disponible: boolean) {
    this._disponible = _disponible;
  }
  }