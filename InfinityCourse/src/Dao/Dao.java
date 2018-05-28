/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;
/**
 *
 * @author Js
 */
public abstract class Dao {
    protected DefaultTableModel model;
    protected String[] koloms;
    protected String kolom;
    protected String tabel;
    protected String sql;
    protected JComboBox combo;
    
    protected Connection conn = new Koneksi().getKoneksi();
    protected Statement st;
    protected ResultSet rs;
    
    
    public abstract void cari(String key);
    public abstract HashMap getComboItems();
    public abstract String getOrderKolom(String order);
    
    public void Pesan(String text){
        JOptionPane.showMessageDialog(null, text);
    }
    
    public void Pesan(SQLException ex ){
        switch ( ex.getErrorCode() ) {
            case 1054:  Pesan("Kolom tidak ditemukan!"); break;
            case 1451:  Pesan("Data Masih Digunakan Ditransaksi!"); break;
            case 1110:  Pesan("Kolom tidak ditemukan!"); break;
            default: Pesan(this.getClass().getName() +" \n"+ex.getMessage() +" "+ex.getErrorCode());
                break;
        }
    }
    
    public void sqlQuery(String sql){
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Pesan(ex);
        }
    }
    public void sqlUpdate(String sql){
        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
            Pesan("SUKSES!");
        } catch (SQLException ex) {
            Pesan(ex);
        }
    }
    
    public void urutkan(String order){
        order = getOrderKolom(order.toLowerCase());
        if(order!=null){
            sql = "SELECT * FROM "+tabel+" ORDER BY "+order+" ASC ;";
            sqlQuery(sql);
        }else{
            Pesan("Urutkan : Kolom tidak ditemukan!");
        }
    }
    
    public void filter(String textmin,String textmax){
            
        if(textmin.isEmpty()){
            textmin = "0";
        }
        if(textmax.isEmpty()){
            textmax = "99999";
        }
        try{
            int min = Integer.parseInt(textmin);
            int max = Integer.parseInt(textmax);
            
            if(min<0 || max<0){
                Pesan("Masukan Harus Positif!");
            }else if(min>max){
                Pesan("Batas Minimum Harus Lebih Kecil \n"
                        + "Dari Batas Maksimum!");
            }else{
                sql = "SELECT * FROM "+tabel+" WHERE "+kolom+" "
                    + "BETWEEN "+min+" AND "+max+" ;";
                sqlQuery(sql);
            }
            
        }catch(NumberFormatException ex){
            Pesan("Masukkan Harus Angka");
        }
        
    }
    
    public void filter(int min,int max,String kolom){
        
        if(min<0 || max<0){
            Pesan("Masukan Harus Positif!");
        }else if(min>max){
            Pesan("Batas Minimum Harus Lebih Kecil \n"
                    + "Dari Batas Maksimum!");
        }else{
            sql = "SELECT * FROM "+tabel+" WHERE "+kolom+" "
                + "BETWEEN "+min+" AND "+max+" ;";
            sqlQuery(sql);
        }

    }
    
    public void select(){
        sqlQuery("SELECT * FROM "+tabel);
    }
    
    public void fillComboOrder(JComboBox combo){
        clearCombo(combo);
        for(String k : koloms){
            combo.addItem(k);
        }
    }
    
    public void clearCombo(JComboBox combo){
        for(int i = combo.getItemCount()-1 ; i >0 ; i-- ){
            combo.removeItemAt(i);
        }
    }
    
    public void fillCombo(JComboBox combo){
        clearCombo(combo);
        Iterator<String> it;
        it = getComboItems().keySet().iterator();
        while(it.hasNext()){
            combo.addItem(it.next());
        }
    }
    
}
