package com.wll.test.java.ut.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.File;

//普通Mock： Mock参数传递的对象
public class TestDemo1 {

    @Test
    public void testCallArgumentInstance(){
        //mock出入参File对象
        File file = PowerMockito.mock(File.class);
        Demo1 demo = new Demo1();
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(demo.callArgumentInstance(file));
    }

}
