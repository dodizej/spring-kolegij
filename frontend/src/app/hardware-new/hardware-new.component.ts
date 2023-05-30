import {Component} from '@angular/core';
import {Hardware} from "../hardware";
import {HardwareService} from "../hardware.service";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-hardware-new',
  templateUrl: './hardware-new.component.html',
  styleUrls: ['./hardware-new.component.css']
})
export class HardwareNewComponent {
  constructor(
    private hardwareService: HardwareService,
    private router: Router,
    private location: Location
  ) {
  }

  add(code: string, name: string, type: string, numberOfComponents: number, price: number): void {
    code = code.trim();
    name = name.trim();
    type = type.trim();
    if (!code || !name || !type) {
      return;
    }

    this.hardwareService.addHardware({code, name, type, numberOfComponents, price} as Hardware)
      .subscribe(
        () => {
          this.router.navigate(['/hardwares']).then();
        }
      );
  }

  goBack(): void {
    this.location.back();
  }

}
