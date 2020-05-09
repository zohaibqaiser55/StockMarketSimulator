package observer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StockSimulator {
	ArrayList<Company> companies;
	ArrayList<Investor> investors;
	int sharesBought;
	double moneySpent;
	final int NUMBER = 3;
	
	ArrayList<Company> tempCompanies;
	ArrayList<Investor> tempInvestors;
	
	Subject subject;
	MarketData data = MarketData.getInstance();
	
	public StockSimulator() {
		companies = new ArrayList<Company>();
		investors = new ArrayList<Investor>();
		subject = new Subject();
		
		genDynamicData();
		
		tempCompanies = companies;
		tempInvestors = investors;
	}
	
	private void genDynamicData() {
		companies.addAll(DynamicData.genCompanies(NUMBER, subject));
		investors.addAll(DynamicData.genInvestors(NUMBER, subject));
		
		System.out.println("Total Shares " + data.getTotalShares());
		System.out.println("Total Money " + data.getTotalMoney());
		System.out.println("Cheapest Share " + data.getCheapestStockPrice());
	}
	
//	private void reduceSharePriceForCompanyWithUnSoldShares() {
//		for(Company company : companies) {
//			if(company.sharesSold == 0) {
//				company.setPriceOfShare(company.getPriceOfShare()*0.02);
////				System.out.println("price reduced for a company");
//			}
//		}
//	}
	
	public void runTradingDay() {
		System.out.println("START OF TRADING DAY");
//		do {
//			Company company = companies.get(DynamicData.generate(0, companies.size() - 1));
//			Investor investor = investors.get(DynamicData.generate(0, investors.size() - 1));
//			
//			if(investor.buyShare(company)){
//				sharesBought++;
//				moneySpent += company.getPriceOfShare();
//
//				//System.out.println("Shares Bought " + sharesBought + " Money Spent " + moneySpent);
//			}
//			
////			if(company.increasePriceOfShare()) {
////				reduceSharePriceForCompanyWithUnSoldShares();
////			}
//			
////			if(company.sharesSold >= company.getNumOfShares()) {
////				companies.remove(company);
////				//System.out.println("Company removed, size " + companies.size());
////			}
////			
////			if(investor.getBudget() <= 0) {
////				investors.remove(investor);
////				//System.out.println("Investor removed, size " + investors.size());
////			}
//			
//			
//		} while(sharesBought < DynamicData.totalShares && moneySpent < DynamicData.totalMoney);
		System.out.println("END OF TRADING DAY");
	}
	
	public static void main(String args[]) {
		StockSimulator obj = new StockSimulator();		
//		obj.runTradingDay();
		
//		System.out.println("REPORT of Companies");
//		Collections.sort(obj.companies, Company.Capital);
//		for(Company comp : obj.companies) {
//			System.out.println("Max Capital " + comp);
//		}
//		
//		System.out.println("REPORT of Investor");
//		Collections.sort(obj.investors, Investor.Shares);
//		for(Investor comp : obj.investors) {
//			System.out.println("Max Shares " + comp);
//		}
		
		
		
//		System.out.println("" + obj);
//		System.out.println("" + obj);
//		System.out.println("" + obj);
	}
}