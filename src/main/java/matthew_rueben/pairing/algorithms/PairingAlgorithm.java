package matthew_rueben.pairing.algorithms;

import matthew_rueben.pairing.Matchable;

import java.util.List;

/**
 * Able to pair a List of matthew_rueben.pairing.Matchable objects
 * and return the pairing performance in 
 * a matthew_rueben.pairing.algorithms.ComparisonsCounts object.
 *
 * @author Matt Rueben
 */
@FunctionalInterface
public interface PairingAlgorithm
{
    <T extends Matchable<? super T>> ComparisonsCounts pair(List<T> objects)
            throws NoMatchRemainingException;
}
