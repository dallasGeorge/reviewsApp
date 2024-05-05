package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneralUserTest {
    public ArrayList<Accommodation> AccommodationsDB;
    public ArrayList<Review> ReviewsDB;
    public ArrayList<GeneralUser> AccountsDB;
    public GeneralUser g;
    @Before
    public void setUp() throws Exception {
        AccommodationsDB= (ArrayList<Accommodation>) FileInteractions.loadFromBinaryFile(("src/files/accommodations.bin"));
        ReviewsDB=(ArrayList<Review>) FileInteractions.loadFromBinaryFile(("src/files/reviews.bin"));
        AccountsDB=(ArrayList<GeneralUser>) FileInteractions.loadFromBinaryFile(("src/files/accounts.bin"));
        g = new GeneralUser("nikos","test","testUser","testUser","RegularUser");
    }

    @After
    public void tearDown() throws Exception {
        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);
    }

    @Test
    public void checkLogin() {
        assertTrue(GeneralUser.checkLogin("user1","password1"));
    }

    @Test
    public void checkExistence() {
        assertTrue(GeneralUser.checkExistence("user1"));
    }

    @Test
    public void getUserByUsername() {
        assertEquals(GeneralUser.getUserByUsername("user1").getUsername(),"user1");
    }

    @Test
    public void createAccount() {
        GeneralUser.createAccount("nikosCreateAccountTest", "test", "test", "password", "Provider");
        assertEquals(GeneralUser.getUserByUsername("test").getName(),"nikosCreateAccountTest");
        assertFalse(GeneralUser.createAccount("nikosCtTest", "test","test", "password", "Provider" ));

    }

    @Test
    public void setUsername() {
        g.setUsername("aooaoo");
        assertEquals(g.getUsername(),"aooaoo");
    }

    @Test
    public void setPassword() {
        g.setPassword("teest");
        assertEquals(g.getPassword(),"teest");
    }

    @Test
    public void setName() {
        g.setName("testName");
        assertEquals(g.getName(),"testName");
    }

    @Test
    public void setSurname() {
        g.setSurname("epiueto");
        assertEquals(g.getSurname(),"epiueto");
    }

    @Test
    public void setUserType() {
        g.setUserType("Provider");
        assertEquals(g.getUserType(),"Provider");
    }

    @Test
    public void getName() {
        assertEquals(g.getName(),"nikos");
    }

    @Test
    public void getSurname() {
        assertEquals(g.getSurname(),"test");
    }

    @Test
    public void getPassword() {
        assertEquals(g.getPassword(),"testUser");
    }

    @Test
    public void getUsername() {
        assertEquals(g.getUsername(),"testUser");
    }

    @Test
    public void getUserType() {
        assertEquals(g.getUserType(),"RegularUser");
    }
}