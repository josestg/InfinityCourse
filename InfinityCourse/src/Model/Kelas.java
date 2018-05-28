
package Model;

public class Kelas {
    private String kdKelas;
    private int kapasitas;
    private String jadwal;

    public Kelas() {
    }

    public Kelas(String kdKelas, int kapasitas, String jadwal) {
        this.kdKelas = kdKelas;
        this.kapasitas = kapasitas;
        this.jadwal = jadwal;
    }
    
    
    public String getKdKelas() {
        return kdKelas;
    }

    public void setKdKelas(String kdKelas) {
        this.kdKelas = kdKelas;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }
    
    
}
