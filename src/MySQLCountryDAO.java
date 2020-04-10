import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

	
	@Override
	public ArrayList<Country> getCountries() {
		// CREATE THE ARRAYLIST TO PUT ALL THE CUSTOMERS
				// THAT ARE GOING TO BE RETURNED
				ArrayList<Country> countries = new ArrayList<Country>();
				
				// THIS IS THE METHOD IN CHARGE OF CREATE THE QUERY
				String query = "select * from country";
				
				// ACCESSING THE DATABASE
				DataSource db = new DataSource();
				
				// QUERYING THE DATABASE
				ResultSet rs = db.select(query);
				
	
				String code = "";
				String name = "";
				String continent = "";
				float surfaceArea = 0;
				String headOfState = "";
				Country c= null;
				
				// LOOP OVER THE RESULT SET
				try {
					while( rs.next() ) {
						// FOR EACH ONE OF THE VALUES, WE WANT TO
						// GET THE ATTRIBTUES
						
						code = rs.getString(1);
						name = rs.getString(2);
						continent = rs.getString(3);
						surfaceArea = rs.getFloat(4);
						headOfState = rs.getString(5);
						
						c = new Country.CountryBuilder(code, name, continent, surfaceArea, headOfState).build();
						countries.add(c);
						
					//	country.add(new Country(code, name, continent, surfaceArea,surfaceArea));	
					}
					
					// CLOSING THE CONNECTION TO THE DATABASE
					db.closing();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// RETURN THE ARRAYLIST WITH ALL THE CUSTOMERS
				return countries;
	}

	@Override
	public Country findCountryByCode(String code) {
		
		// THIS IS THE METHOD IN CHARGE OF CREATE THE QUERY
		String query = "select * from country where code = '"+code+"';";
		
		// ACCESSING THE DATABASE
		DataSource db = new DataSource();
		
		// QUERYING THE DATABASE
		ResultSet rs = db.select(query);
		
		String name = "";
		String continent = "";
		float surfaceArea = 0;
		String headOfState = "";
		Country c= null;
		
		// LOOP OVER THE RESULT SET
		try {
			if( rs.next() ) {
				// FOR EACH ONE OF THE VALUES, WE WANT TO
				// GET THE ATTRIBTUES
				name = rs.getString(2);
				continent = rs.getString(3);
				surfaceArea = rs.getFloat(4);
				headOfState = rs.getString(5);
				
				c = new Country.CountryBuilder(code, name, continent, surfaceArea, headOfState).build();;
				return c;
			}	
			
			return null;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// RETURN THE ARRAYLIST WITH ALL THE CUSTOMERS
		return null;
	}
	
	@Override
	public ArrayList<Country> findCountryByName(String name) {
		// CREATE THE ARRAYLIST TO PUT ALL THE CUSTOMERS
				// THAT ARE GOING TO BE RETURNED
				ArrayList<Country> countries = new ArrayList<Country>();
				
				// THIS IS THE METHOD IN CHARGE OF CREATE THE QUERY
				String query = "select * from country where name = '"+name+"';";
				
				// ACCESSING THE DATABASE
				DataSource db = new DataSource();
				
				// QUERYING THE DATABASE
				ResultSet rs = db.select(query);
				
	
				String code = "";
				String continent = "";
				float surfaceArea = 0;
				String headOfState = "";
				Country c= null;
				
				// LOOP OVER THE RESULT SET
				try {
					while( rs.next() ) {
						// FOR EACH ONE OF THE VALUES, WE WANT TO
						// GET THE ATTRIBTUES
						
						code = rs.getString(1);
						name = rs.getString(2);
						continent = rs.getString(3);
						surfaceArea = rs.getFloat(4);
						headOfState = rs.getString(5);
						
						c = new Country.CountryBuilder(code, name, continent, surfaceArea, headOfState).build();
						countries.add(c);
						
					//	country.add(new Country(code, name, continent, surfaceArea,surfaceArea));	
					}
										
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// RETURN THE ARRAYLIST WITH ALL THE CUSTOMERS
				return countries;
	}

	@Override
	public boolean saveCountry(Country country) {
		
		String code = country.getCode();
		String name = country.getName();
		String continent = country.getContinent();
		float surfaceArea = country.getSurfaceArea();
		String headOfState = country.getHeadOfState();
		
		// THIS IS THE METHOD IN CHARGE OF CREATE THE QUERY
		String query = "INSERT INTO country (code, name, continent, surfaceArea, HeadOfState) VALUES ('"+code+"', '"+name+"', '"+continent+"', "+surfaceArea+", '"+headOfState+"');";
		
		DataSource db = new DataSource();
		return db.save(query);
	}

}
