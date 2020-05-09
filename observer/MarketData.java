package observer;

public class MarketData {
	
	private int totalShares;
	
	private double totalMoney;
	
	private int totalMoneyLeft;
	
	private double cheapestStockPrice;

   //create an object of MarketData
   private static MarketData instance = new MarketData();

   //make the constructor private so that this class cannot be
   //instantiated
   private MarketData(){
	   setTotalShares(0);
	   setTotalMoneyLeft(0);
	   setCheapestStockPrice(0);
   }

   //Get the only object available
   public static MarketData getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }

public int getTotalMoneyLeft() {
	return totalMoneyLeft;
}

public void setTotalMoneyLeft(int totalMoneyLeft) {
	this.totalMoneyLeft = totalMoneyLeft;
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
}
