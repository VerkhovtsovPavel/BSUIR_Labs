package by.bsuir.verkpavel.courseproject.logic.tablemodel;

import java.util.List;

import by.bsuir.verkpavel.courseproject.dao.Entity;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.ClientTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.CorporateCarTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.DeliveryTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.DriverLicenceCategoryTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.EmployeeTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.EmptyTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.MarkParcelTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.OfficeTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.ParcelTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.PaymentTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.PaymentsSystemTypeTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.PermissionTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.PositionTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.SalaryTableModel;
import by.bsuir.verkpavel.courseproject.logic.tablemodel.concrete.StreetTableModel;
public class TableModelFactory {

    public static GeneralDeliveryServiceTableModel getTableModel(List<? extends Entity> beans) {
        if(beans.isEmpty()){
            return new EmptyTableModel(beans);
        }
        switch (beans.get(0).getClass().getSimpleName()) { 
        case "City":
            return null;
        case "Client":
            return new ClientTableModel(beans);
        case "CorporateCar":
            return new CorporateCarTableModel(beans);
        case "Delivery":
            return new DeliveryTableModel(beans);
        case "DriverLicenceCategory":
            return new DriverLicenceCategoryTableModel(beans);
        case "Employee":
            return new EmployeeTableModel(beans);
        case "MarkParcel":
            return new MarkParcelTableModel(beans);
        case "Office":
            return new OfficeTableModel(beans);
        case "Parcel":
            return new ParcelTableModel(beans);
        case "Payment":
            return new PaymentTableModel(beans);
        case "PaymentsSystemType":
            return new PaymentsSystemTypeTableModel(beans);
        case "Permission":
            return new PermissionTableModel(beans);
        case "Position":
            return new PositionTableModel(beans);
        case "Street":
            return new StreetTableModel(beans);
        case "Salary":
            return new SalaryTableModel(beans);
        default:
            throw new IllegalArgumentException();
        }
    }
}
