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
import { FormateurComponent } from './formateur/formateur.component';
import { SalleService } from './service/salle.service';
import { MaterielComponent } from './materiel/materiel.component';
import { CursusService } from './service/cursus.service';
import { FormateurService } from './service/formateur.service';
import { MaterielService } from './service/materiel.service';
import { FooterModule } from './shared/footer/footer.module';
import { NavbarModule } from './shared/navbar/navbar.module';
import { SidebarModule } from './sidebar/sidebar.module';
import { MatierelistComponent } from './matierelist/matierelist.component';
import { MatiereeditComponent } from './matiereedit/matiereedit.component';
import { ModulelistComponent } from './modulelist/modulelist.component';
import { ModuleeditComponent } from './moduleedit/moduleedit.component';
import { MaterieleditComponent } from './materieledit/materieledit.component';
import { FormateureditComponent } from './formateuredit/formateuredit.component';
import { MatiereService } from './service/matiere.service';
import { ModuleService } from './service/module.service';
import { AlertComponent } from './alert/alert.component';
import { AlertService } from './service/alert.service';
import { UserlistComponent } from './userlist/userlist.component';
import { UsereditComponent } from './useredit/useredit.component';


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
    path: 'sallelist',
    canActivate: [AuthgardGuard],
    component: SalleComponent
  },
  {
    path: 'salleedit',
    canActivate: [AuthgardGuard],
    component: EditsalleComponent
  },
  {
    path: 'cursuslist',
    canActivate: [AuthgardGuard],
    component: CursuslistComponent
  },
  {
    path: 'cursusedit',
    canActivate: [AuthgardGuard],
    component: CursuseditComponent
  },
  {
    path: 'materiellist',
    canActivate: [AuthgardGuard],
    component: MaterielComponent
  },
  {
    path: 'materieledit',
    canActivate: [AuthgardGuard],
    component: MaterieleditComponent
  },
    {path: 'formateurlist',
      canActivate: [AuthgardGuard],
    component: FormateurComponent
  },
  {
    path: 'formateuredit',
    canActivate: [AuthgardGuard],
    component: FormateureditComponent
  },
  {
    path: 'matierelist',
    canActivate: [AuthgardGuard],
    component: MatierelistComponent
  },
  {
    path: 'matiereedit',
    canActivate: [AuthgardGuard],
    component: MatiereeditComponent
  },
  {
    path: 'modulelist',
    canActivate: [AuthgardGuard],
    component: ModulelistComponent
  },
  {
    path: 'moduleedit',
    canActivate: [AuthgardGuard],
    component: ModuleeditComponent
  },
  {
    path: 'userlist',
    canActivate: [AuthgardGuard],
    component: UserlistComponent
  },
  {
    path: 'useredit',
    canActivate: [AuthgardGuard],
    component: UsereditComponent
  },
  {path:'salleedit/:id',canActivate: [AuthgardGuard], component:EditsalleComponent},{path:'moduleedit/:id', canActivate: [AuthgardGuard],component:ModuleeditComponent},{path:'matiereedit/:id', canActivate: [AuthgardGuard],component:MatiereeditComponent},
  {path:'cursusedit/:id', canActivate: [AuthgardGuard],component:CursuseditComponent},{path:'materieledit/:id', canActivate: [AuthgardGuard],component:MaterieleditComponent},{path:'formateuredit/:id', canActivate: [AuthgardGuard],component:FormateureditComponent},
  {path:'useredit/:id', canActivate: [AuthgardGuard],component:UsereditComponent}
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
    CursuseditComponent,
     FormateurComponent,
     MatierelistComponent,
     MatiereeditComponent,
     ModulelistComponent,
     ModuleeditComponent,
     MaterieleditComponent,
     FormateureditComponent,
     AlertComponent,
     UserlistComponent,
     UsereditComponent,

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule,SidebarModule,NavbarModule,
    FooterModule
  ],
  providers: [UserService,AuthgardGuard, SalleService,MaterielService, CursusService,FormateurService,ModuleService,MatiereService, AlertService],
  bootstrap: [AppComponent]
})
export class AppModule { }

