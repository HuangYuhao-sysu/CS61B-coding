package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{

    // The attribute of every vertex, for algorithm.
    private class Attr {
        double distTo;
        Vertex edgeTo;
        double h;

        public Attr(double distTo, Vertex edgeTo, double h) {
            this.distTo = distTo;
            this.edgeTo = edgeTo;
            this.h = h;
        }
    }

    AStarGraph<Vertex> input;
    HashMap<Vertex, Attr> vertexes;
    ArrayHeapMinPQ<Vertex> fringe;
    Vertex start;
    Vertex end;
    double timeout;
    Stopwatch sw;
    SolverOutcome result;
    List<Vertex> solution;
    double solutionWeight;
    int numStatesExplored;
    double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.input = input;
        this.start = start;
        this.end = end;
        this.timeout = timeout;
        this.result = SolverOutcome.UNSOLVABLE;
        this.numStatesExplored = 0;
        this.sw = new Stopwatch();
        this.vertexes = new HashMap<>();
        this.fringe = new ArrayHeapMinPQ<>();
        initializeMap();
        AStar();
        timeSpent = sw.elapsedTime();
    }

    private void initializeMap() {
        // Put the start vertex.
        double h = input.estimatedDistanceToGoal(start,end);
        vertexes.put(start, new Attr(0,null, h));
        fringe.add(start, vertexes.get(start).distTo + h);
    }

    private void AStar() {
        while (fringe.size() != 0) {
            if (sw.elapsedTime() > timeout) {
                result = SolverOutcome.TIMEOUT;
                solution(result);
                break;
            }
            Vertex smallest = fringe.removeSmallest();
            numStatesExplored += 1;
            if (smallest.equals(end)) {
                result = SolverOutcome.SOLVED;
                solution(result);
                break;
            }
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(smallest);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                // Add vertex to vertexes.
                relax(e);
            }
            result = SolverOutcome.UNSOLVABLE;
            solution(result);
        }
    }

    private void relax(WeightedEdge<Vertex> edge) {
        Vertex p = edge.from();
        Vertex q = edge.to();
        double w = edge.weight();
        // Meaning it's distTo == infinity.
        if (!vertexes.containsKey(q)) {
            vertexes.put(q, new Attr(Double.POSITIVE_INFINITY, p, input.estimatedDistanceToGoal(q,end)));
        }
        if (vertexes.get(p).distTo + w < vertexes.get(q).distTo) {
            vertexes.get(q).distTo = vertexes.get(p).distTo + w;
            if (fringe.contains(q)) {
                fringe.changePriority(q, vertexes.get(q).distTo + vertexes.get(q).h);
            } else {
                fringe.add(q, vertexes.get(q).distTo + vertexes.get(q).h);
            }
        }
    }

    private void solution(SolverOutcome s) {
        this.solution = new ArrayList<>();
        if (s.equals(SolverOutcome.UNSOLVABLE) || s.equals(SolverOutcome.TIMEOUT)) {
            this.solutionWeight = 0;
        } else {
            this.solutionWeight = vertexes.get(end).distTo;
            addSolution(end);
        }
    }

    private void addSolution(Vertex v) {
        if (v == null) {
            return;
        } else {
            solution.add(0,v);
            addSolution(vertexes.get(v).edgeTo);
        }
    }

    @Override
    public SolverOutcome outcome() {
        return result;
    }

    @Override
    public List<Vertex> solution() {
        return this.solution;
    }

    @Override
    public double solutionWeight() {
        return this.solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
