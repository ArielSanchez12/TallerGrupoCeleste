import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    String URL = "jdbc:mysql://uwfx3wgrfnj9px2s:x6xJuDOr496wThYiOPUr@bsmvfr83jdr81zezcwxq-mysql.services.clever-cloud.com:3306/bsmvfr83jdr81zezcwxq";
    String USER = "uwfx3wgrfnj9px2s";
    String PASSWORD = "x6xJuDOr496wThYiOPUr";


    public Connection connect() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
