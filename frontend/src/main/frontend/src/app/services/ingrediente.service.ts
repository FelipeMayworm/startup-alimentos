import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class IngredienteService {

  private headers = new Headers({ 'Content-Type': 'application/json', 'charset': 'UTF-8' });
  private options = new RequestOptions({ headers: this.headers });

  constructor(private http: Http) { }

  getIngredientes(): Observable<any> {
    return this.http.get('/api/ingredientes').map(res => res.json());
  }

  addIngrediente(ingrediente): Observable<any> {
    return this.http.post('/api/ingrediente', JSON.stringify(ingrediente), this.options);
  }

  editIngrediente(ingrediente): Observable<any> {
    return this.http.put(`/api/ingrediente/${ingrediente.id}`, JSON.stringify(ingrediente), this.options);
  }

  deleteIngrediente(ingrediente): Observable<any> {
    return this.http.delete(`/api/ingrediente/${ingrediente.id}`, this.options);
  }
}  