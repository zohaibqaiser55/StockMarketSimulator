package observer;

// Singleton Pattern is implemented in this class
public class MarketData {
	
	private int totalShares;
	private double totalCapital;
	
	public double getTotalCapital() {
		return totalCapital;
	}

	public void setTotalCapital(double totalCapital) {
		this.totalCapital = totalCapital;
	}

	private double totalMoney;
	
	private double totalMoneySpent;
	private int totalSharesBought;
	
	private double cheapestStockPrice;

   //create an object of MarketData
   private static MarketData instance = new MarketData();

   //make the constructor private so that this class cannot be instantiated
   private MarketData(){
	   setTotalShares(0);
	   setTotalMoneySpent(0);
	   setCheapestStockPrice(0);
   }

   //Get the only object available
   public static MarketData getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }

public double getTotalMoneySpent() {
	return totalMoneySpent;
}

public void setTotalMoneySpent(double totalMoneySpent) {
	this.totalMoneySpent = totalMoneySpent;
}

public int getTotalShares() {
	return totalShares;
}

public void setTotalShares(int totalStocks) {
	this.totalShares = totalStocks;
}

public double getCheapestStockPrice() {
	return cheapestStockPrice;
}

public void setCheapestStockPrice(double cheapestStockPrice) {
	this.cheapestStockPrice = cheapestStockPrice;
}

public double getTotalMoney() {
	return totalMoney;
}

public void setTotalMoney(double totalMoney) {
	this.totalMoney = totalMoney;
}

public int getTotalSharesBought() {
	return totalSharesBought;
}

public void setTotalSharesBought(int totalSharesBought) {
	this.totalSharesBought = totalSharesBought;
}
}
