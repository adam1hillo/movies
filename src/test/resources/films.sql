insert into films (genreId, titel, voorraad, gereserveerd, prijs)
values ((select id from genres where naam like 'test1'), 'test1', 5, 0, 5),
       ((select id from genres where naam like 'test2'), 'test2', 0, 5, 3);
