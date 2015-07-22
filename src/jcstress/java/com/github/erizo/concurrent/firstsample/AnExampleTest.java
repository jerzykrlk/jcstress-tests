package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
@Description("Tests the thread-safeness of the StringUtil class.")
@Outcome(id = "[1, 1]", expect = Expect.ACCEPTABLE, desc = "Acceptable to see 2.")
@Outcome(expect = Expect.FORBIDDEN, desc = "Other cases are not expected. -1 would mean an exception raised.")
@State
public class AnExampleTest {

    @Actor
    public void actor1(IntResult2 result) {
        try {
            result.r1 = StringUtils.isJsonObject("1234") ? 1 : 0;
        } catch (Exception ex) {
            result.r1 = -1;
        }
    }

    @Actor
    public void actor2(IntResult2 result) {
        try {
            result.r2 = StringUtils.isJsonObject("5678") ? 1 : 0;
        } catch (Exception ex) {
            result.r2 = -1;
        }
    }
}
