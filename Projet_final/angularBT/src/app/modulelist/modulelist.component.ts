import { Module } from '../model/module';
import { ModuleService } from '../service/module.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modulelist',
  templateUrl: './modulelist.component.html',
  styleUrls: ['./modulelist.component.css']
})
export class ModulelistComponent implements OnInit {
   private modules: Module[];
  constructor(private moduleService: ModuleService) { }

  ngOnInit() {
     this.list();
  }
list(){
    this.moduleService.list().subscribe(result=>{
      this.modules=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  delete(id:number){
    this.moduleService.delete(id).subscribe(result=>{
       this.list();
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }

}
