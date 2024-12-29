import {Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {CommonModule, Location} from "@angular/common";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ResourceApiService} from "../../services/resource-api.service";
import {MatIcon} from "@angular/material/icon";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-edit-resource',
  standalone: true,
    imports: [
        MatButtonModule,
        MatFormField,
        MatInput,
        MatLabel,
        MatRadioButton,
        MatRadioGroup,
        ReactiveFormsModule,
        FormsModule,
        CommonModule,
        RouterLink,
        MatIcon
    ],
  templateUrl: './edit-resource.component.html',
  styleUrl: './edit-resource.component.css'
})
export class EditResourceComponent implements OnInit{
  resource: any = {
    name: '',
    type: '',
    quantity: '',
    date: '',
    observations: ''
  };
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private resourceApiService: ResourceApiService,
    private location: Location,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadResource(id);
    }
  }

  private loadResource(id: string) {
    this.resourceApiService.getOne(id).subscribe((resource: any) => {
      this.resource = resource;
    }, error => {
      console.error('Error loading resource', error);
    });
  }

  handleClick(): void {
    console.log(this.resource);
    this.resourceApiService.update(this.resource.id, this.resource).subscribe(() => {
      this.snackBar.open('Recurso actualizado con éxito😎', 'Cerrar', {
        duration: 2000,
      }).afterDismissed().subscribe(() => {
        this.router.navigate(['/criador/mi-granja/recursos']);
      })
    }, error => {
      this.snackBar.open('Error al actualizar el recurso😥', 'Cerrar', {
        duration: 2000,
      });
    });
  }

  goBack(): void {
    this.location.back();
  }
}
