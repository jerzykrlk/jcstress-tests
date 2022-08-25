package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Arbiter;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

@JCStressTest
@Description("Tests the thread-safeness of x++.")
@Outcome.Outcomes({
        @Outcome(id = "[2]", expect = Expect.ACCEPTABLE, desc = "Acceptable to see true."),
        @Outcome(expect = Expect.FORBIDDEN, desc = "Other cases are not expected.")
})
@State
public class NaiveAddingTest {

    private volatile int x;

    @Actor
    public void actor1() {
        x++;
    }

    @Actor
    public void actor2() {
        x++;
    }

    @Arbiter
    public void arbiter(I_Result result) {
        result.r1 = x;
    }

}
