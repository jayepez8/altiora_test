import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { constants } from "../../../constants/constants";
import { Observable } from "rxjs";
import { Customer } from "../../shared/models/customer";
import { Item } from "../../shared/models/item";
import { NextCode } from "../../shared/models/next-code";

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  private readonly itemController:string;
  constructor(private http:HttpClient) {
    this.itemController = constants.SERVICES.SERVER + constants.SERVICES.TRACKING_SERVICES.CONTEXT +
      constants.SERVICES.TRACKING_SERVICES.API.CONTROLLERS.ITEM +
      constants.SERVICES.TRACKING_SERVICES.API.PATH
  }

  getAll():Observable<Item[] | Object>{
    return this.http.get(this.itemController);
  }

  create(item:Item):Observable<Item[] | Object>{
    return this.http.post(this.itemController,item);
  }

  getNextItemCode():Observable<NextCode> | any{
    return this.http.get(this.itemController+"/getNextItemCode");
  }
}
