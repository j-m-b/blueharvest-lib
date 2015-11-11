package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public interface skeleton<T> {

    //public abstract <S> T get(S s);
    T get(Object o);

    boolean insert(T t);

    boolean update(T t);

    boolean delete(java.util.UUID id);

}
