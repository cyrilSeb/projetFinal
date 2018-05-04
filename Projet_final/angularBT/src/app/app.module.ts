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
import { MaterielComponent } from './materiel/materiel.component';
import { MaterielService } from './service/materiel.service';


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
  },
  {
    path: 'editsalle',
    component: EditsalleComponent
  },
  {
    path: 'materiel',
    component: MaterielComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    HomeComponent,
    SalleComponent,
    EditsalleComponent,
    MaterielComponent,

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule
  ],
  providers: [UserService,AuthgardGuard, SalleService,MaterielService],
  bootstrap: [AppComponent]
})
export class AppModule { }

