import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Game } from '../../api/models/Game.model';

@Component({
  selector: 'app-game-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './game-form.component.html',
  styleUrl: './game-form.component.css'
})
export class GameFormComponent {
    @Input() game: Game = {
        id: '',
        name: '',
        minimalPlayerAge: 0,
        genreId: ''
    }
    @Input() message: string = '';
    @Output() submit = new EventEmitter<Game>();

    onSubmit(): void {
        if (this.game) {
            this.submit.emit(this.game);
        }
    }
}
