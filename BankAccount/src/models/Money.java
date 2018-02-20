package models;


public class Money {
	
	private double amount = 0.00;
	
	public Money() {}
	
	public Money(double creationAmount) {
		this.setMoney(creationAmount);
	}
	
	public double getValue() {
		return this.amount;
	}
	
	public void setMoney(double setAmount) {
		this.amount = setAmount;
	}
	
}
