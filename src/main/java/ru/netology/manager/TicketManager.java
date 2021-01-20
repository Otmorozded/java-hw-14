package ru.netology.manager;

import ru.netology.domain.TicketOffer;
import ru.netology.repository.TicketRepository;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }


    public void add(TicketOffer item) {
        repository.save(item);
    }

    public TicketOffer[] getAll() {
        TicketOffer[] items = repository.findAll();
        TicketOffer[] result = new TicketOffer[items.length];
        for (int i = 0; i < result.length; i++) {
            int index = items.length - i - 1;
            result[i] = items[index];
        }
        return result;
    }

    public void removeById(int id) {
        repository.removeById(id);
    }


    public TicketOffer[] findAll(String from, String to) {
        TicketOffer[] items = repository.findAll();
        TicketOffer[] result = new TicketOffer[0];
        for (TicketOffer item : items) {
            if (item.getFrom().equals(from) && item.getTo().equals(to)) {
                TicketOffer[] tmp = new TicketOffer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }


    public TicketOffer findById(int id) {
        return repository.findById(id);
    }
}





