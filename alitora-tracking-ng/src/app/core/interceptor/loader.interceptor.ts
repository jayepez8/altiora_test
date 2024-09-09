import { HttpEvent, HttpEventType, HttpHandlerFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { LoaderService } from "../services/loader.service";

const requests: HttpRequest<any>[] = [];
function removeRequest(req: HttpRequest<any>, loaderService:LoaderService): void {
  const i = requests.indexOf(req);
  if (i >= 0) {
    requests.splice(i, 1);
  }
  loaderService.setLoading(requests.length > 0);
}
export function loaderInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const loaderService = inject(LoaderService);
  requests.push(req);
  loaderService.setLoading(true);
  return next(req).pipe(tap({
    next:(event=>{
      if (event.type === HttpEventType.Response) {
        removeRequest(req,loaderService);
      }
    }),
    complete:(()=>{
      removeRequest(req,loaderService);
    }),
    error:(error=>{
      removeRequest(req,loaderService);
    })
  }));
}
