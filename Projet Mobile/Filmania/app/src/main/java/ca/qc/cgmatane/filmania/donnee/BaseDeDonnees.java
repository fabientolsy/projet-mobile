package ca.qc.cgmatane.filmania.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static synchronized BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name,factory,version);
    }

    public BaseDeDonnees(Context context)
    {
        super(context, "filmania", null, 1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "create table film(id INTEGER PRIMARY KEY, titre TEXT, realisateur TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    /*public void onOpen(SQLiteDatabase db)
    {
        String DELETE = "delete from film WHERE 1=1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into film(titre, realisateur) VALUES('Le Mans 66','Peter Chernin')";
        String INSERT_2 = "insert into film(titre, realisateur) VALUES('Harry Potter et la chambre des secrets','Chris Columbus')";
        String INSERT_3 = "insert into film(titre, realisateur) VALUES('It','Andres Muschietti')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

    }*/

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
    {
        String CREER_TABLE = "create table film(id INTEGER PRIMARY KEY, titre TEXT, realisateur TEXT)";
        db.execSQL(CREER_TABLE);
    }
}
