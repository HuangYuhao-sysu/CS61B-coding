import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    /**
     * Test initial state of UnionFind.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testBasic() {
        UnionFind uf = new UnionFind(16);
        for (int i = 0; i < 16; i +=1 ) {
            assertEquals(uf.sizeOf(i),1);
        }
        for (int i = 0; i < 16; i +=1 ) {
            assertEquals(uf.parent(i),-1);
        }
        for (int i = 0; i < 16; i += 1) {
            for (int j = i + 1; j < 16; j += 1) {
                assertEquals(uf.connected(i,j),false);
            }
        }
        for (int i = 0; i < 16; i +=1 ) {
            assertEquals(uf.find(i),i);
        }
        uf.sizeOf(16);
    }

    @Test
    public void testAll() {
        UnionFind uf = new UnionFind(16);
        uf.union(0,1);
        uf.union(1,2);
        assertEquals(uf.sizeOf(2),3);
        assertEquals(uf.find(2),1);
        assertEquals(uf.connected(0,2),true);

        uf.union(3,4);
        uf.union(3,5);
        assertEquals(uf.sizeOf(3),3);
        assertEquals(uf.find(4),4);
        assertEquals(uf.connected(3,5),true);

        uf.union(0,5);
        assertEquals(uf.parent(2),1);
        assertEquals(uf.sizeOf(2),6);
        assertEquals(uf.parent(2),4);
        assertEquals(uf.connected(1,5),true);
        assertEquals(uf.parent(1),4);

        uf.union(6,7);
        uf.union(6,3);
        assertEquals(uf.parent(7),4);
        assertEquals(uf.connected(6,2),true);
        uf.find(6);
        assertEquals(uf.parent(6),4);
    }
}
