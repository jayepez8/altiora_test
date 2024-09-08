import { Customer } from "./customer";
import { OrderItem } from "./order-item";

export interface Order{
  orderCode:string;
  orderDate?:Date;
  customer?:Customer;
  identification?:string;
  totalOrder:number;
  orderItems?:OrderItem[];
}
