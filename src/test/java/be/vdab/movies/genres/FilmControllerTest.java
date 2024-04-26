package be.vdab.movies.genres;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Sql ({"/genre.sql", "/films.sql"})
@AutoConfigureMockMvc
class FilmControllerTest {
    private static final String GENRES_TABLE = "genres";
    private static final String FILMS_TABLE = "films";
    private final JdbcClient jdbcClient;
    private final MockMvc mockMvc;

    FilmControllerTest(JdbcClient jdbcClient, MockMvc mockMvc) {
        this.jdbcClient = jdbcClient;
        this.mockMvc = mockMvc;
    }

    long idVanGenreTest1() {
        return jdbcClient.sql("select id from genres where naam = 'test1'")
                .query(Long.class)
                .single();
    }
    @Test
    void findFilmsVanGenreVindtJuisteFilms() throws Exception {
        long idGenreTest1 = idVanGenreTest1();
        int aantalFilmsGevonden = JdbcTestUtils.countRowsInTableWhere(
                jdbcClient, FILMS_TABLE, "genreId = " + idGenreTest1);
        mockMvc.perform(get("/genres/{id}/films", idGenreTest1))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()").value(aantalFilmsGevonden));
    }
}
