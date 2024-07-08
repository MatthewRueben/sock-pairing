package matthew_rueben.pairing.algorithms;

import java.util.List;

/**
 * E.g., for pairing socks:
 * Pulls socks out of the dryer one by one. Each 
 * time a sock is pulled out, it is compared 
 * with all currently unpaired socks.   
 * 
 * @author Matt Rueben
 */
public class AsYouGoPairer implements PairingAlgorithm
{
    @Override
    public ComparisonsCounts pair(List<Matchable> objects)
    {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
    }
}
