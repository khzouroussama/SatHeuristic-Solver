package Problems;

/**
 *
 * @param <T> type of problem (list of clauses in SAT for exemple)
 * @param <S> type of solution
 */
public abstract class Problem <T, S> {

    private T instance ;
    long search_space ;
    public int SOLUTION_SIZE ;

    public Problem(T instance ) {
        this.instance = instance;
    }


    abstract public Solution<S, T> getEmptySolution() ;


    public void setSOLUTION_SIZE(int SOLUTION_SIZE) {
        this.SOLUTION_SIZE = SOLUTION_SIZE;
    }

    public T getInstance() {
        return instance;
    }

}
