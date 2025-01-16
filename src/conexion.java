
import java.sql.*;

public class conexion {

    private static final String URL = "mysql://uwfx3wgrfnj9px2s:x6xJuDOr496wThYiOPUr@bsmvfr83jdr81zezcwxq-mysql.services.clever-cloud.com:3306/bsmvfr83jdr81zezcwxq";
    private static final String USER = "uwfx3wgrfnj9px2s";
    private static final String PASSWORD = "x6xJuDOr496wThYiOPUr";

    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
