package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Pool of objects
 * that are Matchable
 * with other objects of the same type
 * or of super types up to some ancestor type.
 *
 * @author Matt Rueben
 */
public abstract class MatchablePool<M extends Matchable<? super M>> // M is for Matchable.
{
    protected final List<M> instances = new ArrayList<>();

    public abstract M getNewUnmatchedInstance();

    public abstract M getNewInstanceThatMatches(M thisMatchable);

    public void removeTheseInstances(List<M> instancesToRemove)
    { this.instances.removeAll(instancesToRemove); }

    public void clearAllInstances()
    { this.instances.clear(); }
}
