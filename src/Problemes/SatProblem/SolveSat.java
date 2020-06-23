package Problemes.SatProblem;

import Solvers.BSO.Bso;
import Solvers.GA.GAAlgorithm;
import Solvers.GA.GAClauses;
import Solvers.GA.GASolution;
import Solvers.PSO.PSO;

import java.io.IOException;
import java.util.HashMap;


public class SolveSat {

    public static void main(String[] args) throws IOException {

        SatProblem satProblem  ;

        switch (args[0]) {
            case "-parse" :
                satProblem = new SatProblem(Instance.fromDIMACS(args[1])) ;
                System.out.println(satProblem.getInstance().getNbVars() + ";"
                        + satProblem.getInstance().getNbClauses());
                break;

            /*
                -solvePSO /home/oussama/IdeaProjects/SatHeuristicSolver/src/Problemes/SatProblem/Benchmarks/uf75-01.cnf
                5 1000 90 0.9 1.5 1.4
             */
            case "-solvePSO" :
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

            /*
                -solveBSO /home/oussama/IdeaProjects/SatHeuristicSolver/src/Problemes/SatProblem/Benchmarks/uf75-01.cnf
                5 1000 15 15 11 10
                        maxIter = 1000;         //max iteration
                        maxLocalSearch = 15;    //max local search
                        kbee = 15;              //number of bees
                        flip = 11;              //flip
                        maxChance = 10;         // max chances
             */
            case "-solveBSO" :
                System.out.println("[");

                Bso BSO = new Bso(Integer.parseInt(args[3]) ,Integer.parseInt(args[4]),
                        Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]),
                        Instance.fromDIMACS(args[1]));

                for (int attempt = 0; attempt < Integer.parseInt(args[2]); attempt++) {
                    if (attempt != 0) System.out.println(" , ");
                    SatSolution b_sol = BSO.Search() ;
                    System.out.println(
                            "{" +'\n'+
                                    " \"evol\" : "+ BSO.evolution + ",\n"+
                                    " \"sol\" : " + " \"" + b_sol + "\"," + '\n'+
                                    " \"fitness\" : " +  b_sol.Fitness()   +'\n'+
                                    "} "
                    );
                }
                System.out.println("]\n");
                break;



           /*
                -solveGA /home/oussama/IdeaProjects/SatHeuristicSolver/src/Problemes/SatProblem/Benchmarks/uf75-01.cnf
                5 1000 20 100 20
                int maxIterations,
                int populationSize,
                int crossoverRate,
                int mutationRate,
             */
            case "-solveGA" :
                System.out.println("[");

                GAClauses cls = new GAClauses(args[1]) ;

                for (int attempt = 0; attempt < Integer.parseInt(args[2]); attempt++) {
                    if (attempt != 0) System.out.println(" , ");
                    GAAlgorithm.searchGA(cls , Integer.parseInt(args[4]),Integer.parseInt(args[5])
                            ,Integer.parseInt(args[6]) ,Integer.parseInt(args[3]), 120000) ;
                }
                System.out.println("]\n");
                break;
        }
    }
}
