package matthew_rueben.pairing.performance;

import matthew_rueben.pairing.Matchable;
import matthew_rueben.pairing.MatchableByNumber;
import matthew_rueben.pairing.algorithms.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import static com.google.common.collect.Collections2.orderedPermutations;

import matthew_rueben.pairing.performance.ExportingToCSV;

/**
 * Tests pairing algorithms.
 *
 * @author Matt Rueben
 */
public class PairerTester<CM extends Comparable<CM> & Matchable<CM>> // CM is for Comparable and Matchable.
{
    private static final Logger logger = LogManager.getLogger();

    private final Matchable.Pool<CM> pool;

    private int numOfPairsInPool;

    private List<CM> selectedPairs;

    private Object[][] resultsTable;
    public String resultsFilePath;

    PairerTester(Matchable.Pool<CM> pool)
    {   this.pool = pool;
        this.numOfPairsInPool = 0;
    }

    private void addPairToPool()
    {   logger.info("Adding a pair to the pool.");
        CM instance1of2 = this.pool.makeNewUnmatchedInstance();
        this.pool.makeNewInstanceThatMatches(instance1of2);
        this.numOfPairsInPool++;
    }

    private void ensurePoolHasAtLeastThisManyPairs(int numOfPairsNeeded)
    {   while (this.numOfPairsInPool < numOfPairsNeeded)
        {   this.addPairToPool(); }
    }

    public void selectNumOfPairs(int numPairs)
    {   this.ensurePoolHasAtLeastThisManyPairs(numPairs);
        this.selectedPairs = this.pool.getAllCurrentInstances().subList(0, numPairs*2);
    }

    // Might want to pass in *all* the pairers at once so we don't have to do permutations multiple times.
    public void testAlgorithmOnSelectedPairs(final PairingAlgorithm pairer)
            throws IOException
    {   int numOfPairs = this.selectedPairs.size()/2;
        logger.info("Testing {} on {} pairs.", pairer, numOfPairs);

        int orderNumber = 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.resultsFilePath, true))) // BufferedWriter because there will be many small writes.
        {   for (List<CM> reorderedPairables : orderedPermutations(this.selectedPairs)) // That List is actually an ImmutableList.
            {
                ComparisonsCounts comparisons = pairer.pair(new ArrayList<>(reorderedPairables)); // Making a copy because orderedPermutations() actually returns an ImmutableList.
                //logger.info("Comparisons: {}.", comparisons.total);
                ExportingToCSV.writeRowToCSV(writer, new Object[]{pairer, numOfPairs, orderNumber, comparisons.total});
                orderNumber++;
            }
        }
    }
    
    public static void main(String[] args)
            throws IOException
    {
        PairerTester<MatchableByNumber> tester = new PairerTester<>(new MatchableByNumber.Pool()); // Anonymous pool so people don't access it directly here in main().

        int maxNumOfPairs = 5;
        PairingAlgorithm[] algorithmsToTest = {new AsYouGoPairer(), new AtTheEndPairer()}; // BatchPairer.makeBatchPairerWithBatchSize(5)

        String pathName = "/Users/matth/Downloads/";
        String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        dateAndTime = dateAndTime.replace('T','_').replace(':',' ').replace(".","__");
        tester.resultsFilePath = pathName + "sock-pairing-results" + "@" + dateAndTime + ".csv";

        // Write header to results file.
        try (FileWriter writer = new FileWriter(tester.resultsFilePath, true))
        {   ExportingToCSV.writeRowToCSV(writer, new Object[]{"Algorithm", "Number of Pairs", "Order Number", "Comparisons"});   }

        for (int numOfPairs = 1; numOfPairs <= maxNumOfPairs; numOfPairs++)
        {
            tester.selectNumOfPairs(numOfPairs);

            for (PairingAlgorithm algorithm : algorithmsToTest)
            {   tester.testAlgorithmOnSelectedPairs(algorithm);   }
        }
    }
}