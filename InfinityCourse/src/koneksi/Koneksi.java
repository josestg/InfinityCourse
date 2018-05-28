
package koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private  Connection conn;
    
    public Koneksi(){
        
        MysqlDataSource mds = new MysqlDataSource();
        mds.setUser("root");
        mds.setPassword("");
        mds.setDatabaseName("infinitycourse");
        
        try {
            conn = mds.getConnection();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"KONEKSI GAGAL "+ex.getMessage());
        }
    }
    public Connection getKoneksi(){
        return this.conn;
    }
    
    public static void main(String[] args) {
        Koneksi k = new Koneksi();
    }
    
}
