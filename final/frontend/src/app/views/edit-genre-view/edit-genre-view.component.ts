import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, Router } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { GenreFormComponent } from "../../components/genre-form/genre-form.component";
import { catchError, EMPTY, tap } from 'rxjs';

@Component({
  selector: 'app-edit-genre-view',
  standalone: true,
  imports: [GenreFormComponent],
  templateUrl: './edit-genre-view.component.html',
  styleUrl: './edit-genre-view.component.css'
})
export class EditGenreViewComponent implements OnInit {
    constructor(
        private appService: AppService,
        private route: ActivatedRoute,
        private router: Router
      ) {}

      genre: GameGenre | undefined;
      message: string = '';

      ngOnInit(): void {
        this.route.params.subscribe((params) => {
          this.appService.getGenreById(params['id'])
            .pipe(
                catchError((error) => {
                    this.message = error.statusText;
                    return EMPTY;
                }),
                tap((category: GameGenre) => {
                    this.genre = category;
                })
            )
          .subscribe();
        });
      }

      onSubmit(): void {
        this.message = '';
        if (this.genre === undefined) {
          return;
        }
        this.appService.updateGenre(this.genre)
            .pipe(
                catchError((error) => {
                    this.message = error.statusText;
                    return EMPTY;
                }),
                tap((category: GameGenre) => {
                    this.router.navigate(['/categories', category.id]);
                })
            )
        .subscribe();
      }
}
