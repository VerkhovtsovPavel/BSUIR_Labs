package by.bsuir.verkpavel.adb.logic;

import java.time.format.DateTimeFormatter;

import by.bsuir.verkpavel.adb.resources.ProjectProperties;

public abstract class AbstractDayCloser {
  public static DateTimeFormatter dateMask = ProjectProperties.getDateFormatter();
   
  public abstract void closeDay();
}
