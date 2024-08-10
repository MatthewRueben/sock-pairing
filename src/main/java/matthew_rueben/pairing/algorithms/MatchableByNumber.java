package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public class MatchableByNumber implements Matchable<MatchableByNumber>, Comparable<MatchableByNumber>
{
    public final int ID;

    public String toString() {return String.valueOf(ID);}
    
    public MatchableByNumber(int id) {
        this.ID = id;
    }
    
    public final boolean matches(MatchableByNumber candidate)
    {
        if (this.ID == candidate.ID) return true;
        else return false;
    }

    public int compareTo(MatchableByNumber other)
    {
        return Integer.compare(this.ID, other.ID);
    }
}
