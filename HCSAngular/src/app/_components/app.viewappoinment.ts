import {Component, OnInit} from '@angular/core';
import { HcsService } from '../_service/app.hcsservice';
import { AppointmentModel } from '../_model/app.appointmentmodel';
import { saveAs } from 'file-saver';


@Component({
    selector: 'viewappointment',
    templateUrl: '../_html/app.viewappointment.html'
})

export class ViewAppointment implements OnInit{

    appointmentList:AppointmentModel[]=[];
    userId:any;
    blockedDocument = true;

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

    downloadExcel(){
      this.service.download(this.userId).subscribe(
        response => {
          var blob = new Blob([response], {type: 'application/xlsx'});
          console.log(blob.size);
          var filename = 'appointments.xlsx';
          saveAs(blob,filename);        
        } ,
      error=>{
        alert("Some error");
      }
      );
    }
    


}