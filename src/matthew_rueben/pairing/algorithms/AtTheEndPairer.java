package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
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
    public ComparisonsCounts pair(List<Matchable> pairables)
    {
        ComparisonsCounter comparisonsCounter = new ComparisonsCounter();

        Iterator<Matchable> pairablesIterator;
        while ((pairablesIterator = pairables.iterator()).hasNext()) // Iterator resets to beginning of List every loop.
        {
            final Matchable pairable_1 = pairablesIterator.next();
            System.out.println("First: " + pairable_1);
            pairablesIterator.remove(); // Removes pairable_1 from pairables.
            System.out.println("Removed first.");

            boolean noMatchYet = true;
            while (pairablesIterator.hasNext() && noMatchYet)
            {
                final Matchable pairable_2 = pairablesIterator.next();
                System.out.println("Second: " + pairable_2);

                if (pairable_1.matches(pairable_2))
                {
                    pairablesIterator.remove(); // Removes pairable_2 from pairables.
                    System.out.println("Removed second.");
                    noMatchYet = false;
                }
                comparisonsCounter.add(1);
            }
        }
        return comparisonsCounter.getCounts();
    }

    public static void main(String[] args)
    {
        PairingAlgorithm pairer = new AtTheEndPairer();

        int numPairs = 5;
        List<Matchable> socks = new ArrayList<>(numPairs*2);
        for (int pair = 0; pair < numPairs; pair++)
        {
            socks.add(new MatchableByNumber(pair)); // First sock in pair.
        }
        for (int pair = 0; pair < numPairs; pair++)
        {
            socks.add(new MatchableByNumber(pair)); // Second sock in pair.
        }
        ComparisonsCounts counts = pairer.pair(socks);
        System.out.println(counts.total);
    }
}
