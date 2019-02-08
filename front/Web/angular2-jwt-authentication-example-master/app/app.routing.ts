import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register.component';
import { HomeComponent } from './home/home.component';
import { CanvasComponent } from './sample/new_sample.component';
import { ViewCanvasComponent } from './sample/view_sample.component';
import { AuthGuard } from './guards/auth.guard';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'sample/new', component: CanvasComponent },
    { path: 'sample/browse/:id', component: ViewCanvasComponent },
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);