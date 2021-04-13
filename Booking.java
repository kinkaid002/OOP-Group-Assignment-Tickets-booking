/* MADE BY - NABEEL KHAN
 * ROLL NO - B-50
 */



package msql;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

public class Booking {
	
	String db="",table="",name="",insert="",user="",type="",path="",fullname="",mobile="",gender="";
	int column,payment;
	Booking()
	{
		
	}
	//Constructor for booking
	Booking(String table,String user,String fullname,String mobile,String gender){
		this.db="project";
		this.user =user;
		this.type=table;
		this.table=user+"_"+table;
		this.fullname=fullname;
		this.mobile=mobile;
		this.gender=gender;
		//classified different parameters of hotel and flight using if
		if (table == "hotel") {
			this.column=11;
			this.payment=11;
			this.name="serial";
			this.path="Home/hotel.txt" ;
		}
		else if(table == "flight") {
			this.column=12;
			this.payment=10;
			this.name="flt_id";
			this.path="Home/flight.txt" ;
		}
				
	}
	
	//method to access last serial no. in the database
	public int getEntries() {
		int i = 0;
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/"+db;
		String username = "root";
		String password = "";
		//try-catch block to catch exceptions
		try {
			 //establishing connection with database
			 con = DriverManager.getConnection(url,username,password);
			 state = con.createStatement();
			 rs = state.executeQuery("SELECT * FROM "+table);
			 //iterating different rows in database using while
			 while(rs.next())  
					i=rs.getInt(1);
			 //closing the connection with database
			 con.close();
		}catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return i;
	}
	
