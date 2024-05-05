package gui;

import api.Accommodation;
import api.RegularUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Κλάση για τη γραφική διεπαφή των λειτουργιών απλού χρήστη
 */
public class RegularUserGUI extends JFrame {

    private JPanel regUserShow;
    private JTextField textField1;
    private JRadioButton όνομαRadioButton;
    private JRadioButton τύποςRadioButton;
    private JRadioButton τοποθεσίαRadioButton;
    private JRadioButton παροχέςRadioButton;
    private JButton αναζήτησηButton;
    private JButton dasboardButton;
    private JScrollPane showResults;
    private JPanel listings;
    private JLabel noResults;
    private JButton button1;

    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο με τις λειτουργίες απλού χρήστη
     * @param g ο απλός χρήστης για τον οποίο εμφανίζεται το παράθυρο
     */
    public RegularUserGUI(RegularUser g) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(regUserShow);
    setResizable(false);
    setSize(950,700);
    setTitle("Regular User");
    setLocationRelativeTo(null);

    button1.setText("Log out from user: "+g.getUsername());
    noResults.setVisible(true);
    dasboardButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegularUserDashboard f = new RegularUserDashboard(g);
            f.show();
            dispose();
        }
    });
    αναζήτησηButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> criteria = new ArrayList<>();
            if (παροχέςRadioButton.isSelected()) {
                criteria.add(4);
            }
            if (όνομαRadioButton.isSelected()) {
                criteria.add(1);
            }
            if (τύποςRadioButton.isSelected()) {
                criteria.add(2);
            }
            if (τοποθεσίαRadioButton.isSelected()) {
                criteria.add(3);
            }

            if (criteria.size() == 0) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Επιλέξτε κριτήρια αναζήτησης");
            }
            else {

                ArrayList<Accommodation> results = g.searchAccommodations(criteria, textField1.getText());

                listings.removeAll();
                if (results.size() == 0) noResults.setVisible(true);
                else {
                    noResults.setVisible(false);
                    listings.setLayout(new GridLayout(0, 1));
                    int index = 0;
                    for (Accommodation a : results) {
                        index += 1;

                        GridLayout layout = new GridLayout(0, 3);
                        layout.setHgap(15);
                        layout.setVgap(20);

                        JPanel listing_i = new JPanel(layout);
                        listing_i.setBorder(new EmptyBorder(5, 2, 10, 5)); //padding
                        if (index % 2 == 0) {
                            listing_i.setBackground(Color.lightGray);
                        } else {
                            listing_i.setBackground(Color.white);
                        }

                        JLabel accomNamei = new JLabel();
                        JLabel accomAvgRatingi = new JLabel();
                        JLabel accomAdressi = new JLabel();
                        JLabel accomIconi = new JLabel(new ImageIcon("src/gui/icons/" + a.getType() + ".png"));
                        JButton accomViewi = new JButton("Προβολή Καταλύματος");
                        JLabel accomType = new JLabel();
                        accomNamei.setText(a.getName());

                        accomAvgRatingi.setText("Μέση αξιολόγηση: " + String.valueOf(a.getAverageRating()));
                        String labelText = String.format("<html><div WIDTH=%d>%s</div></html>", 50, a.getAddress()+" " + a.getCity() + " " + a.getPostalCode());
                        accomAdressi.setText(labelText);

                        accomType.setText("Τύπος καταλύματος: " + a.getType());
                        listing_i.add(accomIconi);
                        listing_i.add(accomNamei);
                        listing_i.add(new JLabel());
                        listing_i.add(accomAvgRatingi);
                        listing_i.add(accomType);
                        listing_i.add(accomAdressi);
                        listing_i.add(accomViewi);

                        listing_i.setSize(950, 200);
                        listing_i.setVisible(true);
                        listings.add(listing_i);
                        accomViewi.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                showAccommodationGUI f = new showAccommodationGUI(a, g);
                                f.show();
                                dispose();
                            }
                        });


                    }

                }
            }
        }
    });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI f = new LoginGUI();
                f.show();
                dispose();
            }
        });
    }
}
