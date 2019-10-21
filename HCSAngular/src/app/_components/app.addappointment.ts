import {Component, OnInit} from '@angular/core';
import { CenterModel } from '../_model/app.centermodel';
import {HcsService} from '../_service/app.hcsservice';
import { TestModel } from '../_model/app.testmodel';
import {AppointmentModel} from '../_model/app.appointmentmodel';
import {Router} from "@angular/router"

@Component({
    selector: 'addappointment',
    templateUrl: '../_html/app.addappointment.html'
})
export class AddAppointmentComponent implements OnInit{
    // p: number = 1;
    centerList:CenterModel[]=[];
    testList:TestModel[]=[];
    appointment:any={ centerId:"", testId:"", dateAndTime:"", userId:""};
    centerId:any;
    testId:any;
    dateAndTime:string;
    userId:any;
    errorMessage:any;

    constructor(private service:HcsService, private router:Router){    
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
            (data:any)=>{alert("Appointment booked successfully");
            this.router.navigate(['/userhome'])},
            error => this.errorMessage= error.error
            );
    }


}