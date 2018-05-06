import { Matiere } from '../model/matiere';
import { MatiereService } from '../materiel/service/matiere.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-matierelist',
  templateUrl: './matierelist.component.html',
  styleUrls: ['./matierelist.component.css']
})
export class MatierelistComponent implements OnInit {

  private matieres: Matiere[];
  constructor(private matiereService: MatiereService) { }

  ngOnInit() {
    
    this.list();
  }
  
  list(){
    this.matiereService.list().subscribe(result=>{
      this.matieres=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  delete(id:number){
    this.matiereService.delete(id).subscribe(result=>{
       this.list();
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }

}
