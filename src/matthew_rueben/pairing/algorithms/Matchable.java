package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public interface Matchable<T>
{
    boolean matches(T candidate);
}