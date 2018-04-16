package com.wll.test.java.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class TestDemo6 {
    @Test
    @PrepareForTest(Demo6.class)
    public void testCallSystemStaticMethod(){
        Demo6 demo = new Demo6();
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
        Assert.assertEquals("bbb", demo.callSystemStaticMethod("aaa"));
    }
}
