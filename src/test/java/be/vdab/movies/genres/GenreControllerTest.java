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
@Sql("/genre.sql")
@AutoConfigureMockMvc
class GenreControllerTest {
    private static final String GENRES_TABLE = "genres";
    private final JdbcClient jdbcClient;
    private final MockMvc mockMvc;

    GenreControllerTest(JdbcClient jdbcClient, MockMvc mockMvc) {
        this.jdbcClient = jdbcClient;
        this.mockMvc = mockMvc;
    }

    @Test
    void findAllVindtAlleGenres() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()").value(
                                JdbcTestUtils.countRowsInTable(jdbcClient,GENRES_TABLE)));
    }
}
