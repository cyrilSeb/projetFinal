

export abstract class Materiel  {
   constructor (private _code:string='', private _cout:string ='', private _disponnible :boolean =  null ){
     
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

public get disponnible(): boolean {
    return this._disponnible;
  }
public set disponnible(_disponnible: boolean) {
    this._disponnible = _disponnible;
  }
  }