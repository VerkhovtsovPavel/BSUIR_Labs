package by.bsuir.verkpavel.adb.server.data.entity;

public class Client {
    public String firstName;
    public String lastName;
    public String middleName;
    public String bornDate;

    public boolean isMan;

    public String passportSeries;
    public String passportNumber;
    public String whoGivePassport;
    public String passportTakeDate;
    public String identifyNumber;
    public String bornPlace;

    public int realCity;
    public String realAddress;
    public String homePhone;
    public String mobilePhone;
    public String eMail;

    public String officialAddress;
    public int familyStatus;
    public int nationality;
    public int disability;
    public boolean pensioner;

    public int salary;
    
    public int id;

    public Client(String firstName, String lastName, String middleName, String bornDate,
            boolean isMan, String passportSeries, String passportNumber, String whoGivePassport,
            String passportTakeDate, String identifyNumber, String bornPlace, int realCity,
            String realAddress, String homePhone, String mobilePhone, String eMail,
            String officialAddress, int familyStatus, int nationality, int disability,
            boolean pensioner, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.bornDate = bornDate;

        this.isMan = isMan;

        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.whoGivePassport = whoGivePassport;
        this.passportTakeDate = passportTakeDate;
        this.identifyNumber = identifyNumber;
        this.bornPlace = bornPlace;

        this.realCity = realCity;
        this.realAddress = realAddress;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.eMail = eMail;

        this.officialAddress = officialAddress;
        this.familyStatus = familyStatus;
        this.nationality = nationality;
        this.disability = disability;
        this.pensioner = pensioner;

        this.salary = salary;
    }

    public Client(int id, String firstName, String lastName,
			String middleName, String bornDate, boolean isMan,
			String passportSeries, String passportNumber,
			String whoGivePassport, String passportTakeDate,
			String identifyNumber, String bornPlace, int realCity,
			String realAddress, String homePhone, String mobilePhone,
			String eMail, String officialAddress, int familyStatus,
			int nationality, int disability, boolean pensioner, int salary) {
		this(firstName, lastName, middleName, bornDate, isMan, passportSeries, passportNumber, whoGivePassport, passportTakeDate, identifyNumber, bornPlace, realCity, realAddress, homePhone, mobilePhone, eMail, officialAddress, familyStatus, nationality, disability, pensioner, salary);
		this.id = id;
	}

	public void setId(int id) {
        this.id = id;
        
    }
}
