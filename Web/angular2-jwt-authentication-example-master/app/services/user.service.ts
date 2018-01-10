import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operator/map';

import { AuthenticationService } from './authentication.service';
import { User } from '../models/user';

@Injectable()
export class UserService {
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService) {
    }

    getUser(): Observable<User> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/profiles/me', options)
            .map((response: Response) => response.json());
    }
}