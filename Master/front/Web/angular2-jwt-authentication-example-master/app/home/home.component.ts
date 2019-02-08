import { Component, OnInit } from '@angular/core';

import { User } from '../models/user';
import { Sample } from '../models/sample';
import { UserService } from '../services/user.service';
import { SampleService } from '../services/sample.service';

@Component({
    moduleId: module.id,
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    user: User;
    samples: Sample[] = [];
    
    constructor(private userService: UserService, private sampleService: SampleService) { }

    ngOnInit() {
        // get users from secure api end point
        this.userService.getUser()
            .subscribe((user: User)=> {
                this.user = user;
            });

        // get user samples
        this.sampleService.getAllSamples()
            .subscribe((sampleSeq: Sample[]) => {
                  this.samples = sampleSeq;
            });        
    }
}