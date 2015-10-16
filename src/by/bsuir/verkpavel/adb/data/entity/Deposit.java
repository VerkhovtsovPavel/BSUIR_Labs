package by.bsuir.verkpavel.adb.data.entity;

public class Deposit {

    public int depositType;
    public String contractNumber;
    public int currency;
    public String startDate;
    public String endDate;
    public long depositPeriod;
    public double depositSum;
    public double persent;
    public int client;

    
	public Deposit(String contractNumber, int depositType, String startDate, String endDate,
			long depositPeriod2, int currency, double persent, long depositSum, int client) {
		this.contractNumber = contractNumber;
		this.depositType = depositType;
		this.currency = currency;
	    this.startDate = startDate;
	    this.endDate = endDate;
	    this.depositPeriod = depositPeriod2;
	    this.depositSum = depositSum;
	    this.persent = persent;
	    this.client = client;	
	}


    public Deposit() {
    }
}
