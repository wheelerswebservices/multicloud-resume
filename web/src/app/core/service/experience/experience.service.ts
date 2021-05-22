import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {AppComponent} from "../../../app.component";
import {tap} from "rxjs/operators";
import {MonthYearService} from "../../../shared/service/month-year.service";
import {ExperienceState} from "../../store/experience/experience.state";

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {

  constructor(private httpClient: HttpClient) {}

  retrieve(): Observable<ExperienceState> {
    return this.httpClient
      .get<ExperienceState>(AppComponent.api.experience.retrieve)
      .pipe(tap(state => ExperienceService.sort(state)));
  }

  private static sort(state: ExperienceState): ExperienceState {
    state.data.sort((n1, n2) => {
      return MonthYearService.compare(n1.start, n2.start, false);
    });
    return state;
  }
}


