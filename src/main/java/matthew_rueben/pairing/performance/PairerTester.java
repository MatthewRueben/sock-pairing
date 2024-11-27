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
import java.util.ListIterator;

import static com.google.common.collect.Collections2.orderedPermutations;

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
    public String resultsFilename;


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

    public void setResultsFilename()
    {   String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        dateAndTime = dateAndTime.replace('T','_').replace(':',' ').replace(".","__");
        this.resultsFilename = "pairing-results" + "@" + dateAndTime + ".csv";
    }

    public void testAlgorithmsOnSelectedPairs(
            final List<PairingAlgorithm> pairers) // There's no easy annotation to ensure that all elements are non-null.
            throws IOException
    {
        List<Object> currentResultsRow = new ArrayList<>();
        int firstComparisonIndex = 0; //

        int numOfPairs = this.selectedPairs.size()/2;
        logger.info("Testing {} on {} pairs.", pairers, numOfPairs);
        currentResultsRow.add(numOfPairs);
        firstComparisonIndex++;

        class Int
        {   private int eger;
            private Int(int eger) {this.eger = eger;}
            private void increment() {this.eger++;}
            @Override public String toString() {return String.valueOf(this.eger);}
        }
        Int orderNumber = new Int(1);
        currentResultsRow.add(orderNumber);
        firstComparisonIndex++;

        for (PairingAlgorithm ignored : pairers)
            currentResultsRow.add(0); // Placeholder element.

        ListIterator<Object> currentResultsComparisons;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.resultsFilename, true))) // BufferedWriter because there will be many small writes.
        {   for (List<CM> reorderedPairables : orderedPermutations(this.selectedPairs)) // That List is actually an ImmutableList.
            {
                currentResultsComparisons = currentResultsRow.listIterator(firstComparisonIndex);

                for (PairingAlgorithm pairer : pairers)
                {
                    ComparisonsCounts comparisons = pairer.pair(new ArrayList<>(reorderedPairables)); // Making a copy because orderedPermutations() actually returns an ImmutableList.
                    //logger.info("Comparisons by {}: {}.", pairer, comparisons.total);
                    currentResultsComparisons.next();
                    currentResultsComparisons.set(comparisons.total);
                }
                ExportingToCSV.writeRowToCSV(writer, currentResultsRow.toArray());
                orderNumber.increment();
            }
        }
    }
    
    public static void main(String[] args)
            throws IOException
    {
        PairerTester<MatchableByNumber> tester = new PairerTester<>(new MatchableByNumber.Pool()); // Anonymous pool so people don't access it directly here in main().

        int maxNumOfPairs = 5;

        List<PairingAlgorithm> algorithmsToTest = new ArrayList<>();
        algorithmsToTest.add(new AsYouGoPairer());
        algorithmsToTest.add(new AtTheEndPairer());

        tester.setResultsFilename();

        List<String> headers = new ArrayList<>();
        headers.add("Number of Pairs");
        headers.add("Order Number");
        headers.addAll(
                algorithmsToTest.stream()
                .map(algorithm -> "Comparisons by " + algorithm.getClass().getSimpleName())
                .toList());

        // Write header to results file.
        try (FileWriter writer = new FileWriter(tester.resultsFilename, true))
        {   ExportingToCSV.writeRowToCSV(writer, headers.toArray());   }

        for (int numOfPairs = 1; numOfPairs <= maxNumOfPairs; numOfPairs++)
        {
            tester.selectNumOfPairs(numOfPairs);
            tester.testAlgorithmsOnSelectedPairs(algorithmsToTest);
        }
    }
}