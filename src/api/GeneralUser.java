package api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Αυτή η κλάση αναπαριστά έναν γενικό χρήστη. Με τη βοήθεια της κληρονομικότητας μπορεί να χρησιμοποιηθεί για τη δημιουργία
 * της κλάσης του παρόχου καταλυμάτων, του απλού χρήστη αλλά και για την ανάπτυξη νέων ειδών χρήστη (πχ διαχειριστή).
 * @author Γεώργιος Δάλλας
 */
public class GeneralUser implements Serializable {

    private String name,surname;
    private String username,password;
    private String userType;

    /**
     * Κατασκευαστής που παίρνει ως παράμετρο το όνομα, το επίθετο, το Username, το Password και τον τύπο ενός χρήστη.
     * @param name Το όνομα του χρήστη
     * @param surname Το επίθετο του χρήστη
     * @param username Το username που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     * @param password Το password που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     * @param userType Ο τύπος του χρήστη (πχ πάροχος, χρήστης).
     */
    public GeneralUser(String name, String surname, String username, String password, String userType) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Μέθοδος ελέγχου στοιχείων για τη σύνδεση ενός χρήστη στην εφαρμογή.
     * @param username το username του χρήστη
     * @param password ο κωδικός πρόσβασης του χρήστη
     * @return true αν τα στοιχεία ταιριάζουν με αυτά ενός χρήστη στην λίστα χρηστών που υπάρχει αποθηκευμένη.
     */
    public static boolean checkLogin(String username, String password){
        ArrayList<GeneralUser>AccountsDB = (ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile("src/files/accounts.bin");
        boolean found=false;
        for(GeneralUser acc : AccountsDB){
            if(acc.getUsername().equals(username) && acc.getPassword().equals(password)){
                found=true;
                break;
            }
        }
        return found;
    }

    /**
     * ελέγχει αν υπάρχει χρήστης με το συγκεκριμένο username στη λίστα χρηστών που υπάρχει αποθηκευμένη.
     * @param username username του χρήστη που ελέγχεται.
     * @return επιστρέφει true αν υπάρχει χρήστης που να ταιριάζει με αυτό το username αλλιώς false.
     */
    public static boolean checkExistence(String username){
        ArrayList<GeneralUser>AccountsDB = (ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile("src/files/accounts.bin");
        boolean found=false;
        for(GeneralUser acc : AccountsDB){
            if(acc.getUsername().equals(username)){
                found=true;
                break;

            }
        }
        return found;
    }

    /**
     * Μέθοδος επιστροφής ενός χρήστη χρησιμοποιώντας απλά το username του για παράμετρο αναζήτησης.
     * @param username το username του χρήστη
     * @return επιστρέφει έναν χρήστη τύπου general user που έχει το username της παραμέτρου.
     */
    public static GeneralUser getUserByUsername(String username){
        ArrayList<GeneralUser>AccountsDB = (ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile("src/files/accounts.bin");
        for(GeneralUser acc : AccountsDB){
            if(acc.getUsername().equals(username)){
                return acc;
            }
        }
        return null;
    }

    /**
     * Μέθοδος δημιουργίας ενός χρήστη και αποθήκευσης αυτού στο αρχείο με την λίστα χρηστών.
     * @param name το όνομα του χρήστη
     * @param surname το επίθετο του χρήστη
     * @param username το username του χρήστη
     * @param password ο κωδικός πρόσβασης του χρήστη
     * @param userType ο τύπος του χρήστη πχ Provider
     * @return επιστρέφει true αν η δημιουργία του χρήστη ήταν επιτυχείς, αλλιώς false.
     */
    public static boolean createAccount(String name, String surname, String username, String password, String userType){

        if(checkExistence(username)==false){
            ArrayList<GeneralUser>AccountsDB = (ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile("src/files/accounts.bin");
            GeneralUser newAcc = new GeneralUser(name,surname,username,password,userType);
            AccountsDB.add(newAcc);
            FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
            return true;
        }
        return false;
    };
    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα username και το αναθέτει ως username του χρήστη για τον οποίο καλείται.
     * @param username Το username που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα password και το αναθέτει ως password του χρήστη για τον οποίο καλείται.
     * @param password Το password που χρησιμοποιεί ο χρήστης για τη σύνδεση του.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα όνομα και το αναθέτει ως όνομα του χρήστη για τον οποίο καλείται.
     * @param name Το όνομα του χρήστη
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα επίθετο και το αναθέτει ως επίθετο του χρήστη για τον οποίο καλείται.
     * @param surname Το επίθετο του χρήστη
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Αυτή η μέθοδος δέχεται ως παράμετρο ένα τύπο χρήστη (πχ πάροχος) και το αναθέτει ως τύπο του χρήστη για τον οποίο καλείται.
     * @param userType Ο τύπος του χρήστη (πχ πάροχος, χρήστης).
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Μέθοδος για την επιστροφή του ονόματος ενός χρήστη.
     * @return το όνομα του χρήστη
     */
    public String getName() {
        return name;
    }

    /**
     * Μέθοδος για την επιστροφή του επιθέτου ενός χρήστη.
     * @return το επίθετο του χρήστη
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Μέθοδος για την επιστροφή του κωδικού ενός χρήστη.
     * @return τον κωδικό του χρήστη
     */
    public String getPassword() {
        return password;
    }

    /**
     * Μέθοδος για την επιστροφή του username ενός χρήστη.
     * @return το username του χρήστη
     */
    public String getUsername() {
        return username;
    }

    /**
     * Μέθοδος για την επιστροφή του τύπου ενός χρήστη.
     * @return τον τύπου του χρήστη (πχ πάροχος)
     */
    public String getUserType() {
        return userType;
    }




}
