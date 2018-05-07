import { Cursus } from '../model/cursus';
import { Formateur } from '../model/formateur';
import { Gestionnaire } from '../model/gestionnaire';
import { Salle } from '../model/salle';
import { User } from '../model/user';
import { CursusService } from '../materiel/service/cursus.service';
import { SalleService } from '../materiel/service/salle.service';
import { UserService } from '../materiel/service/user.service';
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
  private salles: Salle[];
  private gestionnaires: User[];
  private referents: User[];
  
  constructor(private route: ActivatedRoute, private router:Router, private cursusService:CursusService,private salleService: SalleService, private userService:UserService) {
  this.cursus=new Cursus();
    this.cursus.gestionnaire=new Gestionnaire();
    this.cursus.referent= new Formateur();
    this.cursus.salle= new Salle();
  
   }

   ngOnInit() {
    this.list();
     console.log(this.salles);
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.cursusService.findById(this.id).subscribe(res=>{
      this.cursus=res;
         });
       }
    });
  }
  
  list(){
    this.salleService.list().subscribe(result=>{
      this.salles=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
    this.userService.list().subscribe(result=>{
      this.gestionnaires=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
    this.userService.list().subscribe(result=>{
      this.referents=result;
    }, error=>{
      console.log(`erreur:${error}`);
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
