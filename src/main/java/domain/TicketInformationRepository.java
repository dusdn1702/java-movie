package domain;

import java.util.HashMap;
import java.util.Map;

public class TicketInformationRepository {
    private static final Map<MovieInformation, Integer> tickets = new HashMap<>();

    public static void addPeople(int people, MovieInformation enrollFlag) {

        if (tickets.containsKey(enrollFlag)) {
            people += tickets.get(enrollFlag);
            tickets.remove(enrollFlag);
        }
        tickets.put(enrollFlag, people);
    }

    public static Map<MovieInformation, Integer> getTickets() {
        return tickets;
    }

    public static long calculatePrice(long point) {
        long price = 0;
        for (MovieInformation movieInformation : tickets.keySet()) {
            price += movieInformation.makePriceWithPeople(tickets.get(movieInformation));
        }
        return price - point;
    }
}
