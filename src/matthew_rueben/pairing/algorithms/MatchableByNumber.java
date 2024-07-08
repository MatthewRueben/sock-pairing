package matthew_rueben.pairing.algorithms;

/**
 * @author Matt Rueben
 */
public class MatchableByNumber implements Matchable<MatchableByNumber>
{
    public final int ID;
    
    public MatchableByNumber(int id) {
        this.ID = id;
    }
    
    public final boolean checkIfMatches(MatchableByNumber candidate) {
        if (this.ID == candidate.ID) {return true;}
        else return false;
    }
    
    public static void test() {
        MatchableByNumber sock1a = new MatchableByNumber(1);
        MatchableByNumber sock1b = new MatchableByNumber(1);
        MatchableByNumber sock2a = new MatchableByNumber(2);
        System.out.println(sock1a.checkIfMatches(sock1b));
        System.out.println(sock1b.checkIfMatches(sock1a));
        System.out.println(sock1a.checkIfMatches(sock2a));

    }
}
