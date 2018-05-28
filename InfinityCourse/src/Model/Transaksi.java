/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Js
 */
public class Transaksi {
    private int kdTransaksi;
    private String nim;
    private int idPengajar;
    private String tglDaftar;
    private String tglSelesai;

    public Transaksi() {
    }

    
    public Transaksi(String nim, int idPengajar, String tglDaftar, String tglSelesai) {
        this.nim = nim;
        this.idPengajar = idPengajar;
        this.tglDaftar = tglDaftar;
        this.tglSelesai = tglSelesai;
    }

    public int getKdTransaksi() {
        return kdTransaksi;
    }

    public void setKdTransaksi(int kdTransaksi) {
        this.kdTransaksi = kdTransaksi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public int getIdPengajar() {
        return idPengajar;
    }

    public void setIdPengajar(int idPengajar) {
        this.idPengajar = idPengajar;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }
    
    
    
}
