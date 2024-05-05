package gui;

import api.Accommodation;
import api.RegularUser;
import api.Review;
import jdk.jfr.consumer.RecordedThreadGroup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Κλάση για την γραφική διεπαφή Dashboard του απλού χρήστη
 */
public class RegularUserDashboard extends JFrame {
    private JLabel avgStarsGiven;
    private JScrollPane showReviews;
    private JPanel listings;
    private JButton πίσωΣτηνΑρχικήΣελίδαButton;
    private JPanel showRegUser;

    /**
     * κατασκευαστής ο οποίος εμφανίζει το dashboard του απλού χρήστη r
     * @param r ο απλός χρήστης για τον οποίο εμφανίζεται το παράθυρο
     */
    public RegularUserDashboard(RegularUser r) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(showRegUser);
    setResizable(false);
    setSize(950,700);
    setTitle("Dashboard");
    setLocationRelativeTo(null);
    avgStarsGiven.setText("Μέσος όρος βαθμολογίας αξιολογήσεων: "+String.valueOf(r.getAverageRating()));

    listings.setLayout(new GridLayout(0,1));
    int index=0;
    for(Review rev: r.getListOfReviews()) {

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
        JLabel AccomNamei=new JLabel(rev.getAccommodationReviewed().getName());
        JLabel accomAdressi= new JLabel("Τοποθεσία: "+rev.getAccommodationReviewed().getAddress()+" " +
                rev.getAccommodationReviewed().getCity() + " " + rev.getAccommodationReviewed().getPostalCode());
        JLabel accomTypei = new JLabel("Τύπος καταλύματος: "+rev.getAccommodationReviewed().getType());
        JLabel accomIconi = new JLabel(new ImageIcon("src/gui/icons/"+rev.getAccommodationReviewed().getType()+".png"));

        JLabel accomReviewStars = new JLabel("Βαθμός αξιολόγησης: "+String.valueOf(rev.getReviewStars()));
        JButton showReviewi = new JButton("Προβολή καταχώρησης");
        JButton editReviewi = new JButton("Επεξεργασία αξιολόγησης");
        JButton deleteReviewi = new JButton("Διαγραφή αξιολόγησης");

        listing_i.add(accomIconi);
        listing_i.add(AccomNamei);
        listing_i.add(accomTypei);
        listing_i.add(accomAdressi);
        listing_i.add(accomReviewStars);
        listing_i.add(new JLabel());
        listing_i.add(showReviewi);
        listing_i.add(editReviewi);
        listing_i.add(deleteReviewi);
        listing_i.setSize(950,200);
        listing_i.setVisible(true);
        listings.add(listing_i);
        showReviewi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccommodationGUI f = new showAccommodationGUI(rev.getAccommodationReviewed(), r);
                f.show();
                dispose();
            }
        });
        editReviewi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addReviewGUI f = new addReviewGUI(r,rev.getAccommodationReviewed(), rev);
                rev.delete();
                f.show();
                dispose();
            }
        });
        deleteReviewi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rev.delete();
                RegularUserGUI f = new RegularUserGUI(r);
                f.show();
                dispose();
            }
        });


    }

        πίσωΣτηνΑρχικήΣελίδαButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RegularUserGUI f = new RegularUserGUI(r);
            f.show();
            dispose();
        }
    });
}
}
