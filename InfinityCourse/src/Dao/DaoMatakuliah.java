
package Dao;

import Interface.InterfaceDao;
import Interface.InterfaceDM;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Matakuliah;

public class DaoMatakuliah extends Dao implements InterfaceDao,InterfaceDM {
    
    private Matakuliah mk;
    
    public DaoMatakuliah(JTable table,JComboBox combo){
        this.tabel = "Matakuliah";
        koloms = new String[] {"Nama","Harga","SKS"};
        kolom = "harga";

        /*
            Load Table, Fill ComboOrder
            Pada kelas MainView
        */
        sqlQuery("SELECT * FROM matakuliah");
        fillComboOrder(combo);
        loadtable(table);
    }
    
    public DaoMatakuliah(){
        this.tabel = "Matakuliah";
        koloms = new String[] {"Nama","Harga","SKS"};
        kolom = "harga";
    }
    
    public void setMatakuliah(Matakuliah mk){
        this.mk = mk;
    }

    @Override
    public void cari(String key) {
        sql = "SELECT * FROM Matakuliah WHERE kd_mk LIKE '%"+key+"%'"
                + "OR nama_mk LIKE '%"+key+"%' "
                + "OR  harga LIKE '%"+key+"%' "
                + "OR sks LIKE '%"+key+"%';";
        sqlQuery(sql);
    }
    
    @Override
    public HashMap getComboItems(){       
        HashMap<String,String> hm = new HashMap<>();
        try {

            sqlQuery("SELECT kd_mk,nama_mk From Matakuliah");
            while(rs.next()){
                
                hm.put( rs.getString("kd_mk"),
                        rs.getString("nama_mk")
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
            case "nama":
                return "nama_mk";
            case "harga":
                return "harga";
            case "sks" :
                return "sks";
            default :
                return null;
        }
    }
    
    public static void main(String[] args) {
        DaoMatakuliah d = new  DaoMatakuliah(); 
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
                        this.rs.getString("kd_mk"),
                        this.rs.getString("nama_mk"),
                        this.rs.getString("sks"),
                        this.rs.getString("harga")
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
                    this.rs.getString("kd_mk"),
                    this.rs.getString("nama_mk"),
                    this.rs.getString("harga"),
                    this.rs.getString("sks")
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
        model.addColumn("Kode Matakuliah");
        model.addColumn("Nama Matakuliah");
        model.addColumn("SKS");
        model.addColumn("Harga");
    }

    @Override
    public void setModel(JTable table) {
         table.setModel(model);
    }
    
    @Override
    public void insert(JTable table){
        
        if(mk.getKdMK().length()!=4){
            Pesan("KODE MATAKULIAH HARUS 4 DIGIT!");
        }else{
            sql = "INSERT INTO "+tabel+" VALUES "
                + "('"+mk.getKdMK()+"','"+mk.getNamaMk()+"','"+mk.getSks()+"','"+mk.getHarga()+"');";
            sqlUpdate(sql);
            select();
            loadtable(table);
        }
    }
    
    @Override
    public void update(JTable tbl){
        sql = "UPDATE "+tabel+" SET "
            + " nama_mk = '"+mk.getNamaMk()+"', sks = '"+mk.getSks()+"', harga = '"+mk.getHarga()+"'"
                + "WHERE kd_mk = '"+mk.getKdMK()+"';";
        sqlUpdate(sql);
        select();
        loadtable(tbl);
    }
    
    @Override
    public void delete(JTable tbl,String kd){
        sql = " DELETE FROM "+tabel+" WHERE kd_mk = '"+kd+"' ;";
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
        while(it.hasNext()){
            combo.addItem("("+it.next() +") "+ is.next() );
        }
    }

}
