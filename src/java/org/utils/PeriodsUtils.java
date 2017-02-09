package org.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Calender;
import org.pojos.Periods;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class PeriodsUtils {

    public List<Periods> generatePeriods(Calender calender) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar start_end = Calendar.getInstance();
        Calendar transaction = Calendar.getInstance();
        Calendar last_day = Calendar.getInstance();

        start_end.setTime(calender.getStartFrom());

        Character type = calender.getType();
        List<Periods> periods = new ArrayList<Periods>();
        int quantity = 12;
        boolean period_more = false;

        if (type.equals("W")) { // Weekly (52/year)
            quantity = 52;
        } else if (type.equals("B")) { // Bi-Weekly (26/year)
            quantity = 26;
        } else if (type.equals("M")) { // Monthly (12/year) default
            quantity = 12;
        }

        Date start, end, trans;

        for (int i = 0; i < quantity; i++) {

            //  from Calender to java.sql.Date
            start = new Date(start_end.getTime().getTime());
            end = trans = null;
            //  System.out.print("Start: " + sdf.format(start_end.getTime()));

            if (type.equals("M")) {               // Monthly

                if (i == 0) {
                    start_end.add(Calendar.DATE, start_end.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);

                    // if start day = 1
                    if ((calender.getStartDay() - 1.0) > 0.0) {
                        start_end.set(Calendar.DATE, calender.getStartDay() - 1);
                    }

                } else {
                    start_end.add(Calendar.MONTH, 1);
                    start_end.add(Calendar.DATE, -1);
                }

                end = new Date(start_end.getTime().getTime());
                System.out.print("| End: " + sdf.format(start_end.getTime()));

                // get transaction date
                transaction.setTime(end);

                if (end.getDate() > calender.getTransDay()) {

                    transaction.add(Calendar.MONTH, 1);
                }

                transaction.set(Calendar.DATE, calender.getTransDay());
                trans = new Date(transaction.getTime().getTime());
                System.out.println("| Trans: " + sdf.format(transaction.getTime()));

            } else if (type.equals("W")) {       // Weekly

                if (i == 0) {
                    // get first period start day
                    int month = start_end.get(Calendar.MONTH);

                    if (start_end.get(Calendar.DAY_OF_WEEK) != calender.getWeekStartDay() + 1) {
                        start_end.set(Calendar.DAY_OF_WEEK, calender.getWeekStartDay() + 1);

                        if (start_end.get(Calendar.MONTH) != month) {
                            start_end.add(Calendar.DATE, 7);
                        }
                    }

                    int day = start_end.get(Calendar.DATE);
                }

                System.out.print("\n" + i + ": ");

                start = new Date(start_end.getTime().getTime());
                System.out.print("start: " + start.toString());

                // get End date (add week - 6 days)
                start_end.add(Calendar.DATE, 6);
                end = new Date(start_end.getTime().getTime());
                System.out.print(" | end: " + end.toString());

                // get Transaction date (end date + week_trans_day)
                transaction.setTime(end);
                transaction.add(Calendar.DATE, calender.getWeekTransDate());
                trans = new Date(transaction.getTime().getTime());
                System.out.println("| Trans: " + sdf.format(transaction.getTime()));

            } else if (type.equals("B")) {

                if (i == 0) {
                    // get first period start day
                    int month = start_end.get(Calendar.MONTH);

                    if (start_end.get(Calendar.DAY_OF_WEEK) != calender.getWeekStartDay() + 1) {
                        start_end.set(Calendar.DAY_OF_WEEK, calender.getWeekStartDay() + 1);

                        if (start_end.get(Calendar.MONTH) != month) {
                            start_end.add(Calendar.DATE, 7);
                        }
                    }

                    int day = start_end.get(Calendar.DATE);
                }

                System.out.print("\n" + i + ": ");

                start = new Date(start_end.getTime().getTime());
                System.out.print("start: " + start.toString());

                // get End date (add week - 13 days)
                start_end.add(Calendar.DATE, 13);
                end = new Date(start_end.getTime().getTime());
                System.out.print(" | end: " + end.toString());

                // get Transaction date (end date + week_trans_day)
                transaction.setTime(end);
                transaction.add(Calendar.DATE, calender.getWeekTransDate());
                trans = new Date(transaction.getTime().getTime());
                System.out.println("| Trans: " + sdf.format(transaction.getTime()));

            }

            last_day.setTime(end);
            last_day.set(Calendar.MONTH, start_end.getActualMaximum(Calendar.MONTH));
            last_day.set(Calendar.DAY_OF_MONTH, start_end.getActualMaximum(Calendar.DAY_OF_MONTH));

            System.out.println("\n >>>>> Last day on year >>>> " + sdf.format(last_day.getTime()));
            // add period to complete the year
            if (i == quantity - 1 && start_end.before(last_day)) {
                System.out.println("\n check more period");
                if (period_more) {
                    break;
                }
                quantity += 1;
                period_more = true;
            }

            // set start to next day
            start_end.add(Calendar.DATE, 1);

            Periods period = new Periods();
            period.setStartDate(start);
            period.setEndDate(end);
            period.setTransDate(trans);
            period.setCalender(calender);
            period.setStatus('O');

            periods.add(period);
        }

        return periods;
    }
}
