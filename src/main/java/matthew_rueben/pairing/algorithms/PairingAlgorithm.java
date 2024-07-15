package matthew_rueben.pairing.algorithms;

import java.util.List;

/**
 * Able to pair a List of matthew_rueben.pairing.algorithms.Matchable objects
 * and return the pairing performance in 
 * a matthew_rueben.pairing.algorithms.ComparisonsCounts object.
 *
 * @author Matt Rueben
 */
@FunctionalInterface
public interface PairingAlgorithm
{
    ComparisonsCounts pair(List<Matchable> objects)
            throws NoMatchRemainingException;
}
