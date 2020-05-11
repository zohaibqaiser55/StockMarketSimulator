package observer;

import java.util.Comparator;

// This class uses Observer Pattern to synchronise stock prices
public class Company extends Observer {
	
	private String id;
	private int numOfShares;
	private double priceOfShare;
	private double capital;
	
	private int sharesSold;

	MarketData data = MarketData.getInstance();
	
	public Company(String id, int numOfShares, double priceOfShare, Subject subject) {
		this.id = id;
		this.numOfShares = numOfShares;
		this.priceOfShare = priceOfShare;
		
		capital = numOfShares * priceOfShare;
		
		sharesSold = 0;
		
		this.subject = subject;
	    this.subject.attach(this);
	}
	
	public boolean sellShare() {
		if(numOfShares > 0) {
			return true;
		}
		return false;
	}
	
	public boolean increasePriceOfShare() {
		if(sharesSold % 10 == 0) {
			priceOfShare = priceOfShare*2;
			return true;
		}
		return false;
	}
	
	// This lamda function is used for sorting based on Capital
	public static Comparator<Company> Capital = new Comparator<Company>() {

		public int compare(Company s1, Company s2) {

		   double capital1 = s1.capital;
		   double capital2 = s2.capital;

		   return (int) (capital2 - capital1);
	   }};
	   
	// This lamda function is used for sorting based on Capital
	public static Comparator<Company> SharePrice = new Comparator<Company>() {

		public int compare(Company s1, Company s2) {

		   double capital1 = s1.priceOfShare;
		   double capital2 = s2.priceOfShare;

		   return (int) (capital1 - capital2);
	   }};


	@Override
	public void update() {
		if(sharesSold <= 0) {
			priceOfShare = priceOfShare * 0.98;
			capital = numOfShares * priceOfShare;
		}
	}
	
	public double getCapital() {
		return capital;
	}
	
	public void updateData() {
		data.setTotalShares(data.getTotalShares() + numOfShares);
		data.setTotalCapital(data.getTotalCapital() + (numOfShares * priceOfShare));
	}

	public String toString() {
		return id + " " + numOfShares + " " + priceOfShare;
	}
	
	public double getPriceOfShare() {
		return priceOfShare;
	}

	public void setPriceOfShare(double priceOfShare) {
		this.priceOfShare = priceOfShare;
	}
	
	public int getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}

	public int getSharesSold() {
		return sharesSold;
	}

	public void setSharesSold(int sharesSold) {
		this.sharesSold = sharesSold;
	}
}
