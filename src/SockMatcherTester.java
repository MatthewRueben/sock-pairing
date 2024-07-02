import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/**
 * Tests pairing algorithms -- e.g., on socks.
 *
 * @author Matt Rueben
 */
public class SockMatcherTester
{
    //private MatchesSocks sockMatcher;

    //public SockMatcherTester(MatchesSocks sockMatcher)
    //{
    //    this.sockMatcher = sockMatcher;  
    //}
    private static void testAlgorithm(PairingAlgorithm pairingAlgorithm) {
        int numPairs = 30;
        List<Matchable> socks = new ArrayList<>(numPairs*2);
        for (int pair = 0; pair < numPairs; pair++) {
            socks.add(new MatchableByNumber(pair)); // First sock in pair.
            socks.add(new MatchableByNumber(pair)); // Second sock in pair.
        }
        //System.out.println(socks);
        //System.out.println();
        Collections.shuffle(socks); // Randomize.
        //System.out.println(socks);
        
        pairingAlgorithm.pair(socks);
    }

    public static void main(String[] args)
    {
        /* 
         * Each SockMatcherTester object puts one sockMatcher 
         * through its paces. Make several Tester 
         * objects, one for each sockMatcher. Each 
         * one will try the sockMatcher on (1) all 
         * permutations of (2) each number of pairs 
         * of socks from 1 to 100. 
         */

        
        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);
        //testAYGMatcher.test(numPairs);
        
        
        // DECIDE WHICH OF THESE TWO EXPRESSIONS I PREFER:
        
        // 1. enum of algorithms all implementing an interface
        // Pro: can refer directly to the algorithm. 
        // Pro: might allow for parameterization, e.g., of batch matcher.
        // Con: must name the collection of algorithms.
        // Con: cannot separate algorithms into separate files. 
        //testAlgorithm(PairingAlgorithmsByMatt.AS_YOU_GO);
        //testAlgorithm(PairingAlgorithmsByMatt.BATCH);
        
        // 2. assigning method reference to functional interface
        // Pro: still enforces the method signature via the interface.
        // Con: must refer to the method instead of the algorithm.
        testAlgorithm(AsYouGoPairingAlgorithm::pair);
        testAlgorithm(new BatchPairingAlgorithmWithBatchSize(5)::pair);
        testAlgorithm(AtTheEndPairingAlgorithm::pair);

        
        
    }
}
