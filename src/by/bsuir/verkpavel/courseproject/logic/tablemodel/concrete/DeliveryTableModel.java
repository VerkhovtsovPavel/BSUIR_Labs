package by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.DeliveryServiceDao;
import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.dao.entity.Delivery;
import by.bsuir.verkpavel.courseproject.dao.entity.Parcel;
import by.bsuir.verkpavel.courseproject.dao.entity.ParcelM2MDelivery;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.GeneralDeliveryServiceTableModel;
import by.bsuir.verkpavel.courseproject.resources.ProjectProperties;
import by.bsuir.verkpavel.courseproject.ui.EntityShowView;

public class DeliveryTableModel extends GeneralDeliveryServiceTableModel {

    public DeliveryTableModel(List<? extends Entity> beans) {
        super(beans);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 8;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Номер";
        case 1:
            return "Офис отправки";
        case 2:
            return "Офис доставки";
        case 3:
            return "Дата отправки";
        case 4:
            return "Дата доставки";
        case 5:
            return "Статус";
        case 6:
            return "Машина";
        case 7:
            return "Водитель";
        }
        return "";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Delivery bean = (Delivery) getBeans().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bean.getIdDelivery();
        case 1:
            return bean.getFromOffice().getDescription();
        case 2:
            return bean.getToOffice().getDescription();
        case 3:
            return ProjectProperties.getDateFormatter().format(bean.getStartDate());
        case 4:
            return ProjectProperties.getDateFormatter().format(bean.getEndDate());
        case 5:
            return bean.getDeliveryStatus().getDescription();
        case 6:
            return bean.getCorporateCar().getNumber();
        case 7:
            return bean.getDriver().getEmployee().getFullName();
        }
        return "";
    }

    @Override
    public void processClick(int row, int column) {
        Delivery delivery = (Delivery) getBeans().get(row);
        List<ParcelM2MDelivery> p2d = DeliveryServiceDao.getInstance().getAllRecord(ParcelM2MDelivery.class);
        List<Parcel> bookedParcel = new ArrayList<Parcel>();
        for (ParcelM2MDelivery pm2md : p2d) {
            if (pm2md.getDelivery().equals(delivery)) {
                bookedParcel.add(pm2md.getParcel());
            }
        }
        new EntityShowView(bookedParcel).showView();
    }

}
