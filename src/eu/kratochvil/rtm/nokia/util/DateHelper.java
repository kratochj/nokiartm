/**
 * mobile-model created at Jul 30, 2008
 */
package eu.kratochvil.rtm.nokia.util;

import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @author karol.bucek@jetminds.com
 *
 */
public abstract class DateHelper {
    
    /**
     * 1 day in milliseconds (same format as {@link Date} uses)
     */
    public static final int DAY_MS = 24 * 60 * 60 * 1000;
    
    /**
     * A shared calendar instance used by this helper, available to the outside world.
     */
    public static final Calendar sharedCalendar = Calendar.getInstance();
    
    /**
     * Returns days from the current date.
     * @param date the current date
     * @param days day count, if positive "+" days, else "-" days
     * @return days from the current date
     */
    public static Date daysFrom(final Date date, final int days) {
        /* DOES NOT WORK "LENIENT" AS IN J2ME !
        java.lang.IllegalArgumentException
         - com.sun.cldc.util.j2me.TimeZoneImpl.getOffset(), bci=76
         - com.sun.cldc.util.j2me.TimeZoneImpl.getOffset(), bci=33
         - com.sun.cldc.util.j2me.CalendarImpl.computeTime(), bci=225
         - java.util.Calendar.getTimeInMillis(), bci=8
         - java.util.Calendar.getTime(), bci=5
         - com.peersone.mobile.util.DateHelper.daysFrom(), bci=24
         */
        //final Calendar sharedCalendar = Calendar.getInstance();
        //sharedCalendar.setTime(date);
        //final int currentDay = sharedCalendar.get(Calendar.DATE);
        //sharedCalendar.set(Calendar.DATE, currentDay + days);
        //return sharedCalendar.getTime();
        
        // NOTE: this alone will not handle DST e.g. 31.10 0:0:0 - 14.days = 17.10 1:0:0
        //return new Date(date.getTime() + (days * (long) DAY_MS));
        
        final Date from = new Date(date.getTime() + (days * (long) DAY_MS));
        // handle DST correctly :
        final int dateHour, fromHour;
        synchronized (sharedCalendar) {
            sharedCalendar.setTime(date);
            dateHour = sharedCalendar.get(Calendar.HOUR_OF_DAY); // 0 - 24
            sharedCalendar.setTime(from);
            fromHour = sharedCalendar.get(Calendar.HOUR_OF_DAY); // 0 - 24
        }
        if ( dateHour != fromHour ) {
            // DST occurred between thus further adjust date :
            int diff = dateHour - fromHour;
            if ( Math.abs(diff) > 1 ) {
                diff = diff > 0 ? -1 : +1; // 0 - 23 and 23 - 0 cases
            }
            from.setTime(from.getTime() + diff * 60 * 60 * 1000); // +- 1 hour
        }
        return from;
    }
    
    /**
     * Returns d1 - d2 but in days only (time info discarded).
     * @param d1
     * @param d2
     * @return day difference for the dates
     */
    public static int dateDayDiff(Date d1, Date d2) {
        /*
        final Calendar sharedCalendar = Calendar.getInstance();
        sharedCalendar.setTime(d1);
        final int year1 = sharedCalendar.get(Calendar.YEAR);
        // BUG Sun WTK ... DAY_OF_YEAR always null !
        final int dofy1 = sharedCalendar.get(Calendar.DAY_OF_YEAR);
        //final int date1 = sharedCalendar.get(Calendar.DATE);
        sharedCalendar.setTime(d2);
        final int year2 = sharedCalendar.get(Calendar.YEAR);
        final int dofy2 = sharedCalendar.get(Calendar.DAY_OF_YEAR);
        //final int date2 = sharedCalendar.get(Calendar.DATE);
        // 365 would not be exact for a long period with a
        // transient (366 day long) year, however for our purposes
        // this is enough (year will be mostly the same) !
        //return (year1 - year2) * 365 + (dofy1 - dofy2);
         */
        //final Calendar sharedCalendar = Calendar.getInstance();
        synchronized (sharedCalendar) {
            sharedCalendar.setTime(d1);
            resetTimeFields(sharedCalendar);
            //d1 = sharedCalendar.getTime();
            final long d1time = sharedCalendar.getTime().getTime();
            
            sharedCalendar.setTime(d2);
            resetTimeFields(sharedCalendar);
            //d2 = sharedCalendar.getTime();
            final long d2time = sharedCalendar.getTime().getTime();

            //return (int) (( d1.getTime() - d2.getTime() ) / DAY_MS);
            return (int) (( d1time - d2time ) / DAY_MS);
        }
    }
    
    /**
     * @param date
     * @return true if given date has date fields same as today
     */
    public static boolean isToday(final Date date) {
        return sameDate(date, new Date());
    }
    
    /**
     * @param d1
     * @param d2
     * @return true if date are same (regardless of the time values)
     */
    public static boolean sameDate(final Date d1, final Date d2) {
        //final Calendar sharedCalendar = Calendar.getInstance();
        synchronized (sharedCalendar) {
            sharedCalendar.setTime(d1);
            final int year = sharedCalendar.get(Calendar.YEAR);
            // BUG Sun WTK ... DAY_OF_YEAR always null !
            //final int dofy = sharedCalendar.get(Calendar.DAY_OF_YEAR);
            final int month = sharedCalendar.get(Calendar.MONTH);
            final int date = sharedCalendar.get(Calendar.DATE);
            sharedCalendar.setTime(d2);
            return year  == sharedCalendar.get(Calendar.YEAR)
                && date  == sharedCalendar.get(Calendar.DATE)
                && month == sharedCalendar.get(Calendar.MONTH);
                //&& dofy == sharedCalendar.get(Calendar.DAY_OF_YEAR);
        }
    }
    
    /*
    private static Date daysFromDate(final Date date, final int days) {
        final long dateTime = date.getTime();
        return new Date(dateTime + days * DAY_MS);
    }
    */
    
    /**
     * @param date
     * @return pure date (with 'reset' time fields)
     * @see #resetTimeFields(Calendar)
     */
    public static Date pureDate(final Date date) {
        //final Calendar sharedCalendar = Calendar.getInstance();
        synchronized (sharedCalendar) {
            sharedCalendar.setTime(date);
            resetTimeFields(sharedCalendar);
            return sharedCalendar.getTime();
        }
    }

    /**
     * Resets the calendar's time fields.
     * @param calendar
     */
    public static void resetTimeFields(final Calendar calendar) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        //calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    
    /**
     * Resets the milliseconds for a date.
     * @param date
     */
    public static void resetMillis(final Date date) {
        long time = (date.getTime() / 1000) * 1000;
        date.setTime(time);
    }
    
    /**
     * Date merge (with time).
     * @param date the date values
     * @param time the time value
     * @return merged date with the given date and time
     */
    public static Date mergeDate(final Calendar date, final Date time) {
        //Calendar sharedCalendar = Calendar.getInstance();
        synchronized (sharedCalendar) {
            sharedCalendar.setTime(time);
            sharedCalendar.set(Calendar.YEAR, date.get(Calendar.YEAR));
            sharedCalendar.set(Calendar.MONTH, date.get(Calendar.MONTH));
            sharedCalendar.set(Calendar.DATE, date.get(Calendar.DATE));
            return sharedCalendar.getTime();
        }
    }
    
    /**
     * Clones a calendar.
     * @param calendar
     * @return cloned calendar instance
     */
    public static Calendar cloneCalendar(final Calendar calendar) {
        final Calendar clone = Calendar.getInstance();
        clone.setTime(calendar.getTime());
        return clone;
    }
    
}
