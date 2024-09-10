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
import { Table, TableModule } from "primeng/table";
import { IconFieldModule } from "primeng/iconfield";
import { InputIconModule } from "primeng/inputicon";
import { Button } from "primeng/button";
import { InputNumberModule } from "primeng/inputnumber";
import { OrderService } from "../../../core/http/order.service";
import { Order } from "../../../shared/models/order";
import { OrderItem } from "../../../shared/models/order-item";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastModule } from "primeng/toast";
import { MessageService } from "primeng/api";

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
    TableModule,
    IconFieldModule,
    InputIconModule,
    Button,
    InputNumberModule,
    ToastModule,
  ],
  providers: [MessageService],
  templateUrl: './order-add.component.html',
  styleUrl: './order-add.component.scss'
})
export class OrderAddComponent implements OnInit{

  @ViewChild('tableList') table!: Table;


  customers:Customer[]| any = [];
  items:Item[]|any=[];

  customer!:Customer |undefined;
  itemsSelected!: Item[];
  order:Order={
    orderCode:'',
    totalOrder:0
  };


  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef,
    private orderService:OrderService,
    private itemsService:ItemsService,
    private messageService: MessageService,
    private customerService:CustomerService
  ) {
  }

  ngOnInit(): void {
    forkJoin([
      this.customerService.getAll(),
      this.itemsService.getAll(),
      this.orderService.getNextOrderCode()
    ]).subscribe(([customers,items,orderCode])=>{
      this.customers = customers;
      this.items = items;
      this.order.orderCode = orderCode.code;
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
    this.order.totalOrder = 0;
    this.itemsSelected.forEach(i => this.order.totalOrder+=i.total || 0);
  }

  clearSelected() {
    this.itemsSelected = [];
    this.order.totalOrder = 0;
  }

  saveOrder() {
    if(!this.customer){
      this.messageService.add({severity: 'warn', summary: 'Warnign', detail: 'Select client'});
      return;
    }
    if(this.itemsSelected.length === 0){
      this.messageService.add({severity: 'warn', summary: 'Warning', detail: 'Select items'});
      return;
    }
    this.order.identification = this.customer.identification;
    this.order.orderItems = this.itemsSelected.map(item => {
      const itemCode: OrderItem = {
        itemCode: item.itemCode,
        quantity: item.amount || 0,
        totalPrice: item.total || 0
      }
      return itemCode;
    });
    this.orderService.create(this.order).subscribe({
      complete:(()=>{
        this.clearSelected();
        this.customer = undefined;
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Order created successfully'});
        this.router.navigate(['/home/order'], {relativeTo: this.route}).finally();
      }),
      error:(err =>{
        this.messageService.add({severity: 'error', summary: 'Error', detail: err.error.message});
      })
    });
  }
}
