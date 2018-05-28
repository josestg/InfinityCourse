/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceDM;
import Interface.InterfaceDao;
import Model.Pengajar;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DaoPengajar extends Dao implements InterfaceDao,InterfaceDM {
    private Pengajar mm;
    public DaoPengajar(JTable table,JComboBox combo){
        this.tabel = "Pengajar";
        koloms = new String[] {"ID Pengajar","Nama","Pendidikan"};
        kolom = "id_pengajar";

        /*
            Load Table, Fill ComboOrder
            Pada kelas MainView
        */
        sqlQuery("SELECT * FROM Pengajar");
        fillComboOrder(combo);
        loadtable(table);
    }
    
    public DaoPengajar(){
        this.tabel = "Pengajar";
        koloms = new String[] {"ID Pengajar","Nama","Pendidikan"};
        kolom = "id_pengajar";
    
    }
   
    
    public void setPengajar(Pengajar mm){
        this.mm = mm;
    }

    @Override
    public void cari(String key) {
        sql = "SELECT * FROM Pengajar WHERE id_pengajar LIKE '%"+key+"%'"
                + "OR nama_pengajar LIKE '%"+key+"%' "
                + "OR jk LIKE '%"+key+"%'"
                + "OR pendidikan LIKE '%"+key+"%'"
                + "OR  kd_mk LIKE '%"+key+"%' ;";
        sqlQuery(sql);
    }

    @Override
    public HashMap getComboItems() {
        HashMap<String,String> hm = new HashMap<>();
        try {

            sqlQuery("SELECT id_pengajar,nama_pengajar From Pengajar");
            while(rs.next()){
                
                hm.put( rs.getString("id_pengajar"),
                        rs.getString("nama_pengajar")
                      );
            }
        } catch (SQLException ex) {
            Pesan("getComboItems : "+ex.getMessage());
        }
        return hm;
    }
    public HashMap getComboItems(String sql) {
        HashMap<String,String> hm = new HashMap<>();
        try {

            sqlQuery(sql);
            while(rs.next()){
                
                hm.put( rs.getString("id_pengajar"),
                        rs.getString("nama_pengajar")
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
            case "id pengajar":
                return "id_pengajar";
            case "nama":
                return "nama_pengajar";
            case "pendidikan" :
                return "pendidikan";
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
                        this.rs.getString("id_pengajar"),
                        this.rs.getString("nama_pengajar"),
                        this.rs.getString("jk"),
                        this.rs.getString("pendidikan"),
                        this.rs.getString("kd_mk")
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
                        this.rs.getString("id_pengajar"),
                        this.rs.getString("nama_pengajar"),
                        this.rs.getString("jk"),
                        this.rs.getString("pendidikan"),
                        this.rs.getString("kd_mk")
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
        model.addColumn("ID");
        model.addColumn("Nama Pengajar");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Pendidikan");
        model.addColumn("KODE MK");
    }

    @Override
    public void setModel(JTable table) {
       table.setModel(model);
    }

    @Override
    public void update(JTable tbl) {
        sql = "UPDATE "+tabel+" SET "
            + " nama_pengajar = '"+mm.getNama()+"', jk = '"+mm.getJk()+"', pendidikan = '"+mm.getPendidikan()+"',"
                + " kd_mk = '"+mm.getKdMK()+"' "
                + "WHERE id_pengajar = '"+mm.getId()+"';";
        sqlUpdate(sql);
        select();
        loadtable(tbl); 
    }

    @Override
    public void insert(JTable tbl) {

            sql = "INSERT INTO "+tabel+"(nama_pengajar,jk,pendidikan,kd_mk) VALUES "
                + "('"+mm.getNama()+"','"+mm.getJk()+"','"+mm.getPendidikan()+"', '"+mm.getKdMK()+"');";
            sqlUpdate(sql);
            select();
            loadtable(tbl);
    }

    @Override
    public void delete(JTable tbl, String kd) {
        sql = " DELETE FROM "+tabel+" WHERE id_pengajar = "+kd;
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
   public void fillCombo(JComboBox combo,String sql){
        clearCombo(combo);
        Iterator<String> it,is;
        it = getComboItems(sql).keySet().iterator();
        is = getComboItems(sql).values().iterator();
        while(it.hasNext()){
            combo.addItem("("+it.next() +") "+ is.next() );
        }
    }
}
