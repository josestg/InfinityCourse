
package Dao;

import Interface.InterfaceDM;
import Interface.InterfaceDao;
import Model.Mahasiswa;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DaoMahasiswa extends Dao implements InterfaceDao,InterfaceDM{
    
    private Mahasiswa mm;
    public DaoMahasiswa(JTable table,JComboBox combo){
        this.tabel = "Mahasiswa";
        koloms = new String[] {"Nim","Nama","jurusan"};
        kolom = "semester";

        /*
            Load Table, Fill ComboOrder
            Pada kelas MainView
        */
        sqlQuery("SELECT * FROM Mahasiswa");
        fillComboOrder(combo);
        loadtable(table);
    }
    public DaoMahasiswa(){
        this.tabel = "Mahasiswa";
        koloms = new String[] {"Nim","Nama","jurusan"};
        kolom = "semester";
    }
    
    public void setMahasiswa(Mahasiswa mm){
        this.mm = mm;
    }

    @Override
    public void cari(String key) {
        sql = "SELECT * FROM Mahasiswa WHERE nim LIKE '%"+key+"%'"
                + "OR nama_mhs LIKE '%"+key+"%' "
                + "OR jurusan LIKE '%"+key+"'%"
                + "OR jk LIKE '%"+key+"%'"
                + "OR  semester LIKE '%"+key+"%' ";
        sqlQuery(sql);
    }

    @Override
    public HashMap getComboItems() {
        HashMap<String,String> hm = new HashMap<>();
        try {

            sqlQuery("SELECT nim,nama_mhs From Mahasiswa");
            while(rs.next()){
                
                hm.put( rs.getString("nim"),
                        rs.getString("nama_mhs")
                      );
            }
        } catch (SQLException ex) {
            Pesan("getComboItems : "+ex.getMessage());
        }
        return hm;
    }

    @Override
    public String getOrderKolom(String order) {
        switch (order) {
            case "nim":
                return "nim";
            case "nama":
                return "nama_mhs";
            case "jurusan" :
                return "jurusan";
            default :
                return null;
        }
    }

    @Override
    public int loadtable(JTable table) {
        int count = 0;
        try {
            if(rs!=null){
                createTable();
                while(this.rs.next()){
                    count+=1;
                    model.addRow(new Object[]{
                        this.rs.getString("nim"),
                        this.rs.getString("nama_mhs"),
                        this.rs.getString("jk"),
                        this.rs.getString("semester"),
                        this.rs.getString("jurusan")
                    });
                    table.setModel(model);
                }
            }
        } catch (SQLException ex) {
            Pesan("Load Tabel : "+ex.getMessage());
        }
        //return number of row
        return count;
    }

    @Override
    public DefaultTableModel getModel() {
        try {
            while(this.rs.next()){
                 model.addRow(new Object[]{
                        this.rs.getString("nim"),
                        this.rs.getString("nama_mhs"),
                        this.rs.getString("jk"),
                        this.rs.getString("semester"),
                        this.rs.getString("jurusan")
                    });
                
                return model;
            }
        } catch (SQLException ex) {
            Pesan("getModel :" + ex.getMessage());
        }
        return null;
    }

    @Override
    public void createTable() {
        model=  new DefaultTableModel();
        model.addColumn("NIM");
        model.addColumn("Nama Mahasiswa");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Semester");
        model.addColumn("Jurusan");
    }

    @Override
    public void setModel(JTable table) {
       table.setModel(model);
    }

    @Override
    public void update(JTable tbl) {
        sql = "UPDATE "+tabel+" SET "
            + " nama_mhs = '"+mm.getNama()+"', jk = '"+mm.getJk()+"', semester = '"+mm.getSemester()+"',"
                + " jurusan = '"+mm.getJurusan()+"' "
                + "WHERE nim = '"+mm.getNim()+"';";
        sqlUpdate(sql);
        select();
        loadtable(tbl); 
    }

    @Override
    public void insert(JTable tbl) {
        if(mm.getNim().length()!=8){
            Pesan("NIM HARUS 8 DIGIT!");
        }else{
            sql = "INSERT INTO "+tabel+" VALUES "
                + "('"+mm.getNim()+"','"+mm.getNama()+"','"+mm.getJk()+"','"+mm.getSemester()+"', '"+mm.getJurusan()+"');";
            sqlUpdate(sql);
            select();
            loadtable(tbl);
        }
    }

    @Override
    public void delete(JTable tbl, String kd) {
        sql = " DELETE FROM "+tabel+" WHERE nim = '"+kd+"' ;";
        sqlUpdate(sql);
        select();
        loadtable(tbl);
    }
    @Override
    public void fillCombo(JComboBox combo){
        clearCombo(combo);
        Iterator<String> it,is;
        it = getComboItems().keySet().iterator();
        is = getComboItems().values().iterator();
        while(it.hasNext() && is.hasNext()){
            combo.addItem("(" + it.next() +") "+is.next());
        }
    }
    
}
