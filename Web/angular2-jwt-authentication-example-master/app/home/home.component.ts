import { Component, OnInit } from '@angular/core';

import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    user: User;

    constructor(private userService: UserService) { }

    ngOnInit() {
        // get users from secure api end point
        this.userService.getUser()
            .subscribe((user: User)=> {
                this.user = user;
            });
    }

}