package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public class MatchableByNumber implements Matchable<MatchableByNumber>, Comparable<MatchableByNumber>
{
    public final int ID;

    @Override
    public String toString() {return String.valueOf(ID);}
    
    private MatchableByNumber(int id) {
        this.ID = id;
    }

    public static MatchableByNumber createWithID(int id)
    {
        return new MatchableByNumber(id);
    }

    @Override
    public final boolean matches(MatchableByNumber candidate)
    {
        if (this.ID == candidate.ID) return true;
        else return false;
    }

    @Override
    public int compareTo(MatchableByNumber other)
    {
        return Integer.compare(this.ID, other.ID);
    }
}
