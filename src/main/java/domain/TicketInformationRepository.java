package domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketInformationRepository {
    private static Map<MovieInformation, Integer> tickets = new HashMap<>();

    public static void addPeople(int people, MovieInformation enrollFlag) {

        if(tickets.containsKey(enrollFlag)){
            people+=tickets.get(enrollFlag);
            tickets.remove(enrollFlag);
        }
        tickets.put(enrollFlag, people);
    }

    public static Map<MovieInformation, Integer> getTickets() {
        return tickets;
    }
}
