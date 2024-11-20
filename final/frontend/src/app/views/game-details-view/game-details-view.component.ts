import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { Game } from '../../api/models/Game.model';
import { catchError, EMPTY, forkJoin, tap } from 'rxjs';

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
            this.appService.getGameById(params['gameId'])
                .pipe(
                    catchError(error => {
                        this.message = `Game: ${error.statusText}`;
                        return EMPTY;
                    }),
                    tap((game) => {
                        this.game = game;
                    })
                )
                .subscribe();
            this.appService.getGenreById(params['id'])
                .pipe(
                    catchError(error => {
                        this.message = `Genre: ${error.statusText}`;
                        return EMPTY;
                    }),
                    tap((genre) => {
                        this.genre = genre;
                    })
                )
                .subscribe();
        });
    }
}
