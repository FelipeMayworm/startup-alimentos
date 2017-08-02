import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class LancheService {

  private headers = new Headers({ 'Content-Type': 'application/json', 'charset': 'UTF-8' });
  private options = new RequestOptions({ headers: this.headers });

  constructor(private http: Http) { }

  getLanches(): Observable<any> {
    return this.http.get('/api/lanches').map(res => res.json());
  }

  countLanches(): Observable<any> {
    return this.http.get('/api/lanches/count').map(res => res.json());
  }

  addLanche(lanche): Observable<any> {
    return this.http.post('/api/lanche', JSON.stringify(lanche), this.options);
  }

  getLanche(lanche): Observable<any> {
    return this.http.get(`/api/lanche/${lanche.id}`).map(res => res.json());
  }

  deleteLanche(lanche): Observable<any> {
    return this.http.delete(`/api/lanche/${lanche.id}`, this.options);
  }

  askLanche(lanche): Observable<any> {
    return this.http.put(`/api/lanche/${lanche.id}`, JSON.stringify(lanche), this.options);
  }

}
