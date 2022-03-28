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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.*;

/**
 *  This provides a generic way to enter a date and time.
 *
 *@@created    May 21, 2002
 */
public class DateTimePanel extends JPanel
{
	/**
	 * Date models
	 */
	SpinnerDateModel m_dayModel;
	SpinnerDateModel m_monthModel;
	SpinnerDateModel m_yearModel;

	/** 
	 * Time models
	 */
	SpinnerNumberModel m_hourModel;
	SpinnerNumberModel m_minuteModel;
	SpinnerNumberModel m_secondModel;
	
	
	
	/**
	 * Filters and data
	 */
	Date m_minDate;
	Date m_maxDate;
	Date m_curDate;
	boolean m_constraints;
	
	/**
	 *  Constructor for the MementoDateTimePanel object
	 *
	 *@@param  minDate  Minimum constraint on date input
	 *@@param  maxDate  Maximum constraint on date input
	 *@@param  curDate  The default date to set the component to.
	 */
	public DateTimePanel(Date minDate, Date maxDate, Date curDate)
	{
		m_minDate = (Date) minDate.clone();
		m_maxDate = (Date) maxDate.clone();
		m_curDate = (Date) curDate.clone();
		m_constraints = true;
		addComponents();
	}
	
	public DateTimePanel()
	{
		m_minDate = m_maxDate = m_curDate = null;
		m_constraints = false;
		addComponents();
	}


	/**
	 *  Sets the minDate attribute of the MementoDateTimePanel object
	 *
	 *@@param  minDate  The new minDate value
	 */
	public void setMinDate(Date minDate)
	{
		m_dayModel.setStart(minDate);
		m_monthModel.setStart(minDate);
		m_yearModel.setStart(minDate);
	}


	/**
	 *  Sets the maxDate attribute of the MementoDateTimePanel object
	 *
	 *@@param  maxDate  The new maxDate value
	 */
	public void setMaxDate(Date maxDate)
	{
		m_dayModel.setEnd(maxDate);
		m_monthModel.setEnd(maxDate);
		m_yearModel.setEnd(maxDate);
	}


	/**
	 *  Sets the currentDate attribute of the MementoDateTimePanel object
	 *
	 *@@param  curDate  The new currentDate value
	 */
	public void setCurrentDate(Date curDate)
	{
		m_dayModel.setValue(new Integer(dayNumber(curDate)));
		m_monthModel.setValue(curDate);
		m_yearModel.setValue(curDate);
	}


	/**
	 *  Adds a feature to the Components attribute of the MementoDateTimePanel
	 *  object
	 */
	public void addComponents()
	{
		setLayout(new PercentLayout());

		add("0 50 20 100", monthSpinner());
		add("20 50 35 100", daySpinner());
		add("35 50 58 100", yearSpinner());
		
		add("58 50 72 100", hourSpinner());
		add("72 50 88 100", minuteSpinner());
		add("88 50 100 100", secondSpinner());

		add("0 0 20 50", new JLabel("Month", SwingConstants.CENTER));
		add("20 0 35 50", new JLabel("Day", SwingConstants.CENTER));
		add("35 0 58 50", new JLabel("Year", SwingConstants.CENTER));
		
		add("58 0 72 50", new JLabel("Hour", SwingConstants.CENTER));
		add("72 0 88 50", new JLabel("Min.", SwingConstants.CENTER));
		add("88 0 100 50", new JLabel("Sec", SwingConstants.CENTER));
	}


