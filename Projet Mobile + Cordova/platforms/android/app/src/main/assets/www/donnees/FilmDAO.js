class FilmDAO
{
    constructor()
    {
       /* this.listeFilm = [
            {titre:"Le Mans 66", realisateur:"Jame Mangold", description:"Film d'histoire de voitures", id:0},
            {titre:"Harry Potter", realisateur:"Chris Columbus", description:"Film de sorciers et de magie", id:1},
            {titre:"Le Seigneur des Anneaux la communeaute de l'anneau", realisateur:"Peter Jackson", description:"Film de magie et de suspense", id:2}
        ]*/

        this.listeFilm = [];
    }

    lister()
    {
        if(localStorage['film'])
        {
            this.listeFilm = JSON.parse(localStorage['film']);
        }

        for(let position in this.listeFilm)
        {
            let film = new Film(this.listeFilm[position].titre,this.listeFilm[position].realisateur,this.listeFilm[position].description,this.listeFilm[position].id);

            this.listeFilm[film.id] = film;
        }

        return this.listeFilm;
    }
	
	ajouter(film)
	{
		if(this.listeFilm.length > 0)
			film.id = this.listeFilm[this.listeFilm.length-1].id + 1;		
		else
			film.id = 0;
		
		this.listeFilm[film.id] = film;		
		console.log(this.listeFilm);

        localStorage['film'] = JSON.stringify(this.listeFilm);
        JSON.stringify(this.listeFilm);
        console.log("JSON.stringify(this.listeFilm)");
	}

    modifier(film)
    {
        this.listeFilm[film.id] = film;
        console.log("Liste de film: " + this.listeFilm);

        localStorage['film'] = JSON.stringify(this.listeFilm);
        JSON.stringify(this.listeFilm);
        
    }
}