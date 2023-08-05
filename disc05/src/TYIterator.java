import java.util.Iterator;

/*
    If the current item’s description contains the words \thank u," it should skip the next item on the queue.
 **/
public class TYIterator implements Iterator<OHRequest> {
    OHRequest curr;
    public TYIterator(OHRequest queue) {
        curr = queue;
    }


    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }

    @Override
    public boolean hasNext() {
        if (curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        return curr != null;
    }

    @Override
    public OHRequest next() {
        OHRequest returnItem = curr;
        if (curr.description.contains("thank u")) {
            curr = curr.next.next;
            return returnItem;
        }
        curr = curr.next;
        return returnItem;
    }
}
