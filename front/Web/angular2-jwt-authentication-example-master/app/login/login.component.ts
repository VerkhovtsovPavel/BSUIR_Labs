import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../services/alert.service';

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                (result) => {
                    if (result === true) {
                        this.router.navigate(['/']);
                    } else {
                        this.alertService.error('Username or password is incorrect');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Username or password is incorrect');
                        this.loading = false;
                });
    }

}
