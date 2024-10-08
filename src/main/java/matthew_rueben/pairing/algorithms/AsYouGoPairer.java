package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import matthew_rueben.pairing.Matchable;
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
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String toString() {return "As-You-Go Pairer";}

    @Override
    public <M extends Matchable<? super M>> ComparisonsCounts pair(List<M> pairablesHidden) // M is for Matchable.
            throws NoMatchRemainingException
    {
        ComparisonsCounter comparisonsCounter = new ComparisonsCounter();

        List<M> pairablesRevealed = new ArrayList<>(pairablesHidden.size());

        while (!pairablesHidden.isEmpty())
        {
            final M pairableNewlyRevealed = pairablesHidden.getFirst();
            pairablesHidden.removeFirst();
            logger.debug("ID of new: {}", pairableNewlyRevealed);

            boolean noMatchYet = true;
            Iterator<M> revealedPairableHandler = pairablesRevealed.iterator(); // Iterator always starts at beginning of List.
            while (noMatchYet && revealedPairableHandler.hasNext())
            {
                final M pairablePreviouslyRevealed = revealedPairableHandler.next();
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

    private <M extends Matchable<? super M>> String buildExceptionDetails(List<M> pairablesRemaining)
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
            Iterator<M> remainingPairableHandler = pairablesRemaining.iterator();
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
