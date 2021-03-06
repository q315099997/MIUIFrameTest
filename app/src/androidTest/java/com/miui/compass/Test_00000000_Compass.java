package com.miui.compass;

/**
 * Project name : marmot-cases2
 * Package name : com.miui.compass
 * Created by jiahuixing
 * Created on 2015-07-03
 * Created at 11:32
 */

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.test.InstrumentationTestCase;

import com.miui.frame.Lib_Frame_Constants;
import com.miui.frame.Lib_Frame_Utils;
import com.miui.marmot.lib.Checker;
import com.miui.marmot.lib.Marmot;

public class Test_00000000_Compass extends InstrumentationTestCase {

    public Marmot marmot;
    public Checker checker;
    public UiDevice uiDevice;

    public static int testStep = 0;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        marmot = new Marmot(this);
        checker = new Checker(marmot);
        uiDevice = marmot.getUiDevice();
        Lib_Frame_Utils.unLock(marmot);
    }

    public void test_Compass() throws Exception {
        testStep += 1;
        marmot.log(String.format("%s. launch compass.", testStep));
        marmot.launchActivity(Lib_Frame_Constants.ACTIVITY_NAME_COMPASS);
        marmot.waitFor(2);
        checker.assertTrue(
                "compass not launched.",
                marmot.getCurrentPackageName().equals(
                        Lib_Frame_Constants.PACKAGE_NAME_COMPASS));
        UiObject2 confirm;
        // UiObject2 alertDialog, confirm;
        // alertDialog = marmot.getUiObject(By.clazz("android.widget.TextView")
        // .res("miui:id/alertTitle"));
        confirm = marmot.getUiObject(By.clazz("android.widget.Button").text(
                "同意并继续"));
        if (confirm != null) {
            marmot.log("deal with alert dialog.");
            marmot.saveScreenshot("alertDialog"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            confirm.click();
        }
        marmot.waitFor(2);
        UiObject2 calibratePressure;
        calibratePressure = marmot.getUiObject(By
                .clazz("android.widget.Button").text("校准海拔"));
        if (calibratePressure != null) {
            marmot.log("calibratePressure.");
            // marmot.log(String.format("calibratePressure: %s",
            // calibratePressure.getClassName()));
            marmot.saveScreenshot("calibratePressure"
                    + Lib_Frame_Constants.IMAGE_EXTENSION);
            calibratePressure.click();
            marmot.waitFor(2);
            confirm = marmot.getUiObject(By.clazz("android.widget.Button")
                    .text("确定"));
            if (confirm != null) {
                marmot.log("not connected.");
                marmot.saveScreenshot("noNet"
                        + Lib_Frame_Constants.IMAGE_EXTENSION);
                confirm.click();
                marmot.waitFor(1);
            }
        }
        for (int i = 0; i < 2; i++) {
            marmot.move(Direction.LEFT);
            marmot.waitFor(1);
        }
        for (int i = 0; i < 2; i++) {
            marmot.move(Direction.RIGHT);
            marmot.waitFor(1);
        }
        testStep += 1;
        marmot.log(String.format("%s. quit compass.", testStep));
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
    }

    @Override
    public void tearDown() throws Exception {
        Lib_Frame_Utils.backToPackage(marmot,
                Lib_Frame_Constants.PACKAGE_NAME_HOME);
        super.tearDown();
    }
}
