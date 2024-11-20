import { Component } from '@angular/core';
import { AppService } from '../../api/AppService';
import { Router } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { GenreFormComponent } from "../../components/genre-form/genre-form.component";
import { catchError, EMPTY, tap } from 'rxjs';

@Component({
  selector: 'app-add-genre-view',
  standalone: true,
  imports: [GenreFormComponent],
  templateUrl: './add-genre-view.component.html',
  styleUrl: './add-genre-view.component.css'
})
export class AddGenreViewComponent {
    constructor(private service: AppService, private router: Router) {}

    message: string = '';

    onSubmit(genre: GameGenre): void {
        this.message = '';
        if (!genre.name) {
            return;
        }
        this.service.createGenre(genre)
            .pipe(
                catchError((error) => {
                    this.message = error.statusText;
                    return EMPTY;
                }),
                tap((genre: GameGenre) => {
                    this.router.navigate(['/genres', genre.id]);
                })
            )
        .subscribe();
    }
}
