package com.traininapp.Model;

import java.util.Date;
import java.util.List;

/**
 * A session is your actual workout, complete with the exercises you're going to do with time and date
 */
public class Session {



    private String name;
    private List<Exercise> exercises;
    private Date date;
    private long time; // Number of seconds since January 1st 1970 (UNIX time)

}
