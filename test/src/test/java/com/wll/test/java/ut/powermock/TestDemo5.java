package com.wll.test.java.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//Mock 私有方法
@RunWith(PowerMockRunner.class)
public class TestDemo5 {
    @Test
    @PrepareForTest(Demo5.class)
    public void testCallFinalMethod() throws Exception {
        Demo5 demo = PowerMockito.mock(Demo5.class);
        PowerMockito.when(demo.callPrivateMethod()).thenCallRealMethod();
        PowerMockito.when(demo, "isAlive").thenReturn(true);
        Assert.assertTrue(demo.callPrivateMethod());
    }
}