package bank;
import models.*;

public interface Account {
	
	public Account CreateAccount(Person customer, Money initialDeposit);
	
	public Account[] GetAccountsForCustomer(Person customer);
	
	void Deposit(Account to, Money amount);
	
	void Withdraw(Account from, Money amount);
	
	void Transfer(Account from, Account to, Money amount);
}
