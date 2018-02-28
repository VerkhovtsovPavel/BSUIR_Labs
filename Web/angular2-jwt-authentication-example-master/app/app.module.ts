import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AuthGuard } from './guards/auth.guard';
import { JwtInterceptor } from './interceptors/jwt.interceptor';
import { AuthenticationService} from './services/authentication.service';
import { UserService } from './services/user.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register.component';
import { AlertService } from './services/alert.service';
import { HomeComponent } from './home/home.component';
import { CanvasComponent } from './sample/new_sample.component';
import { AlertComponent } from './alert/alert.component';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        LoginComponent,
        HomeComponent,
        RegisterComponent,
        CanvasComponent
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }