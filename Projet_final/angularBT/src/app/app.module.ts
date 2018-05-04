import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AuthComponent } from './auth/auth.component';
import { CursuseditComponent } from './cursusedit/cursusedit.component';
import { CursuslistComponent } from './cursuslist/cursuslist.component';
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
import { CursusService } from './service/cursus.service';
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
    path: 'cursuslist',
    component: CursuslistComponent
  },
  {
    path: 'cursusedit',
    component: CursuseditComponent
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
    CursuslistComponent,
    CursuseditComponent

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule
  ],
  providers: [UserService,AuthgardGuard, SalleService,MaterielService, CursusService],
  bootstrap: [AppComponent]
})
export class AppModule { }

