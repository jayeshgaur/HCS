import { Component, OnInit } from "@angular/core";
import { HcsService } from "../_service/app.hcsservice";
import { Router } from '@angular/router';

@Component({
    selector:'login',
    templateUrl:'../_html/app.login.html'
})

export class LoginComponent implements OnInit {
    model:any=[];
    useremail:any;
    password:any;
    invalidLogin = false;
  
    constructor(private router: Router,
      private hcsservice:HcsService) { }
  
    ngOnInit() {
    }
  
    checkLogin() {
      console.log("Inside login.ts checkLogin.. email: "+this.useremail+" password: "+this.password)
      if (this.hcsservice.authenticate(this.useremail, this.password)
) {
      //  this.router.navigate([''])
        this.invalidLogin = false
      } else
        this.invalidLogin = true
    }
  
  }