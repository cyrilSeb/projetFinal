import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  model: User = new User();

  constructor(private router: Router, private userService: UserService) {}

  ngOnInit() {
  }

  login() {
    this.userService.login(this.model.username, this.model.password)
      .subscribe(
      data => {
        this.router.navigate(['home']);

      }, error => {
        console.log(`erreur:${error}`);
      });

  }

  //  submit(e){
  //    var username= e.elements[0].value;
  //    var password= e.elements[1].value;
  //    if (username== 'admin' && password =='admin'){
  //      this.userService.setUserLoggedIn();
  //      this.router.navigate(['home']);
  //    }
  //    
  //   if (username== 'gestion' && password =='gestion'){
  //      this.userService.setUserLoggedIn();
  //      this.router.navigate(['home']);
  //    }
  //  }
}
