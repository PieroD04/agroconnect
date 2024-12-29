import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import { MatButton } from "@angular/material/button";
import { MatTableModule, MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatIcon } from "@angular/material/icon";
@Component({
  selector: 'cage-table',
  standalone: true,
  imports: [
    MatButton,
    MatTableModule,
    MatPaginator,
    MatIcon
  ],
  templateUrl: './cage-table.component.html',
  styleUrl: './cage-table.component.css'
})
export class CageTableComponent {
  @Input() dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  displayedColumns: string[] = ['id', 'name', 'size', 'observations', 'actions'];
  @Output() editCage = new EventEmitter<number>();
  @Output() deleteCage = new EventEmitter<number>();
  @Output() goToCage = new EventEmitter<number>();

}
