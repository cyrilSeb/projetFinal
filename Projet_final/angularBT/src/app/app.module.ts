import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { AuthgardGuard } from './authgard.guard';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';


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
    AuthComponent,
    HomeComponent,

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule
  ],
  providers: [UserService,AuthgardGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
