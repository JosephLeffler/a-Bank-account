package bank;

import java.util.List;

import exceptions.InsufficiantFundsException;
import exceptions.NotOwnerOfAccountException;
import models.Money;
import models.Person;

public class Bank implements Account {

	private Person accountholder;
	private String accountName;
	private Money balance = new Money(0.00);

	
	@Override
	public Account CreateAccount(Person customer, Money initialDeposit) {
		try {
			customer.removeLiquidAssets(initialDeposit);
		} catch (InsufficiantFundsException e) {
			e.printStackTrace();
		}
		Bank newAccount = new Bank();
		newAccount.setAccountName(customer);
		newAccount.setAccountHolder(customer);
		newAccount.Deposit(newAccount, initialDeposit);
		customer.addAccount(newAccount);
		return newAccount;
	}


	@Override
	public Account[] GetAccountsForCustomer(Person customer) {
		List<Account> bankAccounts = customer.getAccounts();
		if(bankAccounts.isEmpty()) {
			return new Account[0];
		}
		return bankAccounts.toArray(new Account[bankAccounts.size()]);
	}

	@Override
	public void Deposit(Account to, Money amount) {
		Bank destination = (Bank) to;
		try {
			if(checkAccountOwnership(this, destination)){
				this.accountholder.removeLiquidAssets(amount);
			}
			}catch (InsufficiantFundsException e) {
				e.printStackTrace();
			}catch (NotOwnerOfAccountException e) {
				e.printStackTrace();
		}
			destination.setBalance(destination.getBalance().getValue()+amount.getValue());
	}

	@Override
	public void Withdraw(Account from, Money amount) {
		Bank origin = (Bank) from;
		try {
			if(checkAccountOwnership(this, (Bank) from)){
				if(origin.getBalance().getValue() < amount.getValue()) {
					throw new InsufficiantFundsException("Not enough Funds in account");
				}
			}
			}catch (NotOwnerOfAccountException e) {
				e.printStackTrace();
			} catch (InsufficiantFundsException e) {
				e.printStackTrace();
		}
			origin.setBalance(amount.getValue() + balance.getValue());
	}

	@Override
	public void Transfer(Account from, Account to, Money amount) {
		Withdraw(from, amount);
		Deposit(to, amount);
	}
	
	
	
	private void setAccountName(Person customer) {
		customer.increamentSerial();
		accountName = customer.getName()+" "+customer.getSerialNum();
	}
	
	private void setAccountHolder(Person customer) {
		this.accountholder = customer;
	}
	
	private void setBalance(double amount) {
		this.balance.setMoney(amount);
	}
	
	private boolean checkAccountOwnership(Bank alpha, Bank beta) throws NotOwnerOfAccountException {
		if(alpha.getAccountHolder().getName().equals(beta.getAccountHolder().getName())){
			return true;
		}
		throw new NotOwnerOfAccountException("Incorrect account holder for this account.");
	}
	
	public Person getAccountHolder() {
		return accountholder;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public Money getBalance() {
		return balance;
	}
	
	
	
	
	
}
