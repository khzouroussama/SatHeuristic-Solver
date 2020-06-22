import AbstractProblems.Solution;
import Problemes.SatProblem.Instance;
import Problemes.SatProblem.SatProblem;
import Problemes.SatProblem.SatSolution;
import Solvers.PSO.PSO;

import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;


public class testPSO {

    public static void main(String[] args) throws IOException {

        SatProblem satProblem  ;

        switch (args[0]) {
            case "-parse" :
                satProblem = new SatProblem(Instance.fromDIMACS(args[1])) ;
                System.out.println(satProblem.getInstance().getNbVars() + ";"
                        + satProblem.getInstance().getNbClauses());
                break;

            /*
                -solve /home/oussama/IdeaProjects/SatHeuristicSolver/src/Problemes/SatProblem/Benchmarks/uf75-01.cnf
                5 1000 90 0.9 1.5 1.4
             */
            case "-solve" :
                satProblem = new SatProblem(Instance.fromDIMACS(args[1])) ;

                System.out.println("[");
                PSO<Instance> psoSolver = new PSO<>(satProblem , new SatSolution(satProblem)) ;

                for (int attempt = 0; attempt < Integer.parseInt(args[2]); attempt++) {
                    if (attempt != 0) System.out.println(" , ");
                    psoSolver.solve(
                        new HashMap<String, Double>(){{
                            put("maxIter", (double) Integer.parseInt(args[3])) ;
                            put("numParticles", (double) Integer.parseInt(args[4])) ;
                            put("inWeight", Double.parseDouble(args[5])) ;
                            put("c1", Double.parseDouble(args[6])  ) ;
                            put("c2", Double.parseDouble(args[7]) ) ;
                        }}
                    ) ;
                }
                System.out.println("]\n");
            break;
        }


//        System.out.println(satProblem.getInstance().getNbVars() + "// "
//                + satProblem.getInstance().getNbClauses());
//
//        PSO<Instance , SatSolution > psoSolver = new PSO<>(satProblem) ;
//
//        for (int attempt = 0; attempt < 5; attempt++) {
//            Solution<SatSolution, Instance> solution = psoSolver.solve(
//                    new HashMap<String, Double>(){{
//                        put("numParticles", (double) 80) ;
//                        put("maxIter", (double) 10000) ;
//                        put("c1", 1.9  ) ;
//                        put("c2", 1.9 ) ;
//                        put("inWeight", 0.9) ;
//                    }}
//            ) ;
//        }

//            System.out.println("___________________\n  attempt -> " + attempt);
//            System.out.println(solution + "\n Fitness = " + solution.Fitness(satProblem)
//                    +    "\n SatRate = " + (solution.Fitness(satProblem) * 100. /  satProblem.getInstance().getNbClauses() ) + "%"
//            );
//        }
////
//
//        Solution<SatSolution, Instance> solution1 =
//                satProblem.getEmptySolution().randomSolution(satProblem);
//        System.out.println("\n Fitness OF RAndom Solution  -> " + solution1.Fitness(satProblem)
//                +    "\n SatRate = " + (solution1.Fitness(satProblem) * 100 / satProblem.getInstance().getNbClauses() ) + "%"
//        );

    }
}
