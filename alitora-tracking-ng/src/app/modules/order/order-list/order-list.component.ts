import { Component, OnInit } from '@angular/core';
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Button } from "primeng/button";
import { OrderService } from "../../../core/http/order.service";
import { Order } from "../../../shared/models/order";
import { CommonModule } from "@angular/common";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    Button
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.scss'
})
export class OrderListComponent implements OnInit{

  orders: Order[] | any = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService:OrderService
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
    this.router.navigate(['/home/order/add'],{relativeTo:this.route})
  }

  showDetails(orderCode: string) {

  }
}
