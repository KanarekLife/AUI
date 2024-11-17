import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { ActivatedRoute, Router } from '@angular/router';
import { GameGenre } from '../../api/models/GameGenre.model';
import { GenreFormComponent } from "../../components/genre-form/genre-form.component";

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
          this.appService.getGenreById(params['id']).subscribe({
            next: (category: GameGenre) => {
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
        if (this.genre === undefined) {
          return;
        }
        this.appService.updateGenre(this.genre).subscribe({
          next: (category: GameGenre) => {
            this.router.navigate(['/categories', category.id]);
          },
          error: (error) => {
            this.message = error.error.message;
          },
        });
      }
}
