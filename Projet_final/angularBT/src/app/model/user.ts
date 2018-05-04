import {Adresse} from './adresse';
import {Coordonnees} from './coordonnees';
import {UserRole} from './userrole';
export class User {

  constructor(
    private _id: string = '',
    private _username: string = '',
    private _password: string = '',
    private _prenom: string = '',
    private _nom: string = '',
    private _adresse: Adresse = null,
    private _coordonnees: Coordonnees = null,
    private _roles: UserRole = null,
    private _enable: boolean = true) {}

  public get password(): string {
    return this._password;
  }

  public set password(_password: string) {
    this._password = _password;
  }

  public get username(): string {
    return this._username;
  }

  public set username(_username: string) {
    this._username = _username;
  }

  public get prenom(): string {
    return this._prenom;
  }

  public set prenom(_prenom: string) {
    this._prenom = _prenom;
  }

  public get nom(): string {
    return this._nom;
  }

  public set nom(_nom: string) {
    this._nom = _nom;
  }

  public get id(): string {
    return this._id;
  }

  public set id(_id: string) {
    this._id = _id;
  }
  public get adresse(): Adresse {
    return this._adresse;
  }

  public set adresse(_adresse: Adresse) {
    this._adresse = _adresse;
  }

  public get enable(): boolean {
    return this._enable;
  }

  public set enable(_enable: boolean) {
    this._enable = _enable;
  }

  public get coordonnees(): Coordonnees {
    return this._coordonnees;
  }

  public set coordonnees(_coordonnees: Coordonnees) {
    this._coordonnees = _coordonnees;
  }

  public get roles(): UserRole {
    return this._roles;
  }

  public set roles(_roles: UserRole) {
    this._roles = _roles;
  }

}