	/**
	 *  Description of the Method
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner monthSpinner()
	{
		if (m_constraints)
		{
			m_monthModel = new SpinnerDateModel(m_curDate, m_minDate, m_maxDate, Calendar.MONTH);
		}
		else
		{
			m_monthModel = new SpinnerDateModel();
			m_monthModel.setCalendarField(Calendar.MONTH);
		}

		JSpinner monthSpinner = new JSpinner(m_monthModel);
		JSpinner.DateEditor monthEditor = new JSpinner.DateEditor(monthSpinner, "MMMMM");

		monthSpinner.setEditor(monthEditor);
		return monthSpinner;
	}


	/**
	 *  Create a spinner for the day of the month
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner daySpinner()
	{
			m_dayModel = new SpinnerDateModel();
			m_dayModel.setCalendarField(Calendar.DATE);
		
		JSpinner daySpinner = new JSpinner(m_dayModel);
        JSpinner.DateEditor dayEditor = new JSpinner.DateEditor( daySpinner, "d"
        );
        daySpinner.setEditor( dayEditor );
		return daySpinner;
	}


	/**
	 *  Create a spinner for the day of the month
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner hourSpinner()
	{
		m_hourModel =
				new SpinnerNumberModel(0,0,23,1);
					
		JSpinner hourSpinner = new JSpinner(m_hourModel);
		JSpinner.NumberEditor hourEditor = new JSpinner.NumberEditor(hourSpinner);
		hourSpinner.setEditor(hourEditor);

		return hourSpinner;
	}

	/**
	 *  Create a spinner for the day of the month
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner minuteSpinner()
	{
		m_minuteModel =
				new SpinnerNumberModel(0,0,60,1);
		JSpinner minuteSpinner = new JSpinner(m_minuteModel);
		JSpinner.NumberEditor minuteEditor = new JSpinner.NumberEditor(minuteSpinner);
		minuteSpinner.setEditor(minuteEditor);

		return minuteSpinner;
	}

	/**
	 *  Create a spinner for the day of the month
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner secondSpinner()
	{
		m_secondModel =
				new SpinnerNumberModel(0,0,60,1);
		JSpinner secondSpinner = new JSpinner(m_secondModel);
		JSpinner.NumberEditor secondEditor = new JSpinner.NumberEditor(secondSpinner);
		secondSpinner.setEditor(secondEditor);

		return secondSpinner;
	}

	/**
	 *  Description of the Method
	 *
	 *@@return    Description of the Return Value
	 */
	private JSpinner yearSpinner()
	{
		if (m_constraints)
		{
			m_yearModel = new SpinnerDateModel(m_curDate, m_minDate, m_maxDate, Calendar.YEAR);
		}
		else
		{
			m_yearModel = new SpinnerDateModel();
			m_yearModel.setCalendarField(Calendar.YEAR);
		}

		JSpinner yearSpinner = new JSpinner(m_yearModel);
		JSpinner.DateEditor yearEditor = new JSpinner.DateEditor(yearSpinner, "yyyy");

		yearSpinner.setEditor(yearEditor);

		return yearSpinner;
	}


	/**
	 *  Given a complete date, this will return the number of the day as an int.
	 *
	 *@@param  date  Description of the Parameter
	 *@@return       Description of the Return Value
	 */
	private int dayNumber(Date date)
	{
		Calendar dayCal = Calendar.getInstance();

		dayCal.setTime(date);
		return dayCal.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 *  Retrieve the information from this panel in the form of a date.
	 *
	 *@@return    The date value
	 */
	public Date getDate(TimeZone zone, int dst)
	{

		Calendar mergedCal = Calendar.getInstance();
		TimeZone timeZone = zone;
		int dSTOffset = dst;
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime( ( Date ) m_dayModel.getValue() );
        int day = tempCal.get( Calendar.DAY_OF_MONTH );
        tempCal.setTime( ( Date ) m_monthModel.getValue() );
        int mon = tempCal.get( Calendar.MONTH );
        tempCal.setTime( ( Date ) m_yearModel.getValue() );
        int yr = tempCal.get( Calendar.YEAR );
        tempCal = null;
		// We need to consolidate the proper pieces from each one into our new date.
		mergedCal.set(Calendar.DAY_OF_MONTH, day );
		mergedCal.set(Calendar.MONTH, mon );
		mergedCal.set(Calendar.YEAR, yr );
		mergedCal.set(Calendar.HOUR_OF_DAY, m_hourModel.getNumber().intValue());
		mergedCal.set(Calendar.MINUTE, m_minuteModel.getNumber().intValue());
		mergedCal.set(Calendar.SECOND, m_secondModel.getNumber().intValue());
		mergedCal.setTimeZone(timeZone);
		mergedCal.set(Calendar.DST_OFFSET, dSTOffset);

		
		return mergedCal.getTime();
	}

}

@
