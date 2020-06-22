package Solvers.PSO;

import AbstractProblems.Problem;
import AbstractProblems.Solution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PSO <T>{
    private Problem<T> problem ;
    private Solution initSolution ;

    public PSO(Problem<T> problem , Solution initSolution) {
        this.problem = problem;
        this.initSolution = initSolution ;
    }

    private class Particle{
        Solution solution, pBest;
        int velocity ;
        Particle() {
            solution = initSolution.randomSolution() ;
            pBest = initSolution.randomSolution() ;
            velocity = ThreadLocalRandom.current().nextInt(problem.SOLUTION_SIZE);
        }
        @Override
        public String toString() {
            return solution.Fitness() + "<->" +pBest.Fitness() +"<->" + velocity ;
        }
    }

    /**
     *
     * @param parms
     * @return
     */
    public void solve( HashMap<String, Double> parms){
        int    numParticles  = parms.get("numParticles").intValue() ;
        double      c1       = parms.get("c1") ;
        double      c2       = parms.get("c2") ;
        double   inWeight    = parms.get("inWeight") ;
        int       maxIter    = parms.get("maxIter").intValue() ;
        // mesur performance
        LinkedList<Integer> evolution = new LinkedList<>() ;

        Solution gBest = initSolution.getEmptySolution() ;

        LinkedList<Particle> particles = new LinkedList<>();

        for (int i = 0; i < numParticles; i++){
            Particle p = (new Particle());
            if (gBest.Fitness() < p.solution.Fitness())         //
                gBest = p.solution ;                                         // Update gBest
            particles.add(p) ;
        }

        long start_time = System.currentTimeMillis();
        long wait_time = 120000; // MaxTime 2 min
        long end_time = start_time + wait_time;

        for (int i = 0; i < maxIter ; i++) {
            if (System.currentTimeMillis() > end_time) break;
            //TODO check runtime
            for (Particle p : particles) {
                // Update Velocity
                p.velocity = (int) (
                        inWeight * p.velocity +                                // internal
                        c1  *  Math.random()  * p.pBest.Distance(p.solution) + // cognitive
                        c2  *  Math.random()  * gBest.Distance(p.solution)     // social
                );
                // Update Position
                List<Integer> range =  IntStream.range(0,problem.SOLUTION_SIZE)
                                                .boxed().collect(Collectors.toList());
                Collections.shuffle(range);

                for (int j = 0; j < p.velocity % problem.SOLUTION_SIZE; j++)
                    p.solution.update(range.get(j));
                //
                int particalFitness = p.solution.Fitness() ;
                //Update pBest
                if (p.pBest.Fitness() < particalFitness)
                    p.pBest = p.solution ;
                // Update Gbest
                if (gBest.Fitness() < particalFitness)
                    gBest = p.solution.copy() ;
            }
            int t = gBest.Fitness() ;
            if (i%(maxIter/10) == 0)  evolution.addLast(t); ;
//            if (gBest.isSolution(problem)) return gBest ;
        }
        // ------ Deliver results in JSON
        System.out.println(
            "{" +'\n'+
            " \"evol\" : "+ evolution + ",\n"+
            " \"sol\" : " + " \"" + gBest + "\"" + '\n'+
            " \"fitness\" : " +  gBest.Fitness()   +'\n'+
            "} "
        );

    }

}
