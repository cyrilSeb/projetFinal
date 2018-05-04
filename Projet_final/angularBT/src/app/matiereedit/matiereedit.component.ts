import { Matiere } from '../model/matiere';
import { MatiereService } from '../service/matiere.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-matiereedit',
  templateUrl: './matiereedit.component.html',
  styleUrls: ['./matiereedit.component.css']
})
export class MatiereeditComponent implements OnInit {

  private id;
  private matiere: Matiere;
  
  constructor(private route: ActivatedRoute, private router:Router, private matiereService:MatiereService) {
  this.matiere=new Matiere();
  
   }

   ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.matiereService.findById(this.id).subscribe(res=>{
      this.matiere=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/matierelist']);
  }
  
  submit() {
    if(!!this.matiere.id){
      //maj
    this.matiereService.update(this.matiere).subscribe(res=>{
      this.retour();
        });
      
    }else{
      //creation
      this.matiereService.create(this.matiere).subscribe(res=>{
      this.retour();
        });
    }
  }

}
