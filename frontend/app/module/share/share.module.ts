import { NgModule } from "@angular/core";
import { BackToHomeDirective } from "src/app/directive/back.directive";

@NgModule({
    declarations: [ BackToHomeDirective ],
    exports: [ BackToHomeDirective ]
  })
  
  export class SharedModule {}
  