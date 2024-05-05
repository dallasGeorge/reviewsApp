package gui;

import api.GeneralUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Κλάση για τη γραφική διεπαφή δημιουργίας νέου λογαριασμού.
 */
public class CreateAccountGUI extends JFrame {
    private JTextField textFieldName;
    private JTextField textFieldSiname;
    private JPasswordField passwordField;
    private JTextField textFieldUsername;
    private JRadioButton πάροχοςRadioButton;
    private JRadioButton απλόςΧρήστηςRadioButton;
    private JButton createAccountButton;
    private JButton goBackToLogButton;
    private JPanel createAccount;

    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο με τη φόρμα για την εγγραφή ενός χρήστη και αν τα στοιχεία γίνονται
     * δεκτά προσθέτει τον χρήστη στο αρχείο με τη λίστα χρηστών.
     */
    public CreateAccountGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(450,300);
        setTitle("Create Account");
        setContentPane(createAccount);
        setLocationRelativeTo(null);
        setVisible(true);

        goBackToLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI l = new LoginGUI();
                l.show();
                dispose();
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().length()==0||textFieldSiname.getText().length()==0||textFieldUsername.getText().length()==0
                        ||(new String(passwordField.getPassword())).length()==0
                        ||(!πάροχοςRadioButton.isSelected() && !απλόςΧρήστηςRadioButton.isSelected())){
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Παρακαλώ συμπληρώστε όλα τα πεδία");
                }
                else{
                    if(πάροχοςRadioButton.isSelected()) {
                        if (GeneralUser.createAccount(textFieldName.getText(), textFieldSiname.getText(), textFieldUsername.getText(), new String(passwordField.getPassword()), "Provider")) {
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Created account, go back to log in now.");
                        } else {
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "User already exists with that username");
                        }
                    }
                    else{
                                if(GeneralUser.createAccount(textFieldName.getText(),textFieldSiname.getText(),textFieldUsername.getText(), new String(passwordField.getPassword()),"RegularUser")){
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Created account, go back to log in now.");
                                }
                                else{
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"User already exists with that username");
                                }
                    }
                }
            }
        });
    }
}
