import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { constants } from "../../../constants/constants";
import { Observable } from "rxjs";
import { Customer } from "../../shared/models/customer";
import { Order } from "../../shared/models/order";
import { NextCode } from "../../shared/models/next-code";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly orderController:string;

  constructor(private http:HttpClient) {
    this.orderController = constants.SERVICES.SERVER + constants.SERVICES.TRACKING_SERVICES.CONTEXT +
      constants.SERVICES.TRACKING_SERVICES.API.CONTROLLERS.ORDER +
      constants.SERVICES.TRACKING_SERVICES.API.PATH
  }

  getAll():Observable<Order[] | Object>{
    return this.http.get(this.orderController);
  }

  create(order:Order):Observable<Order[]>{
    return this.http.post<Order[]>(this.orderController,order);
  }

  getNextOrderCode():Observable<NextCode> {
    return this.http.get<NextCode>(this.orderController+"/getNextOrderCode");
  }
}
