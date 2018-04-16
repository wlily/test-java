package com.wll.test.java.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//Mock普通对象的final方法
//当需要mock final方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是final方法所在的类。
@RunWith(PowerMockRunner.class)
public class TestDemo3 {
    @Test
    @PrepareForTest(ClassDependency.class)
    public void testCallFinalMethod() {
        ClassDependency refer = PowerMockito.mock(ClassDependency.class);
        PowerMockito.when(refer.isAlive1()).thenReturn(true);
        Demo3 demo = new Demo3();
        Assert.assertTrue(demo.callFinalMethod(refer));
    }
}