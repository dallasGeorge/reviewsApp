package api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Αυτή η κλάση αναπαριστά ένα κατάλυμα με τα εξής χαρακτηριστικά:το όνομα, τον τύπο του καταλύματος, την τοποθεσία του, την περιγραφή και τις παροχές του.
 * @author Γεώργιος Δάλλας
 * @author Χρήστος Θεοφυλακτίδης
 */
public class Accommodation implements Serializable {

    private String owner;
    private String name;
    private String type;
    private String address,city,postalCode;
    private String description;
    private ArrayList<String>  listOfAmenities;


    /**
     * Κατασκευαστής που παίρνει ως παράμετρο το username του παρόχου,το όνομα, τον τύπο, την τοποθεσία,μια περιγραφή του καταλύματος και μια λίστα με διάφορα είδη παροχών του για παραμέτρους.
     * Το κατάλυμα στη συνέχεια αποθηκεύεται στο αρχείο με τα καταλύματα.
     * @param owner Το username του παρόχου του καταλύματος.
     * @param name Το όνομα του καταλύματος ή κάποια σύντομη περιγραφή που θα ορίζει ο πάροχος ως όνομα.
     * @param type Ο τύπος του καταλύματος (πχ ξενοδοχείο, διαμέρισμα, μεζονέτα).
     * @param address Η τοποθεσία του καταλύματος που απαρτίζεται από τη διεύθυνση, την πόλη και τον ταχυδρομικό κώδικα του.
     * @param city Η πόλη στην οποία βρίσκεται το κατάλυμα.
     * @param postalCode Ο ταχυδρομικός κώδικας της περιοχής στην οποία βρίσκεται το κατάλυμα.
     * @param description Η περιγραφή, στην οποία παρουσιάζονται διάφορα στοιχεία σχετικά με το κατάλυμα.
     * @param listOfAmenities Μια λίστα με παροχές.
     */
    public Accommodation(String owner, String name, String type, String address, String city, String postalCode, String description, ArrayList<String>  listOfAmenities) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.description = description;
        this.listOfAmenities = listOfAmenities;
        ArrayList<Accommodation> AccommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        AccommodationsDB.add(this);
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
    }

    /**
     * Κατασκευαστής που παίρνει ως παράμετρο το username του παρόχου,το όνομα, τον τύπο, την τοποθεσία,μια περιγραφή του καταλύματος.
     * Το κατάλυμα στη συνέχεια αποθηκεύεται στο αρχείο με τα καταλύματα.
     * @param owner Το username του παρόχου του καταλύματος.
     * @param name Το όνομα του καταλύματος ή κάποια σύντομη περιγραφή που θα ορίζει ο πάροχος ως όνομα.
     * @param type Ο τύπος του καταλύματος (πχ ξενοδοχείο, διαμέρισμα, μεζονέτα).
     * @param address Η τοποθεσία του καταλύματος που απαρτίζεται από τη διεύθυνση, την πόλη και τον ταχυδρομικό κώδικα του.
     * @param city Η πόλη στην οποία βρίσκεται το κατάλυμα.
     * @param postalCode Ο ταχυδρομικός κώδικας της περιοχής στην οποία βρίσκεται το κατάλυμα.
     * @param description Η περιγραφή, στην οποία παρουσιάζονται διάφορα στοιχεία σχετικά με το κατάλυμα.
     */
    public Accommodation(String owner,String name, String type, String address, String city, String postalCode, String description) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.description = description;
        this.listOfAmenities = new ArrayList<>();
        ArrayList<Accommodation> AccommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        AccommodationsDB.add(this);
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα όνομα και το αναθέτει ως όνομα του καταλύματος στο οποίο καλείται.
     * @param name Το όνομα του καταλύματος.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο το είδος καταλύματος και το αναθέτει ως είδος του συγκεκριμένου καταλύματος στο οποίο θα κληθεί.
     * @param type Τον τύπο του καταλύματος.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο την περιγραφή ενός καταλύματος και την αναθέτει ως περιγραφή
     * στο συγκεκριμένο κατάλυμα όπου και καλείται.
     * @param description Την περιγραφή του καταλύματος.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο μια λίστα με παροχές ενός καταλύματος και την αναθέτει
     * στο συγκεκριμένο κατάλυμα όπου και καλείται.
     * @param listOfAmenities Τη λίστα με παροχές που προσφέρει του κατάλυμα.
     */
    public void setListOfAmenities(ArrayList<String> listOfAmenities) {
        this.listOfAmenities = listOfAmenities;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο μια διεύθυνση
     * και την αναθέτει ως διεύθυνση στο κατάλυμα όπου καλείται.
     * @param address Τη διεύθυνση του καταλύματος.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο την πόλη στην οποία βρίσκεται ένα κατάλυμα η και την αναθέτει ως πόλη στην οποία βρίσκεται το κατάλυμα για το οποίο καλείται.
     * @param city Την πόλη στην οποία βρίσκεται το κατάλυμα.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο τον ταχυδρομικό κώδικα ενός
     * καταλύματος η και την αναθέτει ως ταχυδρομικό κώδικα στο κατάλυμα που καλείται.
     * @param postalCode Τον ταχυδρομικό κώδικα του καταλύματος.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Μέθοδος για την επιστροφή του username του παρόχου ενός καταλύματος.
     * @return το username του παρόχου.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Μέθοδος για την επιστροφή του ονόματος ενός καταλύματος.
     * @return το όνομα του καταλύματος.
     */
    public String getName() {
        return name;
    }

    /**
     * Μέθοδος για την επιστροφή της περιγραφής ενός καταλύματος.
     * @return την περιγραφή, στην οποία παρουσιάζονται διάφορα στοιχεία σχετικά με το κατάλυμα.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Μέθοδος για την επιστροφή του τύπου του καταλύματος (πχ ξενοδοχείο, διαμέρισμα, μεζονέτα).
     * @return τον τύπο του καταλύματος (πχ ξενοδοχείο).
     */
    public String getType() {
        return type;
    }

    /**
     * Μέθοδος για την επιστροφή της διεύθυνσης του καταλύματος.
     * @return τη διεύθυνση του καταλύματος.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Μέθοδος για την επιστροφή της πόλης στην οποία βρίσκεται το κατάλυμα.
     * @return τημ πόλη που βρίσκεται το κατάλυμα.
     */
    public String getCity() {
        return city;
    }
    /**
     * Μέθοδος για την επιστροφή του ταχυδρομικού κώδικα του καταλύματος.
     * @return τον ταχυδρομικό κώδικα του καταλύματος.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Μέθοδος για την επιστροφή της λίστας με είδη παροχών του καταλύματος.
     * @return λίστα με είδη παροχών που προσφέρει το κατάλυμα.
     */
    public ArrayList<String>  getListOfAmenities() {
        return listOfAmenities;
    }

    /**
     * Μέθοδος που προσθέτει μία νέα παροχή στη λίστα με τις παροχές του καταλύματος.
     * @param amenity παροχή του καταλύματος
     */
    public void addAmenity(String amenity){
        listOfAmenities.add(amenity);
    }



    /**
     * Αυτή η μέθοδος τροποποιεί ένα κατάλυμα.
     * @param name το όνομα του καταλύματος
     * @param type το είδος του καταλύματος
     * @param address η διεύθυνση του καταλύματος
     * @param city η πόλη στην οποία βρίσκεται το κατάλυμα
     * @param postalCode ο ταχυδρομικός κώδικας του καταλύματος
     * @param description Η περιγραφή του καταλύματος
     * @param listOfAmenities Η λίστα με τα είδη παροχών τα οποία προσφέρει το κατάλυμα
     */
    public void editAccommodation(String name, String type, String address, String city, String postalCode, String description, ArrayList<String>  listOfAmenities){
        delete();
        setName(name);
        setType(type);
        setAddress(address);
        setCity(city);
        setPostalCode(postalCode);
        setDescription(description);
        setListOfAmenities(listOfAmenities);
        ArrayList<Accommodation> AccommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        AccommodationsDB.add(this);
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);

    }
    /**
     * Μέθοδος όπου δέχεται μια λίστα με όλες τις αξιολογήσεις και επιστρέφει μια νεα λίστα με όλες τις
     * αξιολογήσεις ενός συγκεκριμένου καταλύματος
     * @return Μια λίστα με όλες τις αξιολογήσεις του συγκεκριμένου καταλύματος για το οποίο καλείται.
     */
    public ArrayList<Review> getListOfReviews(){
        ArrayList<Review> reviewsDB= (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");
        ArrayList<Review> list = new ArrayList<>();
        for (Review r : reviewsDB){

            if (r.getAccommodationReviewed().equals(this)){

                list.add(r);
            }
        }
        return list;
    }
    /**
     * Μέθοδος επιστροφής του αριθμού αξιολογήσεων ενός καταλύματος.
     * @return τον αριθμό αξιολογήσεων του καταλύματος.
     */
    public int getNumberOfReviews(){
        return getListOfReviews().size();
    }

    /**
     * Μέθοδος επιστροφής της μέσης βαθμολογίας ενός καταλύματος με βάση τις αξιολογήσεις του.

     * @return τημ μέση βαθμολογίας ενός καταλύματος.
     */
    public float getAverageRating(){

        int sum=0;

        if(getNumberOfReviews()==0){

            return 0;
        }

        for(Review r:getListOfReviews()){
            sum+=r.getReviewStars();

        }
        return (float) (sum/(float)getNumberOfReviews());
    }

    /**
     * Μέθοδος διαγραφής του καταλύματος για το οποίο καλείται από το αρχείο με τη λίστα καταλυμάτων.
     * Η μέθοδος επίσης διαγράφει όλες τις αξιολογήσεις απο το αρχείο με τις αξιολογήσεις που σχετίζονται με το συγκεκριμένο
     * κατάλυμα
     */
    public void delete(){
        ArrayList<Accommodation> AccommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        ArrayList<Review> ReviewsDB = (ArrayList<Review>) FileInteractions.loadFromBinaryFile("src/files/reviews.bin");
        Iterator itr = ReviewsDB.iterator();
        while (itr.hasNext()) {
            Review r = (Review)itr.next();
            if(r.getAccommodationReviewed().equals(this)){
                itr.remove();
            }
        }
        Iterator itr2 = AccommodationsDB.iterator();
        while (itr2.hasNext()) {
            Accommodation accom = (Accommodation) itr2.next();
            if(accom.equals(this)){
                itr2.remove();
            }
        }

        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    /**
     * Μέθοδος που συγκρίνει το όνομα, τον τύπο, την τοποθεσία, την περιγραφή, και την λίστα με παροχές ενός καταλύματος
     * με ένα άλλο.
     * @param a το κατάλυμα με το οποίο γίνεται η σύγκριση
     * @return Επιστρέφει true αν όλες οι προαναφερθέντες τιμές είναι ίσες, αλλιώς επιστρέφει false.
     */
    public boolean equals(Accommodation a){
        return this.getAddress().equals(a.getAddress()) && this.getCity().equals(a.getCity()) && this.getPostalCode().equals(a.getPostalCode())
                && this.getDescription().equals(a.getDescription()) && this.getListOfAmenities().equals(a.getListOfAmenities())
                && this.getName().equals(a.getName()) && this.getType().equals(a.getType());
    }
}


