package observer;

import java.util.Comparator;

public class Company extends Observer {
	
	private String id;
	private int numOfShares;
	private double priceOfShare;

	private int counter;
	private double capital;
	
	public int sharesSold;

	MarketData data = MarketData.getInstance();
	
	public Company(String id, int numOfShares, double priceOfShare, Subject subject) {
		this.id = id;
		this.numOfShares = numOfShares;
		this.priceOfShare = priceOfShare;
		counter = 0;
		
		capital = numOfShares * priceOfShare;
		
		sharesSold = 0;
		
		this.subject = subject;
	    this.subject.attach(this);
	      
		System.out.println(toString());
	}
	
	public boolean sellShare() {
		if(numOfShares > 0) {
			counter++;
			sharesSold++;
			return true;
		}
		return false;
	}
	
	public boolean increasePriceOfShare() {
		if(counter >= 10) {
			priceOfShare = priceOfShare*2;
			counter = 0;
//			System.out.println("price increased for a company");
			return true;
		}
		return false;
	}
	
	public static Comparator<Company> Capital = new Comparator<Company>() {

		public int compare(Company s1, Company s2) {

		   double capital1 = s1.capital;
		   double capital2 = s2.capital;

		   return (int) (capital2 - capital1);
	   }};

	@Override
	public void update() {
		//data.setTotalMoneySpent(data.getTotalMoneySpent() + priceOfShare);
		if(sharesSold <= 0) {
			priceOfShare = priceOfShare * 0.8;
			System.out.println("Price down to " + priceOfShare);
		}
		updateCheapestShare();
	}
	
	private void updateCheapestShare() {
		if(data.getCheapestStockPrice() > priceOfShare || data.getCheapestStockPrice() == 0) {
			data.setCheapestStockPrice(priceOfShare);
		}
	}
	
	public void updateData() {
		updateCheapestShare();
		data.setTotalShares(data.getTotalShares() + numOfShares);
		System.out.println("Company update");
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
}
