package be.vdab.movies.films;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class FilmService {
    private final FilmRepository filmRepository;

    FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
    List<Film> findByGenreId(long genreId) {
        return filmRepository.findByGenreId(genreId);
    }
}
