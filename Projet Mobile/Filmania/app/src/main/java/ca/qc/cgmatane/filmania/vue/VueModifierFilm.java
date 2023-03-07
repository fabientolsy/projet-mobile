package ca.qc.cgmatane.filmania.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.filmania.R;
import ca.qc.cgmatane.filmania.donnee.FilmDAO;
import ca.qc.cgmatane.filmania.modele.Film;

public class VueModifierFilm extends AppCompatActivity {

    protected EditText vueModifierFilmChampsTitre;
    protected EditText vueModiferFilmChampsRealisateur;
    protected FilmDAO filmDAO;
    protected Film film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_film);

        Button vueModifierFilmActionModifier = (Button)findViewById(R.id.vueModifierFilmActionModifier);
        Button vueModifierFilmActionAnnuler = (Button)findViewById(R.id.vueModifierFilmActionAnnuler);

        // BOUTON ET ACTION ENREGISTRER FILM
        Bundle parametres  = this.getIntent().getExtras();

        String idParametre = (String) parametres.get("id");
        int id = Integer.parseInt(idParametre);

        filmDAO = FilmDAO.getInstance();
        film = filmDAO.chercherFilmParId(id);

        vueModifierFilmChampsTitre = (EditText)findViewById(R.id.vueModifierFilmChampTitre);
        vueModiferFilmChampsRealisateur = (EditText)findViewById(R.id.vueModifierFilmChampRealisateur);

        vueModifierFilmChampsTitre.setText(film.getTitre());
        vueModiferFilmChampsRealisateur.setText(film.getRealisateur());

        vueModifierFilmActionModifier.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        enregistrerFilm();
                        naviguerRetourBibliotheque();
                    }
                }
        );

        // BOUTON ET ACTION ANNULER MODIFICATION FILM
        vueModifierFilmActionAnnuler.setOnClickListener(

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



    }

    public  void enregistrerFilm()
    {
        film.setTitre(vueModifierFilmChampsTitre.getText().toString());
        film.setRealisateur(vueModiferFilmChampsRealisateur.getText().toString());

        filmDAO.modifierFilm(film);
    }
    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}