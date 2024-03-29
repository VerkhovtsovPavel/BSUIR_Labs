package by.bsuir.verkpavel.adb.bank_server.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.bsuir.verkpavel.adb.bank_server.data.entity.Client;
import by.bsuir.verkpavel.adb.bank_server.resources.Messages;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ClientProvider {
    private static ClientProvider instance;
    private Connection connection;

    private ClientProvider(Connection connection) {
        this.connection = connection;
    }

    public static ClientProvider getInstance() {
        if (instance == null) {
            instance = new ClientProvider(ConnectionManager.getInstance().getConnection());
        }
        return instance;
    }

    public ArrayList<String> getCityList() {
        ArrayList<String> cities = new ArrayList<String>();
        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `RealCity` FROM `city`;");
            while (rs.next()) {
                cities.add(rs.getString("RealCity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public ArrayList<String> getFamilyStatuses() {
        ArrayList<String> familyStatuses = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Status` FROM `familystatus`;");
            while (rs.next()) {
                familyStatuses.add(rs.getString("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return familyStatuses;
    }

    public ArrayList<String> getNationalitys() {
        ArrayList<String> nationalitys = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Nationality` FROM `nationality`;");
            while (rs.next()) {
                nationalitys.add(rs.getString("Nationality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nationalitys;
    }

    public ArrayList<String> getDisabilitys() {
        ArrayList<String> disabilitys = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT `Disability` FROM `disability`;");
            while (rs.next()) {
                disabilitys.add(rs.getString("Disability"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disabilitys;
    }

    public String saveClient(Client client) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createInsertClientPassportInfoQuery(client));
            statement.executeUpdate(createInsertClientQuery(client));
        } catch (MySQLIntegrityConstraintViolationException e) {
            return Messages.DUBLICATE_PASSPORT_SERIOS_OR_IDENTIFY_NUMBER.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Messages.CLIENT_SUCCESSFULLY_ADDED.get();
    }

    public String updateClient(Client client) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createUpdateClientQuery(client));
            statement.executeUpdate(createUpdateClientPassportInfoQuery(client));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Messages.CLIENT_SUCCESSFULLY_UPDATED.get();
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<Client>();

        Statement statement;
        try {
            statement = connection.createStatement();
            String firstName;
            String lastName;
            String middleName;
            String bornDate;

            boolean isMan;

            String passportSeries;
            String passportNumber;
            String whoGivePassport;
            String passportTakeDate;
            String identifyNumber;
            String bornPlace;

            int realCity;
            String realAddress;
            String homePhone;
            String mobilePhone;
            String eMail;

            String officialAddress;
            int familyStatus;
            int nationality;
            int disability;
            boolean pensioner;
            int salary;

            ResultSet clientsRS = statement
                    .executeQuery("SELECT * FROM user JOIN bank_users.passportinfo WHERE user.id = passportinfo.id;");
            while (clientsRS.next()) {
                firstName = clientsRS.getString("FirstName");
                lastName = clientsRS.getString("LastName");
                middleName = clientsRS.getString("MidleName");
                bornDate = clientsRS.getString("BirthDay");
                isMan = convertIntToBool(clientsRS.getInt("Sex"));
                realCity = clientsRS.getInt("Address_id");
                mobilePhone = clientsRS.getString("MobilePhone");
                homePhone = clientsRS.getString("HomePhone");
                eMail = clientsRS.getString("E-mail");
                familyStatus = clientsRS.getInt("FamilyStatus");
                nationality = clientsRS.getInt("Nationality_id");
                disability = clientsRS.getInt("Disability_id");
                pensioner = convertIntToBool(clientsRS.getInt("Pensioner"));
                salary = clientsRS.getInt("MonthProfit");
                officialAddress = clientsRS.getString("Official Street");
                realAddress = clientsRS.getString("Real Street");
                passportSeries = clientsRS.getString("Serios");
                passportNumber = clientsRS.getString("Number");
                whoGivePassport = clientsRS.getString("WhoGives");
                passportTakeDate = clientsRS.getString("DateGives");
                identifyNumber = clientsRS.getString("IndifyNumber");
                bornPlace = clientsRS.getString("BornPlace");

                Client client = new Client(firstName, lastName, middleName, bornDate, isMan, passportSeries,
                        passportNumber, whoGivePassport, passportTakeDate, identifyNumber, bornPlace, realCity,
                        realAddress, homePhone, mobilePhone, eMail, officialAddress, familyStatus, nationality,
                        disability, pensioner, salary);

                client.setId(clientsRS.getInt("id"));

                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;

    }

    private String createUpdateClientQuery(Client client) {
        return String
                .format("UPDATE `user` SET `FirstName` =\"%s\", `LastName` = '%s', `MidleName` = '%s', `Birthday` = '%s', `Sex` = '%d', `Address_id` = '%d', `MobilePhone` = '%s', `HomePhone` = '%s', `E-mail` = '%s', `FamilyStatus` = '%d', `Nationality_id` = '%d', `Disability_id` = '%d', `Pensioner` = '%d', `MonthProfit` = '%s', `Official Street` = '%s', `Real Street` = '%s' WHERE `id` = '%d'",
                        client.firstName, client.lastName, client.middleName, client.bornDate,
                        convertBoolToInt(client.isMan), client.realCity + 1, client.mobilePhone, client.homePhone,
                        client.eMail, client.familyStatus + 1, client.nationality + 1, client.disability + 1,
                        convertBoolToInt(client.pensioner), client.salary, client.officialAddress, client.realAddress,
                        client.id);

    }

    private String createUpdateClientPassportInfoQuery(Client client) {
        return String
                .format("UPDATE `passportinfo` SET `Serios` = '%s', `Number` = '%s', `WhoGives` = '%s', `DateGives` = '%s', `IndifyNumber` = '%s', `BornPlace` = '%s'WHERE `id` = '%d'; ",
                        client.passportSeries, client.passportNumber, client.whoGivePassport, client.passportTakeDate,
                        client.identifyNumber, client.bornPlace, client.id);
    }

    private String createInsertClientPassportInfoQuery(Client client) {
        return String
                .format("INSERT INTO `passportinfo` (`id`, `Serios`, `Number`, `WhoGives`, `DateGives`, `IndifyNumber`, `BornPlace`) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s' );",
                        client.passportSeries, client.passportNumber, client.whoGivePassport, client.passportTakeDate,
                        client.identifyNumber, client.bornPlace);
    }

    private String createInsertClientQuery(Client client) {
        return String
                .format("INSERT INTO `user`  (`id`, `FirstName`, `LastName`, `MidleName`, `Birthday`, `Sex`, `Address_id`, `MobilePhone`, `HomePhone`, `E-mail`, `FamilyStatus`, `Nationality_id`, `Disability_id`, `Pensioner`, `MonthProfit`, `Official Street`, `Real Street`) VALUES (NULL, '%s', '%s', '%s', '%s', '%d', '%d', '%s', '%s', '%s', '%d', '%d', '%d', '%d', '%d', '%s', '%s');",
                        client.firstName, client.lastName, client.middleName, client.bornDate,
                        convertBoolToInt(client.isMan), client.realCity + 1, client.mobilePhone, client.homePhone,
                        client.eMail, client.familyStatus + 1, client.nationality + 1, client.disability + 1,
                        convertBoolToInt(client.pensioner), client.salary, client.officialAddress, client.realAddress);
    }

    private int convertBoolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    private boolean convertIntToBool(int integer) {
        return integer == 0 ? false : true;
    }

    public void deleteClient(Client client) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `user` WHERE `id` = " + client.id + ";");
            statement.executeUpdate("DELETE FROM `passportinfo` WHERE `id` = " + client.id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getUserFullNames() {
        ArrayList<String> clients = new ArrayList<String>();

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet clientsRS = statement.executeQuery("SELECT * FROM bank_users.user;");
            while (clientsRS.next()) {
                clients.add(String.format("%s %s %s %d", clientsRS.getString("LastName"),
                        clientsRS.getString("FirstName"), clientsRS.getString("MidleName"), clientsRS.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;

    }
}
