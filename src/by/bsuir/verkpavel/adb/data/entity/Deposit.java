package by.bsuir.verkpavel.adb.data.entity;

public class Deposit {

	public int depositType;
	public String contractNumber;
	public int currency;
	public String startDate;
	public String endDate;
	public double depositSum;
	public double persent;
	public int client;
    public int id;

	public Deposit(String contractNumber, int depositType, String startDate,
			String endDate, int currency, double persent, double depositSum,
			int client) {
		this.contractNumber = contractNumber;
		this.depositType = depositType;
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.depositSum = depositSum;
		this.persent = persent;
		this.client = client;
	}

	public Deposit() {
	}
}
