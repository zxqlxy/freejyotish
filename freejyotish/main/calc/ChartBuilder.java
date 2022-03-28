head	1.5;
access;
symbols;
locks; strict;
comment	@# @;


1.5
date	2003.05.23.07.55.32;	author michaeltaft;	state Exp;
branches;
next	1.4;

1.4
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
branches;
next	1.3;

1.3
date	2003.05.22.16.33.32;	author michaeltaft;	state Exp;
branches;
next	1.2;

1.2
date	2003.05.17.19.11.40;	author michaeltaft;	state Exp;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.5
log
@House preferences work.
@
text
@package freejyotish.main.calc;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import freejyotish.main.AllPlanets;
import freejyotish.main.BirthData;
import freejyotish.main.Chart;
import freejyotish.main.HouseData;
import freejyotish.main.IChartCalculator;
/*

* Created 4 April, 2003 by Michael W. Taft

*

*/
public class ChartBuilder implements IChartCalculator {
	private SwissEph sw;
	private Chart chart;
	private AllPlanets allPlanets;
	private BirthData nativeInfo;
	//private CalculationPreferences calcPrefs;
	private HouseData houseData;
	private String zodiac;
	private int ayanamsa;
	private String node;
	private char houseSystem;
	private static final int iflag_SID =
		SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;
	private static final int iflag_TROP = SweConst.SEFLG_SPEED;
	private static int iflag;
	public ChartBuilder() {
		sw = new SwissEph();
		
	}
	public void setCalculationPreferences() {
		
		Preferences prefs = Preferences.userRoot().node("/freejyotish");
		zodiac = prefs.get("mode", "sidereal");
		ayanamsa = prefs.getInt("ayanamsa", 1);
		node = prefs.get("node", "true");
		if (zodiac.equals("tropical"))
			iflag = iflag_TROP;
		else
			iflag = iflag_SID;
			String houseString = prefs.get("houseType", "Shripati");
		if (houseString.equals("Shripati")) houseSystem = 'O';
		else if (houseString.equals("Koch")) houseSystem = 'K';
		else if (houseString.equals("Placidus")) houseSystem = 'P';
		else if (houseString.equals("Alcabitus")) houseSystem = 'B';
		else if (houseString.equals("Regiomontanus")) houseSystem = 'R';
		else if (houseString.equals("Campanus")) houseSystem = 'C';
		else houseSystem = 'O'; //IF ALL ELSE FAILS, USE SHRIPATI HOUSES
	}
	public Chart calculateChart(BirthData ni) {
		nativeInfo = ni;
		try
		{
		BirthDataValidator.checkBirthDataValidity(nativeInfo);
		} 
		catch (FJCalculationInputException fje) 
		{System.out.println(fje);}///Currently catches exceptions, but doesn't stop the calculation. In the future, this needs to stop the calculation.

		chart = new Chart();
		double tjd_ut = getTJD_UT();
		allPlanets =
			AllPlanetsCalculator.calculateAllPlanets(
				sw,
				tjd_ut,
				zodiac,
				ayanamsa,
				node,
				iflag);
		double lon = getDecimalLongitude();
		double lat = getDecimalLatitude();
		houseData =
			HouseDataCalculator.calculateHouseData(
				sw,
				tjd_ut,
				lon,
				lat,
				iflag,
				houseSystem);
		VargaData vargaData =
			VargaDataCalculator.calculateVargaData(
				allPlanets,
				houseData.getAscendant());
		PanchangBasics panchangBasics =
			PanchangBasicsCalculator.calculatePanchangBasics(
				sw,
				tjd_ut,
				lon,
				lat,
				allPlanets.getPlanet("Sun"),
				allPlanets.getPlanet("Moon"));
		AstakavargaCalculator avCalculator = new AstakavargaCalculator();
		Astakavarga AV = avCalculator.calculateAstakavarga(vargaData);
		chart.setNativeInfo2(nativeInfo);
		chart.setPlanetInfo(allPlanets);
		chart.setHouseInfo(houseData);
		chart.setVargaData(vargaData);
		chart.setPanchangBasics(panchangBasics);
		chart.setAstakavarga(AV);
		return chart;
	}
	public double getTJD_UT() //converts local time to the Julian Date of GMT
	{
		int year = nativeInfo.getBirthYear();
		int mon = nativeInfo.getBirthMonth();
		int day = nativeInfo.getBirthDate();
		;
		int hour = nativeInfo.getBirthHour();
		;
		int min = nativeInfo.getBirthMinute();
		int sec = nativeInfo.getBirthSecond();
		double offset = nativeInfo.getTimeZoneOffset();
		int dst = nativeInfo.getDSTOffset();
		Calendar birth =
			new GregorianCalendar(year, mon - 1, day, hour, min, sec);
		birth.add(Calendar.SECOND, (int) (-3600 * (offset + dst)));
		//Changes time to UTC
		double UTHour = (double) birth.get(Calendar.HOUR_OF_DAY);
		double UTMin = (double) birth.get(Calendar.MINUTE);
		double UTSec = (double) birth.get(Calendar.SECOND);
		double ut = UTHour + UTMin / 60 + UTSec / 3600;
		SweDate sd =
			new SweDate(
				birth.get(Calendar.YEAR),
				birth.get(Calendar.MONTH) + 1,
				birth.get(Calendar.DATE),
				ut);
		return sd.getJulDay();
	}
	private double getDecimalLongitude() {
		double longitude =
			(double) Math.abs(nativeInfo.getLongitude_Deg())
				+ ((double) nativeInfo.getLongitude_Min()) / 60
				+ ((double) nativeInfo.getLongitude_Sec()) / 3600;
		if (nativeInfo.getLongitude_Deg() < 0)
			longitude = -1 * longitude;
		//System.out.println("Longitude = " + longitude);///TEST CODE
		return longitude;
	}
	private double getDecimalLatitude() {
		double latitude =
			(double) Math.abs(nativeInfo.getLatitude_Deg())
				+ ((double) nativeInfo.getLatitude_Min()) / 60
				+ ((double) nativeInfo.getLatitude_Sec()) / 3600;
		if (nativeInfo.getLatitude_Deg() < 0)
			latitude = -1 * latitude;
		//System.out.println("Latitude = " + latitude);///TEST CODE
		return latitude;
	}
}
@


