package SatProblem;

import Problems.Problem;
import Problems.Solution;

import java.util.BitSet;

public class SatProblem extends Problem<Instance ,SatSolution> {

    public SatProblem(Instance instance) {
        super(instance);
        setSOLUTION_SIZE(instance.getNbVars());
    }

    @Override
    public Solution getEmptySolution() {
        return new SatSolution(new BitSet(SOLUTION_SIZE));
    }
}
