import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { CardModule } from "primeng/card";
import { DividerModule } from "primeng/divider";
import { InputTextModule } from "primeng/inputtext";
import { CustomerService } from "../../../core/http/customer.service";
import { ItemsService } from "../../../core/http/items.service";
import { forkJoin } from "rxjs";
import { Customer } from "../../../shared/models/customer";
import { Item } from "../../../shared/models/item";
import { DropdownModule } from "primeng/dropdown";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { PickListModule } from "primeng/picklist";
import { Table, TableModule } from "primeng/table";
import { IconFieldModule } from "primeng/iconfield";
import { InputIconModule } from "primeng/inputicon";
import { Button } from "primeng/button";
import { InputNumberModule } from "primeng/inputnumber";

@Component({
  selector: 'app-order-add',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    CardModule,
    DividerModule,
    InputTextModule,
    DropdownModule,
    PickListModule,
    TableModule,
    IconFieldModule,
    InputIconModule,
    Button,
    InputNumberModule,
  ],
  templateUrl: './order-add.component.html',
  styleUrl: './order-add.component.scss'
})
export class OrderAddComponent implements OnInit{

  @ViewChild('tableList') table!: Table;


  customers:Customer[]| any = [];
  items:Item[]|any=[];

  customer!:Customer ;
  itemsSelected!: Item[];
  totalOrder: number =0;

  constructor(
    private customerService:CustomerService,
    private itemsService:ItemsService,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {
    forkJoin([this.customerService.getAll(),this.itemsService.getAll()]).subscribe(([customers,items])=>{
      this.customers = customers;
      this.items = items;
      this.cdr.markForCheck();
    })
    this.itemsSelected = [];
  }

  applyGlobalFilter(event: any) {
    const value = event.target.value;
    this.table.filterGlobal(value, 'contains');
  }

  pushItem(item: Item) {
    if(!this.itemsSelected.some(exist=> exist.itemCode === item.itemCode)){
      item.amount = 1;
      this.itemsSelected.push(item);
    }else {
      if(item && item.amount){
        item.amount+=1;
      }
    }
    this.calculateTotal(item);
  }

  calculateTotal(item: Item) {
     const find = this.itemsSelected.find(exist=> exist.itemCode === item.itemCode);
     if(find && find.amount){
       find.total = find.unitPrice * find.amount;
     }else if(find && find.amount===0){
       this.itemsSelected = this.itemsSelected.filter(exist=> exist.itemCode != item.itemCode);
     }
    this.totalOrder = 0;
    this.itemsSelected.forEach(i => this.totalOrder+=i.total || 0);
  }

  clearSelected() {
    this.itemsSelected = [];
    this.totalOrder = 0;
  }
}
