/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import javax.swing.*;
import java.sql.*;
import ProjectDatabaseConnection.ConnectionProvider;
import java.awt.Color;
import java.text.SimpleDateFormat;
//import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;

import java.awt.Container;
import java.awt.event.WindowEvent;
import static java.lang.Character.isDigit;
import java.util.Arrays;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author shaun
 */
public class ExpenseManager extends javax.swing.JFrame {

    /**
     * Creates new form ExpenseManager
     */
    public ExpenseManager() 
    {
        initComponents();
        
        //BasicInternalFrameUI ui = (BasicInternalFrameUI)signUpJFrame.getUI();
        //Container north = (Container)ui.getNorthPane();
        //north.remove(0);
        //north.validate();
        //north.repaint();
        
        
        
       

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *///dfg
    //@SuppressWarnings("kl;lk;ghjhjkhjkunchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        signInPasswordCheckBox = new javax.swing.JCheckBox();
        signInEmailTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        signInPasswordField = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        signUpButton = new necesario.RSMaterialButtonCircle();
        signInButton = new necesario.RSMaterialButtonCircle();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setMaximumSize(new java.awt.Dimension(1360, 750));
        setMinimumSize(new java.awt.Dimension(1360, 750));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        signInPasswordCheckBox.setBackground(new java.awt.Color(51, 51, 51));
        signInPasswordCheckBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        signInPasswordCheckBox.setMargin(new java.awt.Insets(5, 5, 5, 5));
        signInPasswordCheckBox.setMaximumSize(new java.awt.Dimension(40, 40));
        signInPasswordCheckBox.setMinimumSize(new java.awt.Dimension(40, 40));
        signInPasswordCheckBox.setPreferredSize(new java.awt.Dimension(40, 40));
        signInPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInPasswordCheckBoxActionPerformed(evt);
            }
        });
        jPanel2.add(signInPasswordCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 30, 30));

        signInEmailTextField.setBackground(new java.awt.Color(51, 51, 51));
        signInEmailTextField.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        signInEmailTextField.setForeground(new java.awt.Color(255, 255, 255));
        signInEmailTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel2.add(signInEmailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 280, 30));

        jLabel10.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_Secure_50px.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 50, 50));

        jLabel9.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("PASSWORD");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 160, 40));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Login To Your Account");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, 30));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("_");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 50, 50));

        signInPasswordField.setBackground(new java.awt.Color(51, 51, 51));
        signInPasswordField.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        signInPasswordField.setForeground(new java.awt.Color(255, 255, 255));
        signInPasswordField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jPanel2.add(signInPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 280, 30));

        jLabel11.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("EMAIL");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 150, 40));

        jLabel12.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/icons8_Account_50px.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 50, 50));

        signUpButton.setBackground(new java.awt.Color(255, 51, 51));
        signUpButton.setText("SIGN UP");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });
        jPanel2.add(signUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, 360, 70));

        signInButton.setBackground(new java.awt.Color(81, 81, 253));
        signInButton.setText("LOG IN");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        jPanel2.add(signInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 350, 70));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Welcome !");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, 30));

        jLabel6.setBackground(new java.awt.Color(255, 0, 51));
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 40, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 480, 750));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Small", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Expense Manager");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 370, 40));

        jLabel3.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Welcome To");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, 30));

        jLabel8.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frames/Time-Expense-Tracking.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 710, 620));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 880, 10));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 10, 730));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 199));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 10));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 100));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 20, 730));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 750));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void signInPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInPasswordCheckBoxActionPerformed
        // TODO add your handling code here:
        
        char[] pass = signInPasswordField.getPassword();
        
        if(signInPasswordCheckBox.isSelected())
            signInPasswordField.setEchoChar((char)0);
        else
            signInPasswordField.setEchoChar('*');
          
    }//GEN-LAST:event_signInPasswordCheckBoxActionPerformed

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        // TODO add your handling code here:

        String signInEmail = signInEmailTextField.getText();
        String signInPassword = String.valueOf(signInPasswordField.getPassword());

        try
        {
            //Check if all fields are filled
            if(signInEmail.equals("") || signInPassword.equals(""))
            {
                throw new NullFieldException();
            }

            Connection con = ConnectionProvider.getCon();
            Statement st;
            st = con.createStatement();
            ResultSet rs;

            int emailFlag = 0;

            //Check for duplicate email
            rs = st.executeQuery("select user_email from User");
            while(rs.next())
            {
                if(rs.getString(1).equals(signInEmail))
                {
                    emailFlag = 1;
                    break;
                }
            }

            if(emailFlag == 0)
            {
                throw new EmailException();
            }

            //Check is password is correct or not
            rs = st.executeQuery("select user_password from User where user_email=('"+signInEmail+"')");
            while(rs.next())
            {
                if(!rs.getString(1).equals(signInPassword))
                {
                    throw new PasswordException();
                }
            }

            //ExpenseManager em = new ExpenseManager();
            //new ExpenseManager().repaint();
            setVisible(false);
            new Home(signInEmail).setVisible(true);
            JOptionPane.showMessageDialog(null, "You have signed in successfully");

        }

        //Fields are null
        catch(NullFieldException e)
        {
            //new NullFieldException();
            JOptionPane.showMessageDialog(null,"Please fill all the details");
        }

        //Contact number is not right
        catch(PasswordException e)
        {
            JOptionPane.showMessageDialog(null,"Please enter correct passowrd");
        }

        //Duplicate username
        catch (EmailException e)
        {
            JOptionPane.showMessageDialog(null, "Please enter correct email");
        }

        //Stop Process and DO NOTHING
        //catch(StopProcessException e){}

        //Any other Exceotion
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            System.out.println(e);
        }
    }//GEN-LAST:event_signInButtonActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        // TODO add your handling code here:
        
        dispose();
        new ExpenseManager().setVisible(false);
        new SignUp().setVisible(true);
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(new ExpenseManager(),"Sure? You want to exit?", "Expense Manager", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(result == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1ComponentShown

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
            java.util.logging.Logger.getLogger(ExpenseManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExpenseManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExpenseManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExpenseManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExpenseManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private necesario.RSMaterialButtonCircle signInButton;
    private javax.swing.JTextField signInEmailTextField;
    private javax.swing.JCheckBox signInPasswordCheckBox;
    private javax.swing.JPasswordField signInPasswordField;
    private necesario.RSMaterialButtonCircle signUpButton;
    // End of variables declaration//GEN-END:variables
    
    static class StopProcessException extends Exception{}
    static class NullFieldException extends Exception{}
    static class PasswordException extends Exception{}
    static class EmailException extends Exception{}

}
