import {Materiel} from '../model/materiel';
import {Ordinateur} from '../model/ordinateur';
import {Projecteur} from '../model/projecteur';
import {MaterielService} from './service/materiel.service';
import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-materiel',
  templateUrl: './materiel.component.html',
  styleUrls: ['./materiel.component.css']
})
export class MaterielComponent implements OnInit {
  private materiels: Materiel[];
  constructor(private materielService: MaterielService) {}

  ngOnInit() {
    this.list();
  }
  list() {
    this.materielService.list().subscribe(result => {
      this.materiels = result;
      console.log(this.materiels);
    }, error => {
      console.log(`erreur:${error}`);
    });
  }

  delete(id: number) {
    this.materielService.delete(id).subscribe(result => {
      this.list();
    }, error => {
      console.log(`erreur:${error}`);
    });
  }
}