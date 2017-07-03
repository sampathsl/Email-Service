import {Component, ElementRef, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {Email} from '../email.model';
import {EmailService} from '../email.service';
import {Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthCodeResponseData} from 'app/authcode-response-data';
import {AuthCode} from '../authcode.model';
import {EmailSentResponseData} from '../email-sent-response-data';

@Component({
  selector : 'app-add-task',
  templateUrl : './email.component.html',
  styleUrls : ['./email.component.css'],
  providers : [FormBuilder]
})

export class EmailComponent implements OnInit {

  authCodeResponseData: AuthCodeResponseData = new AuthCodeResponseData('');
  emailSentResponseData: EmailSentResponseData = new EmailSentResponseData('');
  sendEmailForm: FormGroup;
  submitted: boolean;
  email: Email;
  authCode: AuthCode;

  to: FormControl;
  cc: FormControl;
  bcc: FormControl;
  subject: FormControl;
  message: FormControl;

  toAlert: String = 'Please enter to email address(es) with comma separated!';
  ccAlert: String = 'Please enter CC email address(es) with comma separated!';
  bccAlert: String = 'Please enter BCC email address(es) with comma separated!';
  mimeTypeAlert: String = 'Please enter the email content type!';
  subjectAlert: String = 'Valid subject is required - Subject should have 5 - 50 characters!';
  messageAlert: String = 'Email message is required! - Email message should have 5 - 500 characters!';

  emailSent: String = '';
  emailSentStatus: String = '';

  @Input() dueDateText: FormControl;
  dueDateDate: Date;
  @Input() resolvedDateText: FormControl;
  resolvedDateDate: Date;
  @Output() dateModelChange: EventEmitter<Date> = new EventEmitter();

  toUTCDate = function(date) {
    const _utc = new Date(date.getUTCFullYear(), date.getUTCMonth(),
      date.getUTCDate(),  date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds());
    return _utc;
  };

  constructor(@Inject(FormBuilder) fb: FormBuilder, private emailService: EmailService , private router: Router ,
              private _eref: ElementRef) {
    this.submitted = false;
    this.dueDateDate = this.toUTCDate(new Date());
    this.resolvedDateDate = this.toUTCDate(new Date());
    this.authCode = new AuthCode('test', 'c3Nzc3NzYWFhYWFhYTEyMzQ1');
  }

  ngOnInit() {

    this.createFormControls();
    this.createForm();
    this.subscribeToFormChanges();

  }

  subscribeToFormChanges() {
    const myFormStatusChanges = this.sendEmailForm.statusChanges;
    const myFormValueChanges = this.sendEmailForm.valueChanges;
  }

  createFormControls() {

    this.to = new FormControl(null, Validators.required);
    this.cc = new FormControl(null);
    this.bcc = new FormControl(null);
    this.subject = new FormControl(null, [Validators.required, Validators.pattern('.{5,50}')]);
    this.message = new FormControl(null, [Validators.required, Validators.pattern('.{5,500}')]);

  }

  resetFormData() {

    this.to.setValue(null);
    this.cc.setValue(null);
    this.bcc.setValue(null);
    this.subject.setValue(null);
    this.message.setValue(null);

  }

  createForm() {

    this.sendEmailForm = new FormGroup({
      to : this.to,
      cc : this.cc,
      bcc : this.bcc,
      subject : this.subject,
      message : this.message,
    });

  }

  sendEmailSubmit(model: Email, isValidForm: boolean) {

    this.submitted = true;

    if (isValidForm) {

      this.emailSent = '';
      this.emailSentStatus = '';

        this.emailService.varifySendEmail(this.authCode, isValidForm)
        .subscribe(
          (data: any) => {

            this.authCodeResponseData = data;

            if ( this.authCodeResponseData && this.authCodeResponseData != null && this.authCodeResponseData.authCode !== '' ) {

              const arr = [];

              this.email = new Email(
                this.authCodeResponseData.authCode,
                this.to.value.split(','),
                this.cc && this.cc.value != null ? this.cc.value.split(',') : arr ,
                this.bcc && this.bcc.value != null ? this.bcc.value.split(',') : arr,
                model.subject,
                model.message
              );

              this.emailService.sendEmail(this.email, isValidForm)
                .map(response => response.json())
                .subscribe(
                  (responseData: any) => {

                    this.emailSentResponseData = responseData;

                    if ( this.emailSentResponseData.emailSent != null && this.emailSentResponseData.emailSent === 'SUCCESS' ) {
                      this.emailSentStatus = 'SUCCESS';
                      this.emailSent = 'Your email has been successfully sent!';
                    } else if ( this.emailSentResponseData.emailSent != null && this.emailSentResponseData.emailSent === 'FAIL' ) {
                      this.emailSentStatus = 'FAIL';
                      this.emailSent = 'Your email has not sent!';
                    } else if ( this.emailSentResponseData.emailSent != null && this.emailSentResponseData.emailSent === 'SESSION_ERROR' ) {
                      this.emailSentStatus = 'WARN';
                      this.emailSent = 'Error! Your session has expired!';
                    } else {
                      this.emailSentStatus = 'FAIL';
                      this.emailSent = 'Error occurred! Please contact system administrator!';
                    }

                  },
                  function (error) {
                     // console.log(error);
                    this.emailSentStatus = 'FAIL';
                    this.emailSent = 'Error occurred! Please contact system administrator!';
                    console.log('this.emailSent ::' + this.emailSent);
                  },
                  function () {
                    // console.log('finally');
                  }
                );

            }
          },
          function (error) {
            // console.log(error);
            this.emailSentStatus = 'WARN';
            this.emailSent = 'Error! Your session has expired!';
          },
          function () {
            // console.log('finally');
          }
        );

    }

  }

  isEmptyObject(obj) {
    return (obj && Object.keys(obj).length === 0);
  }

}
