import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-ok-dialog',
  templateUrl: './ok-dialog.component.html',
  styleUrl: './ok-dialog.component.css'
})
export class OkDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<OkDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: MessageDialogData,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  close(): void {
    this.dialogRef.close();
  }

}

export interface MessageDialogData {
  dialogMessage: string;
}
