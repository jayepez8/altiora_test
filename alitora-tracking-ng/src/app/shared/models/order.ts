import { Customer } from "./customer";

export interface Order{
  orderCode:string;
  orderDate:Date;
  customer:Customer;
  identification?:string;
}
