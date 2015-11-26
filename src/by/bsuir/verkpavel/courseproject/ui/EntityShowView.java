package by.bsuir.verkpavel.courseproject.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.TableModelFactory;

public class EntityShowView extends JFrame {
    private static final long serialVersionUID = 2883993883146596569L;

    public EntityShowView(List<? extends Entity> beans) {
        super("Просмотр");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 50+20*beans.size()));
        TableModel model = TableModelFactory.getTableModel(beans);
        JTable table = new JTable(model);
        getContentPane().add(new JScrollPane(table));
    }
    
    public void showView(){
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}




