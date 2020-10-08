package tools.repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import models.Gender;
import models.UserModel;
import tools.DbTool;

public class UserRepository {
    private static final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public static Integer addUserJdbc(UserModel user, PrintWriter out) {
        try {
            DriverManagerDataSource db = DbTool.getDataSource();

            jdbcTemplate.setDataSource(db);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("User_firstName", user.getFirstName());
            params.put("User_lastName", user.getLastName());
            params.put("User_email", user.getUserName());
            params.put("User_password", user.getPassword());
            params.put("User_dob", user.getDob());
            params.put("User_Gender", user.getGender());
            SimpleJdbcInsert insert =
                new SimpleJdbcInsert(jdbcTemplate).withTableName("user").usingGeneratedKeyColumns("User_id");

            return insert.executeAndReturnKey(params).intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * legger til bruker til databasen.
     * Denne er ikke implementert. Her må dere gjerne prøve å lage en egen servlet som kan kommunisere med
     * denne metoden.
     *
     * @param user bruker objekt som inneholder all informasjon om personen.
     *             Tips: Objektet må instansieres i en servlet før man kaller på addUser().
     * @param p    printwriter for å skrive ut html i servlet. F.eks SQL feilmeldinger eller annen info.
     * @deprecated
     */
    public static void addUser(UserModel user, PrintWriter p) {
        Connection db = null;
        PreparedStatement insertNewUser;
        String idToReturn = null;
        ResultSet rs = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn(p);
            db.setCatalog("otra");
            String query =
                "INSERT INTO user (User_firstName, User_lastName,User_Email, User_password, User_dob,User_Gender) "
                    + "values (?,?,?,?,?,?)";

            insertNewUser = db.prepareStatement(query);
            insertNewUser.setString(1, user.getFirstName());
            insertNewUser.setString(2, user.getLastName());
            insertNewUser.setString(3, user.getUserName());
            insertNewUser.setString(4, user.getPassword());
            insertNewUser.setString(5, user.getDob());
            insertNewUser.setString(6, String.valueOf(user.getGender()));
            rs = insertNewUser.executeQuery();
            insertNewUser.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                db.close();
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    /**
     * henter ut spesifikk person fra databasen
     *
     * @param username brukerens epost-addresse ("trym@example.com");
     * @param p        printwriter see metoden over.
     * @return et String objekt med eposten til brukeren.
     */

    public static List<UserModel> getListOfUsers(PrintWriter p) {
        Connection db = null;
        PreparedStatement prepareStatement = null;

        List<UserModel> toReturn = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn(p);
            ResultSet rs = null;
            String query = "SELECT * FROM user";
            prepareStatement = db.prepareStatement(query);
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Gender gen = (rs.getString("User_Gender").matches("MALE")) ? Gender.MALE : Gender.FEMALE;
                System.out.println("gen = " + gen);
                UserModel getUserModel = new UserModel(rs.getString("User_firstName"), rs.getString("User_lastName"),
                    rs.getString("User_Email"), rs.getString("User_password"), rs.getString("User_dob"), gen);
                toReturn.add(getUserModel);
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return toReturn;

    }

    public static List<UserModel> hentAlleUsers(PrintWriter out) {

        List<UserModel> users = new ArrayList<>();

        DriverManagerDataSource db = DbTool.getDataSource();
        try {
            jdbcTemplate.setDataSource(db);
            String sql = "SELECT * FROM user";
            jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                users.add(
                    new UserModel(
                    rs.getString("User_firstName"),
                    rs.getString("User_lastName"),
                    rs.getString("User_Email"),
                    rs.getString("User_dob"),
                    Gender.valueOf(rs.getString("User_Gender")
                    )))
            );

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public static List<UserModel> hentAll() {
        String sql = "SELECT * FROM user";
        jdbcTemplate.setDataSource(DbTool.getDataSource());
        return jdbcTemplate.query(
            sql, new BeanPropertyRowMapper<>(UserModel.class));
    }
}