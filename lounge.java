/* MADE BY - NITESH DANI
 * ROLL NO - B-52
 */


package msql;

import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import java.util.*;

class formatting{
	public void format1(ArrayList<ArrayList<String>> ab,int rows)
	{
		/*
		 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
		 * make it left justified. Otherwise right justified.
		 */
		boolean leftJustifiedRows = true;
	 
		/*
		 * Maximum allowed width. Line will be wrapped beyond this width.
		 */
		int maxWidth = 30;
	 
		/*
		 * Table to print in console in 2-dimensional array. Each sub-array is a row.
		 */
		String table[][] = new String[rows][7];
		
		for(int i = 0;i < ab.size();i++)
		{
			table[i][0] = ab.get(i).get(0);
			table[i][1] = ab.get(i).get(1);
			table[i][2] = ab.get(i).get(2);
			table[i][3] = ab.get(i).get(3);
			table[i][4] = ab.get(i).get(4);
			table[i][5] = ab.get(i).get(5);
			table[i][6] = ab.get(i).get(6);
		}
	 
		/*
		 * Create new table array with wrapped rows
		 */
		List<String[]> tableList = new ArrayList<>(Arrays.asList(table));
		List<String[]> finalTableList = new ArrayList<>();
		for (String[] row : tableList) {
			// If any cell data is more than max width, then it will need extra row.
			boolean needExtraRow = false;
			// Count of extra split row.
			int splitRow = 0;
			do {
				needExtraRow = false;
				String[] newRow = new String[row.length];
				for (int i = 0; i < row.length; i++) {
					// If data is less than max width, use that as it is.
					if (row[i].length() < maxWidth) {
						newRow[i] = splitRow == 0 ? row[i] : "";
					} else if ((row[i].length() > (splitRow * maxWidth))) {
						// If data is more than max width, then crop data at maxwidth.
						// Remaining cropped data will be part of next row.
						int end = row[i].length() > ((splitRow * maxWidth) + maxWidth)
								? (splitRow * maxWidth) + maxWidth
								: row[i].length();
						newRow[i] = row[i].substring((splitRow * maxWidth), end);
						needExtraRow = true;
					} else {
						newRow[i] = "";
					}
				}
				finalTableList.add(newRow);
				if (needExtraRow) {
					splitRow++;
				}
			} while (needExtraRow);
		}
		String[][] finalTable = new String[finalTableList.size()][finalTableList.get(0).length];
		for (int i = 0; i < finalTable.length; i++) {
			finalTable[i] = finalTableList.get(i);
		}
	 
		/*
		 * Calculate appropriate Length of each column by looking at width of data in
		 * each column.
		 * 
		 * Map columnLengths is <column_number, column_length>
		 */
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(finalTable).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));
//		System.out.println("columnLengths = " + columnLengths);
	 
		/*
		 * Prepare format String
		 */
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
		formatString.append("|\n");
//		System.out.println("formatString = " + formatString.toString());
	 
		/*
		 * Prepare line for top, bottom & below header row.
		 */
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "+-";
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
					(a1, b1) -> a1 + b1);
			templn = templn + "-";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + "+\n";
//		System.out.println("Line = " + line);
	 
		/*
		 * Print table
		 */
		System.out.print("  "+line);
		Arrays.stream(finalTable).limit(1).forEach(a -> System.out.printf("  "+formatString.toString(), a));
		System.out.print("  "+line);
	 
		Stream.iterate(1, (i -> i < finalTable.length), (i -> ++i))
				.forEach(a -> {
					System.out.printf("  "+formatString.toString(), finalTable[a]);
				});
		System.out.print("  "+line+"\n\n\n");
	}
}

