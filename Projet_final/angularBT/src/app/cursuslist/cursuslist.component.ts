import { Cursus } from '../model/cursus';
import { CursusService } from '../materiel/service/cursus.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cursuslist',
  templateUrl: './cursuslist.component.html',
  styleUrls: ['./cursuslist.component.css']
})
export class CursuslistComponent implements OnInit {

  private cursuss: Cursus[];
  constructor(private cursusService: CursusService) { }

  ngOnInit() {
    
    this.list();
  }
  
  list(){
    this.cursusService.list().subscribe(result=>{
      this.cursuss=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  delete(id:number){
    this.cursusService.delete(id).subscribe(result=>{
       this.list();
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }

}
