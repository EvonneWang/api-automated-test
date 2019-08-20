package api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class apiTest {
    @BeforeClass(groups = {"all", "foreign-exchange"})
    public void setUp() {
        System.out.println("beforeClass");
    }

    @Test(groups = {"all", "foreign-exchange"})
    public void depositAccountEnquiryAccountDetailsGet() {
        System.out.println("test");
    }
}

