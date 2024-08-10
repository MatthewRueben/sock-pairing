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

    private int maxNumOfPairs = 10;

    public void setMaxNumOfPairsTo(int newMax)
    {
        this.maxNumOfPairs = newMax;
    }

    public void testAlgorithm(PairingAlgorithm pairer)
    {
        logger.info("Testing {}.", pairer);
        int numPairs = this.maxNumOfPairs; // Later will add sweep functionality, from 1 to max.
        List<Matchable> socks = new ArrayList<>(numPairs*2);
        for (int pair = 0; pair < numPairs; pair++) {
            socks.add(new MatchableByNumber(pair)); // First sock in pair.
            socks.add(new MatchableByNumber(pair)); // Second sock in pair.
        }
        Collections.shuffle(socks); // Randomize.
        logger.debug(socks);

        ComparisonsCounts counts = pairer.pair(socks);
        logger.info("Total comparisons: {}.", counts.total);
    }

    public static void main(String[] args)
    {
        SockPairerTester tester = new SockPairerTester();
        tester.setMaxNumOfPairsTo(30);

        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);

        tester.testAlgorithm(new AsYouGoPairer());
        //tester.testAlgorithm(BatchPairer.makeBatchPairerWithBatchSize(5));
        tester.testAlgorithm(new AtTheEndPairer());
    }
}