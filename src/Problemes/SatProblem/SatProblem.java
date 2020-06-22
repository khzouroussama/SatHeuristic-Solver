package Problemes.SatProblem;

import AbstractProblems.Problem;
import AbstractProblems.Solution;

import java.util.BitSet;

public class SatProblem extends Problem<Instance> {

    public SatProblem(Instance instance) {
        super(instance);
        setSOLUTION_SIZE(instance.getNbVars());
    }
}
