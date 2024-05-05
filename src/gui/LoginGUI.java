package gui;

import api.GeneralUser;
import api.Provider;
import api.RegularUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Κλάση για τη γραφική διεπαφή σύνδεσης ενός χρήστη στην εφαρμογή
 */
public class LoginGUI extends JFrame {
    private JButton logInButton;
    private JTextField tfUsername;
    private JButton createAccountButton;
    private JPasswordField passwordField1;
    private JPanel loginPane;

    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο με τη φόρμα σύνδεσης το χρήστη στην εφαρμογή
     */
    public LoginGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(450,300);
        setTitle("Login");
        setContentPane(loginPane);
        setLocationRelativeTo(null);
        setVisible(true);


        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GeneralUser.checkLogin(tfUsername.getText(), new String(passwordField1.getPassword()))==false){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Try logging in again or create a new account");
                }
                else{

                    GeneralUser g =GeneralUser.getUserByUsername(tfUsername.getText());
                    if(g.getUserType().equals("Provider")){

                        ProviderGUI pGUI = new ProviderGUI(new Provider(g));
                        pGUI.show();
                        dispose();
                    }
                    else if (g.getUserType().equals("RegularUser")){

                        RegularUserGUI rGUI = new RegularUserGUI(new RegularUser(g));
                        rGUI.show();
                        dispose();
                    }

                }
            }
        });


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountGUI c = new CreateAccountGUI();
                c.show();
                dispose();
            }
        });
    }
}
