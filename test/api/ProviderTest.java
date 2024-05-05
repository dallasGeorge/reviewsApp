package api;

import com.sun.source.tree.AssertTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProviderTest {
    public Provider p1;
    public Accommodation a1;
    public Review r1;
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;

    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        p1 = new Provider("test","test","testProvider","testProvider");
        a1= new Accommodation("testProvider","test","ξενοδοχείο","test",
                "test","1234","test");
        RegularUser reg = new RegularUser("SOMEONE","test","test","test");
        reg.AddReview(a1,"test",5);

    }
    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    @Test
    public void addAccommodation() {

        p1.addAccommodation(a1);
        int flag=0;
        for(Accommodation a: p1.getListOfAccommodations()){
            if(a.equals(a1)){
                flag=flag+1;
                break;
            }

        }

        assertEquals(flag,1);
        a1.delete();
    }

    @Test
    public void editAccommodation() {
        for(Accommodation a : p1.getListOfAccommodations()){
            p1.editAccommodation(a,"testedit","διαμέρισμα","test",
                    "test","1234","test",new ArrayList<>());}
        boolean flag=false;
        for(Accommodation a: p1.getListOfAccommodations()){
            if(a.getName().equals("testedit")){
                flag=true;
                a.delete();
            }
        }
        assertTrue(flag);

    }

    @Test
    public void removeAccommodation() {
        for(Accommodation a: p1.getListOfAccommodations()) p1.removeAccommodation(a);
        int found =0;
        ArrayList<Accommodation> accommodationsDB = (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile("src/files/accommodations.bin");
        for(Accommodation accom : accommodationsDB) {
            if(accom.getOwner()==p1.getUsername()){
                found+=1;
            }
        }

        assertEquals(found,0);
    }

    @Test
    public void getNumberOfAccommodations() {
        assertEquals(p1.getNumberOfAccommodations(),1);
    }

    @Test
    public void numberOfTotalReviews() {
        assertEquals(p1.numberOfTotalReviews(),1);
    }

    @Test
    public void getListOfAccommodations() {
        boolean fl=false;
        for(Accommodation a:p1.getListOfAccommodations()){
            if(a.equals(a1)){
                fl=true;
            }
        }
        assertTrue(p1.getListOfAccommodations().size()==1 && fl);
    }

    @Test
    public void averageRating() {
        assertEquals(p1.averageRating(),5,0.01);
    }
}