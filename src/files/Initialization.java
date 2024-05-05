package files;

import api.*;

import java.util.ArrayList;
public class Initialization{
    public Initialization(){

    }
    public static void initAccounts(){

        Provider p1=new Provider("George","Dallas","provider1","password1");
        Provider p2=new Provider("Chris","Theofylaktidis","provider2","password2");

        RegularUser p3=new RegularUser("Maria","Papa","user1","password1");
        RegularUser p4=new RegularUser("Nikos","Papas","user2","password2");

        ArrayList<GeneralUser> AccountsDB = new ArrayList<>();
        AccountsDB.add(p1);
        AccountsDB.add(p2);
        AccountsDB.add(p3);
        AccountsDB.add(p4);

        ArrayList<String> s1 = new ArrayList<>();
        ArrayList<String> s2 = new ArrayList<>();
        ArrayList<String> s3 = new ArrayList<>();
        ArrayList<String> s4 = new ArrayList<>();
        ArrayList<String> s5 = new ArrayList<>();
        ArrayList<String> s6 = new ArrayList<>();
        ArrayList<String> s7 = new ArrayList<>();
        ArrayList<String> s8 = new ArrayList<>();
        ArrayList<String> s9 = new ArrayList<>();
        ArrayList<String> s10 = new ArrayList<>();

        s1.add("Wifi");

        s2.add("Wifi");
        s2.add("Ethernet");

        s3.add("Κουζίνα");
        s3.add("Ψυγείο");
        s3.add("Πλυντήριο πιάτων");

        s4.add("Wifi");
        s4.add("Ethernet");
        s4.add("Εσωτερικό τζάκι");
        s4.add("Κουζίνα");

        s5.add("Καφετιέρα");
        s5.add("Ψυγείο");
        s5.add("Τηλεόραση");
        s5.add("Θέα σε πισίνα");
        s6.add("Wifi");
        s6.add("Ethernet");
        s6.add("Εσωτερικό τζάκι");
        s6.add("Κουζίνα");
        s6.add("Καφετιέρα");
        s6.add("Ψυγείο");
        s6.add("Τηλεόραση");

        s7.add("Θέα σε πισίνα");
        s7.add("Θέα στον δρόμο");
        s7.add("Κλιματισμός");

        s8.add("Θέα στον δρόμο");
        s8.add("Wifi");
        s8.add("Τηλεόραση");
        s8.add("Κεντρική θέρμανση");

        s9.add("Δωρεάν χώρος στάθμευσης στην ιδιοκτησία");
        s9.add("Μπαλκόνι");
        s9.add("Wifi");

        s10.add("Πλυντήριο ρούχων");
        s10.add("Θέα σε παραλία");
        s10.add("Ethernet");
        s10.add("Κουζίνα");
        s10.add("Μπαλκόνι");


        Accommodation a1= new Accommodation("provider1","XYZ HOTEL","ξενοδοχείο","Aποστόλου Π 66","Θεσσαλονίκη","5432","Ένα ξενοδοχείο κάτσε καλά",s1);
        Accommodation a2= new Accommodation("provider1","φοιτητικό διαμέρισμα κέντρο","διαμέρισμα","Λ Νίκης 66","Κοζάνη","1234","Ένα διαμέρισμα κάτι άλλο",s2);
        Accommodation a3= new Accommodation("provider1","μεζονέτα με Κουζίνα και Ψυγείο","μεζονέτα","Aποστόλου Π 66","Θεσσαλονίκη","5432","Ένα ξενοδοχείο κάτσε καλά",s3);
        Accommodation a4= new Accommodation("provider1","μικρό δωματιάκι φοιτητικό","διαμέρισμα","Κορίνθου 21","Καβάλα","1212","φοιτητικό άνετο δωμάτιο",s4);
        Accommodation a5= new Accommodation("provider1","ACDE HOTEL","ξενοδοχείο","Εγνατία 12","Κέρκυρα","0112","Τέλειο ξενοδοχείο",s5);

        Accommodation a6= new Accommodation("provider2","Poseidon Hotel","ξενοδοχείο","Εγνατία 5","Θεσσαλονίκη","5432","ξενοδοχείο πάνω στην Εγνατία",s6);
        Accommodation a7= new Accommodation("provider2","διαμέρισμα 45 τετραγωνικά","διαμέρισμα","Ολύμπου 4","Καστοριά","1234","Ένα διαμέρισμα κάτι άλλο",s7);
        Accommodation a8= new Accommodation("provider2","Το πολυτελές","ξενοδοχείο","Κορίνθιου 6","Τρίκαλα","1872","όλα στην εντέλεια",s8);
        Accommodation a9= new Accommodation("provider2","τεράστιο σπίτι 2όροφο","μεζονέτα","Ολυμπιάδος 1","Καβάλα","3111","τεράστιο σπίτι με γκαράζ",s9);
        Accommodation a10= new Accommodation("provider2","3 δωμάτια + Κουζίνα","διαμέρισμα","Εγνατία 12","Χανιά","7216","διαμέρισμα με μπάνιο 3 δωμάτια και Κουζίνα",s10);

        ArrayList<Accommodation> AccommodationsDB = new ArrayList<>();
        AccommodationsDB.add(a1);
        AccommodationsDB.add(a2);
        AccommodationsDB.add(a3);
        AccommodationsDB.add(a4);
        AccommodationsDB.add(a5);
        AccommodationsDB.add(a6);
        AccommodationsDB.add(a7);
        AccommodationsDB.add(a8);
        AccommodationsDB.add(a9);
        AccommodationsDB.add(a10);

        Review r1 = new Review(p3,a1);
        Review r2 = new Review(p3,a2);
        Review r3 = new Review(p3,a3);
        Review r4 = new Review(p3,a4);
        Review r5 = new Review(p3,a5);
        Review r6 = new Review(p3,a6);
        Review r7 = new Review(p3,a7);
        Review r8 = new Review(p3,a8);
        Review r9 = new Review(p3,a9);
        Review r10 = new Review(p3,a10);


        Review rr1 = new Review(p4,a1);
        Review rr2 = new Review(p4,a2);
        Review rr3 = new Review(p4,a3);
        Review rr4 = new Review(p4,a4);
        Review rr5 = new Review(p4,a5);
        Review rr6 = new Review(p4,a6);
        Review rr7 = new Review(p4,a7);
        Review rr8 = new Review(p4,a8);
        Review rr9 = new Review(p4,a9);
        Review rr10 = new Review(p4,a10);

        r1.setReviewStars(4);
        r1.setReviewText("good enough.");
        r2.setReviewStars(1);
        r2.setReviewText("would not recommend");
        r3.setReviewStars(2);
        r3.setReviewText("did not like it but it was good enough for one day");
        r4.setReviewStars(4);
        r4.setReviewText("good enoughhhh!!! htan teleiooooooooo uelw na ksanapawwwww.");
        r5.setReviewStars(5);
        r5.setReviewText("teleio");
        r6.setReviewStars(1);
        r6.setReviewText("horrible experience");
        r7.setReviewStars(4);
        r7.setReviewText("it had a nice view");
        r8.setReviewStars(3);
        r8.setReviewText("was ok, dont have much to say");
        r9.setReviewStars(5);
        r9.setReviewText("completely satisfied by everything");
        r10.setReviewStars(1);
        r10.setReviewText("not what i expected");

        rr1.setReviewStars(1);
        rr1.setReviewText("καλύτερα στα παγκάκια");
        rr2.setReviewStars(5);
        rr2.setReviewText("ωραία φαση");
        rr3.setReviewStars(2);
        rr3.setReviewText("πολύ αγενής ο ιδιοκτήτης");
        rr4.setReviewStars(1);
        rr4.setReviewText("ωραία θέρμανση..με τα μαγκάλια ήμασταν");
        rr5.setReviewStars(5);
        rr5.setReviewText("νταξ τωρα μιλάς σωστά! ποιότητα και τιμή");
        rr6.setReviewStars(5);
        rr6.setReviewText("αν είχε και άλλα αστέρια θα έβαζα κι άλλα");
        rr7.setReviewStars(3);
        rr7.setReviewText("γεια");
        rr8.setReviewStars(1);
        rr8.setReviewText("απατεώνες μου έφαγαν τα λεφτά");
        rr9.setReviewStars(1);
        rr9.setReviewText("καλά ένα ωραίο μέρος να μείνεις δεν υπάρχει σε αυτή την πόλη");
        rr10.setReviewStars(5);
        rr10.setReviewText("το βρήκα το μέρος που έψαχνα");

        ArrayList<Review> ReviewsDB= new ArrayList<>();
        ReviewsDB.add(r1);
        ReviewsDB.add(r2);
        ReviewsDB.add(r3);
        ReviewsDB.add(r4);
        ReviewsDB.add(r5);
        ReviewsDB.add(r6);
        ReviewsDB.add(r7);
        ReviewsDB.add(r8);
        ReviewsDB.add(r9);
        ReviewsDB.add(r10);

        ReviewsDB.add(rr1);
        ReviewsDB.add(rr2);
        ReviewsDB.add(rr3);
        ReviewsDB.add(rr4);
        ReviewsDB.add(rr5);
        ReviewsDB.add(rr6);
        ReviewsDB.add(rr7);
        ReviewsDB.add(rr8);
        ReviewsDB.add(rr9);
        ReviewsDB.add(rr10);

        FileInteractions.saveToBinaryFile("src/files/accommodations.bin",AccommodationsDB);
        FileInteractions.saveToBinaryFile("src/files/accounts.bin",AccountsDB);
        FileInteractions.saveToBinaryFile("src/files/reviews.bin",ReviewsDB);

    }}