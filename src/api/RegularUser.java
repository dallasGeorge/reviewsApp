package api;

import java.util.ArrayList;

/**
 * Αυτή η κλάση αναπαριστά έναν απλό χρήστη όπου έχει πρόσβαση στην αξιολόγηση καταλυμάτων.
 * @author Γεώργιος Δάλλας
 */

public class RegularUser extends GeneralUser {

    /**
     * Κατασκευαστής όπου δέχεται ως ορίσματα το όνομα, το επίθετο, το Username τον κωδικό του απλού χρήστη και αρχικοποιεί
     * μια λίστα με τα καταλύματα που έχει αξιολογήσει.
     * @param name Το όνομα του χρήστη
     * @param surname Το επίθετο του χρήστη
     * @param username Το username που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     * @param password Το password που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     */
    public RegularUser(String name, String surname, String username, String password){
        super(name,surname,username,password,"RegularUser");
    }

    /**
     * Κατασκευαστής που δημιουργεί ένα αντικείμενο τύπου RegularUser δέχοντας ως παράμετρο μόνο έναν GeneralUser, γονέα
     * κλάση του RegularUser
     * @param g ο χρήστης γενικού τύπου που θα χρησιμοποιηθεί για τη δημιουργία του RegularUser
     */
    public RegularUser(GeneralUser g){
        super(g.getName(), g.getSurname(), g.getUsername(), g.getPassword(), g.getUserType());
    }

    /**
     * Μέθοδος που προσθέτει μια αξιολόγηση σε κάποιο κατάλυμα
     * @param a το κατάλυμα για το οποίο προστίθεται η αξιολόγηση,
     * @param reviewText Το κείμενο της αξιολόγησης
     * @param reviewStars Η βαθμολογία στην αξιολόγηση του
     * @return Η μέθοδος παίρνει την τιμή True αν πληρούνται όλες οι προϋποθέσεις για τη δημιουργία μίας αξιολόγησης.
     * Διαφορετικά παίρνει την τιμή false.
     */
    public boolean AddReview(Accommodation a,String reviewText, int reviewStars){
        Review review = new Review(this,a);
        ArrayList<Review> reviewsDB= (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");

        if(review.setReviewStars(reviewStars) && review.setReviewText(reviewText)){
            review.setAccommodationReviewed(a);

            return  true;
        }
        return false;
    }

    /**
     * Μέθοδος επιστροφής όλων των αξιολογήσεων του χρήστη.
     * @return λίστα με όλες τις αξιολογήσεις του χρήστη.
     */
    public ArrayList<Review> getListOfReviews() {
        ArrayList<Review> reviewsDB= (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");
        ArrayList<Review> ListOfReviews = new ArrayList<>();
        for(Review r: reviewsDB){
            if(r.getAuthor().getUsername().equals(getUsername())){
                ListOfReviews.add(r);
            }
        }
        return ListOfReviews;
    }

    /**
     * Μέθοδος που επιστρέφει τη μέση αξιολόγηση σε αστέρια από όλες τις αξιολογήσεις του χρήστη.
     * @return μέση αξιολόγηση σε αστέρια από όλες τις αξιολογήσεις του χρήστη.
     */
    public float getAverageRating(){
        float sum=0;
        if (getListOfReviews().size()==0){
            return 0;
        }
        for(Review r:getListOfReviews()){
            sum+=r.getReviewStars();
        }
        return sum/(float) getListOfReviews().size();
    }

    /**
     * Mέθοδος αναζήτης καταλυμάτων με τα εξείς κριτήρια:
     * 1. Όνομα του καταλύματος
     * 2. Τύπος του καταλύματος
     * 3. Τοποθεσία του καταλύματος
     * 4. Παροχές του καταλύματος
     * Ο χρήστης μπορεί να επιλέξει πάνω από ένα κριτήριο αναζήτησης και για να επιστρέψει ενα κατάλυμα σαν αποτέλεσμα πρέπει
     * να καλύπτει όλα τα κριτήρια (λογικό AND).
     * @param searchCriteria Λίστα με τα κριτήρια αναζήτησης
     * @param input Η χαρακτήρες αναζήτησης που έχει πληκτρολογήσει ο χρήστης.
     * @return Μια λίστα με καταλύματα που ικανοποιούν όλα τα κριτήρια αναζήτησης του χρήστη.
     */
    public ArrayList<Accommodation> searchAccommodations(ArrayList<Integer> searchCriteria,String input){
        ArrayList<Accommodation> list = new ArrayList<>();
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>)FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        for (Accommodation a:accommodationsDB) {
            int sumOfSatisfiedCriteria = 0;
            for (Integer i : searchCriteria) {
                if (i == 1) {
                    if (a.getName().contains(input)) {
                        sumOfSatisfiedCriteria += 1;
                    }
                } else if (i == 2) {
                    if (a.getType().contains(input)) {
                        sumOfSatisfiedCriteria += 1;
                    }
                } else if (i == 3) {
                    if (a.getAddress().contains(input) || a.getCity().contains(input) ||
                            a.getPostalCode().contains(input)) {
                        sumOfSatisfiedCriteria += 1;
                    }
                } else {
                    for(String str : a.getListOfAmenities()){
                        if(str.contains(input)){
                            sumOfSatisfiedCriteria+=1;
                            break;
                        }
                    }
                }
            }
            if (sumOfSatisfiedCriteria == searchCriteria.size()) {
                list.add(a);
            }
        }

        return list;
    }
}
