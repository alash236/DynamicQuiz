import {Component} from '@angular/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table'
import { ActivatedRoute, Router } from '@angular/router';
import { Service } from './../@service/service';
import { HttpClientService } from '../@service/HttpClientService';
import { format } from 'date-fns';


@Component({
  selector: 'app-feedback',
  imports: [MatTableModule, MatPaginatorModule],
  templateUrl: './feedback.html',
  styleUrl: './feedback.scss'
})


export class Feedback {
  FeedBackData:any[]=[];
  idList:number[]=[];
  datas:any[]=[];
  question_ID!:number;
  constructor(
    private service:Service,
    private router:Router,
    private route:ActivatedRoute,
    private http:HttpClientService){}

  ngOnInit(): void {
    this.question_ID = Number(this.route.snapshot.paramMap.get('id'));

    this.http.getApi(`http://localhost:8080/search/Singlefeedback/${this.question_ID}`).subscribe((res:any)=>{
      this.FeedBackData = res.allFeedBack

      this.FeedBackData = this.FeedBackData.map((item,index)=>{
         item.writetime = format(new Date(item.writetime),"yyyy-MM-dd HH:mm:ss")
         return item
      })
    })

  }
  go_pre(index:number){
    let data = {
      question_id:this.FeedBackData[index].question_id,
      email:this.FeedBackData[index].email
    }
    this.service.setFeedBackData(data);
    this.router.navigate(['/preview',this.FeedBackData[index].question_id],{queryParams:{backcheck: true}})
  }

  go_back(){
    this.router.navigate(['/back'])
  }

  go_statistics(){
    this.router.navigate(['/singlestatistics',this.question_ID]);
  }
}

