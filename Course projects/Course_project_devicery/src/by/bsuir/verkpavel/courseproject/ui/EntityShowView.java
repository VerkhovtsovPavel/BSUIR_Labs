package by.bsuir.verkpavel.courseproject.ui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.TableModelFactory;
import by.bsuir.verkpavel.courseproject.resources.Messages;

public class EntityShowView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;
    private boolean isDelete;

    public EntityShowView(List<? extends Entity> beans) {
        super("Просмотр");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 70 + 20 * beans.size()));
        GeneralDeliveryServiceTableModel model = TableModelFactory.getTableModel(beans);
        JTable table = new JTable(model);
        createDoubleClickListener(table);
        getContentPane().add(new JScrollPane(table));
    }

    public EntityShowView(List<? extends Entity> beans, boolean isDelete) {
        this(beans);
        this.isDelete = isDelete;
    }

    private void createDoubleClickListener(final JTable table) {
        final GeneralDeliveryServiceTableModel model = (GeneralDeliveryServiceTableModel) table.getModel();
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    int selectedColumns = table.getSelectedColumn();
                    if (isDelete) {
                        int result = JOptionPane.showConfirmDialog(EntityShowView.this, Messages.CONFIRM_DELETE.get());
                        if (result == JOptionPane.OK_OPTION) {
                            if(!model.processDelete(selectedRow, selectedColumns)){
                                JOptionPane.showMessageDialog(null, Messages.ERROR_WHILE_DELETE_RECORD.get(), "Error",
                                        JOptionPane.PLAIN_MESSAGE);
                            }else{
                                table.repaint();
                            }
                        }
                    } else {
                        model.processClick(selectedRow, selectedColumns);
                    }
                }
            }
        });
    }

    public void showView() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
