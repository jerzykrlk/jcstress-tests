package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

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
    public void arbiter(II_Result result) {
        result.r1 = x.get();
    }

}
