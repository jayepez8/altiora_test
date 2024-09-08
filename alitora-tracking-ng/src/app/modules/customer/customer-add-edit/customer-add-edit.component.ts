import { Component, OnInit } from '@angular/core';
import { Button } from "primeng/button";
import { DialogService, DynamicDialogComponent, DynamicDialogRef } from "primeng/dynamicdialog";
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
  isEdit:boolean=false;
  instance: DynamicDialogComponent | undefined;

  constructor(
    private ref: DynamicDialogRef,
    private dialogService: DialogService,
    private customerService:CustomerService
  ) {
    this.instance = this.dialogService.getInstance(this.ref);
  }

  ngOnInit(): void {
    if (this.instance && this.instance.data) {
      this.customer = this.instance.data
      this.isEdit=true;
    }
    this.form = new FormGroup({
      identification: new FormControl({value:this.customer.identification,disabled: this.isEdit}, [Validators.required]),
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
    if(this.isEdit){
      this.customerService.update(body).subscribe({
        next: (result => this.ref.close(result))
      })
    }else {
      this.customerService.create(body).subscribe({
        next: (result => this.ref.close(result))
      });
    }
  }
}
