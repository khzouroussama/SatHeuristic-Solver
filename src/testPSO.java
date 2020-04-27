import Problems.Problem;
import Problems.Solution;
import SatProblem.Instance;
import SatProblem.SatProblem;
import SatProblem.SatSolution;
import Solvers.PSO.PSO;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;

public class testPSO {

    public static void main(String[] args) throws IOException {

        SatProblem satProblem = new SatProblem(
                Instance.fromDIMACS("/home/oussama/IdeaProjects/SatHeuristicSolver/src/SatProblem/Benchmarks/test.cnf")
        );

        System.out.println(satProblem.getInstance().getNbVars() + "// " + satProblem.getInstance().getNbClauses());
        PSO<Instance , SatSolution > psoSolver = new PSO<>(satProblem) ;

        Solution<SatSolution, Instance> solution = psoSolver.PSOSolve(
                new HashMap<> (){{
                    put("numParticles", (double) 1000) ;
                    put("maxIter", (double) 100) ;
                    put("c1", 1.4  ) ;
                    put("c2", 1.6 ) ;
                    put("inWeight", 0.7 ) ;
                }}
        ) ;


        System.out.println(solution + "\n Fitness = " + solution.Fitness(satProblem));

    }
}
