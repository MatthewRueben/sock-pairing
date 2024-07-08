package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public class ComparisonsCounter
{
    private int tally = 0;
    
    public void add(int n) {
        this.tally += n;
    }
    
    public ComparisonsCounts count() {
        ComparisonsCounts counts = new ComparisonsCounts();
        counts.total = this.tally;
        return counts;
    }
}
