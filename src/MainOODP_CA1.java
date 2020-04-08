import java.util.ArrayList;
import java.util.List;

public class MainOODP_CA1 {
	
	static String code = "";
	static String name = "";
	static String continent = "";
	static float surfaceArea;
	static String headOfState = "";
	static List<Country> countryList = new ArrayList<Country>();	

	public static void main(String[] args) {
		
		
		//Variables
		
		// NOW THE CLIENT DOES NOT HAVE ANYTHING TO DO
		// THE THE DATABASE CLASS. 
		// THE CLIENT WILL ONLY TALK TO THE CUSTOMERDAO
		// IN TERMS OF CUSTOMER
		// IN OTHER WORDS, THE PASSING OF DATA IS GOING 
		// TO BE CUSTOMERS OBJECTS
		// The Singleton pattern implemented will ensure only 
		// one instance of the db is created
		
		CountryDAO db = Singleton.getInstance().getDb();
		
		// GETTING ALL OF THE CUSTOMERS IN THE DATABASE
		ArrayList<Country> country = db.getCountries();
		
		// PRINTING THEM TO THE CONSOLE
		for (Country c : country) {
			System.out.println(c);
		}
		
		// GETTING ONLY THE CUSTOMER THAT HAS THE GIVEN
		// ID
		Country c = db.findCountryByCode("");
		
		// PRINTING IT TO THE CONSOLE
		System.out.println(c);
		
		// CREATING A NEW CUSTOMER. KEEP IN MIND THAT
		// THE ID OF THE NEW CUSTOMER IS GOING TO BE THE
		// SIZE OF THE ARRAY PLUS ONE
		// Country is initialized using the Builder pattern
		Country newCountry = new Country.CountryBuilder(code, name, continent, surfaceArea, headOfState).build();
		
		// ADDING THE CUSTOMER TO THE ARRAY, TO HAVE LOCAL
		// CONTROL OF THE DATA
		countryList.add(newCountry);
		// ADDING THE NEW CUSTOMER INTO THE DATABASE
		System.out.println(db.saveCountry(newCountry));

	}

}
