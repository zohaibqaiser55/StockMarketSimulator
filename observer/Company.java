package observer;


public class Company extends Observer {
	
	private String id;
	private int numOfShares;
	private double priceOfShare;
	private int counter;
	private double capital;
	
	public int sharesSold;

	MarketData data = MarketData.getInstance();
	
	public Company(String id, int numOfShares, double priceOfShare) {
		this.id = id;
		this.numOfShares = numOfShares;
		this.priceOfShare = priceOfShare;
		counter = 0;
		
		capital = numOfShares * priceOfShare;
		
		sharesSold = 0;
		System.out.println(toString());
	}
	
	@Override
	public void update() {
		if(data.getCheapestStockPrice() > priceOfShare) {
			data.setCheapestStockPrice(priceOfShare);
		}
		
		data.setTotalShares(data.getTotalShares() + numOfShares);
	}

	public String toString() {
		return id + " " + numOfShares + " " + priceOfShare;
	}
}
