import java.util.List;

/**
 * Able to pair a List of Matchable objects 
 * and return the pairing performance in 
 * a ComparisonsCounts object.
 *
 * @author Matt Rueben
 */
@FunctionalInterface
interface PairingAlgorithm
{
    ComparisonsCounts pair(List<Matchable> objects);
}
