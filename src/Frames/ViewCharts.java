/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import ProjectDatabaseConnection.ConnectionProvider;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

/**
 *
 * @author shaun
 */
public class ViewCharts extends javax.swing.JFrame {

    /**
     * Creates new form ViewCharts
     */
    
    String signInEmail="";
    
    // 0 for INCOME 
    // 1 for EXPENSE
    int chartValue = 1;
    
    public ViewCharts(String sign_in_email) {
        initComponents();
        
        signInEmail = sign_in_email ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartTypeRadioButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        backPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        incomeRadioButton = new javax.swing.JRadioButton();
        expenseRadioButton = new javax.swing.JRadioButton();
        chartStartDateTextField = new javax.swing.JTextField();
        startDateLabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        chartStartDateChooser = new com.toedter.calendar.JDateChooser();
        chartEndDateChooser = new com.toedter.calendar.JDateChooser();
        endDateIcon = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        chartEndDateTextField = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        generateChartButton = new rojerusan.RSMaterialButtonCircle();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(340, 210));
        setMaximumSize(new java.awt.Dimension(1000, 570));
        setMinimumSize(new java.awt.Dimension(1000, 570));
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backPanel.setBackground(new java.awt.Color(255, 51, 51));
        backPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backPanelMouseClicked(evt);
            }
        });
        backPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText(" Back");
        backPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel1.add(backPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 40));

        chartTypeRadioButtonGroup.add(incomeRadioButton);
        incomeRadioButton.setFont(new java.awt.Font("Sitka Small", 3, 18)); // NOI18N
        incomeRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        incomeRadioButton.setText(" Income");
        incomeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(incomeRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 120, 40));

        chartTypeRadioButtonGroup.add(expenseRadioButton);
        expenseRadioButton.setFont(new java.awt.Font("Sitka Small", 3, 18)); // NOI18N
        expenseRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        expenseRadioButton.setSelected(true);
        expenseRadioButton.setText(" Expense");
        expenseRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expenseRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(expenseRadioButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 120, 40));

        chartStartDateTextField.setEditable(false);
        chartStartDateTextField.setBackground(new java.awt.Color(0, 51, 51));
        chartStartDateTextField.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        chartStartDateTextField.setForeground(new java.awt.Color(255, 255, 255));
        chartStartDateTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        chartStartDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartStartDateTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(chartStartDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 240, 30));

        startDateLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        startDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        startDateLabel.setText("Start Date");
        jPanel1.add(startDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 200, 30));

        jLabel18.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/calendar.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, -1));

        chartStartDateChooser.setBackground(new java.awt.Color(75, 137, 243));
        chartStartDateChooser.setToolTipText("");
        chartStartDateChooser.setDateFormatString("yyyy-MM-dd");
        chartStartDateChooser.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chartStartDateChooser.setMaxSelectableDate(new java.util.Date(253370748688000L));
        chartStartDateChooser.setMinSelectableDate(new java.util.Date(-62135785712000L));
        chartStartDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chartStartDateChooserPropertyChange(evt);
            }
        });
        jPanel1.add(chartStartDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 40, 30));

        chartEndDateChooser.setBackground(new java.awt.Color(75, 137, 243));
        chartEndDateChooser.setToolTipText("");
        chartEndDateChooser.setDateFormatString("yyyy-MM-dd");
        chartEndDateChooser.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        chartEndDateChooser.setMaxSelectableDate(new java.util.Date(253370748688000L));
        chartEndDateChooser.setMinSelectableDate(new java.util.Date(-62135785712000L));
        chartEndDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                chartEndDateChooserPropertyChange(evt);
            }
        });
        jPanel1.add(chartEndDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 40, 30));

        endDateIcon.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        endDateIcon.setForeground(new java.awt.Color(255, 255, 255));
        endDateIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/calendar.png"))); // NOI18N
        jPanel1.add(endDateIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, -1, -1));

        endDateLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        endDateLabel.setForeground(new java.awt.Color(255, 255, 255));
        endDateLabel.setText("End Date");
        jPanel1.add(endDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 290, 30));

        chartEndDateTextField.setEditable(false);
        chartEndDateTextField.setBackground(new java.awt.Color(0, 51, 51));
        chartEndDateTextField.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        chartEndDateTextField.setForeground(new java.awt.Color(255, 255, 255));
        chartEndDateTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        chartEndDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chartEndDateTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(chartEndDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 500, 240, 30));

        jPanel9.setBackground(new java.awt.Color(0, 153, 0));
        jPanel9.setForeground(new java.awt.Color(255, 102, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 260, 6));

        generateChartButton.setBackground(new java.awt.Color(255, 0, 0));
        generateChartButton.setText("Generate Pie Chart");
        generateChartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateChartButtonActionPerformed(evt);
            }
        });
        jPanel1.add(generateChartButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 610, 270, 60));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 10, 760));

        jLabel3.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Type of Transaction");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 260, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Small", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pie chart");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 170, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 750));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 199));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 820, 10));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 199));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 10));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, -10, 10, 760));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 820, 750));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backPanelMouseClicked
        // TODO add your handling code here:5

        dispose();
        setVisible(false);
        new Home(signInEmail).setVisible(true);
    }//GEN-LAST:event_backPanelMouseClicked

    private void chartStartDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartStartDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chartStartDateTextFieldActionPerformed

    private void chartStartDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chartStartDateChooserPropertyChange
        // TODO add your handling code here:

        String summaryStartDate = String.valueOf(chartStartDateChooser.getDate());
        // FORMAT THE INCOME DATE
        try
        {
            String dateStr = summaryStartDate;
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            java.util.Date date = (java.util.Date)formatter.parse(dateStr);
            System.out.println(date);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +cal.get(Calendar.DATE);
            System.out.println("formatedDate : " + formatedDate);

            summaryStartDate = formatedDate;

            chartStartDateTextField.setText(summaryStartDate);

            //String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
            //System.out.println("formatedDate : " + formatedDate);

        }
        catch(Exception e)
        {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_chartStartDateChooserPropertyChange

    private void chartEndDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_chartEndDateChooserPropertyChange
        // TODO add your handling code here:
        String budgetEndDate = String.valueOf(chartEndDateChooser.getDate());
        // FORMAT THE INCOME DATE
        try
        {
            String dateStr = budgetEndDate;
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            java.util.Date date = (java.util.Date)formatter.parse(dateStr);
            System.out.println(date);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +cal.get(Calendar.DATE);
            System.out.println("formatedDate : " + formatedDate);

            budgetEndDate = formatedDate;

            chartEndDateTextField.setText(budgetEndDate);

            //String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
            //System.out.println("formatedDate : " + formatedDate);
        }
        catch(Exception e)
        {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_chartEndDateChooserPropertyChange

    private void chartEndDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chartEndDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chartEndDateTextFieldActionPerformed
    
    
    
    int expenseAmount[] = new int[10];
    double expenseAmountPercentage[] = new double[10];
    String expenseCategory[] = new String[10];
    int n=0;
    int i;
    int expenseAmountSum=0;
    
    private void generateChartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateChartButtonActionPerformed

        String chartStartDate = chartStartDateTextField.getText();
        String chartEndDate = chartEndDateTextField.getText();
        
        
//        DefaultPieDataset dataSet = new DefaultPieDataset();
//        dataSet.setValue("China", 19.64);
//        dataSet.setValue("India", 17.3);
//        dataSet.setValue("United States", 4.54);
//        dataSet.setValue("Indonesia", 3.4);
//        dataSet.setValue("Brazil", 2.83);
//        dataSet.setValue("Pakistan", 2.48);
//        dataSet.setValue("Bangladesh", 2.38);
//
//        JFreeChart chart = ChartFactory.createPieChart(
//                        "World Population by countries", dataSet, true, true, false);

        String title="";
        if(chartValue == 1)
            title="Expense";
        else
            title="Income";
                 
        PieChart_AWT demo = new PieChart_AWT( title, signInEmail, chartStartDate, chartEndDate );  
        demo.setSize( 800 , 500 );    
        RefineryUtilities.centerFrameOnScreen(demo);    
        //demo.setVisible(true); 
        
        //demo.pack();
        //setDefaultCloseOperation(HIDE_ON_CLOSE);
        //demo.setUndecorated(true);
        demo.setVisible(true);
        
        
        
    }//GEN-LAST:event_generateChartButtonActionPerformed

     public void piechartFunc( String title ) {
      //super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
      dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
      dataset.setValue( "MotoG" , new Double( 40 ) );    
      dataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Mobile Sales",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }
    
    
    private void expenseRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expenseRadioButtonActionPerformed
        // TODO add your handling code here:
        
        if(expenseRadioButton.isEnabled())
        {
            chartValue = 1;
        }
        
    }//GEN-LAST:event_expenseRadioButtonActionPerformed

    private void incomeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incomeRadioButtonActionPerformed
        // TODO add your handling code here:

        if(incomeRadioButton.isEnabled())
        {
            chartValue = 0;
        }
    }//GEN-LAST:event_incomeRadioButtonActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        
//        new PieChart_AWT().setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCharts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCharts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCharts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCharts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCharts("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanel;
    private com.toedter.calendar.JDateChooser chartEndDateChooser;
    private javax.swing.JTextField chartEndDateTextField;
    private com.toedter.calendar.JDateChooser chartStartDateChooser;
    private javax.swing.JTextField chartStartDateTextField;
    private javax.swing.ButtonGroup chartTypeRadioButtonGroup;
    private javax.swing.JLabel endDateIcon;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JRadioButton expenseRadioButton;
    private rojerusan.RSMaterialButtonCircle generateChartButton;
    private javax.swing.JRadioButton incomeRadioButton;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel startDateLabel;
    // End of variables declaration//GEN-END:variables
}
