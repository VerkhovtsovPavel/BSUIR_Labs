﻿import { Injectable } from '@angular/core';
import { Http, Headers, Response,  RequestOptions} from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';

@Injectable()
export class AuthenticationService {
    public token: string;

    constructor(private http: Http) {
        // set token if saved in local storage
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    login(username: string, password: string): Observable<boolean> {
        let headers = new Headers({ 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.post('http://localhost:5467/v1/auth/signIn', JSON.stringify({ login: username, password: password }), options)
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let token = response.json();
                if (token) {
                    // set token property
                    this.token = token;

                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

                    // return true to indicate successful login
                    return true;
                } else {
                    // return false to indicate failed login
                    return false;
                }
            });
    }

    signUp(username: string, email:string, password: string, firstName: string, lastName: string): Observable<boolean> {
        let headers = new Headers({ 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.post('http://localhost:5467/v1/auth/signUp', JSON.stringify({ username: username, email: email, password: password }), options)
            .map((response: Response) => response.json())
            .mergeMap(token => {
                headers.append('Token', token);
                let options = new RequestOptions({headers: headers});
                return this.http.post('http://localhost:5467/v1/profiles/me', JSON.stringify({id: "0", firstName: firstName, lastName: lastName }), options)})
            .map((response: Response) => response.json() ? true : false);
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}