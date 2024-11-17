import { Routes } from '@angular/router';
import { GenresListViewComponent } from './views/genres-list-view/genres-list-view.component';
import { AddGenreViewComponent } from './views/add-genre-view/add-genre-view.component';
import { GenreDetailsViewComponent } from './views/genre-details-view/genre-details-view.component';
import { EditGenreViewComponent } from './views/edit-genre-view/edit-genre-view.component';
import { AddGameViewComponent } from './views/add-game-view/add-game-view.component';
import { GameDetailsViewComponent } from './views/game-details-view/game-details-view.component';
import { EditGameViewComponent } from './views/edit-game-view/edit-game-view.component';

export const routes: Routes = [
    {
        component: GenresListViewComponent,
        path: 'genres'
    },
    {
        component: AddGenreViewComponent,
        path: 'genres/add'
    },
    {
        component: GenreDetailsViewComponent,
        path: 'genres/:id'
    },
    {
        component: EditGenreViewComponent,
        path: 'genres/:id/edit'
    },
    {
        component: AddGameViewComponent,
        path: 'genres/:id/games/add'
    },
    {
        component: GameDetailsViewComponent,
        path: 'genres/:id/games/:gameId'
    },
    {
        component: EditGameViewComponent,
        path: 'genres/:id/games/:gameId/edit'
    },
    {
        component: GenresListViewComponent,
        path: '**'
    }
];
