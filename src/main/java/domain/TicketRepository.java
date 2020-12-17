package domain;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private static Map<Movie, Integer> tickets = new HashMap<>();

    public static void addTicket(Movie movie, Integer people){
        if(tickets.containsKey(movie)){
            people+=tickets.get(movie);
            tickets.remove(movie);
        }
        tickets.put(movie, people);
    }
}
