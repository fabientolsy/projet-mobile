class VueFilm
{
    constructor()
	{
		this.html = document.getElementById("html-vue-film").innerHTML;
        this.film = null;
	}

    initialiserFilm(film)
    {
        this.film = film;
    }

    afficher()
    {
        document.getElementsByTagName("body")[0].innerHTML = this.html;

        document.getElementById("film-titre").innerHTML = this.film.titre;
        document.getElementById("film-realisateur").innerHTML = this.film.realisateur;
        document.getElementById("film-description").innerHTML = this.film.description;
    }
}