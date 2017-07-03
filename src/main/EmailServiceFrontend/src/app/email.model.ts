/**
 * Created by SAMPATH on 7/2/2017.
 */

export class Email {

  public authCode: string;
  public to: string[];
  public cc: string[];
  public bcc: string[];
  public subject: string;
  public message: string;

  constructor(authCode: string, to: string[], cc: string[], bcc: string[],
              subject: string, message: string) {
    this.authCode = authCode;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.message = message;
  }

}