	//method to print database in a table
	public void printEntries() {
		String confirm = null;
		if(this.type=="flight")
			confirm=getValues(getEntries(), 12);
		if(this.type=="hotel")
			confirm=getValues(getEntries(),9 );
		if(confirm==null||confirm=="1") {
			//print table for hotels database 
			if(this.type=="hotel") {
				System.out.printf("\n    %-20s %-12s %-12s %-13s %-10s %-12s %-14s %-14s \n", "Hotel Name", "City", "Check-In", "Check-Out", "Guest", "Room", "Cost/day","Total Cost" );
				Connection con = null;
				Statement state = null;
				ResultSet rs = null;
				String url = "jdbc:mysql://localhost:3306/"+db;
				String username = "root";
				String password = "";
				try {
					 con = DriverManager.getConnection(url,username,password);
					 state = con.createStatement();
					 rs = state.executeQuery("SELECT * FROM "+table);
					 while(rs.next()) {
						 String s9=rs.getString(4);
						 if(s9.length()>20)
							 s9=s9.substring(0,19);
						 else {
							String s7 = "";
							for(int j=0;j<20-s9.length();j++)
								s7=s7+" ";
							s9=s9+s7;
						 }
						 System.out.printf("\n    %-20s %-12s %-12s %-14s %-11s %-13s %-12s %-14s \n",s9, rs.getString(3) , rs.getString(5) , rs.getString(6),rs.getString(7),rs.getString(8), rs.getString(10),"Rs."+ rs.getString(11));
						 System.out.println();
					 }
						 con.close();
				}catch (SQLException ex) {
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
			//print table for flight database 
			else if(this.type=="flight") {
				System.out.printf("\n    %-12s %-12s %-12s %-13s %-16s %-12s %-14s %-20s %-8s \n", "flt_no." +"", "flt_from", "flt_to", "flt_Date", "flt_departure", "Duration", "Airlines Name","Seats Booked", "Cost" );
				Connection con = null;
				Statement state = null;
				ResultSet rs = null;
				String url = "jdbc:mysql://localhost:3306/"+db;
				String username = "root";
				String password = "";
				try {
					 con = DriverManager.getConnection(url,username,password);
					 state = con.createStatement();
					 rs = state.executeQuery("SELECT * FROM "+table);
					 
					 while(rs.next()) 
					 {
						 System.out.printf("\n    %-12s %-12s %-12s %-14s %-15s %-14s %-18s %-14s %-6s \n",rs.getString(2), rs.getString(3) , rs.getString(4) , rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9),"Rs."+rs.getString(10));
						 System.out.println();
					 }
						 con.close();
				}catch (SQLException ex) {
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
			}
		}else
			System.out.println("\nNo items in cart!\nPlease add sum to proceed further");
	}
	
	//method to access different values in the database
	public String getValues(int id,int col) {
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/"+db;
		String username = "root";
		String password = "";
		try {
			 con = DriverManager.getConnection(url,username,password);
			 state = con.createStatement();
			 rs = state.executeQuery("SELECT * FROM "+table);
			 while(rs.next()) {
				 if(rs.getInt(1)==id) {
					 return rs.getString(col);
				 }
			 } 
			 con.close();
		}catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return "null";
	}
	
	//method to print flight ticket confirmation
	public void ticketFlight() throws Exception {
		
		ArrayList<String> s1 = new ArrayList<String>();
		ArrayList<String> s2 = new ArrayList<String>();
		for (int i=2;i<12;i++) {
			s1.add(getValues(getEntries(), i));
		}
		s1.add(this.type);
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis); 
		String s5=date.toString();
		s1.add(s5);
		s1.add(this.fullname);
		s1.add(this.mobile);
		s1.add(this.gender);
		s2.add("XXXXXX");
		s2.add("VVVVVV");
		s2.add("WWWWWW");
		s2.add("YYYYYYYY");
		s2.add("ZZZZZZ");
		s2.add("DDDDDD");
		s2.add("FFFFFFFF");
		s2.add("SSSS");
		s2.add("EEEEE");
		s2.add("IIIII");
		s2.add("HHHHHH");
		s2.add("11111111");
		s2.add("NNNNNNNNNNNNN");
		s2.add("3333333333");
		s2.add("22222222");
		for (int i=0;i<15;i++) {
			Ticket(s2.get(i), s1.get(i),i);
		}
	}
	
	//method to print Hotel ticket confirmation
	public void ticketHotel() throws Exception {
		
		ArrayList<String> s1 = new ArrayList<String>();
		ArrayList<String> s2 = new ArrayList<String>();
		for (int i=2;i<9;i++) {
			if(i!=4)
				s1.add(getValues(getEntries(), i));
			else {
				String s6=getValues(getEntries(), i);
				if(s6.length()>50)
					s1.add(s6.substring(0,50));
				else {
					String s7 = "";
					for(int j=s6.length();j<50;j++)
						s7=s7+" ";
					s1.add(s6+s7);
				}
			}
		}
		s1.add(getValues(getEntries(), 11));
		s1.add(this.type);
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis); 
		String s5=date.toString();
		s1.add(s5);
		s1.add(this.fullname);
		s1.add(this.mobile);
		s1.add(this.gender);
		s2.add("BBBBB");
		s2.add("YYYYYYYY");
		s2.add("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		s2.add("ZZZZZZ");
		s2.add("VVVVVV");
		s2.add("DDD");
		s2.add("SSS");
		s2.add("FFFFF");
		s2.add("HHHHHHHH");
		s2.add("11111111");
		s2.add("NNNNNNNNNNNNN");
		s2.add("GGGGGGGGGG");
		s2.add("22222222");
		for (int i=0;i<13;i++) {
			Ticket(s2.get(i), s1.get(i),i);
		}
	}
	
	//method to create and replace new file and replace string for template
	public void Ticket(String oldS, String newS,int i) throws Exception
    {
		String s = "Home/"+user+"_"+this.type+".txt",s1;
		if(i==0) {
		File y = new File(s);
		 FileInputStream in = new FileInputStream(path);
	     FileOutputStream out = new FileOutputStream(y);
	     try {
	            int n;
	            while ((n = in.read()) != -1) {
	                out.write(n);
	            }
	            in.close();
	            out.close();
	        }finally {
	                in.close();
	                out.close();
	        }
		}
	     try{
	    	 File y = new File(s);
	    	    FileReader fr = new FileReader(y);
	    	    String totalStr = "";
	    	    try (BufferedReader br = new BufferedReader(fr)) {

	    	        while ((s1 = br.readLine()) != null) {
	    	            totalStr += s1+"\n";
	    	        }
	    	        totalStr = totalStr.replace(oldS , newS);
	    	        FileWriter fw = new FileWriter(y);
	    	        fw.write(totalStr);
		    	    fw.close();
	    	    }
	    	}catch(Exception e){
	    	    e.printStackTrace();
	    	}
    }
	
	//method to print tickets 
	public void printTicket() throws Exception {
		String s="Home/"+user+"_"+this.type+".txt",s1;
		File y = new File(s);
	    FileReader fr = new FileReader(y);
		try (BufferedReader br = new BufferedReader(fr)) {
			String totalStr = "		";
	        while ((s1 = br.readLine()) != null) {
	            totalStr += s1+"\n		";
	        }
	        System.out.println("\n\n");
	        System.out.println(totalStr);
	        System.out.println("\n\n");
		}
	}
	
	//method to generate different confirmation id
	public int getPnr() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
	
	//method to update the booking id generated after confirmation in the database
	public void updateSQL(int pnr,int flag) {
		String s= "";
		if(this.type=="hotel") {
			s="UPDATE "+table+" SET pnr = '"+pnr+"', Isconfirm = '"+flag+"' WHERE serial = '"+getEntries()+"'";
		}
		else if(this.type=="flight") {
			s="UPDATE "+table+" SET pnr = '"+pnr+"', Isconfirm = '"+flag+"' WHERE flt_id = '"+getEntries()+"'";
		}
		Connection con = null;
		Statement state = null;
		String url = "jdbc:mysql://localhost:3306/"+db;
		String username = "root";
		String password = "";
		try {
			 con = DriverManager.getConnection(url,username,password);
			 state = con.createStatement();
			 state.executeUpdate(s);
			con.close();
		}catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	//method to process the payment
	public void getPayment(int target) throws Exception {
		int pnr;
		if(target==1) {
			//insert pnr to sql flag to 1
			System.out.println("\nPayment is in Process..\nDo Not press the back key\n");
			pnr=getPnr();
			updateSQL(pnr, 1);
			if(this.type=="flight")
				ticketFlight();
			if(this.type=="hotel")
				ticketHotel();
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Payment Confirmed!");
			System.out.println("Generating Ticket...");
			TimeUnit.SECONDS.sleep(4);
			System.out.println("Booking Id : "+pnr);
			System.out.println("Ticket Generated Successfully");
			printTicket();
			System.out.println("\nThank You for Booking with us!\nHope to see you soon\nHave a great day ahead!");
		}
		else if(target==2) {
			//insert pnr to sql flag to 0
			System.out.println("\nPayment is in Process..\nDo Not press the back key\n");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("\nSorry Your Payment Failed\nPlease try again after some time.");
		}
	}
	
	//method to delete items in the cart from database
	public void deleteItems(int f) throws Exception {
		int i=0;
		String s="";
		if(this.type=="hotel") {
			i=9;
			s="DELETE FROM "+table+" WHERE serial = " + getEntries();
		}
		else if(this.type=="flight") {
			i=12;
			s="DELETE FROM "+table+" WHERE flt_id = " + getEntries();
		}
		if(f==1) {
			String j = getValues(getEntries(), i);
			Connection con = null;
			Statement state = null;
			String url = "jdbc:mysql://localhost:3306/"+db;
			String username = "root";
			String password = "";
			try {
				 con = DriverManager.getConnection(url,username,password);
				 state = con.createStatement();
				 if(j!="0") {
					state.executeUpdate(s);
					TimeUnit.SECONDS.sleep(3);
					System.out.println("\nYour cart having "+this.type+" has been cleared Successfully!");
					System.out.println("\nHope to see you soon\nHave a great day ahead!");
				 }
				con.close();
			}catch (SQLException ex) {
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		else if (f==2) {
				System.out.println("\nProceed to Payment...");
		}
	}
	
	//method to cancel confirmed booking
	public void cancelBooking(int i,int f) throws Exception {
		String s= "";
		int flag =0;
		
		if(this.type=="hotel") {
			s="UPDATE "+table+" SET  Isconfirm = '"+flag+"' WHERE serial = '"+getEntries()+"'";
		}
		else if(this.type=="flight") {
			
			s="UPDATE "+table+" SET  Isconfirm = '"+flag+"' WHERE flt_id = '"+getEntries()+"'";
		}
		
			if(f==1) {
				Connection con = null;
				Statement state = null;
				String url = "jdbc:mysql://localhost:3306/"+db;
				String username = "root";
				String password = "";
				try {
					 con = DriverManager.getConnection(url,username,password);
					 state = con.createStatement();
					 state.executeUpdate(s);
					con.close();
				}catch (SQLException ex) {
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
				TimeUnit.SECONDS.sleep(3);
				System.out.println("\nYour Booking Id : "+getValues(getEntries(),i)+" has been Cancelled Successfully!");
				System.out.println("\nHope to see you soon\nHave a great day ahead!");
			}
			else {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("\nYour Booking Id : "+getValues(getEntries(),i)+" is Active!");
			}
	}
	
	public void cart(String table,String user,String fullname,String mobile,String gender) throws Exception {
		
		Booking b1 = new Booking(table,user,fullname,mobile,gender);
		int f=0;
		Scanner scan= new Scanner(System.in);
		//do while loop for iteration
		do {
			System.out.println("\n1. Proceed to Payment\n2. Clear items in Cart\n3. Cancel Previous Booking\n4. Exit");
			b1.printEntries();
			System.out.print("Enter : ");
			f=scan.nextInt();
			int target;
			//switch case to access different methods in database 
			switch(f) {
			
			//case 1 for proceed to payment
			case 1 :
				int c=0;
				if(b1.type=="flight") {
					c=12;
				}
				else if(b1.type=="hotel") {
					c=9;
				}
				String confirm=b1.getValues(b1.getEntries(),c);
				if(confirm==null) {
					System.out.println("Are you sure you want to proceed with the Payment of Rs."+b1.getValues(b1.getEntries(), b1.payment)+" ?");
					System.out.print("1 : Continue\n2 : Abort\nEnter : ");
					int target1=0;
					target1=scan.nextInt();
					b1.getPayment(target1);
				}else {
					System.out.println("\nNo active Booking in Database!");
					System.out.println("\nNo items in cart!\nPlease add sum to proceed further");
				}
				break;
				
			//case 2 to clear the cart	
			case 2:
				int name = 0,v=0,c1=0;
				if(b1.type=="flight") {
					name=8;
					c1=12;
				}
				else if(b1.type=="hotel") {
					name=4;
					c1=9;
				}
				String confirm1=b1.getValues(b1.getEntries(),c1);
				if(confirm1==null) {
					System.out.println("Do you want to clear cart having  "+b1.type+" "+b1.getValues(b1.getEntries(), name)+" "+" ?");
					System.out.print("1 : Yes\n2 : No\nEnter : ");
					target=scan.nextInt();
					v=target;
					b1.deleteItems(target);
				}else {
					System.out.println("\nNo active Booking in Database!");
					System.out.println("\nNo items in cart!\nPlease add sum to proceed further");
				}	
				if(v!=2)
				break;
				
			//case 3 to cancel confirmed booking
			case 3 :
				int i=0,z=0;
				if(b1.type=="hotel") {
					i = 2;
					z=9;
				}
				else if(b1.type=="flight") {
					i = 11;
					z=12;
				}
				String z1=b1.getValues(b1.getEntries(), z);
				try {
					if( z1.equals("1")) {
						System.out.println("Do you want to cancel "+b1.type+" having Booking Id : "+b1.getValues(b1.getEntries(), i)+" ?");
						System.out.print("1 : Yes\n2 : No\nEnter : ");
						target=scan.nextInt();
						b1.cancelBooking(i,target);
					}else
						System.out.println("\nNo active "+b1.type+" booking in database!\n");
				}catch(NullPointerException e) {
					System.out.println("\nNo active Booking in Database!\n");
				}
				break;
				
			//case 4 to exit the loop
			case 4 :
				f=-1;
				break;
			
			//default if something else comes which is not desired
			default :
				System.out.println("Please Enter between 1-4\n");
			}
		}while(f!=-1);
		System.out.println("Thank You for your Booking\nVisit Again!");
	}

//	    public static void main(String args[]) throws Exception {
//	    	Booking g = new Booking();
//	    	g.cart("hotel", "avrun", "Abhiyant", "9876543210", "Male");
//	    }
}