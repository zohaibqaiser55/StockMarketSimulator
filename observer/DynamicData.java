package observer;
import java.util.ArrayList;
import java.util.UUID;

public class DynamicData {
	
	private static Company genCompany(int numOfShares, int priceOfShare, Subject subject) {
		Company company = new Company(UUID.randomUUID().toString(), numOfShares, priceOfShare, subject);
		company.updateData();
		return company;
	}
	
	private static Investor genInvestor(int budget, Subject subject) {
		Investor investor = new Investor(UUID.randomUUID().toString(), budget, subject);
		investor.updateData();
		return investor;
	}
	
	public static ArrayList<Company> genCompanies(int numOfCompanies, Subject subject){
		ArrayList<Company> companies = new ArrayList<Company>();
		for(int i = 0; i<numOfCompanies; i++) {
			int numOfShares = generate(500, 1000);
			int priceOfShare = generate(10, 100);
			companies.add(genCompany(numOfShares, priceOfShare, subject));
		}
		
		return companies;
	}
	
	public static ArrayList<Investor> genInvestors(int numOfInvestors, Subject subject){
		ArrayList<Investor> investors = new ArrayList<Investor>();
		for(int i = 0; i<numOfInvestors; i++) {
			int budget = generate(1000, 10000);
			investors.add(genInvestor(budget, subject));
		}
		
		return investors;
	}
	
	public static int generate(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
