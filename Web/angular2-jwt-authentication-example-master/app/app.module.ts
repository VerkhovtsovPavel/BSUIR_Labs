import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';

// used to create fake backend

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AuthGuard } from './guards/auth.guard';
import { AuthenticationService} from './services/authentication.service';
import { UserService } from './services/user.service';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent
    ],
    providers: [
        AuthGuard,
        AuthenticationService,
        UserService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }