import { Adresse } from '../model/adresse';
import { Salle } from '../model/salle';
import { SalleService } from '../service/salle.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-editsalle',
  templateUrl: './editsalle.component.html',
  styleUrls: ['./editsalle.component.css']
})
export class EditsalleComponent implements OnInit {

   private id;
  
  private salle: Salle;
  
  constructor(private route: ActivatedRoute, private router:Router, private salleService:SalleService) { 
      this.salle= new Salle();
      this.salle.adresse=new Adresse();
      
  } 
  
  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.salleService.findById(this.id).subscribe(res=>{
      this.salle=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/sallelist']);
  }
  
  submit() {
    if(!!this.salle.id){
      //maj
    this.salleService.update(this.salle).subscribe(res=>{
      this.retour();
        });
      
    }else{
      //creation
      this.salleService.create(this.salle).subscribe(res=>{
      this.retour();
        });
    }
  }

}
