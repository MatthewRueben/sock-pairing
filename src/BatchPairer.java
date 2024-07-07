import java.util.List;

/**
 * Like the AtTheEndPairer, but pulls batches
 * of N at a time instead of the whole pile.
 *
 * @author Matt Rueben
 */
public class BatchPairer implements PairingAlgorithm
{
    private int batchSize = 5;

    public void setBatchSize(int batchSize) {this.batchSize = batchSize;}
    
    public ComparisonsCounts pair(List<Matchable> objects)
    {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
    }
}
