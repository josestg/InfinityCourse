
package Model;

public class User {
    private String nama;
    private String jk;

    public User(){}
    
    public User(String nama, String jk) {
        this.nama = nama;
        this.jk = jk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }
     
}
