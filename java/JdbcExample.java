// From https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
//STEP 1. Import required packages

// Choe: This should work on your MySQL instance running on docker. Just
// change the default username and password below.

import java.sql.*;

public class JdbcExample {

   // JDBC driver name and database URL
   // - depending on your JDBC connector version, comment out 
   //   the appropriate line below.
   // 
   // 1. old version
   //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   // 2. new version 
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  

   // 3. default URL
   static final String DB_URL = "jdbc:mysql://localhost:3306";
   // 4. in case SSL not working 
   // static final String DB_URL = "jdbc:mysql://localhost:3306?useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      //Class.forName("com.mysql.cj.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "USE adventureworks;";
      ResultSet rs;
      //rs = stmt.executeQuery(sql); only works with old versions
      rs = stmt.execute(sql);
      sql = "SELECT EmployeeId, LoginID from employee";
      rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("EmployeeID");
         String login = rs.getString("LoginID");

         //Display values
         System.out.print("ID: " + id);
         System.out.println(", Login: " + login);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample
