import { Component, OnInit } from '@angular/core';
import { IngredienteService } from '../services/ingrediente.service';
import { Http } from '@angular/http';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ToastComponent } from '../shared/toast/toast.component';

@Component({
  selector: 'app-ingredientes',
  templateUrl: './ingredientes.component.html',
  styleUrls: ['./ingredientes.component.scss']
})
export class IngredientesComponent implements OnInit {

  ingrediente = {};
  ingredientes = [];
  isLoading = true;
  isEditing = false;

  addIngredienteForm: FormGroup;
  tipo = new FormControl('', Validators.required);
  quantidade = new FormControl('', Validators.required);
  valor = new FormControl('', Validators.required);

  constructor(private ingredienteService: IngredienteService,
              private formBuilder: FormBuilder,
              private http: Http,
              public toast: ToastComponent) { }

  ngOnInit() {
    this.getIngredientes();    
    this.addIngredienteForm = this.formBuilder.group({
      tipo: this.tipo,
      quantidade: this.quantidade,
      valor: this.valor
    });
  }

  getIngredientes() {
    this.ingredienteService.getIngredientes().subscribe(
      data => this.ingredientes = data,
      error => console.log(error),
      () => this.isLoading = false
    );
  }

  addIngrediente() {
    this.ingredienteService.addIngrediente(this.addIngredienteForm.value).subscribe(
      res => {
        const newIngrediente = res.json();
        this.ingredientes.push(newIngrediente);
        this.addIngredienteForm.reset();        
        this.toast.setMessage('Ingrediente incluído com sucesso.', 'success');
        this.getIngredientes();
      },
      error => console.log(error)
    );
  }

  enableEditing(ingrediente) {
    this.isEditing = true;
    this.ingrediente = ingrediente;
  }

  cancelaEdicao() {
    this.isEditing = false;
    this.ingrediente = {};
    this.toast.setMessage('Cancelamento da edição do ingrediente.', 'warning');
    this.getIngredientes();
  }

  editIngrediente(ingrediente) {
    this.ingredienteService.editIngrediente(ingrediente).subscribe(
      res => {
        this.isEditing = false;
        this.ingrediente = ingrediente;
        this.toast.setMessage('Ingrediente alterado com sucesso.', 'success');        
      },
      error => console.log(error)
    );
  }
  
  deleteIngrediente(ingrediente) {
    if (window.confirm('Você tem certeza que deseja remover o Ingrediente?')) {
      this.ingredienteService.deleteIngrediente(ingrediente).subscribe(
        res => {
          const pos = this.ingredientes.map(elem => { return elem._id; }).indexOf(ingrediente._id);
          this.toast.setMessage('Ingrediente removido com sucesso.', 'success');
          this.getIngredientes();
        },
        error => console.log(error)
      );
    }
  }

}
