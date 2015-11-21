package by.bsuir.verkpavel.courseproject.ui.tablemodel;

import java.util.List;

import javax.swing.table.TableModel;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.entity.EmployeeTableModel;

public class TableModelFactory {

    public static TableModel getTableModel(List<Entity> beans) {
        switch (beans.get(0).getClass().getSimpleName()) {
        case "Authentication":
            return null;
        case "City":
            return null;
        case "Client":
            return null;
        case "Corporatecar":
            return null;
        case "Delivery":
            return null;
        case "Driver":
            return null;
        case "Driverlicencecategory":
            return null;
        case "Driverlicense":
            return null;
        case "Employee":
            return new EmployeeTableModel(beans);
        case "Markparcel":
            return null;
        case "Office":
            return null;
        case "Parcel":
            return null;
        case "ParcelM2mDelivery":
            return null;
        case "Payment":
            return null;
        case "Paymentssystemtype":
            return null;
        case "Permission":
            return null;
        case "Position":
            return null;
        case "Street":
            return null;
        case "StreetPK":
            return null;
        default:
            throw new IllegalArgumentException();
        }
    }

}
