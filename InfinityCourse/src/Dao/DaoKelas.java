
package Dao;

import Interface.InterfaceDM;
import Interface.InterfaceDao;
import Model.Kelas;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DaoKelas  extends Dao implements InterfaceDao,InterfaceDM{

    private Kelas mk;
    
    public DaoKelas(JTable table,JComboBox combo){
        this.tabel = "Kelas";
        koloms = new String[] {"KD Kelas","Kapasitas","Jadwal"};
        kolom = "kapasitas";

        /*
            Load Table, Fill ComboOrder
            Pada kelas MainView
        */
        sqlQuery("SELECT * FROM Kelas");
        fillComboOrder(combo);
        loadtable(table);
    }
    
    public DaoKelas(){
        this.tabel = "Kelas";
        koloms = new String[] {"KD Kelas","Kapasitas","Jadwal"};
        kolom = "kapasitas";
    }
    
    public void setKelas(Kelas k){
        this.mk = k;
    }

    @Override
    public void cari(String key) {
        sql = "SELECT * FROM Kelas WHERE kd_kelas LIKE '%"+key+"%'"
                + "OR kapasitas LIKE '%"+key+"%' "
                + "OR  jadwal LIKE '%"+key+"%'; ";
        sqlQuery(sql);
    }
    
    @Override
    public HashMap getComboItems(){       
        HashMap<String,String> hm = new HashMap<>();
        try {

            sqlQuery("SELECT kd_kelas,kapasitas From Kelas");
            while(rs.next()){
                
                hm.put( rs.getString("kd_kelas"),
                        rs.getString("kapasitas")
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
            case "kd kelas":
                return "kd_kelas";
            case "kapasitas":
                return "kapasitas";
            case "jadwal" :
                return "jadwal";
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
                        this.rs.getString("kd_kelas"),
                        this.rs.getString("kapasitas"),
                        this.rs.getString("jadwal")
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
                    this.rs.getString("kd_kelas"),
                    this.rs.getString("kapasitas"),
                    this.rs.getString("jadwal")
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
        model.addColumn("Kode Kelas");
        model.addColumn("Kapasitas");
        model.addColumn("Jadwal");
    }

    @Override
    public void setModel(JTable table) {
         table.setModel(model);
    }

    @Override
    public void update(JTable tbl) {
        sql = "UPDATE "+tabel+" SET "
            + " kapasitas = '"+mk.getKapasitas()+"', jadwal = '"+mk.getJadwal()+"'"
                + "WHERE kd_kelas = '"+mk.getKdKelas()+"';";
        sqlUpdate(sql);
        select();
        loadtable(tbl);
    }

    @Override
    public void insert(JTable tbl) {
        if(mk.getKdKelas().length()!=4){
            Pesan("KODE KELAS HARUS 4 DIGIT!");
        }else{
            sql = "INSERT INTO "+tabel+" VALUES "
                + "('"+mk.getKdKelas()+"','"+mk.getKapasitas()+"','"+mk.getJadwal()+"');";
            sqlUpdate(sql);
            select();
            loadtable(tbl);
        }
    }

    @Override
    public void delete(JTable tbl, String kd) {
        sql = " DELETE FROM "+tabel+" WHERE kd_kelas = '"+kd+"' ;";
        sqlUpdate(sql);
        select();
        loadtable(tbl);
    }
    


}
