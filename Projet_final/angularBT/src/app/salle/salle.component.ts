import { SalleService } from '../service/salle.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-salle',
  templateUrl: './salle.component.html',
  styleUrls: ['./salle.component.css']
})
export class SalleComponent implements OnInit {

  private salles: Salle[];
  constructor(private salleService: SalleService) { }

  ngOnInit() {
    
    this.list();
  }
  
  list(){
    this.salleService.list().subscribe(result=>{
      this.salles=result;
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }
  
  delete(id:number){
    this.salleService.delete(id).subscribe(result=>{
       this.list();
    }, error=>{
      console.log(`erreur:${error}`);
    });
  }

}
