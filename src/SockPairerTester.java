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
    //private MatchesSocks sockMatcher;

    //public SockPairerTester(MatchesSocks sockMatcher)
    //{
    //    this.sockMatcher = sockMatcher;  
    //}
    private static void testAlgorithm(PairingAlgorithm pairer) {
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
        
        pairer.pair(socks);
    }

    public static void main(String[] args)
    {
        /* 
         * Each SockPairerTester object puts one sockMatcher
         * through its paces. Make several Tester 
         * objects, one for each sockMatcher. Each 
         * one will try the sockMatcher on (1) all 
         * permutations of (2) each number of pairs 
         * of socks from 1 to 100. 
         */
        
        //testAYGMatcher.sweepByNumPairs(minNumPairs, maxNumPairs);
        //testAYGMatcher.test(numPairs);

        testAlgorithm(new AsYouGoPairer());
        testAlgorithm(BatchPairer.makeBatchPairerWithBatchSize(5));
        testAlgorithm(new AtTheEndPairer());

        
        
    }
}
