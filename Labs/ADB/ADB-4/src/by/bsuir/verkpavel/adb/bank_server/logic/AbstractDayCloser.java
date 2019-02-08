package by.bsuir.verkpavel.adb.bank_server.logic;

import java.time.format.DateTimeFormatter;

import by.bsuir.verkpavel.adb.bank_server.resources.ProjectProperties;

public abstract class AbstractDayCloser {
  public static DateTimeFormatter dateMask = ProjectProperties.getDateFormatter();
   
  public abstract void closeDay();
}
