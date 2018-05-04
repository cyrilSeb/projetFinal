import { Module } from '../model/module';
import { ModuleService } from '../service/module.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

@Component({
  selector: 'app-moduleedit',
  templateUrl: './moduleedit.component.html',
  styleUrls: ['./moduleedit.component.css']
})
export class ModuleeditComponent implements OnInit {
   private id;
  
  private module: Module;
  constructor(private route: ActivatedRoute, private router:Router, private moduleService:ModuleService) {
     this.module= new Module();
      
      
   }

  ngOnInit() {
    this.route.paramMap.subscribe((params:ParamMap) =>{
    this.id =params.get('id');
      if(!!this.id){
    this.moduleService.findById(this.id).subscribe(res=>{
      this.module=res;
         });
       }
    });
  }
  
  retour(){
    this.router.navigate(['/modulelist']);
  }
  
  submit() {
    if(!!this.module.id){
      //maj
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


