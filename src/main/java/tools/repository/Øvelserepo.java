package tools.repository;

import models.ØvelseCModell;
import models.ØvelseSmodell;
import tools.DbTool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Øvelserepo {
    /**
     * legger til bruker til databasen.
     * Denne er ikke implementert. Her må dere gjerne prøve å lage en egen servlet som kan kommunisere med
     * denne metoden.
     *
     * @param resultat bruker objekt som inneholder all informasjon om personen.
     *                 Tips: Objektet må instansieres i en servlet før man kaller på addUser().
     * @param p        printwriter for å skrive ut html i servlet. F.eks SQL feilmeldinger eller annen info.
    **/

    public static void regResultatJC(ØvelseCModell resultat, PrintWriter p) {
        Connection db = null;
        PreparedStatement regNyttResultat = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn(p);
            String query =
                    "INSERT INTO ro.resultat (60w, bevegelighet, 3000t, kroppshev, sargeant ) values (?,?,?,?,?)";

            regNyttResultat = db.prepareStatement(query);
            regNyttResultat.setString(1, resultat.getSeksti());
            regNyttResultat.setString(2, resultat.getBeveglighet());
            regNyttResultat.setString(3, resultat.getTretusen());
            regNyttResultat.setString(4, resultat.getKroppshev());
            regNyttResultat.setString(5, resultat.getSargeant());
            regNyttResultat.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    public static void regResultatS(ØvelseSmodell resultat, PrintWriter p) {
        Connection db = null;
        PreparedStatement regNyttResultatS = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn(p);
            String query =
                    "INSERT INTO ro.resultat (60w, bevegelighet, 5000t, 5000w, 2000t, 2000w, ligg_ro_p, ligg_ro_kg, knebøy_kg, knebøy_p) values (?,?,?,?,?,?,?,?,?,?)";

            regNyttResultatS = db.prepareStatement(query);
            regNyttResultatS.setString(1, resultat.getSeksti());
            regNyttResultatS.setString(2, resultat.getBeveglighet());
            regNyttResultatS.setString(3, resultat.getFemtusenT());
            regNyttResultatS.setString(4, resultat.getFemtusenW());
            regNyttResultatS.setString(5, resultat.gettotusenW());
            regNyttResultatS.setString(6, resultat.gettotusenT());
            regNyttResultatS.setString(7, resultat.getLiggendeRoingW());
            regNyttResultatS.setString(8, resultat.getLiggendeRoingKG());
            regNyttResultatS.setString(9, resultat.getKnebøyKG());
            regNyttResultatS.setString(10, resultat.getKnebøyP());
            regNyttResultatS.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}

