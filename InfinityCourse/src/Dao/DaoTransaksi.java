/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.InterfaceDM;
import Interface.InterfaceDao;
import Model.Matakuliah;
import Model.Transaksi;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DaoTransaksi extends Dao implements InterfaceDao,InterfaceDM{
    private Transaksi mk;
    String sqli ="SELECT kd_transaksi, tgl_transaksi ,transaksi.nim,nama_mhs,jurusan,\n" +
                    "nama_pengajar, nama_mk, sks, harga, (sks*harga) as biaya\n" +
                    "FROM transaksi LEFT JOIN mahasiswa ON\n" +
                    "transaksi.nim = mahasiswa.nim left join pengajar on\n" +
                    "transaksi.id_pengajar = pengajar.id_pengajar left join matakuliah\n" +
                     "on pengajar.kd_mk = matakuliah.kd_mk ";
    
    public DaoTransaksi(JTable table){
        this.tabel = "Transaksi";
        koloms = new String[] {"Tanggal","NIM","ID Pengajar"};
        kolom = "tgl_transaksi";
        
        sqlQuery(sqli);
        loadtable(table);
    }
    
    
    public DaoTransaksi(){
        this.tabel = "Transaksi";
        koloms = new String[] {"Tanggal","NIM","ID Pengajar"};
        kolom = "tgl_transaksi";
        sqlQuery(sqli);
    }
    
    public void setTransaksi(Transaksi t){
        this.mk = t;
    }

    @Override
    public void cari(String key) {
        sql = "SELECT * FROM Transaksi WHERE tgl_transaksi LIKE '%"+key+"%'"
                + "OR nim LIKE '%"+key+"%' "
                + "OR  id_pengajar LIKE '%"+key+"%'";
        sqlQuery(sql);
    }
    

    public void cariGabung(String key) {
        sql = sqli + " WHERE tgl_transaksi LIKE '%"+key+"%' OR "
                + "transaksi.nim LIKE '%"+key+"%' OR "
                + "nama_mhs LIKE '%"+key+"%' OR "
                + "nama_pengajar LIKE '%"+key+"%' OR "
                + "nama_mk LIKE '%"+key+"%' OR "
                + "harga LIKE '%"+key+"%' "
                + " ;";
        sqlQuery(sql);
        
        
    }
    
    
    @Override
    public HashMap getComboItems(){       
        HashMap<String,String> hm = new HashMap<>();
        return hm;
    }


    @Override
    public String getOrderKolom(String order) {
        return "";
    }
    
    public static void main(String[] args) {
        DaoMatakuliah d = new  DaoMatakuliah(); 
    }
    
    public void reload(JTable table){
        sqlQuery(sqli);
       loadtable(table);
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
                        this.rs.getString("kd_transaksi"),
                        this.rs.getString("tgl_transaksi"),
                        this.rs.getString("nim"),
                        this.rs.getString("nama_mhs"),
                        this.rs.getString("nama_pengajar"),
                        this.rs.getString("nama_mk"),
                        this.rs.getString("sks"),
                        this.rs.getString("harga"),
                        this.rs.getString("biaya")
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
                        this.rs.getString("kd_transaksi"),
                        this.rs.getString("tgl_transaksi"),
                        this.rs.getString("nim"),
                        this.rs.getString("nama_mhs"),
                        this.rs.getString("nama_pengajar"),
                        this.rs.getString("nama_mk"),
                        this.rs.getString("sks"),
                        this.rs.getString("harga"),
                        this.rs.getString("biaya")
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
        model.addColumn("Kode Transaksi");
        model.addColumn("Tanggal Transaksi");
        model.addColumn("NIM");
        model.addColumn("Nama Mahasiswa");
        model.addColumn("Nama Pengajar");
        model.addColumn("Matakuliah");
        model.addColumn("SKS");
        model.addColumn("Harga/SKS");
        model.addColumn("Biaya");
    }

    @Override
    public void setModel(JTable table) {
         table.setModel(model);
    }
    
    @Override
    public void insert(JTable table){
        
            sql = "INSERT INTO "+tabel+"(tgl_transaksi,nim,id_pengajar) VALUES "
                + "('"+mk.getTglDaftar()+"','"+mk.getNim()+"','"+mk.getIdPengajar()+"');";
            sqlUpdate(sql);
    }
    public void insert(){
        
            sql = "INSERT INTO "+tabel+"(tgl_transaksi,nim,id_pengajar) VALUES "
                + "('"+mk.getTglDaftar()+"','"+mk.getNim()+"','"+mk.getIdPengajar()+"');";
            sqlUpdate(sql);
    }
    @Override
    public void update(JTable tbl){

    }
    
    @Override
    public void delete(JTable tbl,String kd){
        sql = " DELETE FROM "+tabel+" WHERE kd_transaksi = '"+kd+"' ;";
        sqlUpdate(sql);
        reload(tbl);
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
