import org.junit.Test;

import java.util.*;

public class IteratorOfIterators<T> implements Iterator<T>{
    List<Iterator<T>> a;
    int index;
    Iterator<T> choose;
    public IteratorOfIterators(List<Iterator<T>> a){
        this.a = a;
        index = 0;
        choose = a.get(0);
    }
    @Override
    public boolean hasNext() {
        if (choose.hasNext()) {
            indexAdd();
            choose = a.get(index);
        }
        return false;
    }

    private void indexAdd() {
        if (index < a.size()) {
            index += 1;
            return;
        }
        index = 0;
    }

    @Override
    public T next() {
        return choose.next();
    }
}
