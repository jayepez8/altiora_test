import { Component, OnInit } from '@angular/core';
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Button } from "primeng/button";
import { OrderService } from "../../../core/http/order.service";
import { Order } from "../../../shared/models/order";
import { CommonModule } from "@angular/common";
import { ActivatedRoute, Router } from "@angular/router";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { OrderDetailsComponent } from "../order-details/order-details.component";
import { ToastModule } from "primeng/toast";
import { MessageService } from "primeng/api";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    Button,
    ToastModule
  ],
  providers: [DialogService,MessageService],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.scss'
})
export class OrderListComponent implements OnInit{

  orders: Order[] | any = [];
  ref: DynamicDialogRef | undefined;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService,
    public dialogService: DialogService,
  ) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.orderService.getAll().subscribe({
      next:(result=>this.orders = result)
    })
  }

  redirectOrderAdd() {
    this.router.navigate(['/home/order/add'],{relativeTo:this.route}).finally()
  }

  showDetails(orderCode: string) {
    this.orderService.findByOrderCode(orderCode).subscribe({
      next: (order => {
        this.ref = this.dialogService.open(OrderDetailsComponent, {
          header: 'Details order ' + orderCode,
          data: order,
          width: '80vw',
        },);
      })
    })
  }
}
