/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import ProjectDatabaseConnection.ConnectionProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author shaun
 */

public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    String signInEmail;
    
    
    
    public Home(String sign_in_email) {
        initComponents();
        
        signInEmail = sign_in_email;
        
        byDatePanel.setVisible(false);
        byCategoryPanel.setVisible(false);
        byAmountPanel.setVisible(false);
        
        try
        {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs;
            
            //Check for duplicate email
            rs = st.executeQuery("select user_name from User where user_email='"+sign_in_email+"'");
            while(rs.next())
            {
                userNameLabel.setText(rs.getString(1));
            }
            
            //CHECK if table is empty
            boolean flag = true;
            // LATEST INCOME VALUES 
            rs = st.executeQuery("select income_date, income_amount, income_payer, income_category from Income where user_email='"+signInEmail+"' and income_date in (select MAX(income_date) from Income where user_email='"+signInEmail+"')");
            while(rs.next())
            {
                homeIncomeDate.setText(rs.getString(1));
                homeIncomeAmount.setText(rs.getString(2));
                homeIncomePayer.setText(rs.getString(3));
                homeIncomeCategory.setText(rs.getString(4));
                
                flag = false;
            }
            if(flag)
            {
                homeIncomeDateHeading.setText(" No Incomes ");
                homeIncomeAmountHeading.setVisible(false);
                homeIncomePayerHeading.setVisible(false);
                homeIncomeCategoryHeading.setVisible(false);
                
                homeIncomeDate.setVisible(false);
                homeIncomeAmount.setVisible(false);
                homeIncomeAmountRs.setVisible(false);
                homeIncomePayer.setVisible(false);
                homeIncomeCategory.setVisible(false);
            }
            
//            if(!rs.next())
//            {
//                homeIncomeDateHeading.setText(" No Incomes ");
//                homeIncomeAmountHeading.setVisible(false);
//                homeIncomePayerHeading.setVisible(false);
//                homeIncomeCategoryHeading.setVisible(false);
//                
//                homeIncomeDate.setVisible(false);
//                homeIncomeAmount.setVisible(false);
//                homeIncomeAmountRs.setVisible(false);
//                homeIncomePayer.setVisible(false);
//                homeIncomeCategory.setVisible(false);
//            }
//            else
//            {
//                while(rs.next())
//                {
//                    homeIncomeDate.setText(rs.getString(1));
//                    homeIncomeAmount.setText(rs.getString(2));
//                    homeIncomePayer.setText(rs.getString(3));
//                    homeIncomeCategory.setText(rs.getString(4));
//                }
//            }
            
            // LATEST EXPENSE VALUES 
            flag = true;
            rs = st.executeQuery("select expense_date, expense_amount, expense_payee, expense_category from Expense where user_email='"+signInEmail+"' and expense_date in (select MAX(expense_date) from Expense where user_email='"+signInEmail+"')");
            while(rs.next())
            {
                homeExpenseDate.setText(rs.getString(1));
                homeExpenseAmount.setText(rs.getString(2));
                homeExpensePayee.setText(rs.getString(3));
                homeExpenseCategory.setText(rs.getString(4));
                
                flag = false;
            }
            if(flag)
            {
                homeExpenseDateHeading.setText(" No Incomes ");
                homeExpenseAmountHeading.setVisible(false);
                homeExpensePayeeHeading.setVisible(false);
                homeExpenseCategoryHeading.setVisible(false);
                
                homeExpenseDate.setVisible(false);
                homeExpenseAmount.setVisible(false);
                homeExpenseAmountRs.setVisible(false);
                homeExpensePayee.setVisible(false);
                homeExpenseCategory.setVisible(false);
            }
            
            // INCOME TABLE
            //System.out.println("START : "+summary_start_date);
            rs = st.executeQuery("select transaction_date as Date, transaction_amount as Amount, transaction_category as Category, transaction_beneficiary as Beneficiary from Transaction where transaction_type='Income' and user_email='"+signInEmail+"'");
            incomeSummaryTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            // EXPENSE TABLE
            //System.out.println("START : "+summary_start_date);
            rs = st.executeQuery("select transaction_date as Date, transaction_amount as Amount, transaction_category as Category, transaction_beneficiary as Beneficiary from Transaction where transaction_type='Expense' and user_email='"+signInEmail+"'");
            expenseSummaryTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            // PRINT BALANCE
            int incomeTotal = 0;
            int expenseTotal = 0;
            // INCOME TOTAL
            rs = st.executeQuery("select income_amount from Income where user_email='"+signInEmail+"'");
            while(rs.next())
            {
                incomeTotal = incomeTotal + rs.getInt(1);
            }
            
            // EXPENSE TOTAL
            rs = st.executeQuery("select expense_amount from Expense where user_email='"+signInEmail+"'");
            while(rs.next())
            {
                expenseTotal = expenseTotal + rs.getInt(1);
            }
            
            int balance = incomeTotal - expenseTotal;
            
            // SET TEXT
            balanceLabel.setText("Rs." + String.valueOf(balance));
            
            
       
//            //Create Dataset
//            DefaultPieDataset barDataset = new DefaultPieDataset();
//            
//            
//            //Create chart
//            JFreeChart piechart = ChartFactory.createPieChart("mobile", barDataset, false, true ,false);
//            
//            PiePlot piePlot = (PiePlot)piechart.getPlot();
//            
//            PiePlot.setSectionPaint("ANDROID", new Color(255,255,102));
//            PiePlot.setSectionPaint("APPLE", new Color(255,255,50));
//            //PiePlot.setValue("IPHONE", new Double(50));
//            
//            
//            //Create PIECHART
//            pieChartPanel pieChart = new pieChartPanel(piechart);  
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
        }
        try
        {
            //showPieChart();
        }
        catch(Exception e){}
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        userNameWelcomeLabel11 = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        userNameWelcomeLabel13 = new javax.swing.JLabel();
        userNameWelcomeLabel12 = new javax.swing.JLabel();
        logoutPanel = new javax.swing.JPanel();
        userNameWelcomeLabel22 = new javax.swing.JLabel();
        manageExpensePanel = new javax.swing.JPanel();
        userNameWelcomeLabel24 = new javax.swing.JLabel();
        manageIncomePanel = new javax.swing.JPanel();
        userNameWelcomeLabel25 = new javax.swing.JLabel();
        manageBudgetPanel = new javax.swing.JPanel();
        userNameWelcomeLabel26 = new javax.swing.JLabel();
        setReminderPanel = new javax.swing.JPanel();
        userNameWelcomeLabel27 = new javax.swing.JLabel();
        viewReminderPanel = new javax.swing.JPanel();
        userNameWelcomeLabel28 = new javax.swing.JLabel();
        viewChartPanel = new javax.swing.JPanel();
        userNameWelcomeLabel30 = new javax.swing.JLabel();
        viewSummaryPanel = new javax.swing.JPanel();
        userNameWelcomeLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        incomeSummaryTable = new rojerusan.RSTableMetro();
        jLabel16 = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        homeIncomePanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        homeIncomeDate = new javax.swing.JLabel();
        homeIncomeDateHeading = new javax.swing.JLabel();
        homeIncomeAmountRs = new javax.swing.JLabel();
        homeIncomeAmountHeading = new javax.swing.JLabel();
        homeIncomePayer = new javax.swing.JLabel();
        homeIncomePayerHeading = new javax.swing.JLabel();
        homeIncomeCategory = new javax.swing.JLabel();
        homeIncomeCategoryHeading = new javax.swing.JLabel();
        homeIncomeAmount = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeExpenseAmountRs = new javax.swing.JLabel();
        homeExpenseCategory = new javax.swing.JLabel();
        homeExpenseAmountHeading = new javax.swing.JLabel();
        homeExpensePayee = new javax.swing.JLabel();
        homeExpenseDateHeading = new javax.swing.JLabel();
        homeExpensePayeeHeading = new javax.swing.JLabel();
        homeExpenseCategoryHeading = new javax.swing.JLabel();
        homeExpenseAmount = new javax.swing.JLabel();
        homeExpenseDate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        expenseSummaryTable = new rojerusan.RSTableMetro();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        byCategoryPanel = new javax.swing.JPanel();
        testPanel4 = new javax.swing.JPanel();
        userNameWelcomeLabel35 = new javax.swing.JLabel();
        testPanel5 = new javax.swing.JPanel();
        userNameWelcomeLabel36 = new javax.swing.JLabel();
        userNameWelcomeLabel32 = new javax.swing.JLabel();
        byDatePanel = new javax.swing.JPanel();
        testPanel3 = new javax.swing.JPanel();
        userNameWelcomeLabel34 = new javax.swing.JLabel();
        userNameWelcomeLabel41 = new javax.swing.JLabel();
        byAmountPanel = new javax.swing.JPanel();
        testPanel7 = new javax.swing.JPanel();
        userNameWelcomeLabel38 = new javax.swing.JLabel();
        testPanel8 = new javax.swing.JPanel();
        userNameWelcomeLabel39 = new javax.swing.JLabel();
        userNameWelcomeLabel40 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1360, 750));
        setMinimumSize(new java.awt.Dimension(1360, 750));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/male_user_50px.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 50, 50));

        jLabel4.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Expense Manager");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, 40));

        userNameLabel.setFont(new java.awt.Font("Sitka Small", 2, 18)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameLabel.setText("User_Name");
        jPanel1.add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 230, 40));

        jLabel5.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Welcome, ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, 40));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("X");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 0, 50, 50));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 4, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 70));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 102, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel11.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel11.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        userNameWelcomeLabel11.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_Home_26px_2.png"))); // NOI18N
        userNameWelcomeLabel11.setText("  Home");
        jPanel3.add(userNameWelcomeLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 270, 60));

        profilePanel.setBackground(new java.awt.Color(0, 204, 204));
        profilePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profilePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilePanelMouseExited(evt);
            }
        });
        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel13.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel13.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        userNameWelcomeLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_Read_Online_26px.png"))); // NOI18N
        userNameWelcomeLabel13.setText("  Profile");
        profilePanel.add(userNameWelcomeLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 120, 40));

        jPanel2.add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 270, 60));

        userNameWelcomeLabel12.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel12.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        userNameWelcomeLabel12.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel12.setText("Features");
        jPanel2.add(userNameWelcomeLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 40));

        logoutPanel.setBackground(new java.awt.Color(0, 102, 255));
        logoutPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutPanelMouseExited(evt);
            }
        });
        logoutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel22.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel22.setFont(new java.awt.Font("MS PGothic", 1, 18)); // NOI18N
        userNameWelcomeLabel22.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_Exit_26px_2.png"))); // NOI18N
        userNameWelcomeLabel22.setText(" Logout");
        logoutPanel.add(userNameWelcomeLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 30));

        jPanel2.add(logoutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 270, 50));

        manageExpensePanel.setBackground(new java.awt.Color(51, 51, 51));
        manageExpensePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageExpensePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageExpensePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageExpensePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageExpensePanelMouseExited(evt);
            }
        });
        manageExpensePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel24.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel24.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel24.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/cash.png"))); // NOI18N
        userNameWelcomeLabel24.setText("  Manage Expenses");
        manageExpensePanel.add(userNameWelcomeLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(manageExpensePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 270, 60));

        manageIncomePanel.setBackground(new java.awt.Color(51, 51, 51));
        manageIncomePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageIncomePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageIncomePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageIncomePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageIncomePanelMouseExited(evt);
            }
        });
        manageIncomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel25.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel25.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel25.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/income.png"))); // NOI18N
        userNameWelcomeLabel25.setText("  Manage Income");
        manageIncomePanel.add(userNameWelcomeLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(manageIncomePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 270, 60));

        manageBudgetPanel.setBackground(new java.awt.Color(51, 51, 51));
        manageBudgetPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageBudgetPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manageBudgetPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageBudgetPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageBudgetPanelMouseExited(evt);
            }
        });
        manageBudgetPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel26.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel26.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel26.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/analytics.png"))); // NOI18N
        userNameWelcomeLabel26.setText("  Manage Budget");
        manageBudgetPanel.add(userNameWelcomeLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(manageBudgetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 270, 60));

        setReminderPanel.setBackground(new java.awt.Color(51, 51, 51));
        setReminderPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setReminderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setReminderPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setReminderPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setReminderPanelMouseExited(evt);
            }
        });
        setReminderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel27.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel27.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel27.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/add-reminder.png"))); // NOI18N
        userNameWelcomeLabel27.setText("  Set Reminders");
        setReminderPanel.add(userNameWelcomeLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(setReminderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 270, 60));

        viewReminderPanel.setBackground(new java.awt.Color(51, 51, 51));
        viewReminderPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewReminderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewReminderPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewReminderPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewReminderPanelMouseExited(evt);
            }
        });
        viewReminderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel28.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel28.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel28.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/reminder.png"))); // NOI18N
        userNameWelcomeLabel28.setText("  View Reminders");
        viewReminderPanel.add(userNameWelcomeLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(viewReminderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 270, 60));

        viewChartPanel.setBackground(new java.awt.Color(51, 51, 51));
        viewChartPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewChartPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewChartPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewChartPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewChartPanelMouseExited(evt);
            }
        });
        viewChartPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel30.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel30.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel30.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/pie-chart.png"))); // NOI18N
        userNameWelcomeLabel30.setText("  View Charts");
        viewChartPanel.add(userNameWelcomeLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(viewChartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 270, 60));

        viewSummaryPanel.setBackground(new java.awt.Color(51, 51, 51));
        viewSummaryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewSummaryPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewSummaryPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewSummaryPanelMouseExited(evt);
            }
        });
        viewSummaryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel33.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel33.setFont(new java.awt.Font("MS PGothic", 0, 18)); // NOI18N
        userNameWelcomeLabel33.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel33.setText("  View Summary");
        viewSummaryPanel.add(userNameWelcomeLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 200, 40));

        jPanel2.add(viewSummaryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 270, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 680));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        incomeSummaryTable.setForeground(new java.awt.Color(0, 153, 153));
        incomeSummaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Amount", "Category", "Beneficiary"
            }
        ));
        incomeSummaryTable.setColorBackgoundHead(new java.awt.Color(0, 153, 153));
        incomeSummaryTable.setColorBordeFilas(new java.awt.Color(0, 153, 153));
        incomeSummaryTable.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        incomeSummaryTable.setColorSelBackgound(new java.awt.Color(153, 204, 255));
        incomeSummaryTable.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        incomeSummaryTable.setFuenteFilas(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        incomeSummaryTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        incomeSummaryTable.setRowHeight(35);
        incomeSummaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                incomeSummaryTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(incomeSummaryTable);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 860, 160));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Sitka Small", 1, 22)); // NOI18N
        jLabel16.setText("Income Summary");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 240, 40));

        balanceLabel.setBackground(new java.awt.Color(255, 255, 255));
        balanceLabel.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        balanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balanceLabel.setText("{bal}");
        balanceLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(balanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 200, 40));

        homeIncomePanel.setBackground(new java.awt.Color(51, 51, 51));
        homeIncomePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        homeIncomePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeIncomePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeIncomePanelMouseEntered(evt);
            }
        });
        homeIncomePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                homeIncomePanelComponentShown(evt);
            }
        });
        homeIncomePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        homeIncomePanel.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 10));

        homeIncomeDate.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeIncomeDate.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeDate.setText("{date}");
        homeIncomePanel.add(homeIncomeDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 28, 140, 20));

        homeIncomeDateHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeIncomeDateHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeDateHeading.setText("Date        - ");
        homeIncomePanel.add(homeIncomeDateHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        homeIncomeAmountRs.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        homeIncomeAmountRs.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeAmountRs.setText("Rs.");
        homeIncomePanel.add(homeIncomeAmountRs, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 30, -1));

        homeIncomeAmountHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeIncomeAmountHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeAmountHeading.setText("Amount   - ");
        homeIncomePanel.add(homeIncomeAmountHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        homeIncomePayer.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeIncomePayer.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomePayer.setText("{payer}");
        homeIncomePanel.add(homeIncomePayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, -1));

        homeIncomePayerHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeIncomePayerHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomePayerHeading.setText("Payer      - ");
        homeIncomePanel.add(homeIncomePayerHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        homeIncomeCategory.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeIncomeCategory.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeCategory.setText("{category}");
        homeIncomePanel.add(homeIncomeCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, -1));

        homeIncomeCategoryHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeIncomeCategoryHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeCategoryHeading.setText("Category - ");
        homeIncomePanel.add(homeIncomeCategoryHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        homeIncomeAmount.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        homeIncomeAmount.setForeground(new java.awt.Color(255, 255, 255));
        homeIncomeAmount.setText("{amount}");
        homeIncomePanel.add(homeIncomeAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 140, -1));

        jPanel4.add(homeIncomePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 130));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 10));

        homeExpenseAmountRs.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        homeExpenseAmountRs.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseAmountRs.setText("Rs.");
        jPanel5.add(homeExpenseAmountRs, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 30, -1));

        homeExpenseCategory.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeExpenseCategory.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseCategory.setText("{category}");
        jPanel5.add(homeExpenseCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, -1));

        homeExpenseAmountHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeExpenseAmountHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseAmountHeading.setText("Amount   - ");
        jPanel5.add(homeExpenseAmountHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        homeExpensePayee.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeExpensePayee.setForeground(new java.awt.Color(255, 255, 255));
        homeExpensePayee.setText("{payee}");
        jPanel5.add(homeExpensePayee, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, -1));

        homeExpenseDateHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeExpenseDateHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseDateHeading.setText("Date        - ");
        jPanel5.add(homeExpenseDateHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        homeExpensePayeeHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeExpensePayeeHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeExpensePayeeHeading.setText("Payee      - ");
        jPanel5.add(homeExpensePayeeHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        homeExpenseCategoryHeading.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        homeExpenseCategoryHeading.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseCategoryHeading.setText("Category - ");
        jPanel5.add(homeExpenseCategoryHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        homeExpenseAmount.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        homeExpenseAmount.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseAmount.setText("{amount}");
        jPanel5.add(homeExpenseAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 140, -1));

        homeExpenseDate.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        homeExpenseDate.setForeground(new java.awt.Color(255, 255, 255));
        homeExpenseDate.setText("{date}");
        jPanel5.add(homeExpenseDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 140, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, -1, 130));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel3.setText("Latest Expense");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, -1, 40));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Sitka Small", 1, 22)); // NOI18N
        jLabel18.setText("Expense Summary");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 260, 40));

        expenseSummaryTable.setForeground(new java.awt.Color(0, 204, 204));
        expenseSummaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Amount", "Category", "Beneficiary"
            }
        ));
        expenseSummaryTable.setToolTipText("");
        expenseSummaryTable.setCellSelectionEnabled(true);
        expenseSummaryTable.setColorBackgoundHead(new java.awt.Color(0, 204, 204));
        expenseSummaryTable.setColorBordeFilas(new java.awt.Color(0, 204, 204));
        expenseSummaryTable.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        expenseSummaryTable.setColorSelBackgound(new java.awt.Color(255, 153, 153));
        expenseSummaryTable.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        expenseSummaryTable.setFuenteFilas(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        expenseSummaryTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        expenseSummaryTable.setRowHeight(35);
        expenseSummaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                expenseSummaryTableMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(expenseSummaryTable);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 860, 160));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 199));
        jPanel4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 1080, 10));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 10, 680));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel4.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 680));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 199));
        jPanel4.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 10));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel19.setText("Latest Income");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 40));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel21.setText("Balance ");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, -1, 40));

        byCategoryPanel.setBackground(new java.awt.Color(102, 102, 102));
        byCategoryPanel.setBorder(new javax.swing.border.MatteBorder(null));
        byCategoryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        byCategoryPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                byCategoryPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                byCategoryPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                byCategoryPanelMouseExited(evt);
            }
        });
        byCategoryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        testPanel4.setBackground(new java.awt.Color(204, 0, 204));
        testPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel35.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel35.setFont(new java.awt.Font("MS PGothic", 0, 12)); // NOI18N
        userNameWelcomeLabel35.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel35.setText("By Time Frame");
        testPanel4.add(userNameWelcomeLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        byCategoryPanel.add(testPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 40));

        testPanel5.setBackground(new java.awt.Color(204, 0, 204));
        testPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel36.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel36.setFont(new java.awt.Font("MS PGothic", 0, 12)); // NOI18N
        userNameWelcomeLabel36.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel36.setText("By Time Frame");
        testPanel5.add(userNameWelcomeLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        byCategoryPanel.add(testPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 40));

        userNameWelcomeLabel32.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel32.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        userNameWelcomeLabel32.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel32.setText("Income");
        byCategoryPanel.add(userNameWelcomeLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 20));

        jPanel4.add(byCategoryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, 60));

        byDatePanel.setBackground(new java.awt.Color(102, 102, 102));
        byDatePanel.setBorder(new javax.swing.border.MatteBorder(null));
        byDatePanel.setForeground(new java.awt.Color(102, 102, 102));
        byDatePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        byDatePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                byDatePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                byDatePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                byDatePanelMouseExited(evt);
            }
        });
        byDatePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        testPanel3.setBackground(new java.awt.Color(204, 0, 204));
        testPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel34.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel34.setFont(new java.awt.Font("MS PGothic", 0, 12)); // NOI18N
        userNameWelcomeLabel34.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel34.setText("By Time Frame");
        testPanel3.add(userNameWelcomeLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        byDatePanel.add(testPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 40));

        userNameWelcomeLabel41.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel41.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        userNameWelcomeLabel41.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel41.setText("By Date");
        byDatePanel.add(userNameWelcomeLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 20));

        jPanel4.add(byDatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 130, 60));

        byAmountPanel.setBackground(new java.awt.Color(102, 102, 102));
        byAmountPanel.setBorder(new javax.swing.border.MatteBorder(null));
        byAmountPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        byAmountPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                byAmountPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                byAmountPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                byAmountPanelMouseExited(evt);
            }
        });
        byAmountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        testPanel7.setBackground(new java.awt.Color(204, 0, 204));
        testPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel38.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel38.setFont(new java.awt.Font("MS PGothic", 0, 12)); // NOI18N
        userNameWelcomeLabel38.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel38.setText("By Time Frame");
        testPanel7.add(userNameWelcomeLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        byAmountPanel.add(testPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 40));

        testPanel8.setBackground(new java.awt.Color(204, 0, 204));
        testPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameWelcomeLabel39.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel39.setFont(new java.awt.Font("MS PGothic", 0, 12)); // NOI18N
        userNameWelcomeLabel39.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_View_Details_26px.png"))); // NOI18N
        userNameWelcomeLabel39.setText("By Time Frame");
        testPanel8.add(userNameWelcomeLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 20));

        byAmountPanel.add(testPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 40));

        userNameWelcomeLabel40.setBackground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel40.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        userNameWelcomeLabel40.setForeground(new java.awt.Color(255, 255, 255));
        userNameWelcomeLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userNameWelcomeLabel40.setText("Expense");
        byAmountPanel.add(userNameWelcomeLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 20));

        jPanel4.add(byAmountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 130, 60));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 1090, 680));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel7.setText("Latest Income");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 150, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void homeIncomePanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_homeIncomePanelComponentShown
        // TODO add your handling code here:
        
        try
        {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs;
            
            //Check for duplicate email
            rs = st.executeQuery("select income_date, income_amount, income_payer, income_category from Income where user_email='"+signInEmail+"' and income_date=MAX(income_date)");
            while(rs.next())
            {
                homeIncomeDate.setText(rs.getString(1));
                homeIncomeAmountRs.setText(rs.getString(2));
                homeIncomePayer.setText(rs.getString(3));
                homeIncomeCategory.setText(rs.getString(4));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
        }
    }//GEN-LAST:event_homeIncomePanelComponentShown

    private void disableSummaryPanels()
    {
        byDatePanel.setVisible(false);
        byCategoryPanel.setVisible(false);
        byAmountPanel.setVisible(false);
    }
    
    private void manageIncomePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageIncomePanelMouseClicked
        // TODO add your handling code here:
        
        dispose();
        new Home(signInEmail).setVisible(false);      
        new AddIncome(signInEmail).setVisible(true);
    }//GEN-LAST:event_manageIncomePanelMouseClicked

    private void manageIncomePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageIncomePanelMouseEntered
        // TODO add your handling code here:   
        manageIncomePanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
    }//GEN-LAST:event_manageIncomePanelMouseEntered

    private void manageIncomePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageIncomePanelMouseExited
        // TODO add your handling code here:
        manageIncomePanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_manageIncomePanelMouseExited

    private void manageExpensePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageExpensePanelMouseEntered
        // TODO add your handling code here:
        manageExpensePanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
        
    }//GEN-LAST:event_manageExpensePanelMouseEntered

    private void manageExpensePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageExpensePanelMouseExited
        // TODO add your handling code here:
        manageExpensePanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_manageExpensePanelMouseExited

    private void manageBudgetPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageBudgetPanelMouseEntered
        // TODO add your handling code here:
        manageBudgetPanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
    }//GEN-LAST:event_manageBudgetPanelMouseEntered

    private void manageBudgetPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageBudgetPanelMouseExited
        // TODO add your handling code here:
        manageBudgetPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_manageBudgetPanelMouseExited

    private void setReminderPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setReminderPanelMouseEntered
        // TODO add your handling code here:
        setReminderPanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
    }//GEN-LAST:event_setReminderPanelMouseEntered

    private void setReminderPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setReminderPanelMouseExited
        // TODO add your handling code here:
        setReminderPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_setReminderPanelMouseExited

    private void viewReminderPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewReminderPanelMouseEntered
        // TODO add your handling code here:
        viewReminderPanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
    }//GEN-LAST:event_viewReminderPanelMouseEntered

    private void viewReminderPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewReminderPanelMouseExited
        // TODO add your handling code here:
        viewReminderPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_viewReminderPanelMouseExited
    
  
    
    private void viewSummaryPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSummaryPanelMouseEntered
        // TODO add your handling code here:
        viewSummaryPanel.setBackground(new Color(102,102,102));
        
        byDatePanel.setVisible(true);
        byCategoryPanel.setVisible(true);
        byAmountPanel.setVisible(true);
        
        
    }//GEN-LAST:event_viewSummaryPanelMouseEntered

    private void viewSummaryPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewSummaryPanelMouseExited
        // TODO add your handling code here:
        viewSummaryPanel.setBackground(new Color(51,51,51));
        
        
    }//GEN-LAST:event_viewSummaryPanelMouseExited

    private void viewChartPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewChartPanelMouseEntered
        // TODO add your handling code here:
        viewChartPanel.setBackground(new Color(102,102,102));
        
        disableSummaryPanels();
    }//GEN-LAST:event_viewChartPanelMouseEntered

    private void viewChartPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewChartPanelMouseExited
        // TODO add your handling code here:
        viewChartPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_viewChartPanelMouseExited

    private void manageExpensePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageExpensePanelMouseClicked
        // TODO add your handling code here:
        dispose();
        new Home(signInEmail).setVisible(false);      
        new AddExpense(signInEmail).setVisible(true);
    }//GEN-LAST:event_manageExpensePanelMouseClicked

    private void manageBudgetPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageBudgetPanelMouseClicked
        // TODO add your handling code here:
        dispose();
        new Home(signInEmail).setVisible(false);      
        new AddBudget(signInEmail).setVisible(true);
    }//GEN-LAST:event_manageBudgetPanelMouseClicked

    private void viewChartPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewChartPanelMouseClicked
        // TODO add your handling code here:
        dispose();
        new Home(signInEmail).setVisible(false);      
        new ViewCharts(signInEmail).setVisible(true);
    }//GEN-LAST:event_viewChartPanelMouseClicked

    private void profilePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseEntered
        // TODO add your handling code here:
        //[0,102,0]
        profilePanel.setBackground(new Color(0,204,51));
        
        disableSummaryPanels();
    }//GEN-LAST:event_profilePanelMouseEntered

    private void profilePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseExited
        // TODO add your handling code here:
        profilePanel.setBackground(new Color(0,204,102));
    }//GEN-LAST:event_profilePanelMouseExited

    private void profilePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePanelMouseClicked
        // TODO add your handling code here:
        dispose();
        new Home(signInEmail).setVisible(false);      
        new UpdateProfile(signInEmail).setVisible(true);
    }//GEN-LAST:event_profilePanelMouseClicked

    private void logoutPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseEntered
        // TODO add your handling code here:
        logoutPanel.setBackground(new Color(0,51,204));
        
        disableSummaryPanels();
    }//GEN-LAST:event_logoutPanelMouseEntered

    private void logoutPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseExited
        // TODO add your handling code here:
        logoutPanel.setBackground(new Color(75,137,243));
    }//GEN-LAST:event_logoutPanelMouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(new ExpenseManager(),"Sure? You want to exit?", "Expense Manager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_OPTION)
        System.exit(0);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void logoutPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutPanelMouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(new ExpenseManager(),"Sure? You want to Logout ?", "Expense Manager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(result == JOptionPane.YES_OPTION)
        {
            dispose();
            setVisible(false);
            new ExpenseManager().setVisible(true);
        }
        
    }//GEN-LAST:event_logoutPanelMouseClicked

    private void incomeSummaryTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_incomeSummaryTableMouseEntered
        // TODO add your handling code here:
        
        disableSummaryPanels();
    }//GEN-LAST:event_incomeSummaryTableMouseEntered

    private void expenseSummaryTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expenseSummaryTableMouseEntered
        // TODO add your handling code here:
        
        disableSummaryPanels();
    }//GEN-LAST:event_expenseSummaryTableMouseEntered

    private void homeIncomePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeIncomePanelMouseEntered
        // TODO add your handling code here:
        
        disableSummaryPanels();
    }//GEN-LAST:event_homeIncomePanelMouseEntered

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        // TODO add your handling code here:
        
        disableSummaryPanels();
    }//GEN-LAST:event_jPanel5MouseEntered

    private void byDatePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byDatePanelMouseClicked
        // TODO add your handling code here:
        
        dispose();
        setVisible(false);
        new ViewSummaryByDate(signInEmail).setVisible(true);
    }//GEN-LAST:event_byDatePanelMouseClicked

    private void byCategoryPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byCategoryPanelMouseClicked
        // TODO add your handling code here:
        
        dispose();
        setVisible(false);
        new ViewIncomeSummary(signInEmail).setVisible(true);
    }//GEN-LAST:event_byCategoryPanelMouseClicked

    private void byAmountPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byAmountPanelMouseClicked
        // TODO add your handling code here:
        
        dispose();
        setVisible(false);
        new ViewExpenseSummary(signInEmail).setVisible(true);
    }//GEN-LAST:event_byAmountPanelMouseClicked

    private void viewReminderPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewReminderPanelMouseClicked
        // TODO add your handling code here:
        
        int reminder_id;
        
        
        Date todaysDate = new Date();
        String modifiedTodaysDate= new SimpleDateFormat("yyyy-MM-dd").format(todaysDate);
        
        //JOptionPane.showMessageDialog(null, modifiedTodaysDate);
       
        
        // REMINDER 
        try
        {
            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs;
            
            // INCOME TABLE
            //System.out.println("START : "+summary_start_date);
            rs = st.executeQuery("select * from Reminder where reminder_date = '"+modifiedTodaysDate+"' and user_email = '"+signInEmail+"'");
            if(!rs.next())
            {
                throw new NoReminderException();
            }
        }
        catch(NoReminderException e)
        {
            JOptionPane.showMessageDialog(null, "No Reminders today");
            return;
        }
        
        catch(Exception e)
        {
            System.out.println("HERE");
            JOptionPane.showMessageDialog(null, e);
            return;
        }
        
        setVisible(false);
        new ViewReminder(signInEmail).setVisible(true);
    }//GEN-LAST:event_viewReminderPanelMouseClicked

    private void byCategoryPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byCategoryPanelMouseEntered
        // TODO add your handling code here:
       
        byCategoryPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_byCategoryPanelMouseEntered

    private void byDatePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byDatePanelMouseEntered
        // TODO add your handling code here:
        byDatePanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_byDatePanelMouseEntered

    private void byAmountPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byAmountPanelMouseEntered
        // TODO add your handling code here:
        byAmountPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_byAmountPanelMouseEntered

    private void byCategoryPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byCategoryPanelMouseExited
        // TODO add your handling code here:     
        byCategoryPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_byCategoryPanelMouseExited

    private void byDatePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byDatePanelMouseExited
        // TODO add your handling code here:
        byDatePanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_byDatePanelMouseExited

    private void byAmountPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_byAmountPanelMouseExited
        // TODO add your handling code here:
        byAmountPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_byAmountPanelMouseExited

    private void setReminderPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setReminderPanelMouseClicked
        // TODO add your handling code here:
        
        setVisible(false);
        new AddReminder(signInEmail).setVisible(true);
    }//GEN-LAST:event_setReminderPanelMouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JPanel byAmountPanel;
    private javax.swing.JPanel byCategoryPanel;
    private javax.swing.JPanel byDatePanel;
    private rojerusan.RSTableMetro expenseSummaryTable;
    private javax.swing.JLabel homeExpenseAmount;
    private javax.swing.JLabel homeExpenseAmountHeading;
    private javax.swing.JLabel homeExpenseAmountRs;
    private javax.swing.JLabel homeExpenseCategory;
    private javax.swing.JLabel homeExpenseCategoryHeading;
    private javax.swing.JLabel homeExpenseDate;
    private javax.swing.JLabel homeExpenseDateHeading;
    private javax.swing.JLabel homeExpensePayee;
    private javax.swing.JLabel homeExpensePayeeHeading;
    private javax.swing.JLabel homeIncomeAmount;
    private javax.swing.JLabel homeIncomeAmountHeading;
    private javax.swing.JLabel homeIncomeAmountRs;
    private javax.swing.JLabel homeIncomeCategory;
    private javax.swing.JLabel homeIncomeCategoryHeading;
    private javax.swing.JLabel homeIncomeDate;
    private javax.swing.JLabel homeIncomeDateHeading;
    private javax.swing.JPanel homeIncomePanel;
    private javax.swing.JLabel homeIncomePayer;
    private javax.swing.JLabel homeIncomePayerHeading;
    private rojerusan.RSTableMetro incomeSummaryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel logoutPanel;
    private javax.swing.JPanel manageBudgetPanel;
    private javax.swing.JPanel manageExpensePanel;
    private javax.swing.JPanel manageIncomePanel;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JPanel setReminderPanel;
    private javax.swing.JPanel testPanel3;
    private javax.swing.JPanel testPanel4;
    private javax.swing.JPanel testPanel5;
    private javax.swing.JPanel testPanel7;
    private javax.swing.JPanel testPanel8;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel userNameWelcomeLabel11;
    private javax.swing.JLabel userNameWelcomeLabel12;
    private javax.swing.JLabel userNameWelcomeLabel13;
    private javax.swing.JLabel userNameWelcomeLabel22;
    private javax.swing.JLabel userNameWelcomeLabel24;
    private javax.swing.JLabel userNameWelcomeLabel25;
    private javax.swing.JLabel userNameWelcomeLabel26;
    private javax.swing.JLabel userNameWelcomeLabel27;
    private javax.swing.JLabel userNameWelcomeLabel28;
    private javax.swing.JLabel userNameWelcomeLabel30;
    private javax.swing.JLabel userNameWelcomeLabel32;
    private javax.swing.JLabel userNameWelcomeLabel33;
    private javax.swing.JLabel userNameWelcomeLabel34;
    private javax.swing.JLabel userNameWelcomeLabel35;
    private javax.swing.JLabel userNameWelcomeLabel36;
    private javax.swing.JLabel userNameWelcomeLabel38;
    private javax.swing.JLabel userNameWelcomeLabel39;
    private javax.swing.JLabel userNameWelcomeLabel40;
    private javax.swing.JLabel userNameWelcomeLabel41;
    private javax.swing.JPanel viewChartPanel;
    private javax.swing.JPanel viewReminderPanel;
    private javax.swing.JPanel viewSummaryPanel;
    // End of variables declaration//GEN-END:variables

    static class NoReminderException extends Exception{}
}
