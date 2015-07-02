package pl.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.BooleanResult1;
import org.openjdk.jcstress.infra.results.IntResult2;

@JCStressTest
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
