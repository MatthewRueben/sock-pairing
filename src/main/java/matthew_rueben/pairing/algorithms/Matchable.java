package matthew_rueben.pairing.algorithms;

/**
 * Matchable with objects of type T
 * (including child types of T).
 *
 * @author Matt Rueben
 */
public interface Matchable<T>
{
    boolean matches(T candidate);
}