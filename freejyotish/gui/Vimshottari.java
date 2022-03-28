head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/*
 *    class first created May 16, 2000 by Michael Taft (w/help from Harsh Singh)
 *
 *
 */

public class Vimshottari
{
    private double mlongit;
    private int lordName;
    private double lordPeriod;
    private int year;
    private int month;
    private int date;
    private double birth;
    private double remainder;
    private String[] vimD;
    private String birthSL;
    private GregorianCalendar cal;
    private Date dt;
    private SimpleDateFormat formatter;
    private Calendar tempCal;

	private static final double [] periods = {6.0,10.0,7.0,18.0,16.0,19.0,17.0,7.0,20.0};
    private static final String[]lords = {"Su","Mo","Ma","Ra","Ju","Sa","Me", "Ke", "Ve"};
    private static final double nakLength = 13.3333333333;

    /* the only constructor - requires decimal longitude of birth
     * moon (assumes sidereal, but any would work), and the birth
     * date in integer years, months, and days.
     */

    public Vimshottari(double mlong, int yer, int mon, int day)
    {
        mlongit = mlong;
        year = yer;
        month = mon;
        date = day;
       	cal	= new GregorianCalendar(year, month-1, date);

        lordName = dLordName(mlongit);
        lordPeriod = periods[lordName];
        birth = getDecimalY();
        remainder = getBalance();
        birthSL = getBirthSubLord(mlongit);
        formatter = new SimpleDateFormat( "MMM dd, yyyy" );
        tempCal = Calendar.getInstance();
        runDashas();


    }
    /* this is the dasha engine - runs perfectly
     */
    private String[] runDashas()
    {
		int x = lordName;
        int  y = x;

        vimD = new String[81];

        int counter1;
        int counter2;
        int counter3 = 0;
        int counter4 = 0;


        double dDC = 0; //"decimal date of change"
        double lastDDC = (birth + remainder) - lordPeriod;//last calculation
        for (counter1 = 0; counter1<=8; counter1++)
        {
            for (counter2 = 0; counter2<=8; counter2++)
            {

                lastDDC = lastDDC + dDC;
                String result = getYMD(lastDDC);
                String name1 = lords[x];
                String name2 = lords[y];
                String bd =  month + "/" + date + "/" + year;
                if (lastDDC < birth)
                {
                    vimD[counter3] = "";
                    vimD[0] = (name1 + "/" + birthSL + ": " + bd);
                }
                else vimD[counter3] = (name1 + "/" + name2 + ": " + result);
                dDC = (periods[x] * periods[y])/120;//The actual calculation - heart of the whole class
                y++;
                counter3++;
                if(y>=9)y = 9 - y;

            }
            x++;
            if(x>=9)x = 9 - x;
            y = x;
        }
        return vimD;
    }

    private double getBalance()
    {

        double balance = mlongit % nakLength;
        balance = nakLength - balance;
        balance = balance*60;
        return (balance*lordPeriod)/800;

    }

    private double getDecimalY()//converts yy/mm/dd into decimal years
    {
        int days = cal.get(Calendar.DAY_OF_YEAR);
        double dayFrac;

				boolean leap = ((year%4 ==0) && (year%100 !=0 || year%400 == 0))?true:false;
				if (leap){
					dayFrac  = (double)days/366;
				}
				else
					dayFrac = (double)days/365;

        double yr = year + dayFrac;
        return yr;
    }
    /* converts decimal years to years/months/days
     */
    private String getYMD(double yr)
    {

        int yy = (int)yr;
        double m = (yr-yy);
        int d;
        double dd;
        int i;
        int [] days = {31,28,31,30,31,30,31,31,30,31,30,31};

		boolean leap = ((year%4 ==0) && (year%100 !=0 || year%400 == 0))?true:false;

		if (leap) {
			dd = (m*366);
		}
		else {
			dd = (m*365);
		}

		if (dd > (int)dd)
		{
			d = (int)dd + 1;
		}
		else
			d = (int)dd;

		for (i=0;i<12;i++)
		{
			int temp = days[i];
			if (leap&& (i==1))
				temp += 1;

			if (d > temp)
			{
				d -= temp;
			}
			else
				break;
		}
        int mm = i + 1;
        return dealWithDates(yy,mm,(int)d);

    }

    private int dLordName (double lon)
    {

        double i=0;
        for (i=0;i<=26;i++){
            if (i*(nakLength)<=lon&&lon<((i+1)*(nakLength)))
                break;
        }
        return (int)((i+7)%9);
    }

    /* returns name of birth sub-lord
     */
    private String getBirthSubLord(double lon)
    {
        int x = 0;
        int y = lordName;
        lon = lon % nakLength;
        for (double i=0;i<=8;i++)
        {
            if (i*(1.48148148148)<=lon&&lon<((i + 1)*(1.48148148148)))
            {
                x = (int)i;
                break;
			}
        }
        x = y + x;
        if(x>=9)
            x = x - 9;

        return lords[x];
    }

    /* makes sure no impossible dates are given
     */
    private  String dealWithDates(int yer, int monh, int dat)
    {

        int yr = yer;
        int mo = monh;
        int day = dat;
        if (mo>12)
        {
            yr++;
            mo = mo - 12;
        }

        if (mo == 2 && day > 27)
        {
            mo++;
            day = day-30;
        }
        else if ((mo == 4 || mo == 6 || mo == 9 || mo == 11) && day>30)
        {
			mo++;
          	day = day - 30;
        }

        else if ((mo == 1 || mo == 3 || mo == 5 || mo == 7 || mo == 8 || mo == 10 || mo == 12) && day>31)
        {
            mo++;
            day = day - 31;
        }

        if (day<=0)
            day = 1;
        //return (mo + "/" + day + "/" + yr);
        String s = null;
        tempCal.set( Calendar.DATE, day );
        tempCal.set( Calendar.MONTH, mo );
        tempCal.set( Calendar.YEAR, yr );
        try {
            s = formatter.format( tempCal.getTime() );
        } catch( Exception pe ) {
            System.out.println( "ParseException: " + pe );
        }
        return s;

    }

    public String[] getDashaString()
    {
        return vimD;
    }
}



@
