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

  create(customer:Customer):Observable<Customer[] | Object>{
    return this.http.post(this.customerController,customer);
  }

  getByIdentification(identification:string):Observable<Customer | Object>{
    return this.http.get(this.customerController+'/'+identification);
  }
}
