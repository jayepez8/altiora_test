import { Component, OnInit } from '@angular/core';
import { CardModule } from "primeng/card";
import { TableModule } from "primeng/table";
import { Customer } from "../../../shared/models/customer";
import { CustomerService } from "../../../core/http/customer.service";
import { CommonModule } from "@angular/common";
import { Button } from "primeng/button";

@Component({
  selector: 'app-customer-list',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    TableModule,
    Button,
  ],
  templateUrl: './customer-list.component.html',
  styleUrl: './customer-list.component.scss'
})
export class CustomerListComponent implements OnInit{

  customers: Customer[] | any = [];

  constructor(private customerService:CustomerService) {
  }

  ngOnInit(): void {
    this.customerService.getAll().subscribe({
      next:(result=>this.customers = result)
    })
  }


}
