/**
 * Created by SAMPATH on 7/2/2017.
 */

import { Routes, RouterModule } from '@angular/router';
import {EmailComponent} from './email-service/email.component';

const router: Routes = [{ path: '' , component : EmailComponent }];

export const AppRoutes = RouterModule.forRoot(router);
