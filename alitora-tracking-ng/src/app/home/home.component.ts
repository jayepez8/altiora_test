import { Component } from '@angular/core';
import { BadgeModule } from "primeng/badge";
import { MenubarModule } from "primeng/menubar";
import { MenuItem } from "primeng/api";
import { AvatarModule } from "primeng/avatar";
import { Ripple } from "primeng/ripple";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    BadgeModule,
    MenubarModule,
    AvatarModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  items: MenuItem[] = [
    {
      label: 'Customer',
      icon: 'pi pi-home'
    },
    {
      label: 'Items',
      icon: 'pi pi-star'
    },
    {
      label: 'Orders',
      icon: 'pi pi-search',
    }
  ];

}
