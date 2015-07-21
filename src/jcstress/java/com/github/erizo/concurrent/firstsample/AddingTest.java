package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult1;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Description("Tests the thread-safeness of x++.")
@Outcome(id = "[2]", expect = Expect.ACCEPTABLE, desc = "Acceptable to see true.")
@Outcome(expect = Expect.FORBIDDEN, desc = "Other cases are not expected.")
@State
public class AddingTest {

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
    public void arbiter(IntResult1 result) {
        result.r1 = x;
    }

}
