import {HcsService} from '../_service/app.hcsservice';
import {Router} from "@angular/router"
import { Component } from "@angular/core";

@Component({
    selector:'addcenter',
    templateUrl:'../_html/app.addcenter.html'
})
export class AddCenterComponent
{
    model:any={};
    msg: boolean = false;
    errorMessage:any;
    constructor(private service:HcsService,private router:Router)
    {
        
    }
   
    addCenter():any
    {
        this.service.addCenter(this.model).subscribe((data:any)=>{alert("Center added successfully");
    },error => this.errorMessage= error.error
        
    );
    }
}
