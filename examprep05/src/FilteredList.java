import java.util.*;

public class FilteredList<T> implements Iterable<T>  {
    List<T> L;
    Predicate<T> filter;
    public FilteredList(List<T> L, Predicate<T> filter){
        this.L = L;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilteredListIterator<T>(L, filter);
    }
}
