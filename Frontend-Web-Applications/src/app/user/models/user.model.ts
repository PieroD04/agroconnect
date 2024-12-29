export class User {
  id: number;
  email: string;
  password: string;


  constructor(id: number = 0, email: string = '', password: string = '') {
    this.id = id;
    this.email = email;
    this.password = password;
  }
}
