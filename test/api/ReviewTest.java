package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReviewTest {
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;
    public Review r1;
    public Accommodation a;
    public Accommodation a2;
    public String time_now;
    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        RegularUser gen = new RegularUser("test","test","test","test");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //format για να φαίνεται ωραία η ημερομηνία και ο χρόνος
        time_now = LocalDateTime.now().format(format).toString();
        time_now = time_now.substring(0,14);
        a = new Accommodation("test","a","a","aa","","1234","test");
        a2 = new Accommodation("test2","a","a","aa","","1234","test");
        r1=new Review(gen,a);
        r1.setReviewStars(5);
        r1.setReviewText("test");
    }

    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    @Test
    public void setReviewText() {
        r1.setReviewText("test2");
        assertEquals(r1.getReviewText(),"test2");
    }

    @Test
    public void setReviewStars() {
        r1.setReviewStars(3);
        assertEquals(r1.getReviewStars(),3);
    }

    @Test
    public void setAccommodationReviewed() {
        r1.setAccommodationReviewed(a2);
        assertEquals(r1.getAccommodationReviewed().getOwner(),"test2");
    }

    @Test
    public void getReviewStars() {
        assertEquals(r1.getReviewStars(),5);
    }

    @Test
    public void getAuthor() {
        assertEquals(r1.getAuthor().getUsername(),"test");
    }

    @Test
    public void getAccommodationReviewed() {
        assertTrue(r1.getAccommodationReviewed().equals(a));
    }

    @Test
    public void getCurrentDate() {
        assertTrue(r1.getCurrentDate().contains(time_now));
    }

    @Test
    public void getReviewText() {
        assertEquals(r1.getReviewText(),"test");
    }

    @Test
    public void delete() {
        r1.delete();
        assertEquals(a.getNumberOfReviews(),0);
    }
}