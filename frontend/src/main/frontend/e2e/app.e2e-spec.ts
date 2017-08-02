import { LanchesPage } from './app.po';

describe('lanches App', () => {
  let page: LanchesPage;

  beforeEach(() => {
    page = new LanchesPage();
  });

  it('should display welcome message', done => {
    page.navigateTo();
    page.getParagraphText()
      .then(msg => expect(msg).toEqual('Welcome to app!!'))
      .then(done, done.fail);
  });
});
