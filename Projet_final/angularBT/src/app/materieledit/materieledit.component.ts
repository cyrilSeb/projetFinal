import { Cursus } from '../model/cursus';
import { Materiel } from '../model/materiel';
import { Ordinateur } from '../model/ordinateur';
import { Projecteur } from '../model/projecteur';
import { User } from '../model/user';
import { CursusService } from '../service/cursus.service';
import { MaterielService } from '../service/materiel.service';
import { UserService } from '../service/user.service';
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
  private cursuss: Cursus[];
  private stagiaires: User[];

  
  constructor(private route: ActivatedRoute, private router:Router, private materielService:MaterielService, private userService:UserService, private cursusService:CursusService) {
  this.materiel=new Materiel();
  
   }

   ngOnInit() {
    this.list();
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.code =params.get('code');
      if(!!this.code){
    this.materielService.findById(this.code).subscribe(res=>{
      this.materiel=res;
         });
       }
    });
  }
  
    list(){
    this.cursusService.list().subscribe(result=>{
      this.cursuss=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
    this.userService.list().subscribe(result=>{
      this.stagiaires=result;
    }, error=>{
      console.log(`erreur:${error}`);
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
