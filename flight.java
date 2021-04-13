/* MADE BY - VARUN KALBHORE 
 * ROLL NO - B-84
 */



package msql;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class flight {
    public void plane(String usernaam) throws Exception {

        String dbURL = "jdbc:mysql://localhost:3306/project";
        String username ="root";
        String password = "";
        Connection con;
        Statement st;
        ResultSet rs;
        String user = usernaam; 
        String query ="select * from flight_details";

        con = DriverManager.getConnection(dbURL, username, password);

        st = con.prepareStatement(query);

        rs = st.executeQuery(query);

        System.out.println("Tickets Table : ");                                  // Printing Table of tickets available un the database
        System.out.printf("%-8s %-12s %-12s %-12s %-13s %-16s %-12s %-14s %-20s %-12s \n", "flt_id",
                "flt_no.", "flt_from", "flt_to", "flt_Date", "flt_departure", "Duration", "Airlines " +
                        "Name", "Available Seats", "Cost" );
        while(rs.next()) {
            System.out.printf("%-8s %-12s %-12s %-12s %-14s %-15s %-14s %-18s %-14s %-14s \n",
                    rs.getInt(1), rs.getString(2), rs.getString(3)
                    , rs.getString(4) , rs.getDate(5), rs.getTime(6),
                    rs.getTime(7), rs.getString(8), rs.getInt(9), rs.getInt(10) );

        }

        rs = st.executeQuery(query);
        int flag = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the Start City of your journey: ");           // Taking user input for Start point of journey
        String num2 = sc.nextLine();
        String unum2 = num2.toUpperCase();

        System.out.println("Enter your travel Destination : ");                 // Taking user input for End point of journey
        String num = sc.nextLine();
        String unum = num.toUpperCase();

        System.out.println("Enter number of tickets to be booked : ");          // Taking user input for number of tickets to be booked
        int z = sc.nextInt();

        while(rs.next()) {
             String check = rs.getString(3);                         // Checking for the availability of tickets in database
             String check2 = rs.getString(4);
            int check_seats = rs.getInt(9);
            if(check.equals(unum2) &&  check2.equals(unum) && check_seats >= z) {
                flag = 1;
            }
        }

        if(flag == 1) {
            System.out.println("Available Tickets for your Trip are : ");
            ArrayList<Integer> id = new ArrayList<>();
            ArrayList<String> f_target = new ArrayList<>();
            ArrayList<String> f_no = new ArrayList<>();
            ArrayList<Integer> f_cost = new ArrayList<>();
            ArrayList<Integer> f_seats = new ArrayList<>();
            ArrayList<Time> f_time = new ArrayList<>();
            ArrayList<Time> f_duration = new ArrayList<>();
            ArrayList<String> f_Airline = new ArrayList<>();
            ArrayList<Date> f_Date = new ArrayList<>();
            ArrayList<String> f_origin = new ArrayList<>();

            rs = st.executeQuery(query);
            while (rs.next()) {
               String check = rs.getString(3);                      //Checking for all the flights available for the input trip flights
                String check2 = rs.getString(4);
                int check_seats = rs.getInt(9);
                if (check.equals(unum2) && check2.equals(unum) && check_seats >= z) {
                    id.add(rs.getInt(1));
                    f_no.add(rs.getString(2));
                    f_target.add(rs.getString(4));
                    f_origin.add(rs.getString(3));
                    f_Date.add(rs.getDate(5));
                    f_time.add(rs.getTime(6));
                    f_duration.add(rs.getTime(7));
                    f_Airline.add(rs.getString(8));
                    f_seats.add(rs.getInt(9));
                    f_cost.add(rs.getInt(10));
                }
            }


                                                       // Printing the available flights for the input trip

            String tbl = "CREATE TABLE IF NOT EXISTS `project`.`" + user + "_flight` " +
                    " ( `flt_id` INT NOT NULL , `flt_no` VARCHAR(50) NOT NULL , `" + "flt_origin` VARCHAR(50)" +
                    " NOT NULL , `flt_target` VARCHAR(255) NOT NULL , `flt_Date` DATE NOT NULL , `flt_dept` TIME " +
                    "NOT NULL ," + "`flt_duration` TIME NOT NULL ,  `Airline_name` VARCHAR(50) NOT NULL , `seat` " +
                    "INT NOT NULL , `cost` INT NOT NULL , `pnr` VARCHAR(20) NULL, " + " `Isconfirm` VARCHAR(20) NULL" +
                    " , PRIMARY KEY (`flt_id`)) ";
            st = con.createStatement();
            st.executeUpdate(tbl);

            System.out.printf("%-8s %-12s %-12s %-12s %-13s %-16s %-12s %-14s %-20s %-12s \n", "flt_id", "flt_no." +
                            "", "flt_from", "flt_to", "flt_Date", "flt_departure", "Duration", "Airlines Name",
                            "Available Seats", "Cost" );

            for(int i = 0; i < id.size(); i++) {
                System.out.printf("%-8s %-12s %-12s %-12s %-14s %-15s %-14s %-18s %-14s %-14s \n",id.get(i),
                        f_no.get(i), f_origin.get(i) , f_target.get(i) , f_Date.get(i)
                        ,f_time.get(i),f_duration.get(i), f_Airline.get(i), f_seats.get(i),
                        f_cost.get(i));
            }
            System.out.println("Enter the index number of the flight you want to book : ");
            int x = sc.nextInt();

            for (int i = 0; i < id.size(); i++) {
                if(id.get(i) == x) {
                	String count = "SELECT COUNT(serial) FROM "+user+"_hotel";
    				rs = st.executeQuery(count);
    				rs.next();
    				int cnt = rs.getInt(1);cnt++;
                    String tb_name = "INSERT IGNORE INTO `" + user + "_flight` (`flt_id`, `flt_no`, `flt_origin`," +
                            " `flt_target`, `flt_Date`, `flt_dept`, " + "`flt_duration`, `Airline_name`, `seat`, `cost`)" +
                            " VALUES ('" + cnt + "', '" + f_no.get(i) + "', '" + f_origin.get(i) + "', '" +
                            f_target.get(i) + "', '" + f_Date.get(i) + "', '" + f_time.get(i) + "', '" + f_duration.get(i)
                            + "', '" + f_Airline.get(i) + "', '" + f_seats.get(i)  + "', '" + f_cost.get(i) + "') ";

                    st.executeUpdate(tb_name);
                }
            }
            System.out.println("Procceding to Cart.....\n Please Wait Patiently");
            TimeUnit.SECONDS.sleep(3);
        }

        else {
            System.out.println("Sorry Those tickets are not available");        // Output, if tickets are not available
        }

        st.close();
        con.close();

    }
}
