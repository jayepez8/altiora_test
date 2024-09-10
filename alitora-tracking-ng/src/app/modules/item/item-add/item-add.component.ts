import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import { Item } from "../../../shared/models/item";
import { DynamicDialogRef } from "primeng/dynamicdialog";
import { ItemsService } from "../../../core/http/items.service";
import { Button } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { InputNumberModule } from "primeng/inputnumber";
import { NextCode } from "../../../shared/models/next-code";

@Component({
  selector: 'app-item-add',
  standalone: true,
  imports: [
    Button,
    ReactiveFormsModule,
    InputTextModule,
    InputNumberModule
  ],
  templateUrl: './item-add.component.html',
  styleUrl: './item-add.component.scss'
})
export class ItemAddComponent implements OnInit{

  form!: FormGroup;
  item: Item={
    itemCode:'',
    name:'',
    unitPrice:0,
    stock:0
  };

  constructor(
    private ref: DynamicDialogRef,

    private cdr: ChangeDetectorRef,
    private itemsService:ItemsService
  ) {
    this.form = new FormGroup({
      itemCode: new FormControl({ value: this.item.itemCode, disabled: true }, [Validators.required]),
      name: new FormControl(this.item.name, [Validators.required]),
      unitPrice: new FormControl(this.item.unitPrice, [Validators.required,this.nonZeroValidator()]),
      stock: new FormControl(this.item.stock, [Validators.required,this.nonZeroValidator()]),
    });
  }

  nonZeroValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if (value !== null && value !== undefined && value === 0) {
        return {nonZero: true};
      }
      return null;
    };
  }

  ngOnInit(): void {
    this.itemsService.getNextItemCode().subscribe({
      next:((nextCode:NextCode)=>{
        this.form.patchValue({itemCode: nextCode.code})
      })
    })
  }

  saveItem() {
    if(this.form.invalid){
      return;
    }
    const body: Item = {...this.form.getRawValue()};
    this.itemsService.create(body).subscribe({
      next:(result=> this.ref.close(result))
    });
  }

}
