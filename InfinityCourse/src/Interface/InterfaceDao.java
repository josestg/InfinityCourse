
package Interface;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public interface InterfaceDao {
    
    public int loadtable (JTable table);
    public DefaultTableModel getModel();
    public void createTable();
    public void setModel(JTable table);
    
}
