import { Component, OnInit } from '@angular/core';
import { AppService } from '../../api/AppService';
import { GameGenre } from '../../api/models/GameGenre.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-genres-list-view',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './genres-list-view.component.html',
  styleUrl: './genres-list-view.component.css'
})
export class GenresListViewComponent implements OnInit {
    constructor(private service: AppService) {}

    genres: GameGenre[] | undefined;

    ngOnInit(): void {
        this.fetchGenres();
    }

    fetchGenres(): void {
        this.service.getAllGenres().subscribe(genres => {
            this.genres = genres;
        });
    }

    deleteGenre(id: string): void {
        this.service.deleteGenre(id).subscribe(() => {
            this.genres = this.genres?.filter(genre => genre.id !== id);
        });
    }
}
