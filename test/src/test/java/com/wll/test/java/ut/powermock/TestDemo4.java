package com.wll.test.java.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//Mock普通类的静态方法
//当需要mock静态方法的时候，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是静态方法所在的类。
@RunWith(PowerMockRunner.class)
public class TestDemo4 {
    @Test
    @PrepareForTest(ClassDependency.class)
    public void testCallFinalMethod() {
        PowerMockito.mockStatic(ClassDependency.class);
        PowerMockito.when(ClassDependency.isAlive2()).thenReturn(true);
        Demo4 demo = new Demo4();
        Assert.assertTrue(demo.callStaticMethod());
    }
}