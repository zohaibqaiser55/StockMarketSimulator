package observer;

import java.util.Comparator;

public class Investor{
	
	private String id;
	private double budget;
	private int sharesBought;

	MarketData data = MarketData.getInstance();
	
	public Investor(String id, int budget, Subject subject) {
		this.id = id;
		this.budget = budget;
		sharesBought = 0;
	}
	
	public boolean buyShare(Company company) {
		if(company.sellShare() && budget >= company.getPriceOfShare()) {
			sharesBought++;
			budget -= company.getPriceOfShare();
			return true;
		}
		return false;		
	}
	
	// This lamda function is used to sort investors based on number of shares bought
	public static Comparator<Investor> Shares = new Comparator<Investor>() {

		public int compare(Investor s1, Investor s2) {

		   int shares1 = s1.sharesBought;
		   int shares2 = s2.sharesBought;

		   return (shares2 - shares1);
	   }};
	
	public void updateData() {
		data.setTotalMoney(data.getTotalMoney() + budget);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public int getSharesBought() {
		return sharesBought;
	}
	
	public String toString() {
		return id + " " + budget + " " + sharesBought;
	}	
}
