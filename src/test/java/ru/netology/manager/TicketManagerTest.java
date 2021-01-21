package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.TicketOffer;
import ru.netology.repository.TicketRepository;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TicketManagerTest {

    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private TicketOffer ticket1 = new TicketOffer(1, 15000, "LED", "VKO", 60);
    private TicketOffer ticket2 = new TicketOffer(2, 20000, "LED", "VKO", 70);
    private TicketOffer ticket3 = new TicketOffer(3, 16000, "LED", "VKO", 75);
    private TicketOffer ticket4 = new TicketOffer(4, 30000, "IKT", "SVO", 250);
    private TicketOffer ticket5 = new TicketOffer(5, 27000, "IKT", "SVO", 350);
    private TicketOffer ticket6 = new TicketOffer(6, 50000, "SVO", "VVO", 550);

    @BeforeEach
    public void setUp() {

        repository.save(ticket1);
        repository.save(ticket2);
        repository.save(ticket3);
        repository.save(ticket4);
        repository.save(ticket5);
        repository.save(ticket6);


    }

    @Test
    void shouldSortByPrice() {
        TicketOffer[] actual = manager.findAll("LED", "VKO");
        TicketOffer[] expected = new TicketOffer[]{ticket1, ticket3, ticket2};
        assertArrayEquals(expected, actual);
        System.out.print(Arrays.toString(actual));
    }

    @Test
    void shouldNotFindWithWrongIata() {
        TicketOffer[] actual = manager.findAll("GDX", "IST");
        TicketOffer[] expected = new TicketOffer[]{};
        assertArrayEquals(expected, actual);
        System.out.print(Arrays.toString(actual));
    }

    @Test
    void shouldFindWithCorrectIata() {
        TicketOffer[] actual = manager.findAll("IKT", "SVO");
        TicketOffer[] expected = new TicketOffer[]{ticket5, ticket4};
        assertArrayEquals(expected, actual);
        System.out.print(Arrays.toString(actual));
    }

    @Test
    void shouldNotFindWithCorrectFromAndWrongTo() {

        TicketOffer[] actual = manager.findAll("LED", "IST");
        TicketOffer[] expected = new TicketOffer[]{};
        assertArrayEquals(expected, actual);
        System.out.print(Arrays.toString(actual));

    }

    @Test
    void shouldNotFindWithWrongFromAndCorrectTo() {

        TicketOffer[] actual = manager.findAll("IST", "VVO");
        TicketOffer[] expected = new TicketOffer[]{};
        assertArrayEquals(expected, actual);
        System.out.print(Arrays.toString(actual));

    }

}


