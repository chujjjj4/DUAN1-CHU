package View;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Kiểm tra hàng để áp dụng màu cho toàn bộ hàng
        if (row < 2) {
            cellComponent.setBackground(Color.RED);
            cellComponent.setForeground(Color.BLACK); // Đặt màu chữ cho hàng trắng là màu đen
        } else if (row < 5) {
            cellComponent.setBackground(Color.YELLOW);
            cellComponent.setForeground(Color.BLACK); // Đặt màu chữ cho hàng trắng là màu đen
        } else {
            cellComponent.setBackground(Color.WHITE);
            cellComponent.setForeground(Color.BLACK); // Đặt màu chữ cho hàng trắng là màu đen
        }

        return cellComponent;
    }
}
