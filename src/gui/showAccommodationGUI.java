package gui;

import api.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Κλάση για τη γραφική διεπαφή προβολής ενός καταλύματος
 */
public class showAccommodationGUI extends JFrame{
    private JPanel showAccom;
    private JLabel description;
    private JLabel adress;
    private JLabel name;
    private JLabel type;
    private JPanel listings;
    private JScrollPane showReviews;
    private JLabel numOfReviews;
    private JLabel avgRatingOfReviews;
    private JTextArea amenityList;
    private JButton DeleteButton;
    private JButton editButton;
    private JButton addReviewButton;
    private JButton backButton;
    private JLabel noReviews;
    private JLabel amenityListLabel;

    /**
     * Κατασκευαστής που ανοίγει ένα παράθυρο για την προβολή ενός καταλύματος
     * @param a το κατάλυμα το οποίο προβάλλεται
     * @param g ο χρήστης ο οποίος ανοίγει το κατάλυμα για προβολή
     */
    public showAccommodationGUI(Accommodation a,GeneralUser g) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setSize(950,700);
    setTitle("Show Accommodation");
    setLocationRelativeTo(null);
    setContentPane(showAccom);



    if(g.getUserType().equals("Provider")){
        addReviewButton.setVisible(false);

    }
    else if(g.getUserType().equals("RegularUser")){
        DeleteButton.setVisible(false);
        editButton.setVisible(false);
    }
    name.setText("Όνομα: "+a.getName());
    type.setText("Τύπος: "+a.getType());
    adress.setText("Τοποθεσία: "+a.getCity()+" "+a.getAddress()+" "+String.valueOf(a.getPostalCode()));
    description.setText(a.getDescription());
    numOfReviews.setText("Αριθμός Αξιολογήσεων: "+ String.valueOf(a.getNumberOfReviews()));
    avgRatingOfReviews.setText("Μέσος όρος Αξιολογήσεων: "+ String.valueOf(a.getAverageRating()));
    amenityList.setText(""+a.getListOfAmenities());
    amenityList.setEditable(false);
    amenityList.setOpaque(false);

    if(a.getListOfReviews().size()==0){
        noReviews.setVisible(true);
    }
    else {
        noReviews.setVisible(false);
        listings.setLayout(new GridLayout(0, 1));
        int index = 0;
        for (Review r : a.getListOfReviews()) {
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

            JLabel reviewStars=new JLabel("Αστέρια:"+ String.valueOf(r.getReviewStars()));
            JLabel reviewDate = new JLabel(r.getCurrentDate());
            JLabel reviewAuthor = new JLabel("Συγγραφέας: "+r.getAuthor().getName());
            JTextArea reviewTextArea = new JTextArea();
            reviewTextArea.setEditable(false);
            reviewTextArea.setLineWrap(true);
            reviewTextArea.setSize(100,0);
            reviewTextArea.setText(r.getReviewText());

            reviewTextArea.setOpaque(false);
            listing_i.add(reviewStars);
            listing_i.add(new JLabel());
            listing_i.add(reviewDate);
            listing_i.add(reviewAuthor);
            listing_i.add(reviewTextArea);
            listing_i.setSize(950,200);
            listing_i.setVisible(true);
            listings.add(listing_i);
        }
    }
    setContentPane(showAccom);

    DeleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                a.delete();
                ProviderGUI l = new ProviderGUI((Provider) g);
                l.show();
                dispose();


        }

    });
    editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addAccommodationGUI l = new addAccommodationGUI(a,(Provider) g);
            l.show();
            dispose();
        }
    });
    addReviewButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            addReviewGUI l = new addReviewGUI((RegularUser)g, a);
            l.show();
            dispose();
        }
    });
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(g.getUserType().equals("Provider")){
                ProviderGUI l = new ProviderGUI((Provider)g);
                l.show();
                dispose();

            }
            else if(g.getUserType().equals("RegularUser")){
                RegularUserGUI l = new RegularUserGUI((RegularUser)g);
                l.show();
                dispose();
            }

        }
    });

    }
}
