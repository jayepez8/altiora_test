import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Item } from "../../../shared/models/item";
import { DynamicDialogRef } from "primeng/dynamicdialog";
import { ItemsService } from "../../../core/http/items.service";
import { Button } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { InputNumberModule } from "primeng/inputnumber";

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
export class ItemAddComponent {

  form!: FormGroup;
  item: Item = {
    itemCode:'',
    name:'',
    unitPrice:0
  }

  constructor(
    private ref: DynamicDialogRef,
    private itemsService:ItemsService
  ) {
    this.form = new FormGroup({
      itemCode: new FormControl(this.item.itemCode, [Validators.required]),
      name: new FormControl(this.item.name, [Validators.required]),
      unitPrice: new FormControl(this.item.unitPrice, [Validators.required]),
    });
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
