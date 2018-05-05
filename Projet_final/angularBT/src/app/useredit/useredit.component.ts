import { User } from '../model/user';
import { UserService } from '../service/user.service';
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
    this.userService.update(this.user).subscribe(res=>{
      this.retour();
        });
    }
    else{
      //creation
      this.userService.create(this.user).subscribe(res=>{
      this.retour();
        });
    }
  }

}
