import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

import { LancheService } from '../services/lanche.service';
import { ToastComponent } from '../shared/toast/toast.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{

  lanche = {};
  lanches = [];
  isLoading = true;
  isPedido = false;
  valor = 0;

  constructor(private lancheService: LancheService,
              private formBuilder: FormBuilder,
              private http: Http,
              public toast: ToastComponent) { }

  ngOnInit() {
  	this.isPedido = false;
    this.getLanches();       
  }

  getLanches() {
  	this.lancheService.getLanches().subscribe(
      data => this.lanches = data,
      error => console.log(error),
      () => this.isLoading = false      
    );      
  }

  getLanche(lanche) {
  	this.lancheService.getLanche(lanche).subscribe(
      data => this.lanches = data,
      error => console.log(error),
      () => this.isLoading = false
    );      
  }

  askLanche(lanche) {	
  	this.lancheService.askLanche(lanche).subscribe(
      res => {
      	this.lanche = res.json();
        this.toast.setMessage('Lanche pedido com sucesso.', 'success');     
        this.isPedido = true;           
      },
      error => console.log(error)
    );    
  }

  getTotal(valor, qtd) {
	let total: number = valor;
	return total*qtd;
  }

  fecharPedido(){
  	this.lanche = {};
  	this.isPedido = false;
  }

}