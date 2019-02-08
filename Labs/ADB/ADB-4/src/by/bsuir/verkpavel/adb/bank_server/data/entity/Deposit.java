package by.bsuir.verkpavel.adb.bank_server.data.entity;

public class Deposit {

	public int type;
	public String contractNumber;
	public int currency;
	public String startDate;
	public String endDate;
	public double sum;
	public double persent;
	public int client;
    public int id;

	public Deposit(String contractNumber, int depositType, String startDate,
			String endDate, int currency, double persent, double depositSum,
			int client) {
		this.contractNumber = contractNumber;
		this.type = depositType;
		this.currency = currency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sum = depositSum;
		this.persent = persent;
		this.client = client;
	}

	public Deposit() {
	}
}
