package AbstractProblems;

public abstract class Solution<S, T> {

    private Problem<T> problem ;
    private S solution ;

    public Solution(S solution , Problem<T> problem ) {
        setValue(solution);
        setProblem(problem);
    }

    abstract public Solution<S, T> getEmptySolution() ;

    abstract public Solution<S, T> randomSolution();

    abstract public boolean isSolution();

    abstract public int Fitness() ;

    abstract public int Distance(Solution<S, T> s) ;

    abstract public void update(int position)  ;

    abstract public Solution<S, T> copy() ;

    public S getValue(){
        return solution ;
    }

    public void setValue(S solution){
        this.solution =solution ;
    }

    public Problem<T> getProblem() {
        return problem;
    }

    public void setProblem(Problem<T> problem) {
        this.problem = problem;
    }

}













// TRACH
/*
// Functions that define a solution // You can Steal override methods
@FunctionalInterface
public interface funGetRandom {
    Solution getRandom(Problem p);
}
@FunctionalInterface
public interface funIsSolution {
    boolean isSolution(Solution s, Problem p);
}
@FunctionalInterface
public interface funFitness {
    int fitness(Solution s , Problem p);
}
@FunctionalInterface
public interface funDistance {
    int Distance(Solution s1 , Solution s2);
}

    private S solution ;

    // Functions
    private funGetRandom FUNC_getRandom ;
    private funIsSolution FUNC_isSolution ;
    private funFitness FUNC_fitness ;
    private funDistance FUNC_Distance ;

    public
    Solution(S solution,
             funGetRandom fun_getRandom,
             funIsSolution fun_isSolution,
             funFitness fun_fitness) {
        this.solution = solution;
        this.FUNC_getRandom = fun_getRandom;
        this.FUNC_isSolution = fun_isSolution;
        this.FUNC_fitness = fun_fitness;
    }
public void setFUNC_getRandom(funGetRandom FUNC_getRandom) {
    this.FUNC_getRandom = FUNC_getRandom;
}

    public void setFUNC_isSolution(funIsSolution FUNC_isSolution) {
        this.FUNC_isSolution = FUNC_isSolution;
    }

    public void setFUNC_fitness(funFitness FUNC_fitness) {
        this.FUNC_fitness = FUNC_fitness;
    }

    public void setFUNC_Distance(funDistance FUNC_Distance) {
        this.FUNC_Distance = FUNC_Distance;
    }*/
