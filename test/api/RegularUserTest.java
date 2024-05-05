package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class RegularUserTest {
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;
    public RegularUser reg;
    public Accommodation a;
    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        reg=new RegularUser("TEST","TESTT","testUs","testUs");
        a = new Accommodation("test","testSearch","ξενοδοχείο","test","test","1234","test");
    }

    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    @Test
    public void addReview() {
        reg.AddReview(a,"good",5);
        boolean flag=false;
        for (Review rev: a.getListOfReviews()){
            if(rev.getReviewText().equals("good")){
                flag=true;
            }
        }
        assertTrue(flag);

    }

    @Test
    public void getListOfReviews() {
        reg.AddReview(a,"good",5);
        assertEquals(reg.getListOfReviews().size(),1);
    }

    @Test
    public void getAverageRating() {
        reg.AddReview(a,"good",5);
        reg.AddReview(a,"goood",4);
        assertEquals(reg.getAverageRating(),4.5,0.01);
    }

    @Test
    public void searchAccommodations() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);

        assertEquals(reg.searchAccommodations(arr,"testSearch").size(),1);
    }
}