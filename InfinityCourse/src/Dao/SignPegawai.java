/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Interface.Sign;
import Model.Pegawai;

/**
 *
 * @author Simamora
 */
public class SignPegawai extends Sign{
    Pegawai mp;
    
    public SignPegawai (){
        mp= new Pegawai();
    }
    
    public SignPegawai (Pegawai mp) {
        this.mp = mp;
    }

    @Override
    public void setSQL(String username) {
        sql = "SELECT password FROM Pegawai Where username = '"+username+"'";
    }

    @Override
    public boolean validasiData() {
        
        return (!mp.getNama().isEmpty() &&
                !mp.getUsername().isEmpty() &&
                !mp.getJk().isEmpty()
                );
    }

    @Override
    public void setSQL() {
        sql = ("Insert Into Pegawai("
                + "username,password,nama_pgw,jk)"
                + " Values("
                +"'"+mp.getUsername()+"',"
                + "md5('"+mp.getPassword()+"'),"
                + "'"+mp.getNama()+"',"
                + "'"+mp.getJk()+"');");    
    }
    
}
