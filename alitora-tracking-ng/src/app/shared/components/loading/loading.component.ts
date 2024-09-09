import { Component } from '@angular/core';
import { LoaderService } from "../../../core/services/loader.service";
import { ProgressSpinnerModule } from "primeng/progressspinner";
import { CommonModule } from "@angular/common";

@Component({
  selector: 'app-loading',
  standalone: true,
  imports: [
    CommonModule,
    ProgressSpinnerModule
  ],
  templateUrl: './loading.component.html',
  styleUrl: './loading.component.scss'
})
export class LoadingComponent {
  loading:boolean = false;

  constructor(private loaderService: LoaderService) {
    this.loaderService.isLoading$
      .subscribe((isLoading) => {
        this.loading = isLoading;
      });
  }
}
