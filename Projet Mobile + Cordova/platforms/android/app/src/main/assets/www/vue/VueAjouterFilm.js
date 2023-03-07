class VueAjouterFilm
{
	constructor()
	{
		this.html = document.getElementById("html-vue-ajouter-film").innerHTML;
		this.actionAjouterFilm = null;
	}
	
	initialiserActionAjouter(actionAjouterFilm)
	{
		this.actionAjouterFilm = actionAjouterFilm;
	}
	
	afficher()
	{
		document.getElementsByTagName("body")[0].innerHTML = this.html;
		document.getElementById("formulaire-ajouter").addEventListener("submit",evenement=>this.enregistrer(evenement));
	}
	
	enregistrer(evenement)
	{
		evenement.preventDefault();
		
		let titre = document.getElementById("film-titre").value;
		let realisateur = document.getElementById("film-realisateur").value;
		let description = document.getElementById("film-description").value;
		
		this.actionAjouterFilm(new Film(titre, realisateur, description, null));
	}
}