/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
import ProjectDatabaseConnection.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author shaun
 */
public class PieChart_AWT extends ApplicationFrame {
   
    
    double expenseAmount[] = new double[10];
        double expenseAmountPercentage[] = new double[10];
        String expenseCategory[] = new String[10];
        int n=0;
        int i=0;
        double expenseAmountSum=0;

        String chartStartDate ="";
        String chartEndDate = "";
    
    public PieChart_AWT( String title, String signInEmail, String chart_start_date, String chart_end_date ) {
        
       
      super(title); 
      
      chartStartDate = chart_start_date;
      chartEndDate = chart_end_date;
       
        if(chartStartDate.equals("") || chartEndDate.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please fill all the details");
            return;
        }
        
        //int i=0;
        try
        {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs;
            
            if(title.equals("Expense")) 
                rs = st.executeQuery("select expense_amount, expense_category from Expense where user_email='"+signInEmail+"'");
            else 
                rs = st.executeQuery("select income_amount, income_category from Income where user_email='"+signInEmail+"'");
            while(rs.next())
            {
                expenseAmount[i] = rs.getDouble(1);
                expenseCategory[i] = rs.getString(2);
                
                i++;
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        n = i;
        
        for(i=0; i<n; i++)
        {
            expenseAmountSum = expenseAmountSum + expenseAmount[i];
        }
        
        for(i=0; i<n; i++)
        {
            expenseAmountPercentage[i] = (expenseAmount[i]/expenseAmountSum) * 100;
        }
        
        // TODO add your handling code here:
        
      
      setContentPane(createDemoPanel( ));
      setDefaultCloseOperation(HIDE_ON_CLOSE);
   }
   
   private PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      
      JOptionPane.showMessageDialog(null, expenseAmountPercentage[0]);
      
      for(i=0; i<n; i++)
      {
          dataset.setValue( expenseCategory[i] , expenseAmountPercentage[i] );  
      }
      
      return dataset;         
   }
   
   private JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Expenses from '"+chartStartDate+"' to '"+chartEndDate+"'",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }

 
}
