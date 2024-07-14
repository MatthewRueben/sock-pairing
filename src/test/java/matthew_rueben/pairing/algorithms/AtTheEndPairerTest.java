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
    void pair()
    {
        PairingAlgorithm pairer = new AtTheEndPairer();

        int numPairs = 5;
        List<Matchable> socks = new ArrayList<>(numPairs*2);
        for (int pair = 0; pair < numPairs; pair++)
        {
            socks.add(new MatchableByNumber(pair)); // First sock in pair.
        }
        for (int pair = 0; pair < numPairs; pair++)
        {
            socks.add(new MatchableByNumber(pair)); // Second sock in pair.
        }
        ComparisonsCounts counts = pairer.pair(socks);
        logger.info(counts.total);
        assertEquals(15, counts.total);
    }
}