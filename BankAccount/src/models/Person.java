package models;

import java.util.ArrayList;
import java.util.List;

import bank.Account;
import bank.Bank;
import exceptions.InsufficiantFundsException;

public class Person {
	private String name = "";
	private int customerSerial = 0;
	private Money assets = new Money(0.00);
	private List<Account> bankAccounts = new ArrayList<Account>();
	
	public Person(String name, Money assets) {
		this.name = name;
		this.assets = assets;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSerialNum() {
		return customerSerial;
	}
	
	public void increamentSerial() {
		customerSerial++;
	}
	
	public Money getAssets() {
		return assets;
	}
	
	public void addLiquidAssets(Money amount) {
		 assets.setMoney(assets.getValue()+amount.getValue());
	}
	
	public void removeLiquidAssets(Money initialDeposit) throws InsufficiantFundsException{
		if(initialDeposit.getValue() > assets.getValue()) {
			throw new InsufficiantFundsException("Person does not have enough assets");
		}
		assets.setMoney(assets.getValue()-initialDeposit.getValue());
	}
	
	public List<Account> getAccounts() {
		return bankAccounts;
	}
	
	public void addAccount(Bank newAccount ) {
		bankAccounts.add(newAccount);
	}
	
}
