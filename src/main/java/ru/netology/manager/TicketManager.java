package ru.netology.manager;

import ru.netology.domain.TicketOffer;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public TicketOffer[] findAll(String from, String to) {
        TicketOffer[] allTickets = repository.findAll();
        TicketOffer[] result = new TicketOffer[0];

        for (TicketOffer ticket : allTickets) {
            if (ticket.getFrom() == from && ticket.getTo() == to) {
                int length = result.length + 1;
                TicketOffer[] tmp = new TicketOffer[length];

                System.arraycopy(result, 0, tmp, 0, result.length);

                int lastIndex = tmp.length - 1;
                tmp[lastIndex] = ticket;

                result = tmp;
            }
        }

        Arrays.sort(result);

        return result;
    }
}





