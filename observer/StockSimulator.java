package observer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// This class uses Chain of Responsibility Pattern to run the trading demo
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
		
		System.out.println("=========================================");
		System.out.println("Initializing");
		System.out.println("=========================================");
		System.out.println("Total Shares " + data.getTotalShares());
		System.out.println("Total Money " + data.getTotalMoney());
		System.out.println("Cheapest Share " + data.getCheapestStockPrice());
		System.out.println("=========================================");
		System.out.println(" ");
	}
	
	/**
	 * This method demonstrates the chain of responsibility required for trading. The order is as follows
	 * 0 - chooses random companies and investors
	 * 1 - checks if a company can sell share
	 * 2 - checks if an investor can buy share
	 * 3 - buys and sells stock
	 * 4 - update the data
	 * 5 - checks for any rules
	 * 6 - removes companies without shares and investors without money for efficiency
	 */
	public void runTradingDay() {
		System.out.println("=========================================");
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
		System.out.println("=========================================");
		System.out.println(" ");
		
		tempCompanies.addAll(companies);
		tempInvestors.addAll(investors);
	}
	
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
		System.out.println("=========================================");
		System.out.println("REPORT of Companies");
		System.out.println("=========================================");
		Collections.sort(tempCompanies, Company.Capital);
		reportTopCompany(0);
		reportBottomCompany(1);
		System.out.println("=========================================");
		System.out.println(" ");
	}
	
	// Recursive method to print the top company with most capital
	private void reportTopCompany(int index) {
		if(tempCompanies.get(index).getCapital() == tempCompanies.get(index + 1).getCapital()) {
			reportTopCompany(index + 1);
		}
		System.out.println("Max Capital " + tempCompanies.get(index).toString());
	}

	// Recursive method to print the bottom company with least capital
	private void reportBottomCompany(int index) {
		if(tempCompanies.get(tempCompanies.size() - index).getCapital() == 
				tempCompanies.get(tempCompanies.size() - (index + 1)).getCapital()) {
			reportBottomCompany(index + 1);
		}
		System.out.println("Min Capital " + tempCompanies.get(tempCompanies.size() - index).toString());
	}
	
	public void reportInvestor() {
		System.out.println("=========================================");
		System.out.println("REPORT of Investor");
		System.out.println("=========================================");
		Collections.sort(tempInvestors, Investor.Shares);
		reportTopInvestor(0);
		reportBottomInvestor(1);
		System.out.println("=========================================");
		System.out.println(" ");
	}
	
	// Recursive method to print the top investor with most shares bought
	private void reportTopInvestor(int index) {
		if(tempInvestors.get(index).getSharesBought() == tempInvestors.get(index + 1).getSharesBought()) {
			reportTopInvestor(index + 1);
		}
		System.out.println("Max Shares " + tempInvestors.get(index).toString());
	}

	// Recursive method to print the bottom investor with least shares bought
	private void reportBottomInvestor(int index) {
		if(tempInvestors.get(tempInvestors.size() - index).getSharesBought() == 
				tempInvestors.get(tempInvestors.size() -(index + 1)).getSharesBought()) {
			reportBottomInvestor(index + 1);
		}
		System.out.println("Min Shares " + tempInvestors.get(tempInvestors.size() - index).toString());
	}
	
	public static void main(String args[]) throws IOException {
		int userChoice = 1;
		StockSimulator obj = new StockSimulator();;
		do {	    	
	        System.out.println("Welcome to the Stock Market Simulation");
	        System.out.println("Press 0 to Run Full Demo");
	        System.out.println("Press 1 to Run A Trading Day");
	        System.out.println("Press 2 to Generate Company Report");
	        System.out.println("Press 3 to Generate Investor Report");
	        System.out.println("Press 4 to Generate New Data");
	        System.out.println("Press 5 to Exit");
	        
	        System.out.print("Enter choice: ");
	        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
	        userChoice= Integer.parseInt(inp.readLine());
	        
	        switch(userChoice) {
	        case 0:
	        	obj.runTradingDay();
	        	obj.reportCompany();
	        	obj.reportInvestor();
	        	break;
	        case 1:
	        	obj.runTradingDay();
	        	break;
	        case 2:
	        	obj.reportCompany();
	        	break;
	        case 3:
	        	obj.reportInvestor();
	        	break;
	        case 4:
	        	obj = new StockSimulator();
	        	break;
	        case 5:
	        	System.out.println("Bye Bye");
	        	break;
	        default:
	        	System.out.println("Invalid Input, try again");
	        	break;
	        }	        
		} while(userChoice != 5);
	}
}