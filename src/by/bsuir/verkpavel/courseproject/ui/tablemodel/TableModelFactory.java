package by.bsuir.verkpavel.courseproject.ui.tablemodel;

import java.util.List;

import javax.swing.table.TableModel;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.ClientTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.CorporateCarTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.DeliveryTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.DriverLicenceCategoryTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.EmployeeTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.EmptyTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.MarkParcelTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.OfficeTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.ParcelTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.PermissionTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.PositionTableModel;
import by.bsuir.verkpavel.courseproject.ui.tablemodel.concrete.StreetTableModel;
//TODO Fix null returning
public class TableModelFactory {

    public static TableModel getTableModel(List<? extends Entity> beans) {
        if(beans.isEmpty()){
            return new EmptyTableModel(beans);
        }
        switch (beans.get(0).getClass().getSimpleName()) { 
        case "Authentication":
            return null;
        case "City":
            return null;
        case "Client":
            return new ClientTableModel(beans);
        case "CorporateСar":
            return new CorporateCarTableModel(beans);
        case "Delivery":
            return new DeliveryTableModel(beans);
        case "Driver":
            return null;
        case "DriverLicenceCategory":
            return new DriverLicenceCategoryTableModel(beans);
        case "DriverLicense":
            return null;
        case "Employee":
            return new EmployeeTableModel(beans);
        case "MarkParcel":
            return new MarkParcelTableModel(beans);
        case "Office":
            return new OfficeTableModel(beans);
        case "Parcel":
            return new ParcelTableModel(beans);
        case "Payment":
            return null;
        case "PaymentsSystemType":
            return null;
        case "Permission":
            return new PermissionTableModel(beans);
        case "Position":
            return new PositionTableModel(beans);
        case "Street":
            return new StreetTableModel(beans);
        default:
            throw new IllegalArgumentException();
        }
    }
}
