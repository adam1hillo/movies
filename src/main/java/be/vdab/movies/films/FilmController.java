package be.vdab.movies.films;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
class FilmController {
    private final FilmService filmService;

    FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping("genres/{id}/films")
    Stream<IdTitel> findFilmsVanGenre(@PathVariable long id) {
        return filmService.findByGenreId(id)
                .stream()
                .map(IdTitel::new);
    }

    private record IdTitel(long id, String titel) {
        IdTitel(Film film) {
            this(film.getId(), film.getTitel());
        }
    }
}
