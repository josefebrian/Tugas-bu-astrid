
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author jose
 */
public class koneksi {
    Connection conn;
    ResultSet rs;
    Statement st;
    
    
     public static String userName = "oso";
    public static String password = "1234";
    public static String server = "192.168.0.11";
//    public static String server = "66.96.231.241";
    public static String db = "oso";
    public static String connString = "jdbc:mysql://" + server + ":3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public  Connection setKoneksi() {
        try {
            
            Connection conn = DriverManager.getConnection(connString, userName, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("MySQL Connected to: " + server);
           st=conn.createStatement();
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
    
    
  
    
}
    
