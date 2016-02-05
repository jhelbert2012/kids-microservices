package com.twp.petcare.app;

import com.twp.petcare.bean.Card;
import com.twp.petcare.bean.User;
import com.twp.petcare.repository.CardRepository;
import com.twp.petcare.repository.UserRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.twp.petcare.repository")
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    public void run(String... strings) throws Exception {

        initializeUsers();
        initializeCards();

    }

    private void initializeCards() {
        cardRepository.deleteAll();

        // save a couple of cards
        cardRepository.save(new Card("/content/path/to/card1", userRepository.findAll()));
        cardRepository.save(new Card("mybookid1", null));

        // fetch all cards
        System.out.println("Cards found with findAll():");
        System.out.println("-------------------------------");
        for (Card card : cardRepository.findAll()) {
            System.out.println(card);
        }
        System.out.println();

        // fetch an individual card
        System.out.println("Cards found with findByFirstName('jhelbert'):");
        System.out.println("--------------------------------");
        System.out.println(cardRepository.findByCardId("/content/path/to/card1"));
    }

    private void initializeUsers() {
        userRepository.deleteAll();

        // save a couple of cards
        userRepository.save(new User(1, "79996406", "jhelbert", "password1", 'M', "Helbert", "Rico", "Cra 71b Bis 12 60", "3144460648", "jhelbert@gmail.com", Calendar.getInstance().getTime()));
        userRepository.save(new User(1, "55659669", "sofia", "pass2", 'F', "Sofia", "Arias", "Address2", "34344422233", "sofica.arias@gmail.com", Calendar.getInstance().getTime()));

        // fetch all cards
        System.out.println("Cards found with findAll():");
        System.out.println("-------------------------------");
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual card
        System.out.println("Cards found with findByName('koke'):");
        System.out.println("--------------------------------");
        System.out.println(userRepository.findByName(("sofia")));

        System.out.println("Customers found with findBySpecie(34):");
        System.out.println("--------------------------------");

        Pageable pageable = new PageRequest(0, 10);
        for (User user : userRepository.findByDocumentNumber("79996406")) {
            System.out.println(user);
        }

        User user = userRepository.getUserFor(new Card("/content/path/to/card1"));

        System.out.println(user);
    }
}
