import { Cursus } from '../model/cursus';
import { CursusService } from '../service/cursus.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cursusedit',
  templateUrl: './cursusedit.component.html',
  styleUrls: ['./cursusedit.component.css']
})
export class CursuseditComponent implements OnInit {

  private cursus: Cursus;
  constructor(private route: ActivatedRoute, private router:Router, private cursusService:CursusService) { }

  ngOnInit() {
  }
  
  retour(){
    this.router.navigate(['/cursus','list']);
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
