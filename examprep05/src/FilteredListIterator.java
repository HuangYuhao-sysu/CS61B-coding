import java.util.Iterator;
import java.util.List;

public class FilteredListIterator<T> implements Iterator<T> {

    List<T> L;
    int wizPos;
    Predicate<T> filter;

    public FilteredListIterator(List L, Predicate filter) {
        this.L = L;
        this.filter = filter;
        this.wizPos = 0;
    }


    @Override
    public boolean hasNext() {
        while (wizPos < L.size() && !filter.test(L.get(wizPos))) {
            wizPos += 1;
        }
        return wizPos < L.size();
    }

    @Override
    public T next() {
        T returnItem = L.get(wizPos);
        wizPos += 1;
        return returnItem;
    }
}
