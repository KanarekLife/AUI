import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { GameGenre } from "./models/GameGenre.model";
import { Game } from "./models/Game.model";

@Injectable({ providedIn: 'root' })
export class AppService {
    constructor(private httpClient: HttpClient) {}

    getAllGenres(): Observable<GameGenre[]> {
        return this.httpClient.get<GameGenre[]>('/api/genres/');
    }

    getGenreById(id: string): Observable<GameGenre> {
        return this.httpClient.get<GameGenre>(`/api/genres/${id}/`);
    }

    createGenre(genre: GameGenre): Observable<GameGenre> {
        return this.httpClient.post<GameGenre>('/api/genres/', genre);
    }

    updateGenre(genre: GameGenre): Observable<GameGenre> {
        return this.httpClient.put<GameGenre>(`/api/genres/${genre.id}/`, genre);
    }

    deleteGenre(id: string): Observable<void> {
        return this.httpClient.delete<void>(`/api/genres/${id}/`);
    }

    getAllGames(): Observable<Game[]> {
        return this.httpClient.get<Game[]>('/api/games/');
    }

    getGameById(id: string): Observable<Game> {
        return this.httpClient.get<Game>(`/api/games/${id}/`);
    }

    getGamesByGenreId(genreId: string): Observable<Game[]> {
        return this.httpClient.get<Game[]>(`/api/genres/${genreId}/games/`);
    }

    createGame(genreId: string, game: Game): Observable<Game> {
        return this.httpClient.post<Game>(`/api/genres/${genreId}/games/`, game);
    }

    updateGame(game: Game): Observable<Game> {
        return this.httpClient.put<Game>(`/api/games/${game.id}/`, game);
    }

    deleteGame(id: string): Observable<void> {
        return this.httpClient.delete<void>(`/api/games/${id}/`);
    }
}
