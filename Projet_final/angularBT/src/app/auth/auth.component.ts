import { UserService } from '../user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  constructor(private router: Router, private user: UserService) { }

  ngOnInit() {
  }

  submit(e){
    var username= e.elements[0].value;
    var password= e.elements[1].value;
    if (username== 'admin' && password =='admin'){
      this.user.setUserLoggedIn();
      this.router.navigate(['home']);
    }
    
   if (username== 'gestion' && password =='gestion'){
      this.user.setUserLoggedIn();
      this.router.navigate(['home']);
    }
  }
}
