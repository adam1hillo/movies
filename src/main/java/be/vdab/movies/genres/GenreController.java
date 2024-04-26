package be.vdab.movies.genres;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class GenreController {
    private final GenreService genreService;

    GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("genres")
    List<Genre> findAll() {
        return genreService.findAll();
    }
}
