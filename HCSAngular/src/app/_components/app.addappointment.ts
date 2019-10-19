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
    centerId:any;

    constructor(private service:HcsService){    
    }
    
    ngOnInit(){
        this.centerId=null;
        this.testList=[];
        this.service.getCenters().subscribe((centerList:CenterModel[]) => this.centerList = centerList);
    }

    selectCenter(center:CenterModel){
        if(this.centerId == null)
        {
            this.centerId=center.centerId;
            this.centerList=[];
            this.centerList.push(center);
            this.service.getTests(center).subscribe((testList:TestModel[]) => this.testList = testList);
        }
        
    }

    changeCenter(){
        this.ngOnInit();
    }

}