package be.vdab.movies.films;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class FilmRepository {
    private final JdbcClient jdbcClient;

    FilmRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Film> findByGenreId(long genreId) {
        String sql = """
                select id, genreId, titel, voorraad, gereserveerd, prijs
                from films
                where genreId = ?
                order by titel
                """;
        return jdbcClient.sql(sql)
                .param(genreId)
                .query(Film.class)
                .list();
    }
}
