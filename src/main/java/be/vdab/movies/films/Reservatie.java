package be.vdab.movies.films;

import java.time.LocalDateTime;

class Reservatie {
    private final long klantId;
    private final long filmId;
    private final LocalDateTime reservatie;

    Reservatie(long klantId, long filmId, LocalDateTime reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public long getKlantId() {
        return klantId;
    }

    public long getFilmId() {
        return filmId;
    }

    public LocalDateTime getReservatie() {
        return reservatie;
    }
}
