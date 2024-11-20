import { Component, OnInit } from '@angular/core';
import { Game } from '../../api/models/Game.model';
import { GameGenre } from '../../api/models/GameGenre.model';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { AppService } from '../../api/AppService';
import { catchError, EMPTY, tap } from 'rxjs';

@Component({
  selector: 'app-genre-details-view',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './genre-details-view.component.html',
  styleUrl: './genre-details-view.component.css'
})
export class GenreDetailsViewComponent implements OnInit {
    constructor(
        private appService: AppService,
        private route: ActivatedRoute,
    ) {}

    message: string = '';
    genre: GameGenre | undefined;
    games: Game[] | undefined;

    ngOnInit(): void {
        this.route.params.subscribe((params) => {
            this.appService.getGenreById(params['id'])
            .pipe(
                catchError((error) => {
                    this.message = error.statusText;
                    return EMPTY;
                }),
                tap((genre) => {
                    this.genre = genre;
                    this.fetchGames();
                })
            )
            .subscribe();
        })
    }

    fetchGames(): void {
        if (this.genre) {
            this.appService.getGamesByGenreId(this.genre.id).subscribe({
                next: (games) => {
                    this.games = games;
                },
                error: (error) => {
                    this.message = error.error.message;
                }
            });
        }
    }

    deleteGame(gameId: string): void {
        if (this.genre) {
            this.appService.deleteGame(gameId).subscribe({
                next: () => {
                    this.fetchGames();
                },
                error: (error) => {
                    this.message = error.error.message;
                }
            });
        }
    }
}
