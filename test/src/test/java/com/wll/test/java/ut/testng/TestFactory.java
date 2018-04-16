package com.wll.test.java.ut.testng;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestFactory {

    @Test(parameters = {"number-of-times"})
    public void accessPage(int numberOfTimes){
        while(numberOfTimes-- > 0){
            System.out.println("access web page " + numberOfTimes);
        }
    }

    @Factory
    public Object[] createInstances(){
        Object[] result = new Object[10];
        for(int i=0; i<10; i++){
            result[i] = new WebTest(i*10);
        }
        return result;
    }

    public class WebTest {
        private int m_numberOfTimes;
        public WebTest(int numberOfTimes) {
            m_numberOfTimes = numberOfTimes;
        }

        @Test
        public void testServer() {
            for (int i = 0; i < m_numberOfTimes; i++) {
                System.out.println("access web page " + i);
            }
        }
    }

}
