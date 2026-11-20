
package dcl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static DbUtil instance = new DbUtil();
    private Connection conexion = null;
    
    private DbUtil(){}
    
    // Corregido: getInstance con 'c'
    public static DbUtil getInstance(){
        return instance;
    }
    
    private boolean createConexion() throws IOException, SQLException {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
            
            if (inputStream == null) {
                System.err.println("No se encontró el archivo db.properties en resources");
                return false;
            }
            
            prop.load(inputStream);
            String driver = prop.getProperty("driver").trim();
            String url = prop.getProperty("url").trim();
            String database = prop.getProperty("database").trim();
            String user = prop.getProperty("user").trim();
            String password = prop.getProperty("password").trim();

            Class.forName(driver);
            conexion = DriverManager.getConnection(url + database, user, password);
            if (conexion != null) {
                System.out.println("Conexión a la base de datos lista!!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public Connection getConnection() throws IOException, SQLException {
        if (conexion == null) {
            this.createConexion();
        }
        return conexion;
    }
}