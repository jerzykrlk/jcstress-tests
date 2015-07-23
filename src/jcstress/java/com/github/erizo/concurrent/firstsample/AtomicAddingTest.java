package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult1;

import java.util.concurrent.atomic.AtomicInteger;

@JCStressTest
@Description("Tests the thread-safeness of incrementAndGet.")
@Outcome.Outcomes({
        @Outcome(id = "[2]", expect = Expect.ACCEPTABLE, desc = "Acceptable to see 2."),
        @Outcome(expect = Expect.FORBIDDEN, desc = "Other cases are not expected.")
})
@State
public class AtomicAddingTest {

    private AtomicInteger x = new AtomicInteger();

    @Actor
    public void actor1() {
        x.incrementAndGet();
    }

    @Actor
    public void actor2() {
        x.incrementAndGet();
    }

    @Arbiter
    public void arbiter(IntResult1 result) {
        result.r1 = x.get();
    }

}
