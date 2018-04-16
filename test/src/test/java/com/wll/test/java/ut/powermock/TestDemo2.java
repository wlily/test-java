package com.wll.test.java.ut.powermock;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

//Mock方法内部new出来的对象
//当使用PowerMockito.whenNew方法时，必须加注解@PrepareForTest和@RunWith。注解@PrepareForTest里写的类是需要mock的new对象代码所在的类。
@RunWith(PowerMockRunner.class)
public class TestDemo2 {
    @Test
    @PrepareForTest(Demo2.class)
    public void testCallArgumentInstance(){
        File file = PowerMockito.mock(File.class);
        try {
            PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
            Demo2 demo = new Demo2();
            PowerMockito.when(file.exists()).thenReturn(true);
            Assert.assertTrue(demo.callArgumentInstance("bbb"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}