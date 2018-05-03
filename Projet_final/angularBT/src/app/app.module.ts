import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
<<<<<<< HEAD
import { LoginComponent } from './login/login.component';
import { FormateurComponent } from './formateur/formateur.component';
import { SidebarComponent } from './sidebar/sidebar.component';


=======
import { AuthComponent } from './auth/auth.component';
import { AuthgardGuard } from './authgard.guard';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';
>>>>>>> 972652ccded97ffe61f041b01c0c8bc7389c9d8f

const appRoutes: Routes=[
{
  path: '', 
  component: AuthComponent
},
  
  {
    path: 'home',
    canActivate: [AuthgardGuard],
    component: HomeComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    LoginComponent,
    FormateurComponent,
    SidebarComponent,
 
   
=======
    AuthComponent,
    HomeComponent,
>>>>>>> 972652ccded97ffe61f041b01c0c8bc7389c9d8f
  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule
  ],
  providers: [UserService,AuthgardGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
