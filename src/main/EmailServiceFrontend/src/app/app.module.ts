import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import {EmailComponent} from './email-service/email.component';
import {HeaderComponent} from './header/header/header.component';
import {KeysPipe} from './keys-pipe';
import {AppRoutes} from './app.routing';
import {CommonModule} from '@angular/common';
import {EmailService} from './email.service';
import {Ng2Bs3ModalModule} from 'ng2-bs3-modal/ng2-bs3-modal';


@NgModule({
  declarations: [
    AppComponent,
    EmailComponent,
    HeaderComponent,
    KeysPipe
  ],
  exports: [KeysPipe],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutes,
    CommonModule,
    ReactiveFormsModule,
    Ng2Bs3ModalModule
  ],
  providers: [EmailService],
  bootstrap: [AppComponent]
})
export class AppModule { }
