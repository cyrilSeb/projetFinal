import { Cursus } from './cursus';
import { Materiel } from './materiel';
export class Projecteur extends Materiel{
  constructor(private _cursus: Cursus) {
    super();
  }
  public get cursus(): Cursus {
    return this._cursus;
  }
   public set cursus(_cursus: Cursus) {
    this._cursus = _cursus;
  }
}