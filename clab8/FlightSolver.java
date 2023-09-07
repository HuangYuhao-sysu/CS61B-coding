import java.util.ArrayList;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    private PriorityQueue<Flight> pq;
    Comparator<Flight> flightComp = (Flight1, Flight2) -> Flight1.passengers() - Flight2.passengers();

    public FlightSolver(ArrayList<Flight> flights) {
        pq = new PriorityQueue<>(new flightComp);
        for (Flight f : flights) {
            pq.add(f);
        }
    }

    public int solve() {
        /* FIX ME */
        return pq.poll();
    }

}
