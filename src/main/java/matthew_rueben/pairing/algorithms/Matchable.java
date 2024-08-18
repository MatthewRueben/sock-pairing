package matthew_rueben.pairing.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Matchable with objects of type T
 * (including child types of T).
 *
 * Suggests that implementations
 * also implement a pool of objects pattern
 * for creating and forgetting instances.
 *
 * @author Matt Rueben
 */
public interface Matchable<T>
{
    boolean matches(T candidate);

    /**
     * Pool of objects
     * that are Matchable
     * with other objects of the same type
     * or of super types up to some ancestor type.
     */
    public abstract class Pool<M extends Matchable<? super M>> // M is for Matchable.
    {
        protected final List<M> instances = new ArrayList<>();

        public List<M> getAllCurrentInstances()
        { return new ArrayList<>(this.instances); }

        public abstract M makeNewUnmatchedInstance();

        public abstract M makeNewInstanceThatMatches(M thisMatchable);

        public void removeTheseInstances(List<M> instancesToRemove)
        { this.instances.removeAll(instancesToRemove); }

        public void clearAllInstances()
        { this.instances.clear(); }
    }
}