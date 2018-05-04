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
    this.userService.logout();
  }

  login() {
    this.userService.login(this.model.username, this.model.password)
      .subscribe(
      data => {
        if(data!=null){
        this.router.navigate(['materiellist']);}

      }, error => {
        console.log(`erreur:${error}`);
      });

  }
}
