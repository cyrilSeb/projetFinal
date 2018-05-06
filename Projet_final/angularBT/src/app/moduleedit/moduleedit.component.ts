import { Cursus } from '../model/cursus';
import { Formateur } from '../model/formateur';
import { Matiere } from '../model/matiere';
import { Module } from '../model/module';
import { User } from '../model/user';
import { CursusService } from '../materiel/service/cursus.service';
import { MatiereService } from '../materiel/service/matiere.service';
import { ModuleService } from '../materiel/service/module.service';
import { UserService } from '../materiel/service/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-moduleedit',
  templateUrl: './moduleedit.component.html',
  styleUrls: ['./moduleedit.component.css']
})
export class ModuleeditComponent implements OnInit {
   private id;
  private matieres: Matiere[];
  private formateurs: User[];
  private cursuss: Cursus[];
  
  private module: Module;
  constructor(private route: ActivatedRoute, private router:Router, private matiereService:MatiereService, private userService:UserService, private cursusService:CursusService, private moduleService:ModuleService) {
     this.module= new Module();
      this.module.cursus=new Cursus();
    this.module.formateur= new Formateur();
    this.module.matiere= new Matiere();
      
   }

  ngOnInit() {
    this.list();
    console.log(this.matieres);
    console.log(this.formateurs);
    console.log(this.cursuss);
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.moduleService.findById(this.id).subscribe(res=>{
      this.module=res;
         });
       }
    });
  }
  
  list(){
    this.matiereService.list().subscribe(result=>{
      this.matieres=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
    this.userService.list().subscribe(result=>{
      this.formateurs=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
    this.cursusService.list().subscribe(result=>{
      this.cursuss=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  retour(){
    this.router.navigate(['/modulelist']);
  }
  
  
  submit(form: NgForm) {
    if(!!this.module.id){
      //maj
      console.log(this.module);
    this.moduleService.update(this.module).subscribe(res=>{
      this.retour();
        });
      
    }else{
      //creation
      this.moduleService.create(this.module).subscribe(res=>{
      this.retour();
        });
    }
  }
  }


