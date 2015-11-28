package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;

public class EmptyTableModel extends GeneralDeliveryServiceTableModel {

    public EmptyTableModel(List<? extends Entity> beans) {
        super(beans);
        beans.add(null); //Workaround to call getValueAt one time
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Внимание!!";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO Don't call fix
        return "В базе данных нет записей по данному аспекту";
    }

}
