package com.wll.test.java.ut.testng;

import org.testng.*;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class TestListener implements IExecutionListener, IAnnotationTransformer, IAnnotationTransformer2,
        IHookable,
        IInvokedMethodListener,
        IMethodInterceptor,
//        IReporter,
        ISuiteListener,
        ITestListener,
        IConfigurationListener2{
    @Override
    public void transform(IConfigurationAnnotation iConfigurationAnnotation, Class aClass, Constructor constructor, Method method) {
        System.out.println("into transform21");
    }

    @Override
    public void transform(IDataProviderAnnotation iDataProviderAnnotation, Method method) {
        System.out.println("into transform22");
    }

    @Override
    public void transform(IFactoryAnnotation iFactoryAnnotation, Method method) {
        System.out.println("into transform23");
    }

    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        System.out.println("into transform");
    }

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        System.out.println("into IHookable run");
        iHookCallBack.runTestMethod(iTestResult);
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("into IInvokedMethodListener beforeInvocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("into IInvokedMethodListener afterInvocation");
    }

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        System.out.println("into IMethodInterceptor intercept");
        return list;
    }

//    @Override
//    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
//        System.out.println("into IReporter generateReport");
//    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("into ISuiteListener onStart");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println("into ISuiteListener onFinish");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("into ISuiteListener onFinish");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("into ITestListener onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("into ITestListener onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("into ITestListener onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("into ITestListener onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("into ITestListener onStart");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("into ITestListener onFinish");
    }


    @Override
    public void onExecutionStart() {
        System.out.println("onExecutionStart");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("onExecutionFinish");
    }

    @Override
    public void beforeConfiguration(ITestResult iTestResult) {
        System.out.println("beforeConfiguration");
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {
        System.out.println("onConfigurationSuccess");
    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {
        System.out.println("onConfigurationFailure");
    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {
        System.out.println("onConfigurationSkip");
    }
}