1.4
log
@Cleaning up old calculation preferences stuff.
@
text
@d50 8
a57 1
			System.out.println("Calculation Preferences reset.");
@


1.3
log
@Getting new Preferences system working.
@
text
@a24 1
	private AllPlanetsCalculator allPlanetsCalculator;
d26 1
a26 2
	private BirthDataValidator validator;
	private CalculationPreferences calcPrefs;
a27 3
	private VargaDataCalculator vargaDataCalculator;
	private HouseDataCalculator houseDataCalculator;
	private PanchangBasicsCalculator panchangBasicsCalculator;
d38 1
a38 5
		validator = new BirthDataValidator();
		allPlanetsCalculator = new AllPlanetsCalculator();
		houseDataCalculator = new HouseDataCalculator();
		vargaDataCalculator = new VargaDataCalculator();
		panchangBasicsCalculator = new PanchangBasicsCalculator();
d40 2
a41 5
	public void setCalculationPreferences(CalculationPreferences cp) {
		calcPrefs = cp;
		//zodiac = (String) cp.getPreference("mode");
		//ayanamsa = Integer.valueOf((String) cp.getPreference("ayanamsa")).intValue();
		//node = (String) cp.getPreference("node");
a45 1
		//houseSystem = ((String) cp.getPreference("house_system")).charAt(0);
d56 1
a56 1
		validator.checkBirthDataValidity(nativeInfo);
d64 1
a64 1
			allPlanetsCalculator.calculateAllPlanets(
d74 1
a74 1
			houseDataCalculator.calculateHouseData(
d82 1
a82 1
			vargaDataCalculator.calculateVargaData(
d86 1
a86 1
			panchangBasicsCalculator.calculatePanchangBasics(
@


1.2
log
@Working with Exception Handling.
@
text
@d4 2
d51 8
a58 5
		zodiac = (String) cp.getPreference("mode");
		ayanamsa =
			Integer.valueOf((String) cp.getPreference("ayanamsa")).intValue();
		node = (String) cp.getPreference("node");
		houseSystem = ((String) cp.getPreference("house_system")).charAt(0);
d63 1
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a1 9



import freejyotish.main.*;

import swisseph.*;



a2 1

d4 8
a11 3



d19 1
a19 25





///////////////////////////////////////////////////////////////////////////////

///////////////////////// UNDER CONSTRUCTION ///////////////////////////////////////////////////////////////////////////////

/*

*	This is the center of the "Builder" pattern for the calculation section

*	Oversees the creation of a Chart object

*	

*/



public class ChartBuilder implements IChartCalculator

{

a20 1

a21 1

a22 1

a23 1

d25 1
a25 1

a26 1

a27 1

a28 1

a29 1
	
a30 1

a31 1

a32 1

d35 2
a36 5

	

	private static final int iflag_SID = SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;

a37 1

d39 1
a39 11

	

	

	

	public ChartBuilder()

	{

d41 1
a41 1

a42 1

a43 1

a44 1
		
a45 1

d47 1
a47 7

	

	public void setCalculationPreferences(CalculationPreferences cp)

	{

a48 1

d50 2
a51 3

		ayanamsa = Integer.valueOf((String)cp.getPreference("ayanamsa")).intValue();

a52 1

d54 4
a57 11

		if (zodiac.equals("tropical"))iflag = iflag_TROP;

		else iflag = iflag_SID;

		

		

		

d59 1
a59 7

	

	public Chart calculateChart(BirthData ni)

	{

d61 6
a68 1

d70 8
a77 6
		
	
		

		allPlanets = allPlanetsCalculator.calculateAllPlanets(sw, tjd_ut, zodiac, ayanamsa, node, iflag);

a78 1

d80 20
a99 11

		houseData = houseDataCalculator.calculateHouseData(sw, tjd_ut, lon, lat, iflag, houseSystem);

		VargaData vargaData = vargaDataCalculator.calculateVargaData(allPlanets, houseData.getAscendant());
		
		
		PanchangBasics panchangBasics = panchangBasicsCalculator.calculatePanchangBasics(sw, tjd_ut, lon, lat, allPlanets.getPlanet("Sun"), allPlanets.getPlanet("Moon"));
		

		

a101 1
		
a107 2


a108 1

d110 1
a110 5

	

	public double getTJD_UT()//converts local time to the Julian Date of GMT

a111 3

		

a112 1

d114 4
a117 5

		int day = nativeInfo.getBirthDate();;

		int hour = nativeInfo.getBirthHour();;

a118 1

a119 1

a120 1

d122 14
a135 15

		Calendar birth = new GregorianCalendar(year, mon-1, day, hour, min, sec);

		birth.add(Calendar.SECOND, (int) (-3600 * (offset + dst)));//Changes time to UTC

		double UTHour = (double)birth.get(Calendar.HOUR_OF_DAY);

		double UTMin = (double)birth.get(Calendar.MINUTE);

		double UTSec = (double)birth.get(Calendar.SECOND);

		double ut = UTHour + UTMin/60 + UTSec/3600;			

		SweDate sd = new SweDate(birth.get(Calendar.YEAR), birth.get(Calendar.MONTH)+1, birth.get(Calendar.DATE), ut);

a136 3

		

d138 7
a144 11



	private double getDecimalLongitude()

	{

		double longitude = (double)Math.abs(nativeInfo.getLongitude_Deg()) + ((double)nativeInfo.getLongitude_Min())/60 + ((double)nativeInfo.getLongitude_Sec())/3600;

		if(nativeInfo.getLongitude_Deg()<0)longitude = -1*longitude;

a145 1

a146 1

d148 7
a154 11

	

	private double getDecimalLatitude()

	{

		double latitude = (double)Math.abs(nativeInfo.getLatitude_Deg()) + ((double)nativeInfo.getLatitude_Min())/60 + ((double)nativeInfo.getLatitude_Sec())/3600;

		if(nativeInfo.getLatitude_Deg()<0)latitude = -1*latitude;

a155 1

a156 3

		

a157 3



a158 1

@

