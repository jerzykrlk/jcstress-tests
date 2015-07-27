package com.github.erizo.concurrent.firstsample;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.BooleanResult2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@JCStressTest
@Description("Tests the thread-safety of dateFormat.")
@Outcome.Outcomes({
        @Outcome(id = "[true, true]", expect = Expect.ACCEPTABLE, desc = "Both dates correct."),
        @Outcome(expect = Expect.FORBIDDEN, desc = "Other cases are not expected.")
})
@State
public class DateFormatTest {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String date1;
    private String date2;

    @Actor
    public void actor1()  {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2007-12-03");
            date1 = dateFormat.format(parse);
        } catch (Exception e) {
        }
    }

    @Actor
    public void actor2()  {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2008-11-02");
            date2 = dateFormat.format(parse);
        } catch (Exception e) {
        }
    }

    @Arbiter
    public void arbiter(BooleanResult2 booleanResult2) {
        if ("2007-12-03".equals(date1)) {
            booleanResult2.r1 = true;
        }
        if ("2008-11-02".equals(date2)) {
            booleanResult2.r2 = true;
        }
    }
}
