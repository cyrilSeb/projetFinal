import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { AuthgardGuard } from './guard/authgard.guard';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserService } from './service/user.service';
import { HttpClientModule } from '@angular/common/http';
import { SalleComponent } from './salle/salle.component';
import { EditsalleComponent } from './editsalle/editsalle.component';
import { SalleService } from './service/salle.service';


const appRoutes: Routes=[
{
  path: '', 
  component: AuthComponent
},
  
  {
    path: 'home',
    canActivate: [AuthgardGuard],
    component: HomeComponent
  },
  
  {
    path: 'salle',
    component: SalleComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    HomeComponent,
    SalleComponent,
    EditsalleComponent,

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule
  ],
  providers: [UserService,AuthgardGuard, SalleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
