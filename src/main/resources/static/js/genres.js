"use strict"

import {byId, setText, toon, verwijderChildElementenVan} from "./util.js";

const response = await fetch("genres");
if (response.ok) {
    const ul = byId("genresLijst");
    const genres = await response.json();
    for (const genre of genres) {
        const li = document.createElement("li")
        const hyperlink = document.createElement("a")
        hyperlink.innerText = `${genre.naam}`;
        hyperlink.href = "#";
        hyperlink.onclick = async function () {
            verwijderChildElementenVan(byId("filmsVanGenre"));
            findFilmsVanGenre(genre);
        }
        li.appendChild(hyperlink);
        ul.appendChild(li);
    }
} else {
    toon("storing");
}

async function findFilmsVanGenre(genre) {
    const response = await fetch(`genres/${genre.id}/films`);
    if (response.ok) {
        setText("genre", genre.naam)
        const filmsVanGenre = byId("filmsVanGenre");
        const films = await response.json();
        for (const film of films) {
            const img = document.createElement("img");
            img.src = `images/${film.id}.jpg`;
            img.alt = film.titel;
            const hyperlink = document.createElement("a");
            hyperlink.href = "film.html"
            hyperlink.appendChild(img);
            filmsVanGenre.appendChild(hyperlink);
        }
    } else {
        toon("storing");
    }
}
