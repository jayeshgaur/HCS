import {HcsService} from '../_service/app.hcsservice';

import { Component } from "@angular/core";
@Component({
    selector:'addcenter',
    templateUrl:'../_html/app.addcenter.html'
})
export class AddCenterComponent
{
    model:any={};
    msg: boolean = false;
    constructor(private service:HcsService)
    {

    }
    addCenter():any
    {
        this.service.addCenter(this.model).subscribe();
        this.msg=true;
    }
}