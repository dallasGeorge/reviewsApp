package api;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Αυτή η κλάση αναπαριστά μια αξιολόγηση ενός καταλύματος
 * @author Χρήστος Θεοφυλακτίδης
 * @author Γεώργιος Δάλλας
 */
public class Review implements Serializable {
    private String reviewText;
    private final String currentDate;
    private int reviewStars;

    private RegularUser author;
    private Accommodation accommodationReviewed;

    /**
     * Κατασκευαστής που παίρνει ως παράμετρο τον συγγραφέα της αξιολόγησης και το κατάλυμα το οποίο αξιολογείται και προσθέτει
     * την τρέχων ημερομηνία και ώρα στην παράμετρο currentDate.
     * Επιλέγεται να είναι υποχρεωτική η χρήση setters για την αρχικοποίηση των τιμών reviewText και ReviewStars,
     * ώστε να ελέγχονται οι τιμές που παίρνουν.
     */
    public Review(RegularUser author, Accommodation accommodationReviewed) {
        this.author = author;
        this.accommodationReviewed = accommodationReviewed;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //format για να φαίνεται ωραία η ημερομηνία και ο χρόνος
        this.currentDate = LocalDateTime.now().format(format).toString();

    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο και ορίζει το κείμενο μιας αξιολόγησης. Το κείμενο αυτό πρέπει υποχρεωτικά να
     * μην είναι κενό και να μην υπερβαίνει τους 500 χαρακτήρες.
     * @param reviewText το κείμενο της αξιολόγησης
     * @return Επιστρέφει True αν το κείμενο δεν είναι κενό και δεν υπερβαίνει τους 500 χαρακτήρες. Αλλιώς επιστρέφει false.
     */
    public boolean setReviewText(String reviewText) {
        if (reviewText.length() <= 500 && reviewText.length() > 0) {
            this.reviewText = reviewText;
            return true;
        }
        return false;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο και ορίζει τη βαθμολογία μιας αξιολόγησης. Η βαθμολογία αυτή πρέπει υποχρεωτικά να
     * παίρνει τιμές ανάμεσα στο 1 και στο 5.
     * @param reviewStars η βαθμολογία (σε αστέρια) ενός καταλύματος
     * @return Επιστρέφει True αν η βαθμολογία παίρνει τιμές ανάμεσα στο 1 και στο 5. Αλλιώς επιστρέφει false.
     */
    public boolean setReviewStars(int reviewStars) {
        if (reviewStars <= 5 && reviewStars>=1) {
            this.reviewStars = reviewStars;
            return true;
        }
        return false;
    }

    /**
     * Μέθοδος που προσδιορίζει το κατάλυμα στο οποίο αναφέρεται μια αξιολόγηση/
     * @param accommodationReviewed το κατάλυμα για το οποίο θέλουμε να γραφτεί η αξιολόγηση.
     */
    public void setAccommodationReviewed(Accommodation accommodationReviewed) {
        delete();
        this.accommodationReviewed = accommodationReviewed;
        ArrayList<Review> ReviewsDB = (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");
        ReviewsDB.add(this);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);

    }

    /**
     * Μέθοδος επιστροφής της βαθμολογίας ενός καταλύματος.
     * @return τη βαθμολογία ενός καταλύματος.
     */
    public int getReviewStars() {
        return reviewStars;
    }

    /**
     * Μέθοδος επιστροφής του συγγραφέα της αξιολόγησης.
     * @return τον συγγραφέα της αξιολόγησης
     */
    public RegularUser getAuthor(){
        return author;
    }

    /**
     * Μέθοδος επιστροφής του καταλύματος το οποίο αξιολογείται.
     * @return το κατάλυμα το οποίο αξιολογείται.
     */
    public Accommodation getAccommodationReviewed() {
        return accommodationReviewed;
    }

    /**
     * Μέθοδος επιστροφής της ημερομηνίοας και της ώρας που έγινε η αξιολόγηση.
     * @return την ημερομηνία και την ώρα ποπυ έγινε η αξιολόγηση.
     */
    public String getCurrentDate() {
        return currentDate;
    }
    /**
     * Μέθοδος επιστροφής του κειμένου της αξιολόγησης.
     * @return το κείμενο της αξιολόγησης.
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Μέθοδος διαγραφής της αξιολόγησης για την οποία καλείται απο την λίστα με αξιολογήσεις που υπάρχει στο δυαδικό
     * αρχείο reviews.bin.
     */
    public void delete(){
        ArrayList<Review> ReviewsDB = (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");
        Iterator itr = ReviewsDB.iterator();
        while (itr.hasNext()) {
            Review rev = (Review) itr.next();
            if(rev.getAuthor().getUsername().equals(this.getAuthor().getUsername()) && rev.getReviewText().equals(this.getReviewText()) &&
            rev.getAccommodationReviewed().equals(this.getAccommodationReviewed()) && (rev.getReviewStars() ==this.getReviewStars())) {
                itr.remove();
            }


        }
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }
}

