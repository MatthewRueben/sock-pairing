package matthew_rueben.pairing.algorithms;

import java.util.List;

/**
 * E.g., for pairing socks:
 * Pulls out all the socks without matching any of
 * them. Then, a sock is picked up and compared with 
 * each other sock in the pile until it is matched.
 * And so on.
 * 
 * @author Matt Rueben
 */
public class AtTheEndPairer implements PairingAlgorithm
{
    public ComparisonsCounts pair(List<Matchable> objects)
        {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.getCounts();
        }
}
