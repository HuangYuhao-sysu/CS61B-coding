import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    PriorityQueue<FlightTime> minPQ;
    ArrayList<FlightTime> timeList;
    Comparator<FlightTime> FlightComparator = Comparator.comparingInt(i -> i.time());

    public class FlightTime {
        int time;
        boolean isStart;
        int passengers;

        FlightTime (int time, boolean isStart, int passengers) {
            this.time = time;
            this.isStart = isStart;
            this.passengers = passengers;
        }

        public int time() {
            return time;
        }

        public boolean isStart() {
            return isStart;
        }

        public int passengers() {
            return passengers;
        }
    }

    public FlightSolver(ArrayList<Flight> flights) {
        timeList = new ArrayList<FlightTime>();
        for (Flight f : flights) {
            timeList.add(new FlightTime(f.startTime(), true, f.passengers));
            timeList.add(new FlightTime(f.endTime(), false, f.passengers));
        }
        minPQ = new PriorityQueue<FlightTime>(FlightComparator);
        for (FlightTime ft : timeList) {
            minPQ.add(ft);
        }
    }

    public int solve() {
        int current = 0;
        int max = 0;
        while (minPQ.peek() != null) {
            FlightTime ft = minPQ.poll();
            if(ft.isStart()) {
                current += ft.passengers();
            } else {
                current -= ft.passengers();
            }
            max = current >= max ? current : max;
        }
        return max;
    }
}
