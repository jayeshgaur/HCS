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


    constructor(private service:HcsService){

    }

    ngOnInit(){
        this.getList();
    }

    getList(){   
        this.service.getUserAppointments(sessionStorage.getItem('userId')).subscribe(
            (appointmentList:AppointmentModel[]) => 
            (this.appointmentList = appointmentList),
            error => alert(error.error)
            );
    }

    downloadExcel(){
      this.service.download(sessionStorage.getItem('userId')).subscribe(
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