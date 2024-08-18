package matthew_rueben.pairing;

import java.util.Collections;

/**
 * Implements Matchable
 * via an integer ID number.
 *
 * Also implements a pool of objects pattern
 * for creating and forgetting instances.
 *
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

    public static class Pool extends Matchable.Pool<MatchableByNumber>
    {
        private int getAnUnmatchedID()
        {
            if (super.instances.isEmpty())
            { return 1; }
            else
            {   MatchableByNumber instanceWithMaxID = Collections.max(super.instances);
                int maxID = instanceWithMaxID.ID;
                return maxID + 1;
            }
        }

        private MatchableByNumber makeNewInstanceWithID(int id)
        {
            MatchableByNumber newInstance = MatchableByNumber.createWithID(id);
            super.instances.add(newInstance);
            return newInstance;
        }

        public MatchableByNumber makeNewUnmatchedInstance()
        { return this.makeNewInstanceWithID(this.getAnUnmatchedID()); }

        public MatchableByNumber makeNewInstanceThatMatches(MatchableByNumber mbnToMatch)
        { return this.makeNewInstanceWithID(mbnToMatch.ID); }
    }
}
