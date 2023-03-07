package ca.qc.cgmatane.filmania.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.filmania.modele.Film;

public class FilmDAO {

    private static FilmDAO instance = null;
    //private List<HashMap<String,String>> listeFilms;
    private List<Film> listeFilms;

    private BaseDeDonnees baseDeDonnees;

    private FilmDAO(){
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        //listeFilms = new ArrayList<HashMap<String,String>>();
        listeFilms = new ArrayList<Film>();
        //preparerListeFilm();
    }

    private void preparerListeFilm(){

        /*HashMap<String,String> film;

        film = new HashMap<String,String>();
        film.put("titre", "Le Mans 66");
        film.put("realisateur", "Peter Chernin");

        listeFilms.add(film);

        film = new HashMap<String,String>();
        film.put("titre", "Harry Potter et la chambre des secrets");
        film.put("realisateur", "Chris Columbus");

        listeFilms.add(film);

        film = new HashMap<String,String>();
        film.put("titre", "It");
        film.put("realisateur", "Andres Muschietti");

        listeFilms.add(film);*/

        listeFilms.add(new Film("Le Mans 66", "Peter Chernin",0));
        listeFilms.add(new Film("Harry Potter et la chambre des secrets", "Chris Columbus",1));
        listeFilms.add(new Film("It", "Andres Muschietti",2));

    }

    public static FilmDAO getInstance() {

        if(null == instance){
            instance = new FilmDAO();
        }

        return instance;
    }

    public List<Film> listerFilm(){
        String LISTER_LIVRE = "SELECT * FROM film";
        Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_LIVRE, null);

        this.listeFilms.clear();

        Film film;

        int indexId = curseur.getColumnIndex("id");
        int indexRealisateur = curseur.getColumnIndex("realisateur");
        int indexTitre = curseur.getColumnIndex("titre");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext())
        {
            int id = curseur.getInt(indexId);
            String realisateur = curseur.getString(indexRealisateur);
            String titre = curseur.getString(indexTitre);

            film = new Film(titre, realisateur, id);

            this.listeFilms.add(film);
        }
        return listeFilms;
    }

    /*public void ajouterFilm(HashMap<String,String> film){
        //listeFilms.add(film);
    }*/

    public void ajouterFilm(Film film)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();

        baseDeDonneesEcriture.beginTransaction();

        try
        {
            ContentValues filmEnCleValeur = new ContentValues();
            filmEnCleValeur.put("realisateur", film.getRealisateur());
            filmEnCleValeur.put("titre", film.getTitre());

            baseDeDonneesEcriture.insertOrThrow("film", null, filmEnCleValeur);
            baseDeDonneesEcriture.setTransactionSuccessful();
        }

        catch (Exception e)
        {
            Log.d("FilmDAO", "Erreur en tentant d'ajouter un film dans la base donnees");
        }

        finally
        {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public Film chercherFilmParId(int id)
    {
        listerFilm();

        for(Film filmRecherche : this.listeFilms)
        {
            if(filmRecherche.getId() == id) return filmRecherche;
        }

        return null;
    }

    public void modifierFilm(Film film)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();

        try
        {

            ContentValues filmEnCleValeur = new ContentValues();
            filmEnCleValeur.put("titre", film.getTitre());
            filmEnCleValeur.put("realisateur", film.getRealisateur());
            String id =String.valueOf(film.getId());
            baseDeDonneesEcriture.update("film", filmEnCleValeur,"ID=?", new String[]{id});
            baseDeDonneesEcriture.setTransactionSuccessful();
        }

        catch (Exception e)
        {
            Log.d("FilmDAO", "Erreur en tentant d'ajouter un film dans la base donnees");
        }

        finally
        {
            baseDeDonneesEcriture.endTransaction();
        }
    }
}
