package matthew_rueben.pairing.algorithms;

import matthew_rueben.pairing.Matchable;

import java.util.List;

/**
 * Like the matthew_rueben.pairing.algorithms.AtTheEndPairer, but pulls batches
 * of N at a time instead of the whole pile.
 *
 * @author Matt Rueben
 */
public class BatchPairer implements PairingAlgorithm
{
    private final int batchSize;

    private BatchPairer(int batchSize) {this.batchSize = batchSize;}

    public static BatchPairer makeBatchPairerWithBatchSize(int batchSize) {
        return new BatchPairer(batchSize);
    }

    @Override
    public <M extends Matchable<? super M>> ComparisonsCounts pair(List<M> pairables)
    {
        ComparisonsCounter counter = new ComparisonsCounter();
        // matchSocks here.
        // counter.add(1); // Do this each time you make a comparison.
        return counter.getCounts();
    }
}
