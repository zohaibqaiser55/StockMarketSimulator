package observer;

import java.util.Comparator;

public class Investor extends Observer{
	
	private String id;
	private double budget;
	private int sharesBought;
	
	MarketData data = MarketData.getInstance();
	
	public Investor(String id, int budget, Subject subject) {
		this.id = id;
		this.budget = budget;
		sharesBought = 0;
		
		this.subject = subject;
	    this.subject.attach(this);
	      
		System.out.println(toString());
	}
	
	public boolean buyShare(Company company) {
		if(company.sellShare() && budget >= company.getPriceOfShare()) {
			sharesBought++;
			budget -= company.getPriceOfShare();
			return true;
		}
		return false;		
	}
	
	public static Comparator<Investor> Shares = new Comparator<Investor>() {

		public int compare(Investor s1, Investor s2) {

		   int shares1 = s1.sharesBought;
		   int shares2 = s2.sharesBought;

		   return (shares2 - shares1);
	   }};
	
	public void updateData() {
		data.setTotalMoney(data.getTotalMoney() + budget);
	}
	
	@Override
	public void update() {
		//data.setTotalSharesBought(data.getTotalSharesBought() + 1);	
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
	
	public String toString() {
		return id + " " + budget + " " + sharesBought;
	}	
}
