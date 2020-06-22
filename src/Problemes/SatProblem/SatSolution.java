package Problemes.SatProblem;

import AbstractProblems.Problem;
import AbstractProblems.Solution;

import java.util.BitSet;
import java.util.Random;


public class SatSolution extends Solution<BitSet ,Instance> {


    public SatSolution(SatProblem problem) {
        super(new BitSet(problem.SOLUTION_SIZE) , problem);
    }

    @Override
    public Solution<BitSet, Instance> getEmptySolution() {
        return new SatSolution((SatProblem) getProblem());
    }

    @Override
    public Solution<BitSet, Instance> randomSolution() {
        SatSolution s = new SatSolution((SatProblem) getProblem()) ;
        for (int i = 0; i < getProblem().SOLUTION_SIZE; i++)
            if (new Random().nextBoolean()) s.getValue().flip(i);
        return s;
    }

    @Override
    public boolean isSolution() {
        return getProblem().getInstance().getNbClauses() == Fitness() ;
    }

    /**
     * fitness of sat is nb of sat clauses
     * @return fitness value to MAXIMIZE
     */
    @Override
    public int Fitness() {
        BitSet satisfied = new BitSet(getProblem().getInstance().getNbClauses());
        satisfied.clear();
        for (int i = 0; i < getProblem().SOLUTION_SIZE; i++)
            satisfied.or(getProblem().getInstance().getLiterals()[getValue().get(i) ? 1 : 0][i]);
        return satisfied.cardinality();
    }

    /**
     * hamming distance
     * @param s
     * @return
     */
    @Override
    public int Distance(Solution<BitSet, Instance> s) {
        BitSet distance = copy().getValue()  ;
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

    @Override
    public SatSolution copy() {
        SatSolution copyBS = new SatSolution((SatProblem) getProblem());
        copyBS.getValue().clear();
        copyBS.getValue().or(getValue());
        return copyBS;
    }

}
