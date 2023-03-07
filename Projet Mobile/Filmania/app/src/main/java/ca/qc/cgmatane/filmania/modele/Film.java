package ca.qc.cgmatane.filmania.modele;

import java.util.HashMap;

public class Film {

    protected String titre;
    protected String realisateur;
    protected int id;



    public Film(String titre, String realisateur, int id) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, String>obtenirFilmPourAfficher()
    {
        HashMap<String,String> filmPourAfficher = new HashMap<String,String>();

        filmPourAfficher.put("titre", this.titre);
        filmPourAfficher.put("realisateur", this.realisateur);
        filmPourAfficher.put("id",""+ this.id);

        return filmPourAfficher;
    }

}
