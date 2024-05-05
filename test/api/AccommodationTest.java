package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AccommodationTest {
    public Accommodation a1,a2;
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;

    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        ArrayList<String> l1= new ArrayList<>();
        l1.add("wifi");
        l1.add("ethernet");
        a1 = new Accommodation("nikos","Hotel ab","ξενοδοχείο",
                "Καβ Θ 23","Καβάλα","1234","ενα καλό ξενοδοχείο",l1);
        a2 = new Accommodation("kostas","simple room","διαμέρισμα",
                "Καβ Θ 24","Καβάλα","1234","ενα καλό διαμέρισμα");
    }

    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    @Test
    public void setName() {
        a1.setName("Hotel abc");
        a2.setName("Not a simple room");
        assertEquals(a1.getName(),"Hotel abc");
        assertEquals(a2.getName(),"Not a simple room");
    }

    @Test
    public void setType() {
        a1.setType("διαμέρισμα");
        a2.setType("ξενοδοχείο");
        assertEquals(a1.getType(),"διαμέρισμα");
        assertEquals(a2.getType(),"ξενοδοχείο");
    }

    @Test
    public void setDescription() {
        a1.setDescription("κάτσε καλά διαμέρισμα, χωράει και ελέφαντα");
        a2.setDescription("δεν ξέρω");
        assertEquals(a1.getDescription(),"κάτσε καλά διαμέρισμα, χωράει και ελέφαντα");
        assertEquals(a2.getDescription(),"δεν ξέρω");
    }

    @Test
    public void setListOfAmenities() {
        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<String> l2 = new ArrayList<>();
        l1.add("wifi");
        l2.add("ethernet");
        a1.setListOfAmenities(l1);
        a2.setListOfAmenities(l2);
        assertEquals(a1.getListOfAmenities(),l1);
        assertEquals(a2.getListOfAmenities(),l2);
    }

    @Test
    public void setAddress() {
        a1.setAddress("κάπου εδώ 12");
        a2.setAddress("κάπου εκεί 13");
        assertEquals(a1.getAddress(),"κάπου εδώ 12");
        assertEquals(a2.getAddress(),"κάπου εκεί 13");
    }

    @Test
    public void setCity() {
        a2.setCity("Χανιά");
        a1.setCity("Τρίκαλα");
        assertEquals(a1.getCity(),"Τρίκαλα");
        assertEquals(a2.getCity(),"Χανιά");
    }

    @Test
    public void setPostalCode() {
        a1.setPostalCode("3232");
        a2.setPostalCode("3212");
        assertEquals(a1.getPostalCode(),"3232");
        assertEquals(a2.getPostalCode(),"3212");
    }

    @Test
    public void getOwner() {
        assertEquals(a1.getOwner(),"nikos");
        assertEquals(a2.getOwner(),"kostas");
    }

    @Test
    public void getName() {
        assertEquals(a1.getName(),"Hotel ab");
        assertEquals(a2.getName(),"simple room");
    }

    @Test
    public void getDescription() {
        assertEquals(a1.getDescription(),"ενα καλό ξενοδοχείο");
        assertEquals(a2.getDescription(),"ενα καλό διαμέρισμα");
    }

    @Test
    public void getType() {
        assertEquals(a1.getType(),"ξενοδοχείο");
        assertEquals(a2.getType(),"διαμέρισμα");
    }

    @Test
    public void getAddress() {
        assertEquals(a1.getAddress(),"Καβ Θ 23");
        assertEquals(a2.getAddress(),"Καβ Θ 24");
    }

    @Test
    public void getCity() {
        assertEquals(a1.getCity(),"Καβάλα");
        assertEquals(a2.getCity(),"Καβάλα");
    }

    @Test
    public void getPostalCode() {
        assertEquals(a1.getPostalCode(),"1234");
        assertEquals(a2.getPostalCode(),"1234");
    }

    @Test
    public void getListOfAmenities() {
        ArrayList<String> l1= new ArrayList<>();
        ArrayList<String> l2= new ArrayList<>();
        l1.add("wifi");
        l1.add("ethernet");
        assertEquals(a1.getListOfAmenities(),l1);
        assertEquals(a2.getListOfAmenities(),l2);
    }

    @Test
    public void addAmenity() {
        ArrayList<String> l1= new ArrayList<>();
        l1.add("wifi");
        l1.add("ethernet");
        l1.add("πιστολάκι μαλλιών");
        ArrayList<String> l2= new ArrayList<>();
        l2.add("wifi");
        a1.addAmenity("πιστολάκι μαλλιών");
        a2.addAmenity("wifi");
        assertEquals(a1.getListOfAmenities(),l1);
        assertEquals(a2.getListOfAmenities(),l2);
    }

    @Test
    public void editAccommodation() {
        ArrayList<String> l1=new ArrayList<>();
        l1.add("wifi");
        l1.add("ethernet");
        l1.add("κουζίνα");
        Accommodation ac1= new Accommodation("test","new Name","διαμέρισμα","Νέα Περιοχή","Κοζάνη",
                "1234","new desc",l1);
        Accommodation ac2= new Accommodation("test","new Name 2","διαμέρισμα","Νέα Περιοχή 2","Κοζάνη2",
                "1232","new desc2",l1);
        a1.editAccommodation("new Name","διαμέρισμα","Νέα Περιοχή","Κοζάνη",
                "1234","new desc",l1);
        a2.editAccommodation("new Name 2","διαμέρισμα","Νέα Περιοχή 2","Κοζάνη2",
                "1232","new desc2",l1);
        assertTrue(a1.equals(ac1));
        assertTrue(a2.equals(ac2));
    }

    @Test
    public void getListOfReviews() {
        RegularUser us1 = new RegularUser("test","test2","testAccount","password");
        us1.AddReview(a1,"specific test",5);
        boolean flag1=false;
        for(Review r : a1.getListOfReviews()){
            if(r.getReviewText().equals("specific test")){
                flag1=true;
            }
        }
        assertTrue(flag1);
        assertFalse(us1.AddReview(a2,"oof",6));
    }

    @Test
    public void getNumberOfReviews() {
        RegularUser us1 = new RegularUser("test","test2","testAccount","password");
        us1.AddReview(a1,"specific test",5);
        assertEquals(a1.getNumberOfReviews(),1);
    }

    @Test
    public void getAverageRating() {
        RegularUser us1 = new RegularUser("test","test2","testAccount","password");
        us1.AddReview(a1,"specific test",5);
        us1.AddReview(a1,"specific test",4);
        assertEquals(a1.getAverageRating(),4.5,0.01);
    }

    @Test
    public void delete() {
        a1.delete();
        boolean flag=false;
        ArrayList<Accommodation> accList= AccommodationsDB;
        for(Accommodation a: accList){
            if(a.equals(a1)){
                flag=true;
            }
        }
        assertFalse(flag);
    }

    @Test
    public void testEquals() {
        Accommodation aa = new Accommodation("nikos","Hotel ab","ξενοδοχείο",
                "Καβ Θ 23","Καβάλα","1234","ενα καλό ξενοδοχείο");
        Accommodation aaTest = new Accommodation("nikos","Hotel ab","ξενοδοχείο",
                "Καβ Θ 23","Καβάλα","1234","ενα καλό ξενοδοχείο");
        Accommodation aa2 = new Accommodation("nik2os","Hotel ab","ξενοδοχείο",
                "Καβ Θ 23","Καβάλα","1234","ενα καλό ξενοδοχείο");
        assertTrue(aa.equals(aaTest));
        assertFalse(aa2.equals(a1));
    }
}