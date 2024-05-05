package gui;

import api.Accommodation;
import api.Provider;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Κλάση για τη γραφική διεπαφή των λειτουργιών ενός παρόχου.
 */
public class ProviderGUI extends JFrame{
    private JButton addAccommodation;
    private JPanel providerFrame;
    private JLabel avgReviewsValue;
    private JLabel totalReviews;
    private JLabel noListings;

    private JPanel listings;
    private JButton button1;
    private JLabel totNumOfRev;

    /**
     * Κατασκευαστής ο οποίος εμφανίζει ένα παράθυρο το οποίο πληρεί και τις βασικές λειτουργίες αλλά και το dashboard
     * του παρόχου
     * @param p ο πάροχος για τον οποίο θα ανοίξει το αντίστοιχο παράθυρο
     */
    public ProviderGUI(Provider p) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setSize(950,700);
    setTitle("Provider");
    setLocationRelativeTo(null);

    button1.setText("Log out from user: "+p.getUsername());
    avgReviewsValue.setText(String.valueOf(p.averageRating()));
    totalReviews.setText(String.valueOf(p.numberOfTotalReviews()));
    if(p.getListOfAccommodations().size()!=0){
        noListings.setVisible(false);
    }
    listings.setLayout(new GridLayout(0,1));
    int index=0;
    for(Accommodation a : p.getListOfAccommodations()){
        index+=1;

        GridLayout layout = new GridLayout(0,3);
        layout.setHgap(15);
        layout.setVgap(20);

        JPanel listing_i=new JPanel(layout);
        listing_i.setBorder(new EmptyBorder(5, 2, 10, 5)); //padding
        if(index%2==0){
            listing_i.setBackground(Color.lightGray);
        }
        else{
            listing_i.setBackground(Color.white);
        }

        JLabel accomNamei=new JLabel();
        JLabel accomAvgRatingi=new JLabel();
        JLabel accomAdressi=new JLabel();
        JLabel accomIconi = new JLabel(new ImageIcon("src/gui/icons/"+a.getType()+".png"));
        JButton accomViewi=new JButton("Προβολή Καταλύματος");
        JButton accomDeletei=new JButton("Διαγραφή Καταλύματος");
        JButton accomEditi=new JButton("Επεξεργασία Καταλύματος");
        JLabel accomType = new JLabel();
        accomNamei.setText(a.getName());

        accomAvgRatingi.setText("Μέση αξιολόγηση: "+String.valueOf(a.getAverageRating()));
        String labelText = String.format("<html><div WIDTH=%d>%s</div></html>", 50, a.getAddress()+" " + a.getCity() + " " + a.getPostalCode());

        accomAdressi.setText(labelText);

        accomType.setText("Τύπος καταλύματος: "+a.getType());
        listing_i.add(accomIconi);
        listing_i.add(accomNamei);
        listing_i.add(new JLabel());
        listing_i.add(accomAvgRatingi);
        listing_i.add(accomType);
        listing_i.add(accomAdressi);
        listing_i.add(accomViewi);
        listing_i.add(accomDeletei);
        listing_i.add(accomEditi);
        listing_i.setSize(950,200);
        listing_i.setVisible(true);
        accomViewi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccommodationGUI f = new showAccommodationGUI(a,p);
                f.show();
                dispose();
            }
        } );
        accomDeletei.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                a.delete();
                ProviderGUI f = new ProviderGUI(p);
                f.show();
                dispose();
            }
        } );
        accomEditi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAccommodationGUI f = new addAccommodationGUI(a,p);
                f.show();
                dispose();
            }
        } );
        listings.add(listing_i);
    }


    setContentPane(providerFrame);

    setVisible(true);
    addAccommodation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addAccommodationGUI f = new addAccommodationGUI(p);
            f.show();
            dispose();
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
