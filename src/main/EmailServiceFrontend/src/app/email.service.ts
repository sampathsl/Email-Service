import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import 'rxjs/Rx';
import {AuthCode} from './authcode.model';
import {Email} from './email.model';

@Injectable()
export class EmailService {

  mainURI = '/api/v1/email-send';

  toUTCDate = function(date) {
    const _utc = new Date(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(),
      date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
    return _utc;
  };

  constructor(private http: Http) {
  }

  sendEmail(email: Email, checked: boolean) {
    return this.http.post(this.mainURI, email);
  }

  verifySendEmail(authCode: AuthCode, checked: boolean) {
    if (checked) {
      return this.http.post(this.mainURI + '/varify', authCode)
        .map(
          (response: Response) => {
            return response.json() ? response.json() : {};
          }
        );
    }
  }

}
