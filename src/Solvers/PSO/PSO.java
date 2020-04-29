package Solvers.PSO;

import AbstractProblems.Problem;
import AbstractProblems.Solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class PSO <T, S>{
    private Problem<T, S> problem ;

    public PSO(Problem<T, S> problem) {
        this.problem = problem;
    }

    private class Particle{
        Solution<S, T> solution, pBest;
        int velocity ;

        Particle(Problem<T, S> problem) {
            solution = pBest = problem.getEmptySolution().randomSolution(problem) ;
            velocity = ThreadLocalRandom.current().nextInt(problem.SOLUTION_SIZE);
        }

    }

    /**
     *
     * @param parms
     * @return
     */
    public
    Solution<S, T>
    PSOSolve( HashMap<String, Double> parms){
        int    numParticles  = parms.get("numParticles").intValue() ;
        double      c1       = parms.get("c1") ;
        double      c2       = parms.get("c2") ;
        double   inWeight    = parms.get("inWeight") ;
        int       maxIter    = parms.get("maxIter").intValue() ;

        Solution<S, T> gBest = problem.getEmptySolution() ;

        LinkedList<Particle> particles = new LinkedList<>();

        for (int i = 0; i < numParticles; i++){
            Particle p = (new Particle(problem));
            if (gBest.Fitness(problem) < p.solution.Fitness(problem))         //
                gBest = p.solution ;                                         // Update gBest
            particles.add(p) ;
        }
        for (int i = 0; i < maxIter ; i++) {
            //TODO check runtime
            for (Particle p : particles) {
                // Update Velocity
                p.velocity = (int) (
                        inWeight * p.velocity +                                // internal
                        c1  *  Math.random()  * p.pBest.Distance(p.solution) + // cognitive
                        c2  *  Math.random()  * gBest.Distance(p.solution)     // social
                ) ;
                // Update Position
                for (int j = 0; j < p.velocity; j++)
                    p.solution.update(new Random().nextInt(problem.SOLUTION_SIZE)) ; //TODO CHANGE

                //Update pBest
                if (p.pBest.Fitness(problem) < p.solution.Fitness(problem))
                    p.pBest = p.solution ;
            }
            for (Particle p : particles)
                if (gBest.Fitness(problem) < p.solution.Fitness(problem))
                    gBest = p.solution ;

            if (gBest.isSolution(problem)) return gBest ;

        }

        return gBest ;
    }

}
