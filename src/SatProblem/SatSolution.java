package SatProblem;

import Problems.Problem;
import Problems.Solution;

import java.util.BitSet;
import java.util.Random;


public class SatSolution extends Solution<BitSet ,Instance> {


    public SatSolution(BitSet solution) {
        super(solution);
    }

    @Override
    public Solution<BitSet, Instance> randomSolution(Problem<Instance, BitSet> p) {
        SatSolution s = new SatSolution(new BitSet(p.SOLUTION_SIZE)) ;
        for (int i = 0; i < p.SOLUTION_SIZE; i++)
            if (new Random().nextBoolean()) s.getValue().flip(i);
        return s;
    }

    @Override
    public boolean isSolution(Problem<Instance, BitSet> p) {
        return p.getInstance().getNbClauses() - Fitness(p)   == 0;
    }

    /**
     * fitness of sat is nb of sat clauses
     * @param p
     * @return fitness value to MAXIMIZE
     */
    @Override
    public int Fitness(Problem<Instance, BitSet> p) {
        BitSet satisfied = new BitSet(p.SOLUTION_SIZE);
        for (int i = 0; i < getValue().length(); i++)
                satisfied.or(p.getInstance().getLiterals()[getValue().get(i) ? 1 : 0][i]);
        return satisfied.cardinality();
    }

    /**
     * hamming distance
     * @param s
     * @return
     */
    @Override
    public int Distance(Solution<BitSet, Instance> s) {
        BitSet distance = copy().getValue() ;
        distance.xor(s.getValue());
        return distance.cardinality();
    }

    @Override
    public void update(int position) {
        getValue().flip(position);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getValue().length(); i++)
            s.append(getValue().get(i) ? i + 1 : -(i + 1)).append(" ");
        return String.valueOf(s);
    }

    private SatSolution copy() {
        BitSet copyBS = new BitSet(getValue().length()) ;
        copyBS.or(getValue());
        return new SatSolution(copyBS);
    }
}
