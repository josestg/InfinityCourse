
package Model;

public class Pengajar extends User {
    
    private int id;
    private String kdMK;
    private String pendidikan;

    public Pengajar() {}

    public Pengajar(int id, String kdMK, String nama, String jk,String pendidikan) {
        super(nama, jk);
        this.id = id;
        this.kdMK = kdMK;
    }
    public Pengajar(String kdMK, String nama, String jk,String pendidikan) {
        super(nama, jk);
        this.kdMK = kdMK;
        this.pendidikan = pendidikan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKdMK() {
        return kdMK;
    }

    public void setKdMK(String kdMK) {
        this.kdMK = kdMK;
    }

}
