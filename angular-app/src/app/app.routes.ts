import { Routes } from '@angular/router';
import { CreatePersonComponent } from './person/create/create-person.component';
import { DisplayPersonComponent } from './person/search/display-person.component';
import { CompanyComponent } from './company/company.component';
import { TagComponent } from './tag/tag.component';

export const routes: Routes = [
  { path: '', redirectTo: 'create-person', pathMatch: 'full' },
  { path: 'create-person', component: CreatePersonComponent },
  { path: 'display-person', component: DisplayPersonComponent },
  { path: 'company', component: CompanyComponent },
  { path: 'tags', component: TagComponent }
];