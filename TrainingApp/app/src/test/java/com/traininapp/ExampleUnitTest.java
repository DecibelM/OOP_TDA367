package com.traininapp;

import com.traininapp.Model.Calendar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addSessionTest(){
        Calendar c = new Calendar();
        c.addSession("Magpass");

        assertEquals(1,c.getSessionList().size());
    }
}