/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import ProjectDatabaseConnection.ConnectionProvider;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author shaun
 */



public class CreateChart 
{
    int expenseAmount[] = new int[10];
    double expenseAmountPercentage[] = new double[10];
    String expenseCategory[] = new String[10];
    int n=0;
    int i;
    int expenseAmountSum=0;
    
    
    
    public CreateChart(String appTitle, String chartTitle, String chartStartDate, String chartEndDate, String signInEmail)
    {
        
        if(chartStartDate.equals("") || chartEndDate.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please fill all the details");
            return;
        }
        
        int i=0;
        try
        {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("select expense_amount, expense_category from Expense where user_email='"+signInEmail+"'");
            while(rs.next())
            {
                expenseAmount[i] = rs.getInt(1);
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
        
        
        
        PieDataset dataset = createDataset();  
        //DefaultPieDataset pieDataset = new DefaultPieDataset();
        JFreeChart chart = createChart(dataset, "Expenses");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500,300));
        //setContentPane(chartPanel);     
    }
    
    private DefaultPieDataset createDataset()
    {
        DefaultPieDataset result = new DefaultPieDataset();
        for(i=0; i<n ;i++)
        {
            result.setValue(expenseCategory[i], expenseAmountPercentage[i]);		
        }
        
        return result;
    }
    
    private JFreeChart createChart(PieDataset dataset, String title)
    {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
        return chart;
    }
}
