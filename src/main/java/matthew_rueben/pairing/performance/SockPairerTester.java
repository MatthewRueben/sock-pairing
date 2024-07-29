package matthew_rueben.pairing.performance;

import matthew_rueben.pairing.algorithms.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
/**
 * Tests pairing algorithms -- e.g., on socks.
 *
 * @author Matt Rueben
 */
public class SockPairerTester
{
    private static Logger logger = LogManager.getLogger();

    private static void testAlgorithm(PairingAlgorithm pairer) {
        int numPairs = 30;
        List<Matchable> socks = new ArrayList<>(numPairs*2);
        for (int pair = 0; pair < numPairs; pair++) {
            socks.add(new MatchableByNumber(pair)); // First sock in pair.
            socks.add(new MatchableByNumber(pair)); // Second sock in pair.
        }
        Collections.shuffle(socks); // Randomize.
        logger.debug(socks);

        ComparisonsCounts counts = pairer.pair(socks);
        logger.info("Total comparisons: {}", counts.total);
    }

    public static void main(String[] args)
    {
        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);

        testAlgorithm(new AsYouGoPairer());
        testAlgorithm(BatchPairer.makeBatchPairerWithBatchSize(5));
        testAlgorithm(new AtTheEndPairer());
    }
}