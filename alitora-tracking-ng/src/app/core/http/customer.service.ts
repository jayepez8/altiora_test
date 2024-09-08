import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { constants } from "../../../constants/constants";
import { Observable } from "rxjs";
import { Customer } from "../../shared/models/customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly customerController:string;
  constructor(private http:HttpClient) {
    this.customerController = constants.SERVICES.SERVER + constants.SERVICES.TRACKING_SERVICES.CONTEXT +
      constants.SERVICES.TRACKING_SERVICES.API.CONTROLLERS.CUSTOMER +
      constants.SERVICES.TRACKING_SERVICES.API.PATH
  }

  getAll():Observable<Customer[] | Object>{
    return this.http.get(this.customerController);
  }

  create(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>(this.customerController,customer);
  }

  update(customer:Customer):Observable<Customer>{
    return this.http.put<Customer>(this.customerController,customer);
  }

  delete(identification:string):Observable<void>{
    return this.http.delete<void>(this.customerController+'?identification='+identification);
  }
}
