class Application
{
    constructor(window, filmDAO, vueListeFilm, vueAjouterFilm, vueModifierFilm, vueFilm)
    {
        this.window = window;

        this.filmDAO = filmDAO;

        this.vueListeFilm = vueListeFilm;
		this.vueAjouterFilm = vueAjouterFilm;
		this.vueModifierFilm = vueModifierFilm;
		this.vueFilm = vueFilm;
		
		this.vueAjouterFilm.initialiserActionAjouter(film => this.actionAjouterFilm(film));
		this.vueModifierFilm.initialiserActionModifier(film => this.actionModifierFilm(film));

        /*this.vueListeFilm.initialiserListeFilm(this.filmDAO.lister());
        this.vueListeFilm.afficher();*/
		
		/*this.window.addEventListener("hashchange", () => this.naviguer());
		
		this.naviguer();*/

		document.addEventListener('deviceready', () =>this.initialiserNavigation(), false);
    }
	
	naviguer()
	{
		let hash = window.location.hash;
		
		if(!hash)
		{
			this.vueListeFilm.initialiserListeFilm(this.filmDAO.lister());
			this.vueListeFilm.afficher();
		}
		else if(hash.match(/^#ajouter-film/))
		{
			this.vueAjouterFilm.afficher();
		}
		else if(hash.match(/^#modifier-film\/([0-9]+)/))
		{
			let naviguation = hash.match(/^#modifier-film\/([0-9]+)/);
			let idFilm = naviguation[1];

			this.vueModifierFilm.initialiserFilm(this.filmDAO.lister()[idFilm]);			
			this.vueModifierFilm.afficher();
		}
		else
		{
			let naviguation = hash.match(/^#film\/([0-9]+)/);
			let idFilm = naviguation[1];

			this.vueFilm.initialiserFilm(this.filmDAO.lister()[idFilm]);
			this.vueFilm.afficher();
		}
	}
	
	actionAjouterFilm(film)
	{
		this.filmDAO.ajouter(film);
		this.window.location.hash = "#";
	}

	actionModifierFilm(film)
	{
		this.filmDAO.modifier(film);
		this.window.location.hash = "#";
	}

	initialiserNavigation()
	{
	    console.log("Application.js -> initialiserNavigation()");

		this.window.addEventListener("hashchange", () =>this.naviguer());

		setTimeout(() => this.naviguer(), 3000);
	}
}

new Application(window, new FilmDAO(), new VuelisteFilm(), new VueAjouterFilm(), new VueModifierFilm(), new VueFilm());