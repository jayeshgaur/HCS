import {Component, OnInit} from '@angular/core';
import { CenterModel } from '../_model/app.centermodel';
import {HcsService} from '../_service/app.hcsservice';
import { TestModel } from '../_model/app.testmodel';
import {AppointmentModel} from '../_model/app.appointmentmodel';

@Component({
    selector: 'addappointment',
    templateUrl: '../_html/app.addappointment.html'
})
export class AddAppointmentComponent implements OnInit{
    
    centerList:CenterModel[]=[];
    testList:TestModel[]=[];
    appointment:any={ centerId:"", testId:"", dateAndTime:"", userId:""};
    centerId:any;
    testId:any;
    dateAndTime:string;
    userId:any;

    constructor(private service:HcsService){    
    }
    
    ngOnInit(){
        this.centerId=null;
        this.testId=null;
        this.testList=null;
        this.service.getCenters().subscribe((centerList:CenterModel[]) => this.centerList = centerList);
    }

    selectCenter(center:CenterModel){
        if(this.centerId == null)
        {
            this.centerId=center.centerId;
            this.centerList=[];
            this.centerList.push(center);
            this.service.getTests(center.centerId).subscribe((testList:TestModel[]) => this.testList = testList);
        }   
    }

    changeCenter(){
        this.ngOnInit();
    }

    selectTest(test:TestModel){
        this.testId=test.testId;
        this.testList=[];
        this.testList.push(test);
    }

    changeTest(){
        this.testId = null;
        this.service.getTests(this.centerId).subscribe((testList:TestModel[]) => this.testList = testList);
    }

    confirmAppointment(){
        this.appointment.centerId = this.centerId;
        this.appointment.testId = this.testId;
        this.appointment.dateAndTime = this.dateAndTime;
        this.appointment.userId = this.userId;
        this.service.addAppointment(this.appointment).subscribe(
            (data:AppointmentModel)=>alert("Appointment booked successfully"),
            error => alert(error.error)
            );
    }


}