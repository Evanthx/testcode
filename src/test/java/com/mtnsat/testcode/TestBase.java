package com.mtnsat.testcode;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class TestBase {

    @Before
    public final void TestBaseSetup() {
        MockitoAnnotations.initMocks(this);
    }
}
