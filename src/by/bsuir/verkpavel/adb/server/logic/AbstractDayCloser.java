package by.bsuir.verkpavel.adb.server.logic;

import java.time.format.DateTimeFormatter;

import by.bsuir.verkpavel.adb.server.resources.ProjectProperties;

public abstract class AbstractDayCloser {
  public static DateTimeFormatter dateMask = ProjectProperties.getDateFormatter();
   
  public abstract void closeDay();
}
