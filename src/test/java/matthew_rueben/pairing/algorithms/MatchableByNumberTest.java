package matthew_rueben.pairing.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchableByNumberTest {

    @Test
    void matches()
    {
        MatchableByNumber sock1a = MatchableByNumber.createWithID(1);
        MatchableByNumber sock1b = MatchableByNumber.createWithID(1);
        MatchableByNumber sock2a = MatchableByNumber.createWithID(2);
        assertTrue(sock1a.matches(sock1b));
        assertTrue(sock1b.matches(sock1a));
        assertFalse(sock1a.matches(sock2a));
        assertFalse(sock2a.matches(sock1a));
        assertFalse(sock1b.matches(sock2a));
        assertFalse(sock2a.matches(sock1b));
    }
}