package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static Logger logger = LogManager.getLogger();

    public String toString() {return "As-You-Go Pairer";}

    public ComparisonsCounts pair(List<? extends Matchable> pairablesHidden)
            throws NoMatchRemainingException
    {
        ComparisonsCounter comparisonsCounter = new ComparisonsCounter();

        List<Matchable> pairablesRevealed = new ArrayList<>(pairablesHidden.size());

        while (!pairablesHidden.isEmpty())
        {
            final Matchable pairableNewlyRevealed = pairablesHidden.getFirst();
            pairablesHidden.removeFirst();
            logger.debug("ID of new: {}", pairableNewlyRevealed);

            boolean noMatchYet = true;
            Iterator<Matchable> revealedPairableHandler = pairablesRevealed.iterator(); // Iterator always starts at beginning of List.
            while (noMatchYet && revealedPairableHandler.hasNext())
            {
                final Matchable pairablePreviouslyRevealed = revealedPairableHandler.next();
                logger.debug("ID of old: {}", pairablePreviouslyRevealed);

                if (pairableNewlyRevealed.matches(pairablePreviouslyRevealed))
                {
                    noMatchYet = false;
                    revealedPairableHandler.remove();
                    logger.debug("Removed old; discarding new.");
                }
                comparisonsCounter.add(1);
            }

            if (noMatchYet) // Either nothing matched or the revealed List was empty.
            {
                pairablesRevealed.add(pairableNewlyRevealed);
            }
        }

        if (!pairablesRevealed.isEmpty())
        {
            String forThesePairables = this.buildExceptionDetails(pairablesRevealed);
            logger.debug("Throwing NoMatchRemainingException {}", forThesePairables);
            throw new NoMatchRemainingException(forThesePairables);
        }
        else
        {
            return comparisonsCounter.getCounts();
        }
    }

    private String buildExceptionDetails(List<Matchable> pairablesRemaining)
    {
        String exceptionDetails;
        if (pairablesRemaining.size() == 1)
        {
            exceptionDetails = "for object with ID: " + pairablesRemaining.getFirst();
        }
        else // More than one was unpaired.
        {
            exceptionDetails = "for objects with IDs: ";
            StringBuilder remainingIDs = new StringBuilder();
            Iterator<Matchable> remainingPairableHandler = pairablesRemaining.iterator();
            remainingIDs.append(remainingPairableHandler.next());

            while (remainingPairableHandler.hasNext()) // If there are more than one remaining pairables.
            {
                remainingIDs.append(", ").append(remainingPairableHandler.next());
            }
            exceptionDetails += remainingIDs;
        }
        return exceptionDetails;
    }
}
