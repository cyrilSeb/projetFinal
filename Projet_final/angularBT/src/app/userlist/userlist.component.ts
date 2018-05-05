import { User } from '../model/user';
import { UserService } from '../service/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  private users: User[];
  constructor(private userService: UserService) {}

  ngOnInit() {
    this.list();
  }
  list() {
    this.userService.list().subscribe(result => {
      this.users = result;
      console.log(this.users);
    }, error => {
      console.log(`erreur:${error}`);
    });
  }

  delete(id: number) {
    this.userService.delete(id).subscribe(result => {
      this.list();
    }, error => {
      console.log(`erreur:${error}`);
    });
  }

}
