
package Model;

public class Matakuliah {
    private String kdMK;
    private String namaMk;
    private int sks;
    private double harga;

    public Matakuliah() {}
    
    public Matakuliah(String kdMK, String namaMk, int sks, double harga) {
        this.kdMK = kdMK;
        this.namaMk = namaMk;
        this.sks = sks;
        this.harga = harga;
    }

    public String getKdMK() {
        return kdMK;
    }

    public void setKdMK(String kdMK) {
        this.kdMK = kdMK;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    
}
