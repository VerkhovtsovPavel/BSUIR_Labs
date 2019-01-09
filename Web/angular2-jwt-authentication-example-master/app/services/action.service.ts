import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operator/map';

import { AuthenticationService } from './authentication.service';
import { Sample } from '../models/sample';
import { Features } from '../models/features';
import { Result } from '../models/result';

@Injectable()
export class ActionService {
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService) {
    }

    getFeatures(sample: Sample): Observable<Features> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/features'+sample.id, options)
            .map((response: Response) => response.json());
    }

    getMed(sample: Sample): Observable<Result> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/med'+sample.id, options)
            .map((response: Response) => response.json());
    }

    getPsyco(sample: Sample): Observable<Result> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/psyco'+sample.id, options)
            .map((response: Response) => response.json());
    }

    getCybetsec(sample: Sample): Observable<Boolean> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Token' : this.authenticationService.token, 'content-type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get('http://localhost:5467/v1/samples/cybetsec'+sample.id, options)
            .map((response: Response) => response.json());
    }
}