package AbstractProblems;

/**
 *
 * @param <T> type of problem (list of clauses in SAT for exemple)
 */
public abstract class Problem <T> {

    private T instance ;
    long search_space ;
    public int SOLUTION_SIZE ;

    public Problem(T instance ) {
        this.instance = instance;
    }

    public void setSOLUTION_SIZE(int SOLUTION_SIZE) {
        this.SOLUTION_SIZE = SOLUTION_SIZE;
    }

    public T getInstance() {
        return instance;
    }

}
