
package Model;

public class Mahasiswa extends User {
    private String nim;
    private String jurusan;
    private int semester;

    public Mahasiswa() {}

    public Mahasiswa(String nim, String nama,String jk, int semester ,String jurusan) {
        super(nama, jk);
        this.nim = nim;
        this.jurusan = jurusan;
        this.semester = semester;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

}
