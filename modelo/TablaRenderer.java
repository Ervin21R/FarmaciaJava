package modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            if (row% 2 == 0) {
                setHorizontalAlignment(SwingConstants.CENTER);
                setBackground(new Color (163,199,229));
            } else if (row% 2 != 0) {
                setHorizontalAlignment(SwingConstants.CENTER);
                setBackground(Color.white);
            }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
