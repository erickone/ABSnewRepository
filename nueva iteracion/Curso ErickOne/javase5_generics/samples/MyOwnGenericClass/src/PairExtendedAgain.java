public class PairExtendedAgain<F, S, T, F1> extends PairExtended<F, S, T> {
    F1 fourth;

    public PairExtendedAgain(F f, S s, T t,F1 fourth)
    {
        super(f, s, t);
        this.fourth = fourth;
    }
    
    public F1 getFourt(){
        return fourth;
    }
}
