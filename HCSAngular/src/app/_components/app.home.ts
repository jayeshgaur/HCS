import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'home',
    templateUrl:'../_html/app.home.html'
})
export class HomeComponent implements OnInit{
    constructor()
    {console.log("DEBUG");
}
    
    ngOnInit() {
    }

}