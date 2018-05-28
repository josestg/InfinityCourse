
package Interface;

import javax.swing.JTable;

public interface InterfaceDM {
    public void update(JTable tbl);
    public void insert(JTable tbl);
    public void delete(JTable tbl,String kd);
}
