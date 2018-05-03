export class Matiere {
   constructor(private _id:string='',
  private _titre:string='',
  private _nbHeure:string='',
  private _objectifs:string='',
  private _contenu:string='',
  private _prerequis:Matiere=null){
    
  }
  
  public get id(): string {
    return this._id;
  }
  
  public set id(_id: string) {
    this._id =_id;
  }
  
  public get titre(): string {
    return this._titre;
  }
  
  public set titre(_titre: string) {
    this._titre =_titre;
  }
  
  public get nbHeure(): string {
    return this._nbHeure;
  }
  
  public set nbHeure(_nbHeure: string) {
    this._nbHeure =_nbHeure;
  }
  
  public get objectifs(): string {
    return this._objectifs;
  }
  
  public set objectifs(_objectifs: string) {
    this._objectifs =_objectifs;
  }
  
  public get contenu(): string {
    return this._contenu;
  }
  
  public set contenu(_contenu: string) {
    this._contenu =_contenu;
  }
  
  public get prerequis(): Matiere {
    return this._prerequis;
  }
  
  public set prerequis(_prerequis: Matiere) {
    this._prerequis =_prerequis;
  }
}