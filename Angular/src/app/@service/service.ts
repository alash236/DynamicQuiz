import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Service {

  private checkLogin = new BehaviorSubject<any>(null);
  public $checkLogin = this.checkLogin.asObservable();

  setCheckLogin(data:boolean){
    this.checkLogin.next(data);
  }

  private userDateSource = new BehaviorSubject<any>(null);
  public $userDataSource = this.userDateSource.asObservable();
  setUserData(data:any){
    this.userDateSource.next(data);
  }


  private questionDateSource = new BehaviorSubject<any>(null);
  public $questionDataSource = this.questionDateSource.asObservable();

  setQuestionData(data:any){
    this.questionDateSource.next(data);
  }

  private feedbackData = new BehaviorSubject<any>(null);
  public $feedbackData = this.feedbackData.asObservable();

  setFeedBackData(data:any){
    this.feedbackData.next(data);
  }

  private updateDateSource = new BehaviorSubject<any>(null);
  public $updateDataSource = this.updateDateSource.asObservable();

  setUpdateData(data:any){
    this.updateDateSource.next(data);
  }

}
