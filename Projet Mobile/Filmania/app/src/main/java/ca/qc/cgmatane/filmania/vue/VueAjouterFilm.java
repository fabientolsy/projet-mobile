package ca.qc.cgmatane.filmania.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.filmania.R;
import ca.qc.cgmatane.filmania.donnee.FilmDAO;
import ca.qc.cgmatane.filmania.modele.Film;

public class VueAjouterFilm extends AppCompatActivity {

    protected EditText vueAjouterFilmChampTitre;
    protected EditText vueAjouterFilmChampRealisateur;
    protected FilmDAO filmDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_film);

        Button vueAjouterFilmActionAnnuler = (Button)findViewById(R.id.vueAjouterFilmActionAnnuler);

        vueAjouterFilmActionAnnuler.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // TODO : coder!
                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action annuler!",
                                Toast.LENGTH_SHORT);
                        message.show();
                        */
                        naviguerRetourBibliotheque();
                    }
                }
        );

        vueAjouterFilmChampTitre = (EditText)findViewById(R.id.vueAjouterFilmChampTitre);
        vueAjouterFilmChampRealisateur = (EditText)findViewById(R.id.vueAjouterFilmChampRealisateur);


        Button vueAjouterFilmActionAjouter = (Button)findViewById(R.id.vueAjouterFilmActionAjouter);

        vueAjouterFilmActionAjouter.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        // TODO : coder!


                        /*
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Champ titre:"+vueAjouterLivreChampTitre.getText().toString()+
                                        " Champ auteur:"+vueAjouterLivreChampAuteur.getText().toString(),
                                Toast.LENGTH_SHORT);
                        message.show();

                         */
                        enregisterFilm();
                        naviguerRetourBibliotheque();
                    }
                }
        );

    }

    private void enregisterFilm() {

        /*HashMap<String,String> film;

        film = new HashMap<String,String>();
        film.put("titre", vueAjouterFilmChampTitre.getText().toString());
        film.put("realisateur", vueAjouterFilmChampRealisateur.getText().toString());

        filmDAO = FilmDAO.getInstance();
        filmDAO.ajouterFilm(film);*/

        Film film = new Film(vueAjouterFilmChampTitre.getText().toString(), vueAjouterFilmChampRealisateur.getText().toString(),0);

        filmDAO = FilmDAO.getInstance();
        System.out.println(film);
        filmDAO.ajouterFilm(film);

    }


    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}