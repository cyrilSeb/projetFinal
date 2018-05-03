import { Cursus } from './cursus';
import { User } from './user';
export class Gestionnaire extends User{
  constructor(private _cursus: Cursus = null) {
    super();

  }
  
  public get cursus(): Cursus {
    return this._cursus;
  }
  
  public set cursus(_cursus: Cursus) {
    this._cursus =_cursus;
  }

}