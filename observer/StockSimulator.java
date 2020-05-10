package observer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StockSimulator {
	ArrayList<Company> companies;
	ArrayList<Investor> investors;
	int sharesBought;
	double moneySpent;
	final int NUMBER = 100;
	
	ArrayList<Company> tempCompanies;
	ArrayList<Investor> tempInvestors;
	
	Subject subject;
	MarketData data = MarketData.getInstance();
	
	public StockSimulator() {
		companies = new ArrayList<Company>();
		investors = new ArrayList<Investor>();
		subject = new Subject();
		
		genDynamicData();
		
		tempCompanies = new ArrayList<Company>();
		tempInvestors = new ArrayList<Investor>();
	}
	
	private void genDynamicData() {
		companies.addAll(DynamicData.genCompanies(NUMBER, subject));
		investors.addAll(DynamicData.genInvestors(NUMBER, subject));
		
		System.out.println("Total Shares " + data.getTotalShares());
		System.out.println("Total Money " + data.getTotalMoney());
		System.out.println("Cheapest Share " + data.getCheapestStockPrice());
	}
	
	public void runTradingDay() {
		System.out.println("START OF TRADING DAY");
		do {
			Company company = companies.get(DynamicData.generate(0, companies.size() - 1));
			Investor investor = investors.get(DynamicData.generate(0, investors.size() - 1));
			
			doTrade(company, investor);            
			
			if(company.sharesSold >= company.getNumOfShares()) {
				companies.remove(company);
				tempCompanies.add(company);
			}
			
			if(investor.getBudget() <= 0) {
				investors.remove(investor);
				tempInvestors.add(investor);
			}
			
		} while(data.getTotalSharesBought() < data.getTotalShares() && 
				data.getTotalMoneySpent() < data.getTotalMoney() &&
				companies.size() > 0);
		System.out.println("END OF TRADING DAY");
		
		tempCompanies.addAll(companies);
		tempInvestors.addAll(investors);
	}
	
	/**
	 * This method demonstrates the chain of responsibility required for trading. The order is as follows
	 * 1 - checks if a company can sell share
	 * 2 - checks if an investor can buy share
	 * 3 - buys and sells stock
	 * 5 - update the data
	 * 4 - checks for any rules
	 * 
	 * @param company
	 * @param investor
	 */
	private void doTrade(Company company, Investor investor) {
		if(investor.buyShare(company)){
			sharesBought++;
			moneySpent += company.getPriceOfShare();
			data.setTotalMoneySpent(data.getTotalMoneySpent() + company.getPriceOfShare());
			data.setTotalSharesBought(data.getTotalSharesBought() + 1);
			
			if(company.increasePriceOfShare()) {
				subject.updateState();
			}
			
			if(data.getTotalSharesBought() % 10 == 0) {
				subject.updateState();
			}
		}
	}
	
	public void reportCompany() {
		System.out.println("REPORT of Companies");
		Collections.sort(tempCompanies, Company.Capital);
		reportTopCompany(0);
		reportBottomCompany(1);
	}
	
	private void reportTopCompany(int index) {
		if(tempCompanies.get(index).getCapital() == tempCompanies.get(index + 1).getCapital()) {
			reportTopCompany(index + 1);
		}
		System.out.println("Max Capital " + tempCompanies.get(index).toString());
	}
	
	private void reportBottomCompany(int index) {
		if(tempCompanies.get(tempCompanies.size() - index).getCapital() == 
				tempCompanies.get(tempCompanies.size() - (index + 1)).getCapital()) {
			reportBottomCompany(index + 1);
		}
		System.out.println("Min Capital " + tempCompanies.get(tempCompanies.size() - index).toString());
	}
	
	public void reportInvestor() {
		System.out.println("REPORT of Investor");
		Collections.sort(tempInvestors, Investor.Shares);
		reportTopInvestor(0);
		reportBottomInvestor(1);
	}
	
	private void reportTopInvestor(int index) {
		if(tempInvestors.get(index).getSharesBought() == tempInvestors.get(index + 1).getSharesBought()) {
			reportTopInvestor(index + 1);
		}
		System.out.println("Max Shares " + tempInvestors.get(index).toString());
	}
	
	private void reportBottomInvestor(int index) {
		if(tempInvestors.get(tempInvestors.size() - index).getSharesBought() == 
				tempInvestors.get(tempInvestors.size() -(index + 1)).getSharesBought()) {
			reportBottomInvestor(index + 1);
		}
		System.out.println("Min Shares " + tempInvestors.get(tempInvestors.size() - index).toString());
	}
	
	public static void main(String args[]) {
		StockSimulator obj = new StockSimulator();
		obj.runTradingDay();
		obj.reportCompany();
		obj.reportInvestor();
	}
}