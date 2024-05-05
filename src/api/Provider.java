package api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Αυτή η κλάση αναπαριστά έναν πάροχο και κληρονομεί όλα τα χαρακτηριστικά του γενικού χρήστη.
 * @author Χρήστος Θεοφυλακτίδης
 */
public class Provider extends GeneralUser implements Serializable {


    /**
     * Κατασκευαστής όπου δέχεται ως ορίσματα το όνομα, το επίθετο, το Username και τον κωδικό του παρόχου και αρχικοποιεί
     * μια λίστα με τα καταλύματα που ανήκουν στον πάροχο.
     * @param name Το όνομα του παρόχου
     * @param surname Το επίθετο του παρόχου
     * @param username Το username του παρόχου
     * @param password Ο κωδικός του παρόχου
     */
    public Provider(String name, String surname, String username, String password){
        super(name,surname,username,password,"Provider");

    }

    /**
     * Κατασκευαστής όπου δημιουργεί έναν πάροχο και παίρνει ως παράμετρο την κλάση γονέα του, GeneralUser
     * @param g το αντικείμενο GeneralUser το οποίο μετατρέπεται σε πάροχο.
     */
    public  Provider(GeneralUser g){
        super(g.getName(),g.getSurname(),g.getUsername(),g.getPassword(),g.getUserType());
    }
    /**
     * Αυτή η μέθοδος δέχεται ως όρισμα ένα κατάλυμα και το προσθέτει στη λίστα με τα καταλύματα.
     * @param a Ένα κατάλυμα
     */
    public void addAccommodation(Accommodation a){
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>)FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        accommodationsDB.add(a);
        FileInteractions.saveToBinaryFile("accommodations.bin",accommodationsDB);
    }

    /**
     * Αυτή η μέθοδος τροποποιεί ένα ήδη υπάρχον κατάλυμα του πάροχου.
     * @param a Το κατάλυμα το οποίο τροποποιείται
     * @param name το όνομα του καταλύματος
     * @param type το είδος του καταλύματος
     * @param address η διεύθυνση του καταλύματος
     * @param city η πόλη στην οποία βρίσκεται το κατάλυμα
     * @param postalCode ο ταχυδρομικός κώδικας του καταλύματος
     * @param description Η περιγραφή του καταλύματος
     * @param listOfAmenities Η λίστα με τα είδη παροχών τα οποία προσφέρει το κατάλυμα
     */
    public void editAccommodation(Accommodation a,String name, String type, String address, String city, String postalCode, String description, ArrayList<String> listOfAmenities){
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        removeAccommodation(a);
        a.editAccommodation(name,type,address,city,postalCode,description,listOfAmenities);
        accommodationsDB.add(a);
        FileInteractions.saveToBinaryFile("accommodations.bin",accommodationsDB);
    }

    /**
     * Μέθοδος που αφαιρεί ενα κατάλυμα απο τη λίστα των καταλυμάτων.
     * @param a Το κατάλυμα το οποίο αφαιρείται
     */
    public void removeAccommodation(Accommodation a){
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        for(Accommodation accom : accommodationsDB){
            for (Review r : accom.getListOfReviews()){
                r.delete();
            }
        }
        accommodationsDB.remove(a);

    }

    /**
     * Μέθοδος επιστροφής του αριθμού καταλυμάτων που ανήκουν στον πάροχο.
     * @return αριθμού καταλυμάτων του παρόχου
     */
    public int getNumberOfAccommodations(){
        return getListOfAccommodations().size();
    }

    /**
     * Μέθοδος επιστροφής του συνόλου αξιολογήσεων όλων των καταλυμάτων του παρόχου.
     * @return του σύνολο αξιολογήσεων όλων των καταλυμάτων του παρόχου
     */
    public int numberOfTotalReviews(){
        int sum = 0;
        for(Accommodation a:getListOfAccommodations()){
            sum+=a.getNumberOfReviews();
        }
        return sum;
    }

    /**
     * Μέθοδος επιστροφής όλων των καταχωρήσεων καταλυμάτων του παρόχου.
     * @return λίστα με τα καταλύματα του παρόχου
     */
    public ArrayList<Accommodation> getListOfAccommodations() {
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        ArrayList<Accommodation> list = new ArrayList<>();
        for (Accommodation a : accommodationsDB){
            if (a.getOwner().equals(getUsername())){
                list.add(a);
            }
        }
        return list;
    }

    /**
     * Η μέση βαθμολογία απο όλα τα καταλύματα του παρόχου.
     * @return τη μέση βαθμολογία απο όλα τα καταλύματα του παρόχου.
     */
    public float averageRating(){
        float sum = 0;
        if(getNumberOfAccommodations()==0){
            return 0;
        }
        int count =0;
        for(Accommodation a:getListOfAccommodations()){

            if(a.getAverageRating()!=0){
                sum+=a.getAverageRating();
                count+=1;
            }
        }
        if(count==0){
            return 0;
        }
        return sum/(float)count;
    }

}
