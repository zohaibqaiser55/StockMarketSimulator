import java.util.Comparator;

public class Company {
	
	private String id;
	private int numOfShares;
	private double priceOfShare;
	private int counter;
	private double capital;
	
	public int sharesSold;
	
	public Company(String id, int numOfShares, double priceOfShare) {
		this.id = id;
		this.numOfShares = numOfShares;
		this.priceOfShare = priceOfShare;
		counter = 0;
		
		capital = numOfShares * priceOfShare;
		
		sharesSold = 0;
		System.out.println(toString());
	}
	
	public boolean sellShare() {
		if(numOfShares > 0) {
			counter++;
			sharesSold++;
			return true;
		}
		return false;
	}
	
	public boolean increasePriceOfShare() {
		if(counter >= 10) {
			priceOfShare = priceOfShare*2;
			counter = 0;
//			System.out.println("price increased for a company");
			return true;
		}
		return false;
	}
	
	public static Comparator<Company> Capital = new Comparator<Company>() {

		public int compare(Company s1, Company s2) {

		   double capital1 = s1.capital;
		   double capital2 = s2.capital;

		   return (int) (capital2 - capital1);
	   }};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumOfShares() {
		return numOfShares;
	}

	public void setNumOfShares(int numOfShares) {
		this.numOfShares = numOfShares;
	}

	public double getPriceOfShare() {
		return priceOfShare;
	}

	public void setPriceOfShare(double d) {
		this.priceOfShare = d;
	}
	
	public String toString() {
		return id + " " + numOfShares + " " + priceOfShare;
	}
}
