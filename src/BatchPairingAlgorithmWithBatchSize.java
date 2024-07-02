import java.util.List;

/**
 * Like the AtTheEndPairingAlgorithm, but pulls batches 
 * of N at a time instead of the whole pile.
 *
 * @author Matt Rueben
 */
public class BatchPairingAlgorithmWithBatchSize
{
    private int batchSize;
    
    BatchPairingAlgorithmWithBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
    
    public ComparisonsCounts pair(List<Matchable> objects)
    {
        ComparisonsCounter counter = new ComparisonsCounter();
        System.out.println(batchSize); // Testing that this compiles.
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.count();
    }
}
