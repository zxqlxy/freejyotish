head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.18.11.59.53;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Basic I18N support for FJ in the calc prefs dialog.
@
text
@package freejyotish.util;

import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

public class I18NManager
{
	public static final int DATE = 1;
	public static final int TIME = 2;
	public static final int DATE_TIME = 3;

	private static Locale locale = Locale.getDefault();

	private static Map imageCache = new HashMap();

	private static Map formatters = new HashMap();

	private static String imagePrefix = "images/";

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("freejyotish/gui/FreeJyotishGUI", locale, I18NManager.class.getClassLoader());

	//TODO: need to report any errors back to the UI.Waiting for Michael to finish exception handling.
	public static final String getString(String key) throws MissingResourceException
	{
		try
		{
			return resourceBundle.getString(key).trim();
		} catch (MissingResourceException missingResourceException)
		{
			missingResourceException.printStackTrace();
			throw missingResourceException;
		}
	}

	public static final String getLabel(String key)
	{
		String value = getString(key);
		int index = value.indexOf('&');
		return (index == -1 ? value : value.replaceFirst("&",""));
	}
	
	public static final char getMnemonic(String key)
	{
		String value = getString(key);
		int index = value.indexOf('&') + 1;
		if (index == 0 || index >= value.length())
			return 0;

		char mnemonic = value.charAt(index);
		return Character.toUpperCase(mnemonic);
	}

	public static Icon getIcon(String imageKey)
	{
		Icon icon = (Icon) imageCache.get(imageKey);
		if (icon != null)
			return icon;

		String imageName = getString(imageKey);
		if (imageName == null)
		{
			return null;
		}
		imageName = imagePrefix + imageName;
		URL url = ClassLoader.getSystemResource(imageName);
		icon = new ImageIcon(url);
		if (icon != null)
			imageCache.put(imageKey, icon);
		return icon;
	}

	public static String formatDate(Calendar dateToFormat, int style, int type)
	{
		DateFormat formatter = null;
		switch (type)
		{
			case DATE :
				formatter = SimpleDateFormat.getDateInstance(style, locale);
				break;
			case TIME :
				formatter = SimpleDateFormat.getTimeInstance(style, locale);
				break;
			case DATE_TIME :
				formatter = SimpleDateFormat.getDateTimeInstance(style, style, locale);
				break;
		}
		return formatter.format(dateToFormat.getTime());
	}

	public static final DateFormat getDateFormatter(int formatType, int type)
	{
		DateFormat formatter = null;

		switch (type)
		{
			case DATE :
				formatter = SimpleDateFormat.getDateInstance(formatType, locale);
				break;
			case TIME :
				formatter = SimpleDateFormat.getTimeInstance(formatType, locale);
				break;
			case DATE_TIME:
				formatter = SimpleDateFormat.getDateTimeInstance(formatType,formatType,locale);
				break;
		}
		return formatter;
	}

	public static String formatString(String messageKey, Object[] params)
	{
		MessageFormat formatter = (MessageFormat) formatters.get(messageKey);

		if (formatter == null)
		{
			String messageVal = getString(messageKey);
			formatter = new MessageFormat(messageVal);
			formatter.setLocale(locale);
			formatter.applyPattern(messageVal);
			formatters.put(messageKey, formatter);
		}
		return formatter.format(params);
	}	
}
@
