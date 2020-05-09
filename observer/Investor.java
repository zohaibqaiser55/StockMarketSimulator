package observer;

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
	
	public void updateData() {
		data.setTotalMoney(data.getTotalMoney() + budget);	
		System.out.println("Investor update");
	}
	
	@Override
	public void update() {
		data.setTotalMoney(data.getTotalMoney() + budget);	
		System.out.println("Investor update");
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
