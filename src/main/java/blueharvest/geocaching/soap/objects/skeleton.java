package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public interface skeleton<T> {

    //public abstract <S> T get(S s);
    public abstract T get(Object o);

    public abstract boolean insert(T t);

    public abstract boolean update(T t);

    public abstract boolean delete(java.util.UUID id);

}
