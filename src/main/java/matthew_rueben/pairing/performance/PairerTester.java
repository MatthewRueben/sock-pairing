package matthew_rueben.pairing.performance;

import matthew_rueben.pairing.algorithms.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

import static com.google.common.collect.Collections2.orderedPermutations;

/**
 * Tests pairing algorithms.
 *
 * @author Matt Rueben
 */
public class PairerTester<CM extends Comparable<CM> & Matchable<CM>> // CM is for Comparable and Matchable.
{
    private static final Logger logger = LogManager.getLogger();

    private final MatchablePool<CM> pool;
    
    PairerTester(MatchablePool<CM> pool) {this.pool = pool;}

    private void getPermutations(List<CM> in)
    {

    }

    private List<CM> makePairs(int numPairs)
    {
        List<CM> pairsList = new ArrayList<>(numPairs*2);
        
        for (int i = 1; i <= numPairs; i++)
        {
            CM instance1of2 = pool.getNewUnmatchedInstance();
            pairsList.add(instance1of2);
            CM instance2of2 = pool.getNewInstanceThatMatches(instance1of2);
            pairsList.add(instance2of2);
        }
        return pairsList;
    }

    // Might want to pass in *all* the pairers at once so we don't have to do permutations multiple times.
    public void testAlgorithm(final PairingAlgorithm pairer, int maxNumOfPairs)
    {
        logger.info("Testing {}.", pairer);

        int numPairs = maxNumOfPairs; // Later will add sweep functionality, from 1 to max.
        List<CM> pairs = makePairs(numPairs);

        for (List<CM> reorderedPairs : orderedPermutations(pairs)) // That List is actually an ImmutableList.
        {
            ComparisonsCounts comparisons = pairer.pair(new ArrayList<>(reorderedPairs)); // Making a copy because orderedPermutations() actually returns an ImmutableList.
            logger.info("Comparisons: {}.", comparisons.total);
        }
        pool.clearAllInstances();
    }
    
    public static void main(String[] args)
    {
        MatchableByNumberPool pool = new MatchableByNumberPool();
        PairerTester<MatchableByNumber> tester = new PairerTester<>(pool);

        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);

        int maxNumOfPairs = 3;

        tester.testAlgorithm(new AsYouGoPairer(), maxNumOfPairs);
        //tester.testAlgorithm(BatchPairer.makeBatchPairerWithBatchSize(5));
        tester.testAlgorithm(new AtTheEndPairer(), maxNumOfPairs);
    }
}