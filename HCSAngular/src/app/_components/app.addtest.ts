import { Component, OnInit } from "@angular/core";
import { text } from "@angular/core/src/render3/instructions";
import { HcsService } from "../_service/app.hcsservice";
import { CenterModel } from "../_model/app.centermodel";
import { TestModel } from "../_model/app.testmodel";

@Component({
    selector:'addtest',
    templateUrl:'../_html/app.addtest.html'


})

export class AddTestComponent implements OnInit
{
    centerList:CenterModel[]=[];
    model:TestModel={testId:null,testName:null};
    errorMessage: any;
    

    constructor(private service:HcsService){
        
    }
    ngOnInit(): void {

        this.service.getCenters().subscribe((centerListS:CenterModel[]) => this.centerList = centerListS);
        console.log(this.centerList);
    }
    addTest(centerId:any):any
    {
         //console.log(this.model.testName)
        //alert("centerId"+centerId)
       // alert(centerId);
       if(centerId!=undefined && centerId!=null && this.model.testName!=null)
        this.service.addTest(centerId,this.model).subscribe((data:any)=>{alert("Test added successfully");
    
        
    },error => this.errorMessage= error.error);
       
    }
    

   
}