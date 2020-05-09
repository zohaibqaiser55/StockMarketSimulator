import java.util.ArrayList;
import java.util.UUID;

public class DynamicData {
	public static int totalShares = 0;
	public static double totalMoney = 0;
	
	private static Company genCompany(int numOfShares, int priceOfShare) {
		return new Company(UUID.randomUUID().toString(), numOfShares, priceOfShare);
	}
	
	private static Investor genInvestor(int budget) {
		return new Investor(UUID.randomUUID().toString(), budget);
	}
	
	public static ArrayList<Company> genCompanies(int numOfCompanies){
		ArrayList<Company> companies = new ArrayList<Company>();
		for(int i = 0; i<numOfCompanies; i++) {
			int numOfShares = generate(1, 10);
			int priceOfShare = generate(10, 100);
			totalShares += numOfShares;
			companies.add(genCompany(numOfShares, priceOfShare));
		}
		
		return companies;
	}
	
	public static ArrayList<Investor> genInvestors(int numOfInvestors){
		ArrayList<Investor> investors = new ArrayList<Investor>();
		for(int i = 0; i<numOfInvestors; i++) {
			int budget = generate(1000, 10000);
			totalMoney += budget;
			investors.add(genInvestor(budget));
		}
		
		return investors;
	}
	
	public static int generate(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
