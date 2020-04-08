
public class Singleton {
	private static Singleton singletonDaoInstance = null;
	private CountryDAO db = null;
	
	public Singleton() {
		 db = new MySQLCountryDAO();
	}
	
	public static Singleton getInstance() 
    { 
        if (singletonDaoInstance == null) 
        	singletonDaoInstance = new Singleton(); 
  
        return singletonDaoInstance; 
    } 
	
	CountryDAO getDb() {
		return db;
	}

}
