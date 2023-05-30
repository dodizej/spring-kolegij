import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {Review} from "./review";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private reviewsUrl = 'http://localhost:8080/review';

  constructor(private http: HttpClient) { }

  getReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.reviewsUrl)
      .pipe(
        tap(_ => console.log('fetched reviews')),
        catchError(this.handleError<Review[]>('getReviews', []))
      );
  }

  getReviewsByHardwareCode(hardwareCode: string): Observable<Review[]> {
    return this.http.get<Review[]>(this.reviewsUrl + "/" + hardwareCode)
      .pipe(
        tap(_ => console.log('fetched reviews hardwareCode=${hardwareCode}')),
        catchError(this.handleError<Review[]>('getReviewsByHardwareCode hardwareCode=${hardwareCode}', []))
      );
  }

  // tslint:disable-next-line:typedef
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    }
  }
}
