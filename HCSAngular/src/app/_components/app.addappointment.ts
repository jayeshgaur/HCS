import {Component, OnInit} from '@angular/core';
import { CenterModel } from '../_model/app.centermodel';
import {HcsService} from '../_service/app.hcsservice';
import { TestModel } from '../_model/app.testmodel';
@Component({
    selector: 'addappointment',
    templateUrl: '../_html/app.addappointment.html'
})
export class AddAppointmentComponent implements OnInit{
    
    centerList:CenterModel[]=[];
    testList:TestModel[]=[];

    constructor(private service:HcsService){    
    }
    
    ngOnInit(){
        this.service.getCenters().subscribe((centerList:CenterModel[]) => this.centerList = centerList);
    }

}