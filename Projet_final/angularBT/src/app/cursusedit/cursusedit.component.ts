import { Cursus } from '../model/cursus';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cursusedit',
  templateUrl: './cursusedit.component.html',
  styleUrls: ['./cursusedit.component.css']
})
export class CursuseditComponent implements OnInit {

  private cursus: Cursus;
  constructor() { }

  ngOnInit() {
  }
  
  retour(){
    this.router.navigate(['/salle','list']);
  }
  
  submit() {
    if(!!this.salle.numero){
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
