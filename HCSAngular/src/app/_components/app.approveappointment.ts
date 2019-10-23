import {Component, OnInit} from '@angular/core';
import { HcsService } from '../_service/app.hcsservice';
import { CenterModel } from '../_model/app.centermodel';
import { AppointmentModel } from '../_model/app.appointmentmodel';
import { Router } from '@angular/router';

@Component({
    selector: 'approveappointment',
    templateUrl:'../_html/app.approveappointment.html'
})

export class ApproveAppointmentComponent implements OnInit{
    centerList:CenterModel[]=[];
    centerId:any;
    appointmentList:AppointmentModel[]=null;
    appointmentId:any;

    constructor(private service:HcsService, private router:Router){    
    }

    ngOnInit()  {
        if(!(sessionStorage.getItem('userRole') === "ROLE_Admin")){
            this.router.navigate(['forbidden']);
        }
        this.centerId=null;
        this.appointmentList=null;
        this.appointmentId=null;
        this.service.getCenters().subscribe((centerList:CenterModel[]) => this.centerList = centerList);
    }

    selectCenter(center:CenterModel){
        if(this.centerId == null)
        {
            this.centerId=center.centerId;
            this.centerList=[];
            this.centerList.push(center);
            this.service.getAppointments(center.centerId).subscribe((appointmentList:AppointmentModel[]) => this.appointmentList = appointmentList);
        }   
    }

    changeCenter(){
        this.ngOnInit();
    }

    approve(appointment:AppointmentModel){
        this.appointmentId=appointment.appointmentId;
        this.appointmentList = [];
        this.appointmentList.push(appointment);
    }

    confirmApprove(){
        this.service.approveAppointment(this.appointmentId).subscribe(
         (data:boolean) => {alert("Approved Successfully.");
         this.service.getAppointments(this.centerId).subscribe((appointmentList:AppointmentModel[]) => this.appointmentList = appointmentList);
        }
     //    ,error => alert(error.error)
         ,error => alert("Please refresh the page.")
        );
        this.appointmentId=null;
        this.appointmentList = [];
        this.service.getAppointments(this.centerId).subscribe((appointmentList:AppointmentModel[]) => this.appointmentList = appointmentList);
    }

}