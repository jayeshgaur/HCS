import {Component, OnInit} from '@angular/core';
import { HcsService } from '../_service/app.hcsservice';
import { AppointmentModel } from '../_model/app.appointmentmodel';
import { ResponseContentType } from '@angular/http';
import {Router} from '@angular/router'

@Component({
    selector: 'viewappointment',
    templateUrl: '../_html/app.viewappointment.html'
})

export class ViewAppointment implements OnInit{

    appointmentList:AppointmentModel[]=[];
    userId:any;
    blockedDocument = true;
    fileName="Appointments.xlsx";

    constructor(private service:HcsService, private router:Router){

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

     

        // this.service.downloadExcel(this.userId).subscribe(

        //   (resultBlob: Blob) => {
        //     alert("AAB");
        //     var downloadURL = URL.createObjectURL(resultBlob);
        //     alert(downloadURL);
        //     window.open(downloadURL);}
        
          // (success: any) => {
          //   var blob = new Blob([success._body], { type: 'application/vnd.ms-excel' });
       
          //   if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          //     window.navigator.msSaveOrOpenBlob(blob, this.fileName);
          //     this.blockedDocument = false;
          //   } else {
          //     var a = document.createElement('a');
          //     a.href = URL.createObjectURL(blob);
          //     a.download = this.fileName;
          //     document.body.appendChild(a);
          //     a.click();
          //     document.body.removeChild(a);
          //     this.blockedDocument = false;
          //   }
          // },
          // err => {
          //   alert("Error while downloading. File Not Found on the Server");
          //   this.blockedDocument = false;
          // }
      //  );
        alert("AAA");
    }
}