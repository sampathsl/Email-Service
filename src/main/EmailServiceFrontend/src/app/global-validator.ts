import {FormControl} from '@angular/forms';

/**
 * Created by SAMPATH on 7/2/2017.
 */

export class GlobalValidator {

  static mailFormat(control: FormControl): ValidationResult {

    const EMAIL_REGEXP = /^([\w+-.%]+@[\w-.]+\.[A-Za-z]{2,4})(,[\w+-.%]+@[\w-.]+\.[A-Za-z]{2,4}){0,4}$/i;

    if (control.value !== '' && (control.value.length <= 5 || !EMAIL_REGEXP.test(control.value))) {
      return { 'incorrectMailFormat': true };
    }

    return null;
  }

}

interface ValidationResult {
  [key: string]: boolean;
}
