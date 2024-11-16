import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { Game } from '../../api/models/Game.model';

@Component({
  selector: 'app-game-details-view',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './game-details-view.component.html',
  styleUrl: './game-details-view.component.css'
})
export class GameDetailsViewComponent implements OnInit {
    constructor(
        private appService: AppService,
        private route: ActivatedRoute
    ) {}

    message: string = '';
    genre: GameGenre | undefined;
    game: Game | undefined;

    ngOnInit(): void {
        this.route.params.subscribe((params) => {
            this.appService.getGameById(params['gameId']).subscribe({
                next: (game) => {
                    this.game = game;
                },
                error: (error) => {
                    this.message = error.error.message;
                },
            });
            this.appService.getGenreById(params['id']).subscribe({
                next: (genre) => {
                    this.genre = genre;
                },
                error: (error) => {
                    this.message = error.error.message;
                },
            });
        });
    }
}
