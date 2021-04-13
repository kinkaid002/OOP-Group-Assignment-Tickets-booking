/* MADE BY - ABHIYANT GWALANI
 * ROLL NO - B-11
 */


package msql;

import java.sql.*;
import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Password Encryption.
class MD5 {
    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

public class trial {
    public static String db_name;
    public static String db_gen;
    public static String db_age;
    public static String db_uname;
    public static String db_pass;
    public static String db_phone;
    public static String db_address;
    public static String name;
    boolean sucess = false;

    public boolean authentication() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("* Welcome To Travel Genie **");
        String password = "";
        //Establishing Connection to Database
        String url = "jdbc:mysql://localhost:3306/project";
        String uname = "root";
        String pass = "";
        Class.forName("java.sql.Driver");
        String query = "SELECT * FROM credentials";
        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement st = con.createStatement();
        int choice;
        while(true)
        {
        	ResultSet rs = st.executeQuery(query);
            int flag = -1;
            int flag1 = 1;
            System.out.println("Please Select An Alternative:- ");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Press Any Key to Exit.");
            System.out.print("Enter An Option:- ");
            choice = sc.nextInt();
            //Login
            if(choice==1)
            {
                sc.nextLine();
                System.out.print(" Enter Username => ");
                name = sc.nextLine();

                System.out.print(" Enter Password => ");
                password = sc.nextLine();
                password = MD5.getMd5(password);
//                System.out.println(password);



                while (rs.next()) {
                //Checking for Correct Username
                    if (rs.getString(5).equals(name)) {
                        flag1 = 0;
                        //Checking for password associated with entered username
                        if (rs.getString(6).equals(password)) {
                            //Assigning Column indices to variables for further use
                            System.out.println("Successful Login");
                            db_name = rs.getString(2);
                            db_gen = rs.getString(3);
                            db_age = rs.getString(4);
                            db_uname = rs.getString(5);
                            db_pass = rs.getString(6);
                            db_phone = rs.getString(7);
                            db_address = rs.getString(8);
                            sucess = true;
                            flag = 0;
                            break;
                        } else {
                            flag = 1;
                        }
                    }

                }
                if (flag == 1) {
                    System.out.println("---------------Incorrect Password---------------");
                }
                else if(flag==0)
                {
                    break;
                }
                else if (flag1 == 1) {
                    System.out.println("---------------Incorrect username---------------");
                    System.out.println("---------------Please Register From Main Menu------------------");
                }
            }
            else if(choice==2)
            {
                    //Register New User
                sc.nextLine();
                System.out.print(" Enter Full Name => ");
                String full_name = sc.nextLine();
                System.out.print(" Enter Username => ");
                String u_name = sc.nextLine();
                System.out.print(" Enter Password => ");
                String pa = sc.nextLine();
                pa = MD5.getMd5(pa);
                System.out.print(" Enter Phone Number => ");
                String phone = sc.nextLine();
                System.out.print(" Enter Address => ");
                String address = sc.nextLine();
                System.out.print(" Enter Gender(0 for Male & 1 for Female) => ");
                int gen = sc.nextInt();
                System.out.print(" Enter Age => ");
                int age = sc.nextInt();
                String insert = "INSERT IGNORE INTO `credentials` ( `Name`, `Gender`, `Age`, `Username`, `Password`, `Phone_No`, `Address`) VALUES ( '" + full_name + "', '" + gen + "', '" + age + "', '" + u_name + "', '" + pa + "', '" + phone + "', '" + address + "')";
                //System.out.println(insert);
                st = con.createStatement();
                st.executeUpdate(insert);
                System.out.println("Registered Successfully !!!   Please Login to Proceed");
            }
            else
            {
                System.out.println("Exiting....");
                break;
            }
        }

        //Closing Connection
        st.close();
        con.close();
        return sucess;
    }
}