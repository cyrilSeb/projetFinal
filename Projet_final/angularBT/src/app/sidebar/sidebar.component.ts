import { Component, OnInit } from '@angular/core';

declare var $:any;

export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}

export const ROUTES: RouteInfo[] = [
    { path: 'materiellist', title: 'Materiels',  icon: 'ti-settings', class: '' },
    { path: 'cursuslist', title: 'Cursus',  icon:'ti-view-list-alt', class: '' },
    { path: 'userlist', title: 'Utilisateurs',  icon:'ti-user', class: '' },
    { path: 'matierelist', title: 'matieres',  icon:'ti-text', class: '' },
    { path: 'sallelist', title: 'salles',  icon:'ti-pencil-alt2', class: '' },
    { path: 'modulelist', title: 'Modules',  icon:'ti-map', class: '' },
    { path: '', title: 'deconnecter',  icon:'ti-power-off', class: '' },
    
];

@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
  

}
