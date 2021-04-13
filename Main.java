/* GROUP 5 
 * JAVA PROJECT 
 */

package msql;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws Exception {
    	
    	trial login = new trial();
    	flight aircraft = new flight();
    	Booking book = new Booking();
    	lounge motel = new lounge();
    	String choice;
    	String user="",fn="",mob="",gen="";
    	if(login.authentication()==true)
    	{
    		user = login.db_uname;
    		fn = login.db_name;
    		mob = login.db_phone;
    		gen = login.db_gen;
    		while(true)
    		{
    			
    				Scanner sc = new Scanner(System.in);
        			System.out.println("1. Book Flight.");
            		System.out.println("2. Book Hotel.");
            		System.out.println("3. Press Any Key to Exit.");
            		choice = sc.nextLine();
            		
            		if(choice.equals("1"))
            		{
//            			flag = 1;
            			aircraft.plane(user);
//            			aircraft.plane(user);
            			book.cart("flight", user, fn, mob, gen);
//            			break;
            		}
            		else if(choice.equals("2"))
            		{
            			motel.hotel(user);
            			book.cart("hotel",user,fn,mob,gen);
//            			break;
            		}
            		else {
            			break;
            		}
    		}
    	}
   }
}
