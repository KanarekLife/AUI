import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GameGenre } from '../../api/models/GameGenre.model';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-genre-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './genre-form.component.html',
  styleUrl: './genre-form.component.css'
})
export class GenreFormComponent {
    @Input() genre: GameGenre = {
        id: '',
        name: '',
        supportsMultiplayer: false,
    };
    @Output() submit = new EventEmitter<GameGenre>();
    @Input() message: string = '';

    onSubmit(): void {
        if (this.genre) {
            this.submit.emit(this.genre);
        }
    }
}
