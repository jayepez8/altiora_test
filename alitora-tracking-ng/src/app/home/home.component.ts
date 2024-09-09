import { Component } from '@angular/core';
import { BadgeModule } from "primeng/badge";
import { MenubarModule } from "primeng/menubar";
import { MenuItem } from "primeng/api";
import { AvatarModule } from "primeng/avatar";
import { Router } from "@angular/router";
import { DividerModule } from "primeng/divider";
import { LoadingComponent } from "../shared/components/loading/loading.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    BadgeModule,
    MenubarModule,
    AvatarModule,
    DividerModule,
    LoadingComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  constructor(private router: Router) {
  }

  items: MenuItem[] = [
    {
      label: 'Customer',
      icon: 'pi pi-home',
      command: () => {
        this.router.navigate(['home/customer']).finally();
      }
    },
    {
      label: 'Items',
      icon: 'pi pi-star',
      command: () => {
        this.router.navigate(['home/item']).finally();
      }
    },
    {
      label: 'Orders',
      icon: 'pi pi-search',
      command: () => {
        this.router.navigate(['home/order']).finally();
      }
    }
  ];

}