public class lounge {
    public void hotel(String usernaam) throws Exception {
    	//Estabilishing SQL Connection
        String url = "jdbc:mysql://localhost:3306/project";
        String uname = "root";
        String pass = "";
        String query = "select * from hotel";
        Scanner sc = new Scanner(System.in);
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        //Creating Table Using username as unique key
        String user = usernaam;
        String tbl = "CREATE TABLE IF NOT EXISTS `project`.`" + user + "_hotel` ( `serial` INT NOT NULL ,`pnr` VARCHAR(50)  NULL, `city` VARCHAR(255) NOT NULL , `name` VARCHAR(255) NOT NULL , `checkin` VARCHAR(255) NOT NULL , `checkout` VARCHAR(255) NOT NULL ,`guests` INT NOT NULL,`room` INT NOT NULL, `Isconfirm` BOOLEAN NULL ,`cost` INT NOT NULL , `total` INT NOT NULL , PRIMARY KEY (`serial`))";
        st = con.createStatement();
        st.executeUpdate(tbl);
        
        int choice = 0;
        while(true)
        {
        	System.out.println(" Please Select an Alternative:- ");
        	System.out.println("  1. View all Hotel Details.");
        	System.out.println("  2. Book a Hotel.");
        	System.out.println("  3. Exit");
        	choice = sc.nextInt();
        	if(choice==1)
        	{
        		//Storing SQL Database Elements in ArrayList to print all hotel details
        		st = con.createStatement();
                rs = st.executeQuery(query);
                ArrayList<ArrayList<String>> set1 = new ArrayList<ArrayList<String>>();
                set1.add(new ArrayList<>(Arrays.asList("Sr No.","City","Hotel Name","Rating(Out of 5.0)","Features Offered by Hotel","Dist from Airport","Cost")));
                while(rs.next())
                {
                	set1.add(new ArrayList<>(Arrays.asList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7))));
                }
                formatting first = new formatting();
                first.format1(set1, set1.size());
        		
        	}
        	else if(choice==2)
        	{
        		// Asking User for Hotel Booking Details
        		ArrayList<ArrayList<String>> x = new ArrayList<ArrayList<String>>();
                x.add(new ArrayList<>(Arrays.asList("Sr No.","City","Hotel Name","Rating(Out of 5.0)","Features Offered by Hotel","Dist from Airport","Cost")));
                sc.nextLine();
                System.out.printf("  Please Enter City:- ");
                String city = sc.nextLine();
                System.out.printf("  Please Check In Date (For Eg - yyyy-mm-dd):-");
                String checkin = sc.nextLine();
                System.out.printf("  Please Enter Check out Date (For Eg - yyyy-mm-dd):-");
                String checkout = sc.nextLine();
                System.out.printf("  Please Enter Number of Guests:- ");
                int guest = sc.nextInt();
                System.out.printf("  Please Enter Number of Rooms to Reserve: -");
                int rooms = sc.nextInt();
                System.out.println();
            	//Parsing the date
            	LocalDate dateBefore = LocalDate.parse(checkin);
            	LocalDate dateAfter = LocalDate.parse(checkout);
            		
            	//calculating number of days in between
            	int days = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);
            		
                st = con.createStatement();
                rs = st.executeQuery(query);
                while(rs.next())
                {
                	if(rs.getString(2).toLowerCase().contains(city.toLowerCase()))
                	x.add(new ArrayList<>(Arrays.asList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7))));
                }
                //Recommending user hotels based on entered information.
                formatting b = new formatting();
                System.out.println("\n  Hotels Recommendation acoording to your details:-");
                b.format1(x,x.size());
                //Asking user to book hotel
                System.out.print("\n  Please Enter the Serial Number of Hotel you want to Book:- ");
                int serialno = sc.nextInt();
                int total = guest;
                for(int i = 1;i < x.size();i++)
                {
                	if(Integer.parseInt(x.get(i).get(0))==serialno)
                	{
                		String cst="";
                		String test = x.get(i).get(6);
                		for(int j = 0;j < x.get(i).get(6).length();j++)
                		{
                			if(test.charAt(j)>='0' && test.charAt(j)<='9')
                			{
                				cst += test.charAt(j);
                			}
                		}
                		total = Integer.parseInt(cst)*total*days;
                		//Asking user confirmation to proceed with booking
                		System.out.printf("\n  Are you Sure you Want to Book "+x.get(i).get(2)+" for "+guest+" people at Rs. "+total+"\n  Input Yes or No:-");
                		String res = "";
                		res = sc.next();
                		res.toLowerCase();
                		if(res.equals("yes"))
                		{
                			System.out.println("\n  Thank you for Booking Hotel!!!!\n  Visit Again :)\n\n");
                			if(total>0) {
                				//Calculating the number of rows in database
                				String count = "SELECT COUNT(serial) FROM "+user+"_hotel";
                				rs = st.executeQuery(count);
                				rs.next();
                				int cnt = rs.getInt(1);cnt++;
                			// Inserting booked hotel details in cart database.
                			String insert = "INSERT IGNORE INTO `"+user+"_hotel`  (`serial`, `city`, `name`, `checkin`, `checkout`, `guests`,`room`, `Isconfirm`,`cost`, `total`) VALUES ('"+cnt+"', '"+x.get(i).get(1)+"', '"+x.get(i).get(2)+"', '"+checkin+"', '"+checkout+"', '"+guest+"','"+rooms+"', NULL,'"+Integer.parseInt(cst)+"', '"+total+"')";
                			st.executeUpdate(insert);
                			}
                		}
                		else
                		{
                			System.out.println("You Cancelled the Booking.\nVisit Again :)\n\n");
                		}
                	}
                }
        	}
        	else
        	{
        		break;
        	}
        }
        st.close();
        con.close();
    }

}