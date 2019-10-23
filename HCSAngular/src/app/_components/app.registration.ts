import { Component, OnInit } from "@angular/core";
import { HcsService } from "../_service/app.hcsservice";

@Component({
    selector:'register',
    templateUrl:'../_html/app.registration.html'


})
export class RegistrationComponent implements OnInit
{

    constructor(private service:HcsService){
        
    }

    ngOnInit(){
    }
    model:any={};
    
}