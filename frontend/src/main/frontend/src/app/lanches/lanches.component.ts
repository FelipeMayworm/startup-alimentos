import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

import { LancheService } from '../services/lanche.service';
import { IngredienteService } from '../services/ingrediente.service';
import { ToastComponent } from '../shared/toast/toast.component';

@Component({
  selector: 'app-lanches',
  templateUrl: './lanches.component.html',
  styleUrls: ['./lanches.component.scss']
})
export class LanchesComponent implements OnInit {

  lanche = {};
  lanches = [];
  ingrediente = {};
  ingredientes = [];
  isLoading = true;
  isEditing = false;
  ingreds = [];
  
  addLancheForm: FormGroup;
  nome = new FormControl('', Validators.required);
  quantidade = new FormControl('', Validators.required);
  valor = 0;

  constructor(private lancheService: LancheService,
              private ingredienteService: IngredienteService,
              private formBuilder: FormBuilder,
              private http: Http,
              public toast: ToastComponent) { }

  ngOnInit() {
    this.getLanches();
    this.getIngredientes();  
    this.addLancheForm = this.formBuilder.group({
      nome: this.nome,
      ingredientes: this.ingreds,
      quantidade: this.quantidade,
      valor: this.valor
    });
  }

  getLanches() {
    this.lancheService.getLanches().subscribe(
      data => this.lanches = data,
      error => console.log(error),
      () => this.isLoading = false
    );      
  }

  getIngredientes() {
    this.ingredienteService.getIngredientes().subscribe(
      data => this.ingredientes = data,
      error => console.log(error),
      () => this.isLoading = false
    );
  }

  addLanche() {
    this.lancheService.addLanche(this.addLancheForm.value).subscribe(
      res => {
        const newLanche = res.json();
        this.lanches.push(newLanche);
        this.addLancheForm.reset();        
        this.toast.setMessage('Lanche incluído com sucesso.', 'success');
        this.getLanches();
      },
      error => console.log(error)
    );
  }

  getTotal(valor, qtd) {
    let total: number = valor;
    return total*qtd;
  }

  deleteLanche(lanche) {
    if (window.confirm('Você tem certeza que deseja remover o Lanche?')) {
      this.lancheService.deleteLanche(lanche).subscribe(
        res => {
          const pos = this.lanches.map(elem => { return elem._id; }).indexOf(lanche._id);
          this.toast.setMessage('Lanche deletado com sucesso.', 'success');
          this.getLanches();
        },
        error => console.log(error)
      );
    }
  }

}
