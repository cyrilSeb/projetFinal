import { Cursus } from '../model/cursus';
import { Formateur } from '../model/formateur';
import { Gestionnaire } from '../model/gestionnaire';
import { Salle } from '../model/salle';
import { CursusService } from '../service/cursus.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-cursusedit',
  templateUrl: './cursusedit.component.html',
  styleUrls: ['./cursusedit.component.css']
})
export class CursuseditComponent implements OnInit {

  private id;
  private cursus: Cursus;
  
  constructor(private route: ActivatedRoute, private router:Router, private cursusService:CursusService) {
  this.cursus=new Cursus();
    this.cursus.gestionnaire=new Gestionnaire();
    this.cursus.referent= new Formateur();
    this.cursus.salle= new Salle();
  
   }

   ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.cursusService.findById(this.id).subscribe(res=>{
      this.cursus=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/cursuslist']);
  }
  
  submit() {
    if(!!this.cursus.id){
      //maj
    this.cursusService.update(this.cursus).subscribe(res=>{
      this.retour();
        });
      
    }else{
      //creation
      this.cursusService.create(this.cursus).subscribe(res=>{
      this.retour();
        });
    }
  }


}
