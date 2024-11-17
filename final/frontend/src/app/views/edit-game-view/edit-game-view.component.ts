import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, Router } from '@angular/router';
import { Game } from '../../api/models/Game.model';
import { GameGenre } from '../../api/models/GameGenre.model';
import { GameFormComponent } from "../../components/game-form/game-form.component";

@Component({
  selector: 'app-edit-game-view',
  standalone: true,
  imports: [GameFormComponent],
  templateUrl: './edit-game-view.component.html',
  styleUrl: './edit-game-view.component.css'
})
export class EditGameViewComponent implements OnInit {
    constructor(
        private appService: AppService,
        private route: ActivatedRoute,
        private router: Router
      ) {}

      game: Game | undefined;
      genre: GameGenre | undefined;
      message: string = '';

      ngOnInit(): void {
        this.route.params.subscribe((params) => {
          this.appService.getGameById(params['gameId']).subscribe({
            next: (product) => {
              this.game = product;
            },
            error: (error) => {
              this.message = error.error.message;
            },
          });
          this.appService.getGenreById(params['id']).subscribe({
            next: (category) => {
              this.genre = category;
            },
            error: (error) => {
              this.message = error.error.message;
            },
          });
        });
      }

      onSubmit(): void {
        this.message = '';
        if (this.game) {
          this.appService.updateGame(this.game).subscribe({
            next: (product) => {
              this.router.navigate([
                '/genres',
                this.genre?.id,
                'games',
                product.id,
              ]);
            },
            error: (error) => {
              this.message = error.error.message;
            },
          });
        }
      }
}
