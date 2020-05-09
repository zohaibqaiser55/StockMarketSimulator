package observer;

public class Investor extends Observer{
	
	private String id;
	private double budget;
	private int sharesBought;
	
	MarketData data = MarketData.getInstance();
	
	public Investor(String id, int budget) {
		this.id = id;
		this.budget = budget;
		sharesBought = 0;
		System.out.println(toString());
	}
	
	@Override
	public void update() {
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
	
	public String toString() {
		return id + " " + budget + " " + sharesBought;
	}	
}
