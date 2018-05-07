import {User} from '../model/user';
import { AlertService } from '../materiel/service/alert.service';
import {UserService} from '../materiel/service/user.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  
  message:String='';

  model: User = new User();

  constructor(private router: Router, private userService: UserService, private alertService: AlertService) {}

  ngOnInit() {
    this.userService.logout();
  }

  login() {
    this.userService.login(this.model.username, this.model.password)
      .subscribe(
      data => {
        if(data!=null){
        localStorage.setItem('currentUser',JSON.stringify(data));
        this.router.navigate(['materiellist']);}
        else{
          this.message='Erreur de login ou mot de passe';
        }

      }, error => {
        this.alertService.error('Erreur de login ou mot de passe');
        console.log(`erreur:${error}`);
      });

  }
}
