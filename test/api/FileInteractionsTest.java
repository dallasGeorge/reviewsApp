package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FileInteractionsTest {
    public Accommodation a1;
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;
    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        a1 = new Accommodation("nikos","Hotel ab","ξενοδοχείο",
                "Καβ Θ 23","Καβάλα","1234","ενα καλό ξενοδοχείο");

    }

    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
        File myObj = new File("test/api/test.bin");
        myObj.delete();

    }

    @Test
    public void saveToBinaryFile() {

        FileInteractions.saveToBinaryFile("test/api/test.bin", a1);
        Object k;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("test/api/test.bin"))) {
            k = in.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Accommodation a2 = (Accommodation) k;
        assertTrue(a2.equals(a1));
    }

    @Test
    public void loadFromBinaryFile() {
        FileInteractions.saveToBinaryFile("test/api/test.bin",a1);
        Accommodation a2 =(Accommodation)FileInteractions.loadFromBinaryFile("test/api/test.bin");
        assertTrue(a2.equals(a1));
    }
}