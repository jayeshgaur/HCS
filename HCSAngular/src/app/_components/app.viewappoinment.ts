import {Component, OnInit} from '@angular/core';
import { HcsService } from '../_service/app.hcsservice';
import { AppointmentModel } from '../_model/app.appointmentmodel';

@Component({
    selector: 'viewappointment',
    templateUrl: '../_html/app.viewappointment.html'
})

export class ViewAppointment implements OnInit{

    appointmentList:AppointmentModel[]=[];
    userId:any;

    constructor(private service:HcsService){

    }

    ngOnInit(){
        this.appointmentList=null;
    }

    getList(){   
        this.service.getUserAppointments(this.userId).subscribe(
            (appointmentList:AppointmentModel[]) => 
            (this.appointmentList = appointmentList),
            error => alert(error.error)
            );
    }
}