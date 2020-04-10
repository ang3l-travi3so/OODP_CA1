import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainOODP_CA1 {

	static List<Country> countryList = new ArrayList<Country>();
	static StringBuffer messageToClient = new StringBuffer();
	
	public static void main(String[] args) {

		// Variables

		// NOW THE CLIENT DOES NOT HAVE ANYTHING TO DO
		// THE THE DATABASE CLASS.
		// THE CLIENT WILL ONLY TALK TO THE CUSTOMERDAO
		// IN TERMS OF CUSTOMER
		// IN OTHER WORDS, THE PASSING OF DATA IS GOING
		// TO BE CUSTOMERS OBJECTS
		
		// The Singleton pattern implemented will ensure only
		// one instance of the db is created

		CountryDAO db = Singleton.getInstance().getDb();

		boolean repeat = true;
		try {
			// TO SEPARATE THE CLI FROM THE SERVER AS IT WAS REQUESTED TO HAVE A CLI WITH NO DIRECT ACCESS TO THE DB 
			// A SOCKET HAS BEEN IMPLEMENTED SO THAT THE CLI CAN INTERACT REMOTELY
			ServerSocket serverSocket = new ServerSocket(6666);
			// OPENING THE CONNECTION
			Socket socket = serverSocket.accept();
			String str = "";
			String[] choice;
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			// THE WHILE LOOK ALLOWS THE SERVER TO STAY IN EXECUTION UNTIL THE USER DOES NOT DECIDE TO QUIT PROVIDING THE INPUT FROM
			// THE CLIENT APPLICATION SIDE. THE SWITCH CASE WILL ALLOW THE SERVE TO UNDERSTAND WHAT THE USER HAS REQUESTED AND ALSO 
			// EXTRACT THE DATA NEED TO PERFORM THE ACTION
			while (repeat) {
				choice = str.split(":");			
				dout.flush();
				switch (choice[0]) {
				case "all":
					System.out.println("Printing all the countries: ");
					// GETTING ALL OF THE CUSTOMERS IN THE DATABASE
					ArrayList<Country> country = db.getCountries();

					// PRINTING THEM TO THE CONSOLE
					for (Country c : country) {
						System.out.println(c);
						messageToClient.append(c);
					}
					break;
				case "code":
					System.out.println("Retrieving the details of the country with code: " + str.substring(5));
					// GETTING ONLY THE CUSTOMER THAT HAS THE GIVEN
					// ID
					Country c = db.findCountryByCode(str.substring(5));
					// PRINTING IT TO THE CONSOLE
					System.out.println(c);
					messageToClient.append(c);
					System.out.println();
					break;
				case "name":
					System.out.println("Retrieving the details of the country with name: " + str.substring(5));
					// GETTING ONLY THE CUSTOMER THAT HAS THE GIVEN
					// ID
					ArrayList<Country> names = db.findCountryByName(str.substring(5));
					// PRINTING IT TO THE CONSOLE
					for (Iterator iterator = names.iterator(); iterator.hasNext();) {
						Country country2 = (Country) iterator.next();
						System.out.println(country2);
						messageToClient.append(country2);
						System.out.println();
						
					}
					
					break;
				case "add":
					System.out.println("adding new country with the followind detals : " + str.substring(4).replace("@", " "));
					// TOKENIZING THE VALUES THE USER HAS PROVIDED
					String[] values = str.substring(4).split("@");
					// THE TRY CATCH BLOCK IS USED TO AVOID THE SERVER TO STOP IN CASE THE AREA PROVIDED IS NOT A NUMBER
					try {
						// CREATING A NEW CUSTOMER. KEEP IN MIND THAT
						// THE ID OF THE NEW CUSTOMER IS GOING TO BE THE
						// SIZE OF THE ARRAY PLUS ONE
						// Country is initialized using the Builder pattern
						Country newCountry = new Country.CountryBuilder(values[0], values[1], values[2], Float.parseFloat(values[3]), values[4]).build();
						// ADDING THE CUSTOMER TO THE ARRAY, TO HAVE LOCAL
						// CONTROL OF THE DATA
						countryList.add(newCountry);
						// ADDING THE NEW CUSTOMER INTO THE DATABASE
						System.out.println(db.saveCountry(newCountry));
						messageToClient.append("record created.");
						System.out.println();
					} catch (Exception e) {
						System.out.println("The area has to be a number. Creation of the country failed");
					}
					
					break;
				case "quit":
					serverSocket.close();
					repeat = false;
					break;
				default:
					break;
				}
				try {
					dout.writeUTF(messageToClient.toString());
					dout.flush();
					dis = new DataInputStream(socket.getInputStream());
					str = (String) dis.readUTF();
				} catch (Exception e) {
				//	repeat = false;
					System.out.println("Terminated");
				}
				messageToClient = new StringBuffer();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
