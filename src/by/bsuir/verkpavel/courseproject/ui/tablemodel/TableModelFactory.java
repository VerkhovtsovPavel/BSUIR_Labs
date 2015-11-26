package by.bsuir.verkpavel.courseproject.ui.tablemodel;

import java.util.List;

import javax.swing.table.TableModel;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.DriverLicenceCategoryTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.EmployeeTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.StreetTableModel;

public class TableModelFactory {

    public static TableModel getTableModel(List<Entity> beans) {
        switch (beans.get(0).getClass().getSimpleName()) {
        case "Authentication":
            return null;
        case "City":
            return null;
        case "Client":
            return null;
        case "CorporateСar":
            return null;
        case "Delivery":
            return null;
        case "Driver":
            return null;
        case "DriverLicenceCategory":
            return new DriverLicenceCategoryTableModel(beans);
        case "DriverLicense":
            return null;
        case "Employee":
            return new EmployeeTableModel(beans);
        case "MarkParcel":
            return null;
        case "Office":
            return null;
        case "Parcel":
            return null;
        case "ParcelM2mDelivery":
            return null;
        case "Payment":
            return null;
        case "PaymentsSystemType":
            return null;
        case "Permission":
            return null;
        case "Position":
            return null;
        case "Street":
            return new StreetTableModel(beans);
        default:
            throw new IllegalArgumentException();
        }
    }

}
