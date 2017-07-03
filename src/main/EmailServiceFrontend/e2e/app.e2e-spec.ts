import { EmailServiceFrontendPage } from './app.po';

describe('email-service-frontend App', () => {
  let page: EmailServiceFrontendPage;

  beforeEach(() => {
    page = new EmailServiceFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
