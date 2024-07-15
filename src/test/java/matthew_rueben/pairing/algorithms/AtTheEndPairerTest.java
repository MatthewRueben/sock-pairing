package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AtTheEndPairerTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    void pairs3PairsWith1Adjacent()
    {
        PairingAlgorithm pairer = new AtTheEndPairer();

        List<Matchable> threePairs = new ArrayList<>(6);
        threePairs.add(new MatchableByNumber(1));
        threePairs.add(new MatchableByNumber(1));
        threePairs.add(new MatchableByNumber(2));
        threePairs.add(new MatchableByNumber(3));
        threePairs.add(new MatchableByNumber(2));
        threePairs.add(new MatchableByNumber(3));

        ComparisonsCounts counts = pairer.pair(threePairs);
        logger.info("Total comparisons: {}", counts.total);

        assertEquals(4, counts.total);
    }
}