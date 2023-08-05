import java.util.Iterator;
public class OfficeHoursQueue implements Iterable<OHRequest> {
    OHRequest startQueue;
    public OfficeHoursQueue(OHRequest queue) {
        startQueue = queue;
    }

    public static void main(String[] args) {
        OHRequest s1 = new OHRequest("Failing my test for get in arrayDeque, NPE", "Pam", null);
        OHRequest s2 = new OHRequest("conceptual: what is dynamic method selection", "Michael", s1);
        OHRequest s3 = new OHRequest("git: what does checkout do.", "Jim", s2);
        OHRequest s4 = new OHRequest("help", "Dwight", s3);
        OHRequest s5 = new OHRequest("debugging get(i)", "Creed", s4);
        //OfficeHoursQueue ofq = new OfficeHoursQueue(s5);

        OHRequest o1 = new OHRequest("help me", "Pam", null);
        OHRequest o2 = new OHRequest("im bored", "Michael", o1);
        OHRequest o3 = new OHRequest("thank u", "Jim", o2);
        OHRequest o4 = new OHRequest("thank u", "Dwight", o3);
        OfficeHoursQueue ofq = new OfficeHoursQueue(o4);

        for (OHRequest i : ofq) {
            System.out.println(i.name);
        }
    }

    @Override
    public Iterator<OHRequest> iterator() {
        return new TYIterator(startQueue);
    }
}