class VueModifierFilm
{
    constructor()
    {
        this.html = document.getElementById("html-vue-modifier-film").innerHTML;
        this.actionModifierFilm = null;
        this.film = null;
    }

    initialiserActionModifier(actionModifierFilm)
	{
		this.actionModifierFilm = actionModifierFilm;
	}

    initialiserFilm(film)
    {
        this.film = film;
    }

    afficher()
	{

		document.getElementsByTagName("body")[0].innerHTML = this.html;

        document.getElementById("film-titre").value = this.film.titre;
        document.getElementById("film-realisateur").value = this.film.realisateur;
        document.getElementById("film-description").value = this.film.description;

		document.getElementById("formulaire-modifier").addEventListener("submit",evenement=>this.enregistrer(evenement));
	}
	
	enregistrer(evenement)
	{
		evenement.preventDefault();
		
		let titre = document.getElementById("film-titre").value;
		let realisateur = document.getElementById("film-realisateur").value;
		let description = document.getElementById("film-description").value;

        this.film.titre = titre;
        this.film.realisateur = realisateur;
        this.film.description = description;

		this.actionModifierFilm(this.film);
	}
}