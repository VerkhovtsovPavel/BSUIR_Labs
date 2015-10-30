package by.bsuir.verkpavel.adb.logic;

import java.time.format.DateTimeFormatter;

import by.bsuir.verkpavel.adb.resources.Properties;

public abstract class AbstractDayCloser {
  public static DateTimeFormatter dateMask = Properties.getDateFormatter();
   
  public abstract void closeDay();
}
