import { Materiel } from '../model/materiel';
import { Ordinateur } from '../model/ordinateur';
import { Projecteur } from '../model/projecteur';
import { MaterielService } from '../service/materiel.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-materieledit',
  templateUrl: './materieledit.component.html',
  styleUrls: ['./materieledit.component.css']
})
export class MaterieleditComponent implements OnInit {

  private code;
  private materiel: Materiel;

  
  constructor(private route: ActivatedRoute, private router:Router, private materielService:MaterielService) {
  this.materiel=new Materiel();
  
   }

   ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.code =params.get('code');
      if(!!this.code){
    this.materielService.findById(this.code).subscribe(res=>{
      this.materiel=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/materiellist']);
  }
  
  submit() {
    if(!!this.materiel.code){
      //maj
    this.materielService.update(this.materiel).subscribe(res=>{
      this.retour();
        });
    }
    else{
      //creation
      this.materielService.create(this.materiel).subscribe(res=>{
      this.retour();
        });
    }
  }

}
