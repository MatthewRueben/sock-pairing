package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AsYouGoPairerTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    void pairs3PairsWith1Adjacent()
    {
        PairingAlgorithm pairer = new AsYouGoPairer();

        List<MatchableByNumber> threePairs = new ArrayList<>(6);
        threePairs.add(MatchableByNumber.createWithID(1));
        threePairs.add(MatchableByNumber.createWithID(1));
        threePairs.add(MatchableByNumber.createWithID(2));
        threePairs.add(MatchableByNumber.createWithID(3));
        threePairs.add(MatchableByNumber.createWithID(2));
        threePairs.add(MatchableByNumber.createWithID(3));

        ComparisonsCounts counts = pairer.pair(threePairs);
        logger.info("Total comparisons: {}", counts.total);

        assertEquals(4, counts.total);
    }

    @Test
    void throwsWhenNoMatchRemains()
    {
        PairingAlgorithm pairer = new AsYouGoPairer();

        List<MatchableByNumber> triplets = new ArrayList<>(6);
        triplets.add(MatchableByNumber.createWithID(1));
        triplets.add(MatchableByNumber.createWithID(1));
        triplets.add(MatchableByNumber.createWithID(1));

        assertThrows(NoMatchRemainingException.class,
                () -> pairer.pair(triplets));
    }
}