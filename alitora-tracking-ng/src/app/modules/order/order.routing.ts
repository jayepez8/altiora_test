import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { OrderListComponent } from "./order-list/order-list.component";
import { OrderAddComponent } from "./order-add/order-add.component";

const routes: Routes = [
  {
    path: '',
    component: OrderListComponent
  },
  {
    path: 'add',
    component: OrderAddComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRouting{
}
