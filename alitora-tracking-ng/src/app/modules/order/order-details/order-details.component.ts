import { Component, OnDestroy, OnInit } from '@angular/core';
import { DialogService, DynamicDialogComponent, DynamicDialogRef } from "primeng/dynamicdialog";
import { CommonModule } from "@angular/common";
import { Order } from "../../../shared/models/order";
import { TableModule } from "primeng/table";
import { OrderItem } from "../../../shared/models/order-item";
import { FieldsetModule } from "primeng/fieldset";

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    FieldsetModule
  ],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.scss'
})
export class OrderDetailsComponent implements OnInit, OnDestroy {

  order!: Order;
  items: OrderItem[] = [];
  instance: DynamicDialogComponent | undefined;

  constructor(
    public ref: DynamicDialogRef,
    private dialogService: DialogService
  ) {
    this.instance = this.dialogService.getInstance(this.ref);
  }

  ngOnInit(): void {
    if (this.instance && this.instance.data) {
      this.order = this.instance.data
      this.items = this.order.orderItems || [];
    }
  }

  ngOnDestroy() {
    if (this.ref) {
      this.ref.close();
    }
  }
}
