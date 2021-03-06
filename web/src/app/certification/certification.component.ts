import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { MetaData } from '../shared/model/meta-data.model';
import { CertificationState } from '../core/store/certification/certification.state';
import { CertificationFacade } from '../core/store/certification/certification.facade';
import { ViewFacade } from '../core/store/view/view.facade';

@Component({
  selector: 'app-certifications',
  templateUrl: './certification.component.html',
  styleUrls: ['./certification.component.scss', '../shared/component/carousel/carousel.component.scss'],
})
export class CertificationComponent implements OnDestroy, OnInit {
  constructor(private certificationFacade: CertificationFacade, private viewFacade: ViewFacade) {}

  destroyed$ = new Subject<void>();
  state: CertificationState;

  ngOnDestroy(): void {
    this.destroyed$.next();
    this.destroyed$.complete();
  }

  ngOnInit(): void {
    this.viewFacade.setEnable('certification', false);

    this.certificationFacade
      .retrieve()
      .pipe(
        takeUntil(this.destroyed$),
        filter((state) => !!state?.data)
      )
      .subscribe((state) => {
        this.state = state;
        this.viewFacade.setEnable('certification', true);
      });
  }

  getMetaForLevel(value: string): MetaData {
    return this.state.meta.levels.find((meta) => meta.name === value);
  }

  getMetaForVendor(value: string): MetaData {
    return this.state.meta.vendors.find((meta) => meta.name === value);
  }
}
