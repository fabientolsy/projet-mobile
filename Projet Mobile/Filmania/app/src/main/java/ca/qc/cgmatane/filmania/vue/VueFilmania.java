package ca.qc.cgmatane.filmania.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.filmania.R;
import ca.qc.cgmatane.filmania.donnee.BaseDeDonnees;
import ca.qc.cgmatane.filmania.donnee.FilmDAO;
import ca.qc.cgmatane.filmania.modele.Film;

public class VueFilmania extends AppCompatActivity {

    protected ListView vueFilmaniaListeFilms;

    //protected List<HashMap<String, String>> listeFilms;
    protected List<Film> listeFilms;
    protected FilmDAO filmDAO;

    protected Intent intentionNaviguerAjouterFilm;
    protected Intent intentionNaviguerModifierFilm;

    static final public int ACTIVITE_AJOUTER_FILM = 1;
    static final public int ACTIVITE_MODIFIER_FILM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_film);
        vueFilmaniaListeFilms = (ListView)findViewById(R.id.vueFilmaniaListeFilms);

        BaseDeDonnees.getInstance(getApplicationContext());

        filmDAO = FilmDAO.getInstance();
        /*
        listeLivre = filmDAO.listerLivre();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeLivre,
                android.R.layout.two_line_list_item,
                new String[] {"titre","auteur"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueBibliothequeListeLivre.setAdapter(adapteur);

         */
        afficherListeLivre();

        Button vueFilmaniaActionAjouterFilm = (Button)findViewById(R.id.vueFilmaniaActionAjouterFilm);

        intentionNaviguerAjouterFilm = new Intent(this, VueAjouterFilm.class);

        vueFilmaniaActionAjouterFilm.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // TODO : coder!
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action ajouter livre!",
                                Toast.LENGTH_SHORT);
                        message.show();
                        */
                        //startActivity(intentionNaviguerAjouterLivre);
                        startActivityForResult(intentionNaviguerAjouterFilm, ACTIVITE_AJOUTER_FILM);
                    }
                }
        );

        intentionNaviguerModifierFilm = new Intent(this, VueModifierFilm.class);

        vueFilmaniaListeFilms.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue,
                                            int positionDansAdapteur, long positionItem) {


                        ListView vueListeFilms = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> film =
                                (HashMap<String, String>)
                                        vueListeFilms.getItemAtPosition((int)positionItem);

                        /*
                        Toast message = Toast.makeText(getApplicationContext(),
                                "Position "+positionItem + " titre " + film.get("titre"),
                                Toast.LENGTH_SHORT);
                        message.show();
                         */

                        //startActivity(intentionNaviguerModifierFilm);
                        intentionNaviguerModifierFilm.putExtra("id", film.get("id"));
                        startActivityForResult(intentionNaviguerModifierFilm, ACTIVITE_MODIFIER_FILM);

                    }}
        );
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees) {

        super.onActivityResult(activite, resultat, donnees);
        switch(activite)
        {
            case ACTIVITE_AJOUTER_FILM:
                afficherListeLivre();
                break;
            case ACTIVITE_MODIFIER_FILM:
                afficherListeLivre();
                break;
        }
    }

    public void afficherListeLivre(){
        listeFilms = filmDAO.listerFilm();

        List<HashMap<String,String>>listeFilmPourAfficher = new ArrayList<HashMap<String,String>>();

        for(Film film:listeFilms)
        {
            listeFilmPourAfficher.add(film.obtenirFilmPourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeFilmPourAfficher,
                android.R.layout.two_line_list_item,
                new String[] {"titre","realisateur"},
                new int[] {android.R.id.text1, android.R.id.text2});
        vueFilmaniaListeFilms.setAdapter(adapteur);
    }
}

