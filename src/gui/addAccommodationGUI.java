package gui;

import api.Accommodation;
import api.Provider;
import api.Review;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Κλάση γραφικής διεπαφής για την καταχώρηση ή την επεξεργασία καταλύματος.
 * Τη συγκεκριμένη λειτουργία μπορεί να έχει πρόσβαση μόνο ένας πάροχος.
 */
public class addAccommodationGUI extends JFrame{
    private JTextField textFieldName;
    private JTextField textFieldDescription;
    private JRadioButton hotelButton;
    private JRadioButton appartmentButton;
    private JRadioButton duplexButton;
    private JTextField textFieldAdress;
    private JTextField textFieldCity;
    private JTextField textFieldPostalCode;
    private JRadioButton port;
    private JRadioButton mountain;
    private JRadioButton road;
    private JRadioButton beach;
    private JRadioButton sea;
    private JRadioButton pool;
    private JRadioButton clothesWasher;
    private JButton addAccommodationButton;
    private JPanel addAccomm;
    private JRadioButton hairDryer;
    private JRadioButton dryer;
    private JRadioButton tv;
    private JRadioButton fireplace;
    private JRadioButton acButton;
    private JRadioButton centralHeating;
    private JRadioButton wifi;
    private JRadioButton ethernet;
    private JRadioButton oven;
    private JRadioButton fridge;
    private JRadioButton microwave;
    private JRadioButton cookware;
    private JRadioButton platesNCutlery;
    private JRadioButton dishWasher;
    private JRadioButton coffeeMachine;
    private JRadioButton balcony;
    private JRadioButton yard;
    private JRadioButton freeParking;
    private JRadioButton freeGarageParking;
    private JScrollPane scrollBar;

    /**
     * Κατασκευαστής της κλάσης γραφικής διεπαφής για την καταχώρηση καταλύματος που δέχεται ως παράμετρο
     * τον πάροχο ο οποίος κάνει την καταχώρηση.
     * @param pro ο πάροχος ο οποίος κάνει την καταχώρηση καταλύματος
     */
    public addAccommodationGUI(Provider pro) {

        setContentPane(addAccomm);
        JScrollPane jsp = new JScrollPane(addAccomm);
        setContentPane(jsp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(700,500);
        setTitle("Add accommodation");
        setLocationRelativeTo(null);

        addAccommodationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().length()>0 && textFieldAdress.getText().length()>0 &&
                        textFieldDescription.getText().length()>0 && textFieldCity.getText().length()>0 &&
                        textFieldPostalCode.getText().length()>0&& (appartmentButton.isSelected()||hotelButton.isSelected()||
                        duplexButton.isSelected())){
                    Accommodation newAccommodation = new Accommodation(pro.getUsername(),textFieldName.getText(),getTypeFromForm(),
                            textFieldAdress.getText(),textFieldCity.getText(),textFieldPostalCode.getText(),
                            textFieldDescription.getText(),getListOfAmenitiesFromForm());
                    ProviderGUI f = new ProviderGUI(pro);
                    f.show();
                    dispose();
            }
                else{
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Παρακαλώ συμπληρώστε όλα τα υποχρεωτικά πεδία.");
                }
            }

        });
    }

    /**
     * Κατασκευαστής για την γραφική διεπαφή για την επεξεργασία ενός καταλύματος
     * @param a το κατάλυμα προς επεξεργασία
     * @param pro ο πάροχος ο οποίος κάνει την επεξεργασία του καταλύματος του
     */
    public addAccommodationGUI(Accommodation a, Provider pro) {
        setContentPane(addAccomm);
        JScrollPane jsp = new JScrollPane(addAccomm);
        setContentPane(jsp);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(700,500);
        setTitle("Edit accommodation");
        setLocationRelativeTo(null);
        ArrayList<Review> reviews =a.getListOfReviews();
        textFieldName.setText(a.getName());
        textFieldAdress.setText(a.getAddress());
        textFieldCity.setText(a.getCity());
        textFieldDescription.setText(a.getDescription());
        textFieldPostalCode.setText(a.getPostalCode());
        addAccommodationButton.setText("Καταχώρηση επεξεργασίας καταλύματος");
        addAccommodationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldName.getText().length()>0 && textFieldAdress.getText().length()>0 &&
                    textFieldDescription.getText().length()>0 && textFieldCity.getText().length()>0 &&
                        textFieldPostalCode.getText().length()>0 && (appartmentButton.isSelected()||hotelButton.isSelected()||
                        duplexButton.isSelected())){
                    a.editAccommodation(textFieldName.getText(),getTypeFromForm(),
                            textFieldAdress.getText(),textFieldCity.getText(),textFieldPostalCode.getText(),
                            textFieldDescription.getText(),getListOfAmenitiesFromForm());
                    for(Review old : reviews){
                        old.setAccommodationReviewed(a);
                    }
                    ProviderGUI f = new ProviderGUI(pro);
                    f.show();
                    dispose();}
                else {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Παρακαλώ συμπληρώστε όλα τα υποχρεωτικά πεδία.");
                }
            }
        });
    }

    /**
     * Μέθοδος που επιστρέφει μια λίστα με αντικείμενα τύπου String, από τις παροχές που έχει επιλέξει ο πάροχος για
     * το κατάλυμα στη φόρμα.
     * @return λίστα με παροχές που έχουν επιλεχτεί.
     */
    private ArrayList<String> getListOfAmenitiesFromForm(){
        ArrayList<String> radioButtonAndIfItsSelected = new ArrayList<>();
        ArrayList<JRadioButton> radioList = new ArrayList<>();
        radioList.add(beach);
        radioList.add(pool);
        radioList.add(sea);
        radioList.add(mountain);
        radioList.add(port);
        radioList.add(road);

        radioList.add(hairDryer);

        radioList.add(clothesWasher);
        radioList.add(dryer);

        radioList.add(tv);

        radioList.add(fireplace);
        radioList.add(acButton);
        radioList.add(centralHeating);

        radioList.add(wifi);
        radioList.add(ethernet);

        radioList.add(oven);
        radioList.add(fridge);
        radioList.add(microwave);
        radioList.add(cookware);
        radioList.add(platesNCutlery);
        radioList.add(dishWasher);
        radioList.add(coffeeMachine);

        radioList.add(balcony);
        radioList.add(yard);

        radioList.add(freeParking);
        radioList.add(freeGarageParking);
        for(JRadioButton button : radioList){
            if(button.isSelected()){
                radioButtonAndIfItsSelected.add(button.getText());
            }
        }
        return radioButtonAndIfItsSelected;
    }

    /**
     * Μέθοδος επιστροφής του τύπου καταλύματος (Πχ ξενοδοχείο) που έχει επιλέξει ο πάροχος στη φόρμα.
     * @return τον τύπου του καταλύματος που έχει επιλεχτεί στη φόρμα
     */
    private String getTypeFromForm() {
        if (hotelButton.isSelected()) {
            return hotelButton.getText().toLowerCase();
        } else if (appartmentButton.isSelected()) {
            return appartmentButton.getText().toLowerCase();
        } else if (duplexButton.isSelected()) {
            return duplexButton.getText().toLowerCase();
        }

    return appartmentButton.getText().toLowerCase();
    }
}
