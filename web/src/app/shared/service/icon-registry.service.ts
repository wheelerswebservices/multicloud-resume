import {ChangeDetectorRef, Injectable} from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import {BreakpointObserver} from "@angular/cdk/layout";
import {ViewService} from "./view.service";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root',
})
export class IconRegistryService {
  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {}

  init(): void {
    this.registerIcon('aws');
    this.registerIcon('google');
    this.registerIcon('microsoft');
  }

  private registerIcon(name: string): void {
    const resourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl(`../assets/app/${name}.svg`);
    this.iconRegistry.addSvgIcon(name, resourceUrl);
  }
}
