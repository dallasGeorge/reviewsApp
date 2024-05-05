package api;

import java.io.*;

/**
 * Κλάση με χρήσιμες μεθόδους για επικοινωνία αντικειμένων με αρχεία. (serialization)
 */
public class FileInteractions {
    /**
     * Μέθοδος αποθήκευσης ενός αντικειμένου σε ένα binary αρχείο
     * @param outputFilename αρχείο binary στο οποίο θα αποθηκευτεί το αντικείμενο
     * @param o το αντικείμενο προς αποθήκευση
     */
    public static void saveToBinaryFile(String outputFilename,Object o) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFilename))){
            out.writeObject(o);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μέθοδος όπου δέχεται ένα binary αρχείο και επιστρέφει το αντικείμενο το οποίο έχει αποθηκευτεί.
     * @param file αρχείο binary στο οποίο είναι αποθηκευμένο ένα αντικείμενο.
     * @return το αντικείμενο το οποίο είναι αποθηκευμένο στο binary file.
     */
    public static Object loadFromBinaryFile(String file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            Object k;
            k =  in.readObject();
            return k;
        }
        catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
}
