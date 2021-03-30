import { Directive, HostListener } from '@angular/core';
import { Router } from '@angular/router';

@Directive({
    selector: '[click-back-home]',
})
export class BackToHomeDirective { 

    constructor(private router : Router){}
    @HostListener('click')
    BackToHomeDirective(){
        this.router.navigate(['/core/home'])
    }
}