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
    component: SalleComponent
  },
  {
    path: 'salleedit',
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
    path: 'materiellist',
    component: MaterielComponent
  },
  {
    path: 'materieledit',
    component: MaterieleditComponent
  },
    {path: 'formateurlist',
    component: FormateurComponent
  },
  {
    path: 'formateuredit',
    component: FormateureditComponent
  },
  {
    path: 'matierelist',
    component: MatierelistComponent
  },
  {
    path: 'matiereedit',
    component: MatiereeditComponent
  },
  {
    path: 'modulelist',
    component: ModulelistComponent
  },
  {
    path: 'moduleedit',
    component: ModuleeditComponent
  },
  {path:'salleedit/:id', component:EditsalleComponent},
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

  ],
  imports: [
    BrowserModule, FormsModule, RouterModule.forRoot(appRoutes),HttpClientModule,SidebarModule,NavbarModule,
    FooterModule
  ],
  providers: [UserService,AuthgardGuard, SalleService,MaterielService, CursusService,FormateurService,ModuleService,MatiereService],
  bootstrap: [AppComponent]
})
export class AppModule { }

