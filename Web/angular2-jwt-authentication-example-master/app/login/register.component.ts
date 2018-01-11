import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../services/alert.service';

@Component({
    moduleId: module.id,
    templateUrl: 'register.component.html'
})

export class RegisterComponent {
    model: any = {};
    loading = false;

 constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }

    signUp() {
        this.loading = true;
        this.authenticationService.signUp(this.model.username, this.model.email, this.model.password, this.model.firstName, this.model.lastName)
            .subscribe(
                (result) => {
                    if (result === true) {
                        this.alertService.success('Registered successfuly', true);
                        this.router.navigate(['/login']);
                    } else {
                        this.alertService.error('Registarion error');
                        this.loading = false;
                    }
                },
                (error) => {
                        this.alertService.error('Registarion error');
                        this.loading = false;
                });
    }
}
