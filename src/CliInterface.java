
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CliInterface {

	// IMPLEMENTATION OF AN INTERACTINVE CLI, THE USER WILL BE ASKET TO CHOOSE AMONG 5 OPTIONS
	// BASED ON THE CHOICE THE USER WILL THEN REQUESTED TO PROVIDE ADDITIONAL INPUT WHERE NEEDED
	// BY CHOOSING THE OPTION 0 THE USER TERMINATES BOTH THE CLIENT AND THE SERVER EXECUTION

	public static void main(String[] args) {
		boolean repeat = true;
		try {
			// THE CLI IS IMPLEMENTED WITH A SOCKET, THE SERVER WILL LISTEN ON THE SAME PORT
			Socket socket = new Socket("localhost", 6666);
			// THE INPUT FROM THE USER IS HANDLED BY A JAVA SCANNER OBJECT THAT WILL READ FROM KEYBOARD
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner keyboard = new Scanner(System.in);
			String key = "";
			StringBuffer record  = new StringBuffer();
			String str = "";
			// AN INFINITE WHILE LOOP WILL REITERATE SO THE USER CAN PROGRESS WITH MULTIPLE COICHES UNTIL DECIDES TO QUIT
			while (repeat) {
				if(str.equals("")) {
					System.out.println("please make a choice: ");
					System.out.println("0 to quit");
					System.out.println("1 to retrieve all the countries;");
					System.out.println("2 to retrieve one country by code;");
					System.out.println("3 to retrieve one country by name;");
					System.out.println("4 add a new country;");
					key = keyboard.nextLine();
					str = "-0-";
					try {
						Integer.parseInt(key);
					} catch (Exception e) {
						continue;
					}
				}
				// THE SWITCH CASE ALLOWS TO HANDLE PROPERLY THE CHOCES FROM THE USER AND SEND THE INPUT TO THE SERVER
				// THE SERVER WILL PROPERLY HANDLE IT IN ACCORDING
				// EACH OF THE CASES HAS A LABLE TO ENCODE WHAT IS THE ACTION TO PERFOM IN ADDITION TO INPUT DATA WERE NEEDED
				// THE SERVER WILL PROCESS THE INPUT LINE AND DECODE WHICH ACTION TO PERFORM AND EXTRATE DATA WERE NEEDED
				switch (key) {
				case "0": 
					repeat = false;
					dout.writeUTF("quit:");
					socket.close();
					dout.close();
					keyboard.close();
					break;
				case "1": 
					dout.writeUTF("all:");
					dout.flush();
					break;
				case "2": 
					System.out.print("please provide the country code: ");
					dout.writeUTF("code:" + keyboard.nextLine());
					dout.flush();
					break;
				case "3": 
					System.out.print("please provide the country name: ");
					dout.writeUTF("name:" + keyboard.nextLine());
					dout.flush();
					break;
				case "4": 
					record.delete(0, record.length());
					System.out.print("please provide the country code: ");
					record.append(keyboard.nextLine());
					System.out.print("please provide the country name: ");
					record.append( "@" + keyboard.nextLine());
					System.out.print("please provide the continent name: ");
					record.append( "@" + keyboard.nextLine());
					System.out.print("please provide the surface area: ");
					record.append( "@" + keyboard.nextLine());
					System.out.print("please provide the head of state: ");
					record.append( "@" + keyboard.nextLine());
					dout.writeUTF("add:" + record);
					dout.flush();
					break;
				default:
					break;
				}
				key="";
				try {
					dis = new DataInputStream(socket.getInputStream());
					str = (String) dis.readUTF();
					if(!str.equals("-0-")&&!str.equals("")) {
						System.out.println(str);
						str="";
					}
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					System.out.println("Terminated");
				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
