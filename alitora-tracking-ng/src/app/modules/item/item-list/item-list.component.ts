import { Component, OnInit } from '@angular/core';
import { Item } from "../../../shared/models/item";
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Button } from "primeng/button";
import { ItemsService } from "../../../core/http/items.service";
import { Customer } from "../../../shared/models/customer";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { ItemAddComponent } from "../item-add/item-add.component";

@Component({
  selector: 'app-item-list',
  standalone: true,
  imports: [
    CardModule,
    TableModule,
    Button
  ],
  providers: [DialogService],
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.scss'
})
export class ItemListComponent implements OnInit{

  items: Item | any = [];
  ref: DynamicDialogRef | undefined;

  constructor(
    public dialogService: DialogService,
    private itemsService:ItemsService
  ) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.itemsService.getAll().subscribe({
      next:(result=>this.items = result)
    })
  }

  openModalCreate() {
    this.ref = this.dialogService.open(ItemAddComponent, {header: 'Create new item'});
    this.ref.onClose.subscribe((customer:Customer)=>{
      if(customer){
        this.getAll();
      }
    });
  }
}
