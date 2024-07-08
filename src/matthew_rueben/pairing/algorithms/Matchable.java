package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public interface Matchable<T>
{
    boolean checkIfMatches(T candidate);
}

// Just always remember to check within this method whether type of 
// candidate is same as type of current class.

// Use inheritance somehow instead of implementation. 