
public class Country {
	
	private String code;
	private String name;
	private String continent;
	private float surfaceArea;
	private String headOfState;
	
	public Country(CountryBuilder builder) {
		this.code = builder.code;
		this.name = builder.name;
		this.continent = builder.continent;
		this.surfaceArea = builder.surfaceArea;
		this.headOfState = builder.headOfState;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	public float getSurfaceArea() {
		return surfaceArea;
	}

	public void setPhoneNumber(float surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}
	
	
	// I added this method here to be able to print the
	// object without problems
	@Override
	public String toString() {
		return "Code: " + code + ", N: " + name + ", Continent: " + continent +", Surface Area: " + surfaceArea + ", Head Of State: " + headOfState;
	}
	
	
	//Implementation of the Builder Design Pattern
	public static class CountryBuilder{
		
		private String code;
		private String name;
		private String continent;
		private float surfaceArea;
		private String headOfState;
		
		public CountryBuilder(String code, String name, String continent, float surfaceArea, String headOfState) {
			this.code = code;
			this.name = name;
			this.continent = continent;
			this.surfaceArea = surfaceArea;
			this.headOfState = headOfState;
		}
		
		public CountryBuilder code(String code) {
            this.code = code;
            return this;
        }
		
		public CountryBuilder name(String name) {
            this.name = name;
            return this;
        }
		
		public CountryBuilder continente(String continent) {
            this.continent = continent;
            return this;
        }
		
		public CountryBuilder surfaceArea(float surfaceArea) {
            this.surfaceArea = surfaceArea;
            return this;
        }
		
		public CountryBuilder headOfState(String headOfState) {
            this.headOfState = headOfState;
            return this;
        }
		
		public Country build() {
            Country country =  new Country(this);
            return country;
        }
		
	}
	

}
