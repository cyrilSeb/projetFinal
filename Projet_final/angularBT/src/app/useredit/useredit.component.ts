import { Adresse } from '../model/adresse';
import { Coordonnees } from '../model/coordonnees';
import { Formateur } from '../model/formateur';
import { Gestionnaire } from '../model/gestionnaire';
import { Stagiaire } from '../model/stagiaire';
import { Technicien } from '../model/technicien';
import { User } from '../model/user';
import { UserService } from '../materiel/service/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-useredit',
  templateUrl: './useredit.component.html',
  styleUrls: ['./useredit.component.css']
})
export class UsereditComponent implements OnInit {

  private id;
  private user: User;

  
  constructor(private route: ActivatedRoute, private router:Router, private userService:UserService) {
  this.user=new User();
    this.user.adresse=new Adresse();
    this.user.coordonnees=new Coordonnees();
  
   }

   ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.userService.findById(this.id).subscribe(res=>{
      this.user=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/userlist']);
  }
  
  submit() {
    if(!!this.user.id){
      //maj
        if(this.user instanceof Gestionnaire){
        this.userService.updateGestionnaire(this.user).subscribe(res=>{
       this.retour();
          });
      }else if(this.user instanceof Formateur){
        this.userService.updateFormateur(this.user).subscribe(res=>{
       this.retour();
          });
      }else if(this.user instanceof Technicien){
        this.userService.updateTechnicien(this.user).subscribe(res=>{
       this.retour();
          });
      }else if(this.user instanceof Stagiaire){
        this.userService.updateStagiaire(this.user).subscribe(res=>{
       this.retour();
          });
      }
        
    }
    else{
      //creation
      if(this.user instanceof Gestionnaire){
      this.userService.createGestionnaire(this.user).subscribe(res=>{
      this.retour();
        });
      }else if(this.user instanceof Formateur){
      this.userService.createFormateur(this.user).subscribe(res=>{
      this.retour();
        });
      }else if(this.user instanceof Technicien){
      this.userService.createTechnicien(this.user).subscribe(res=>{
      this.retour();
        });
      }else if(this.user instanceof Stagiaire){
      this.userService.createStagiaire(this.user).subscribe(res=>{
      this.retour();
        });
      }
    }
  }

}
