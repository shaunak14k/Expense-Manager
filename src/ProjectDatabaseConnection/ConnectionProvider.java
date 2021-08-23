/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectDatabaseConnection;

import java.sql.*;

/**
 *
 * @author shaun
 * 
 */


public class ConnectionProvider {
    
    public static Connection getCon()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensemanager","root","waitletmethink");
            return con;
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("e");
            return null;
        }
    }
    
}
