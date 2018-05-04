import { Materiel } from '../model/materiel';
import { MaterielService } from '../service/materiel.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-materiel',
  templateUrl: './materiel.component.html',
  styleUrls: ['./materiel.component.css']
})
export class MaterielComponent implements OnInit {
  private materiels: Materiel[];
  constructor(private materielService: MaterielService) { }

  ngOnInit() {
     this.list();
  }
 list(){
    this.materielService.list().subscribe(result=>{
      this.materiels=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  delete(id:number){
    this.materielService.delete(id).subscribe(result=>{
       this.list();
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
}