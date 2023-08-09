package creatures;

import huglife.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param clorus
     */
    private int r;
    private int g;
    private int b;
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyDirection = new ArrayDeque<>();
        Deque<Direction> plipDirection = new ArrayDeque<>();

        for (Direction direction : neighbors.keySet()) {
            if (neighbors.get(direction) instanceof Empty) {
                emptyDirection.add(direction);
            }
            if (neighbors.get(direction) instanceof Plip) {
                plipDirection.add(direction);
            }
        }

        /**
         * Rule1: if there are no empty squares, the Clorus will STAY.
         * */
        if (emptyDirection.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        /**
         * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
         * */
        if (plipDirection.size() != 0) {
            return new Action(Action.ActionType.ATTACK,randomEntry(plipDirection));
        }

        /**
         * Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
         * */
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyDirection));
        }

        return new Action(Action.ActionType.MOVE,randomEntry(emptyDirection));
    }

    @Override
    public Color color() {
        return color(r,g,b);
    }
}
