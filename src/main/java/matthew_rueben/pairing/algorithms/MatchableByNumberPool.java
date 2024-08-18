package matthew_rueben.pairing.algorithms;

import java.util.Collections;

public class MatchableByNumberPool extends MatchablePool<MatchableByNumber>
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
