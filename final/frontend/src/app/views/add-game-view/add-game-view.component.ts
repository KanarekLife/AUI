import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, Router } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { Game } from '../../api/models/Game.model';
import { GameFormComponent } from "../../components/game-form/game-form.component";

@Component({
  selector: 'app-add-game-view',
  standalone: true,
  imports: [GameFormComponent],
  templateUrl: './add-game-view.component.html',
  styleUrl: './add-game-view.component.css'
})
export class AddGameViewComponent implements OnInit {
    constructor(
        private appService: AppService,
        private route: ActivatedRoute,
        private router: Router
    ) {}

    message: string = '';
    genre: GameGenre | undefined;

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.appService.getGenreById(params['id']).subscribe({
                next: (genre: GameGenre) => {
                    this.genre = genre;
                },
                error: (error) => {
                    this.message = error.error.message;
                }
            });
        })
    }

    onSubmit(game: Game): void {
        this.message = '';
        if (!this.genre || !game.name) {
            return;
        }
        this.appService.createGame(this.genre.id, game).subscribe({
            next: (game: Game) => {
                this.router.navigate(['/genres', this.genre?.id, 'games', game.id]);
            },
            error: (error) => {
                this.message = error.error.message;
            }
        })
    }
}
