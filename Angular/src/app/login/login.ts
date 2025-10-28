import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {  Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import { HttpClientService } from '../@service/HttpClientService';
import { Service } from '../@service/service';

@Component({
  selector: 'app-login',
  imports: [FormsModule,MatIconModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  constructor(private service:Service,private router:Router,private http:HttpClientService){}

  userEmail:string="";
  userPassword:string="";
  changetype=false;

  submitForm() {
    if(this.userEmail=="admin" && this.userPassword=="admin"){
      alert("歡迎你Administrator!!!");
      this.router.navigate(['/back'])
    }else{
      if(this.userEmail.trim() === "" || this.userEmail.trim() === null){
        alert("電子信箱不能為空!!!")
        return;
      }

      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if(!regex.test(this.userEmail)){
        alert("電子信箱格式錯誤 請重新輸入!!!");
        this.clear();
        return;
      }

      if(this.userPassword.trim() === "" || this.userPassword.trim() === null){
        alert("密碼不能為空 請重新輸入!!!")
        return;
      }
      const Passwordregex =  /^.{8,16}$/;
      if(!Passwordregex.test(this.userPassword)){
        alert("密碼格式需要8~16個字 請重新輸入!!!")
        this.clear();
        return
      }

      let user = {
        email: this.userEmail,
        password: this.userPassword
      };
      this.http.postApi("http://localhost:8080/login",user).subscribe((res:any) =>{
        if(res.code===200){
          alert(JSON.stringify(res.message).replaceAll('"',""))
          this.service.setUserData(res.user)
          this.service.setCheckLogin(true);
          this.router.navigate(['/front'])
        }else{
          alert(JSON.stringify(res.message).replaceAll('"',""))
          this.clear();
        }
      });
    }
  }

  clear(){
    this.userEmail = "";
    this.userPassword = "";
  }

  changeType(){
    this.changetype = !this.changetype;
  }

  toggleSignUp(){
    this.router.navigate(['/register']);
  }
}
