package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 50;

    private static void fillWithOneHex(TETile[][] t, TETile tile, int size, int x, int y){
        fillWithOneHex(t, tile, size, size, size, x, y);
    }

    private static void fillWithOneHex(TETile[][] t, TETile tile, int org, int size, int times, int x, int y) {
        if (times == 0) return;
        /*
        * org = 3
        * size = 3, dis = 5
        * size = 5, dis = 3
        * size = 7, dis = 1
        * */
        fillTwoRowTile(size, tile, t, x, y, org*2 - 1 - (size - org));
        fillWithOneHex(t, tile, org, size + 2, times - 1, x - 1, y + 1);
    }

    private static void fillTwoRowTile(int num, TETile tile, TETile[][] t, int x, int y, int dis) {
        fillNumTile(num, tile, t, x, y);
        fillNumTile(num, tile, t, x, y + dis);
    }

    private static void fillNumTile(int num, TETile tile, TETile[][] t, int x, int y) {
        int width = t.length;
        int height = t[0].length;
        for (int i = 0; i < num; i += 1) {
            if ((x + i < width) && (0 <= x + i) && (y < height) && (0 <= y)) {
                t[x + i][y] = tile;
            }
        }
    }

    private static void fillWithNothing(TETile[][] t) {
        int width = t.length;
        int height = t[0].length;
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                t[i][j] = Tileset.NOTHING;
            }
        }
    }

    private static void fillWithOneColHex(TETile[][] t, int num, int size, int x, int y, int dis) {
        Random r = new Random();
        TETile randomTile;
        for (int i = 0; i < num; i += 1) {
            switch (r.nextInt(0,10)) {
                case 0: randomTile = Tileset.WALL; break;
                case 1: randomTile = Tileset.FLOWER; break;
                case 2: randomTile = Tileset.FLOOR; break;
                case 3: randomTile = Tileset.GRASS; break;
                case 4: randomTile = Tileset.MOUNTAIN; break;
                case 5: randomTile = Tileset.SAND; break;
                case 6: randomTile = Tileset.TREE; break;
                case 7: randomTile = Tileset.WATER; break;
                case 8: randomTile = Tileset.AVATAR; break;
                case 9: randomTile = Tileset.LOCKED_DOOR; break;
                default: randomTile = Tileset.NOTHING;
            }
            fillWithOneHex(t, randomTile, size, x, y + i*dis);
        }
    }

    private static void fillWithTwoColHex(TETile[][] t, int num, int size, int x, int y, int disX, int disY) {
        fillWithOneColHex(t, num, size, x, y, disY);
        fillWithOneColHex(t, num, size, x + disX, y, disY);
    }

    private static void fillWithBigHex(TETile[][] t, int org, int size, int x, int y) {
        if (size == 6) return;
        /*
        * size = 3;
        * disX = 4 + (org - 1)*8;
        * size = 4;
        * dixX = 4 + (org - 1)*8(1-1/2)
        * size = 5;
        * dixX = 4 + (org - 1)*8(1-1)
        * */
        int disX = (int) ((4 + (org - 1)*8)*(1 - (0.5)*(size - 3)));
        int disY = 2*org;
        fillWithTwoColHex(t, size, org, x, y, disX, disY);
        /*
        * org = 2; x += 3; y -= 2;
        * org = 3; x += 5; y -= 3;
        * org = 4; x += 7; y -= 4;
        * org = n; x += 2*org - 1; y -= n;
        * */
        fillWithBigHex(t, org, size + 1, x + 2*org - 1, y - org);
    }

    private static void fillWithBigHex(TETile[][] t, int org, int x, int y) {
        fillWithBigHex(t, org, 3, x, y);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile hexTile[][] = new TETile[WIDTH][HEIGHT];

        fillWithNothing(hexTile);
        fillWithBigHex(hexTile, 5,10, 10);

        ter.renderFrame(hexTile);
    }
}
