package matthew_rueben.pairing.performance;

import matthew_rueben.pairing.algorithms.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

import static com.google.common.collect.Collections2.orderedPermutations;

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

    // TO CHANGE: will want to pass in *all* the pairers at once so we don't have to do permutations multiple times.
    public void testAlgorithm(PairingAlgorithm pairer)
    {
        logger.info("Testing {}.", pairer);

        int numPairs = this.maxNumOfPairs; // Later will add sweep functionality, from 1 to max.
        List<MatchableByNumber> socks = new ArrayList<>(numPairs*2);
        for (int pairID = 1; pairID <= numPairs; pairID++) {
            socks.add(new MatchableByNumber(pairID)); // First sock in pair.
            socks.add(new MatchableByNumber(pairID)); // Second sock in pair.
        }

        for (List<MatchableByNumber> sockSequence : orderedPermutations(socks))
        {
            List<MatchableByNumber> copyOfSockSequence = new ArrayList<>(numPairs*2);
            for (MatchableByNumber sock : sockSequence)
            {
                copyOfSockSequence.add(new MatchableByNumber(sock.ID));
            }
            logger.debug(copyOfSockSequence);
            ComparisonsCounts comparisons = pairer.pair(copyOfSockSequence);
            logger.info("Comparisons: {}.", comparisons.total);
        }
    }

    public static void main(String[] args)
    {
        SockPairerTester tester = new SockPairerTester();
        tester.setMaxNumOfPairsTo(3);

        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);

        tester.testAlgorithm(new AsYouGoPairer());
        //tester.testAlgorithm(BatchPairer.makeBatchPairerWithBatchSize(5));
        tester.testAlgorithm(new AtTheEndPairer());
    }
}