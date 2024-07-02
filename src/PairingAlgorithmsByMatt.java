import java.util.List;

/**
 * Each assumes that no objects will be left without a pair, 
 * but does not assume that each object can only pair
 * with one other object in the List (e.g., there could be
 * four objects A,B,C,D that could all be matches for each 
 * other -- i.e., AB,CD; AC,BD; or AD,BC could all be valid
 * pairings). 
 *
 * @author Matt Rueben
 */
public enum PairingAlgorithmsByMatt implements PairingAlgorithm
{
    AS_YOU_GO {
        /**
         * E.g., for pairing socks:
         * Pulls socks out of the dryer one by one. Each 
         * time a sock is pulled out, it is compared 
         * with all currently unpaired socks.
         */
        public ComparisonsCounts pair(List<Matchable> objects)
        {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
        }
    },

    BATCH {
        public ComparisonsCounts pair(List<Matchable> objects)
        {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
        }
    },

    AT_THE_END {
        /**
         * E.g., for pairing socks:
         * Pulls out all the socks without matching any of
         * them. Then, a sock is picked up and compared with 
         * each other sock in the pile until it is matched.
         * And so on.
         */
    
        public ComparisonsCounts pair(List<Matchable> objects)
        {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
        }
    };

}
