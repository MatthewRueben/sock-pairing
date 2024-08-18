package matthew_rueben.pairing.algorithms;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String toString() {return "At-The-End Pairer";}

    @Override
    public <M extends Matchable<? super M>> ComparisonsCounts pair(List<M> pairables) // M is for Matchable.
            throws NoMatchRemainingException
    {
        ComparisonsCounter comparisonsCounter = new ComparisonsCounter();

        Iterator<M> pairableHandler;
        while ((pairableHandler = pairables.iterator()).hasNext()) // Iterator resets to beginning of List every loop.
        {
            final M pairable_1 = pairableHandler.next();
            logger.debug("ID of first: {}", pairable_1);
            pairableHandler.remove(); // Removes pairable_1 from pairables.
            logger.debug("Removed first.");

            boolean noMatchYet = true;
            while (noMatchYet && pairableHandler.hasNext())
            {
                final M pairable_2 = pairableHandler.next();
                logger.debug("ID of second: {}", pairable_2);

                if (pairable_1.matches(pairable_2))
                {
                    pairableHandler.remove(); // Removes pairable_2 from pairables.
                    logger.debug("Removed second.");
                    noMatchYet = false;
                }
                comparisonsCounter.add(1);
            }

            if (noMatchYet)
            {
                throw new NoMatchRemainingException("for object with ID " + pairable_1);
            }
        }
        return comparisonsCounter.getCounts();
    }
}
