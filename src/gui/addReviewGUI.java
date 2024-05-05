package gui;

import api.Accommodation;
import api.RegularUser;
import api.Review;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kλάση για τη γραφική διεπαφή προσθήκης και επεξεργασίας αξιολόγησης
 */
public class addReviewGUI extends JFrame{
    private JTextField textField1;
    private JButton addReviewButton;
    private JTextField textField2;
    private JPanel addReview;


    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο για την επεξεργασία αξιολόγησης
     * @param r ο χρήστης ο οποίος επεξεργάζεται την αξιολόγηση
     * @param a το κατάλυμα το οποίο αξιολογείται
     * @param rev  η προηγούμενη αξιολόγηση
     */

    public addReviewGUI(RegularUser r, Accommodation a, Review rev){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(950,700);
        setTitle("Edit Review");
        setLocationRelativeTo(null);
        setContentPane(addReview);
        textField2.setText(rev.getReviewText());
        textField1.setText(String.valueOf(rev.getReviewStars()));

        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((textField1.getText().matches("[0-9]+") && textField1.getText().length() >=1)) {
                    if (!r.AddReview(a, textField2.getText(), Integer.parseInt(textField1.getText()))) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "H βαθμολογία πρέπει να είναι ακαριαίος απο το 1 μέχρι το 5 και το κείμενο αξιολόγησης εώς 500 χαρακτήρες. Ξαναπροσπάθησε.");
                    }
                    else {
                        RegularUserGUI f = new RegularUserGUI(r);
                        f.show();
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "H βαθμολογία πρέπει να είναι ακαριαίος απο το 1 μέχρι το 5 και το κείμενο αξιολόγησης εώς 500 χαρακτήρες. Ξαναπροσπάθησε.");
                }
            }

        });
    }


    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο για την προσθήκη νέας αξιολόγησης
     * @param r ο χρήστης ο οποίος κάνει την αξιολόγηση
     * @param a το κατάλυμα το οποίο αξιολογείται
     **/
    public addReviewGUI(RegularUser r, Accommodation a) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(950,700);
        setTitle("Add Review");
        setLocationRelativeTo(null);
        setContentPane(addReview);

        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((textField1.getText().matches("[0-9]+") && textField1.getText().length() >=1)) {
                    if (!r.AddReview(a, textField2.getText(), Integer.parseInt(textField1.getText()))) {
                        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "H βαθμολογία πρέπει να είναι ακαριαίος απο το 1 μέχρι το 5 και το κείμενο αξιολόγησης εώς 500 χαρακτήρες. Ξαναπροσπάθησε.");
                    }
                    else {
                        RegularUserGUI f = new RegularUserGUI(r);
                        f.show();
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "H βαθμολογία πρέπει να είναι ακαριαίος απο το 1 μέχρι το 5 και το κείμενο αξιολόγησης εώς 500 χαρακτήρες. Ξαναπροσπάθησε.");
                }
            }

        });
    }
}
