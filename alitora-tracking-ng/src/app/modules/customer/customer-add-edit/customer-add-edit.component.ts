import { Component, OnInit } from '@angular/core';
import { Button } from "primeng/button";
import { DynamicDialogRef } from "primeng/dynamicdialog";
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Customer } from "../../../shared/models/customer";
import { CustomerService } from "../../../core/http/customer.service";
import { InputTextModule } from "primeng/inputtext";

@Component({
  selector: 'app-customer-add-edit',
  standalone: true,
  imports: [
    InputTextModule,
    Button,
    ReactiveFormsModule
  ],
  templateUrl: './customer-add-edit.component.html',
  styleUrl: './customer-add-edit.component.scss'
})
export class CustomerAddEditComponent implements OnInit{

  form!: FormGroup;
  customer:Customer ={
    identification: '',
    firstName:'',
    lastName:''
  };

  constructor(
    private ref: DynamicDialogRef,
    private customerService:CustomerService
  ) {
  }

  ngOnInit(): void {
  this.form = new FormGroup({
    identification: new FormControl(this.customer.identification, [Validators.required]),
    firstName: new FormControl(this.customer.firstName, [Validators.required]),
    lastName: new FormControl(this.customer.lastName, [Validators.required]),
  });
  }

  get identification(){
    return this.form.get('identification');
  }
  get firstName(){
    return this.form.get('firstName');
  }
  get lastName(){
    return this.form.get('lastName');
  }

  saveCustomer() {
    if(this.form.invalid){
      return;
    }
    const body: Customer = {...this.form.getRawValue()};
    this.customerService.create(body).subscribe({
      next:(result=> this.ref.close(result))
    });
  }
}
