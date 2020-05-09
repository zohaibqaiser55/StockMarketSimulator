package observer;


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

	@Override
	public void update() {
		if(sharesSold <= 0) {
			priceOfShare = priceOfShare * 0.8;
			updateCheapestShare();
		}
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
}
