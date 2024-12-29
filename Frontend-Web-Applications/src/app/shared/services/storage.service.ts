import { Injectable } from '@angular/core';
import firebase from 'firebase/compat/app';
import 'firebase/compat/storage';
import {environment} from "../../../environments/environment";

firebase.initializeApp(environment.firebaseConfig);

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  storageRef = firebase.app().storage().ref();

  constructor() { }

  async uploadFile(name: string, imgBase64: any) {
    try {
      let respuesta = await this.storageRef.child("images/" + name).putString(imgBase64, 'data_url');
      console.log(respuesta);
      return respuesta.ref.getDownloadURL();
    }
    catch(error) {
      console.log(error);
      return null;
    }
  }
}
