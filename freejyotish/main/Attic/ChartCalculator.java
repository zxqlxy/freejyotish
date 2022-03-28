head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.07.37.12;	author michaeltaft;	state dead;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@No longer needed.
@
text
@package freejyotish.main;

///TESTING ONLY
import java.util.Calendar;
import java.util.GregorianCalendar;

import swisseph.DblObj;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import freejyotish.main.calc.CalculationPreferences;

/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 15, 2002
 */
public class ChartCalculator implements IChartCalculator
{

		private double lon;
		private double lat;
		private double ut;
		private double offset;
		private int sec;
		private int min;
		private int hour;
		private int mon;
		private int day;
		private int year;
		private String snam = null;
		private String retro = "";
		private StringBuffer serr = new StringBuffer();

		private double tjd;

		private double te;

		private double x2[] = new double[6];
		private long iflgret;

		private int iflag = SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;
		
		private SwissEph sw;
		private BirthData nativeInfo2;
		private ChartCalcPrefs prefs;
		
		//CalcPref variables
		private int ayanamsa;
		private String houseSys;
		private boolean sidereal_ON;
		
		
		
	/**
	 *  Constructor for the ChartCalculator object
	 */
	public ChartCalculator(ChartCalcPrefs ccp) 
	{ 
		prefs = ccp;
		sw = new SwissEph();
	}
	
	public void setCalcPrefs(ChartCalcPrefs ccp)
	{
		prefs = ccp;
	}
	
	public void setCalculationPreferences(CalculationPreferences cp)
	{
		
	}
	private void initCalcPrefs()
	{
		ayanamsa = prefs.getAyanamsa();
		
	}
	
	private double getDecimalLongitude()
	{
		double longitude = (double)Math.abs(nativeInfo2.getLongitude_Deg()) + ((double)nativeInfo2.getLongitude_Min())/60 + ((double)nativeInfo2.getLongitude_Sec())/3600;
		if(nativeInfo2.getLongitude_Deg()<0)longitude = -1*longitude;
		//System.out.println("Longitude = " + longitude);///TEST CODE
		return longitude;
	}
	
	private double getDecimalLatitude()
	{
		double latitude = (double)Math.abs(nativeInfo2.getLatitude_Deg()) + ((double)nativeInfo2.getLatitude_Min())/60 + ((double)nativeInfo2.getLatitude_Sec())/3600;
		if(nativeInfo2.getLatitude_Deg()<0)latitude = -1*latitude;
		//System.out.println("Latitude = " + latitude);///TEST CODE
		return latitude;
		
	}
	
	public Chart calculateChart(BirthData ni2)
	{
		
		nativeInfo2 = ni2;
				
		sw.swe_set_sid_mode(SweConst.SE_SIDM_LAHIRI, 0, 0);
		
		year = nativeInfo2.getBirthYear();
		mon = nativeInfo2.getBirthMonth();
		day = nativeInfo2.getBirthDate();;
		hour = nativeInfo2.getBirthHour();;
		min = nativeInfo2.getBirthMinute();
		sec = nativeInfo2.getBirthSecond();
		offset = nativeInfo2.getTimeZoneOffset();
		int dst = nativeInfo2.getDSTOffset();
		//System.out.println("[Calculator]: offset is: " + offset);
		
		//This new section has been tested and is working PERFECTLY
		//But I think it should be made a separate method (?)
		Calendar birth = new GregorianCalendar(year, mon-1, day, hour, min, sec);
		//System.out.println("[Calculator]: adding: "+ (int)(-3600* offset + dst));
		birth.add(Calendar.SECOND, (int) (-3600 * (offset + dst)));//Changes time to UTC
		
		
		double UTHour = (double)birth.get(Calendar.HOUR_OF_DAY);
		double UTMin = (double)birth.get(Calendar.MINUTE);
		double UTSec = (double)birth.get(Calendar.SECOND);
		ut = UTHour + UTMin/60 + UTSec/3600;
		System.out.println("UT = " + ut);
		///
		///
			
		SweDate sd = new SweDate(birth.get(Calendar.YEAR), birth.get(Calendar.MONTH)+1, birth.get(Calendar.DATE), ut);
		tjd = sd.getJulDay();
			
		te = tjd + sd.getDeltaT(tjd);
		
		Chart newChart = new Chart(nativeInfo2); 
		AllPlanets planets = calculatePlanets();
		HouseData houseData = calculateAscendant();
		newChart.setPlanetInfo(planets);
		newChart.setHouseInfo(houseData);
		//BasicsI bI = new BasicsI();
		
		//bI.setSunrise(getSunrise());
		//getSunset();

		///TESTING ONLY
		System.out.print("date: " + birth.get(Calendar.DATE) + "." + birth.get(Calendar.MONTH)+1 + "." + birth.get(Calendar.YEAR) + " at " + ut + " Universal time\n");
		 ///Castor Testing
		 /* try {
		 File file = new File("castor_test.xml");
		 Writer writer = new FileWriter(file);
		 Marshaller.marshal(newChart, writer);
		
        
			 } catch (IOException ex) {
			    ex.printStackTrace(System.err);
			} catch (MarshalException ex) {
			    ex.printStackTrace(System.err);
			} catch (ValidationException ex) {
			    ex.printStackTrace(System.err);
			}*/
			///TESTING ONLY
		//ChartXMLWriter writer = new ChartXMLWriter();
		//writer.writeChartToXML(newChart, "ChartWriteTest.xml");
		return newChart;
	}

	/**
	 *  Description of the Method
	 *
	 *@@return    Description of the Return Value
	 */
	private AllPlanets calculatePlanets()
	{
		
		AllPlanets planets = new AllPlanets();
		int p;

		///The actual calculation routine begins:
		for (p = SweConst.SE_SUN; p <= 11; p++)
		{
			if (p == SweConst.SE_EARTH)
			{
				continue;
			}
			/*
			 *  Here's the calcultion:
			 */
			iflgret = sw.swe_calc(te, p, (int) iflag, x2, serr);
			/*
			 *  if there is a problem, a negative value is returned and an
			 *  errpr message is in serr.
			 */
			if (iflgret < 0)
			{
				System.out.print("error: " + serr.toString() + "\n");
			}
			else if (iflgret != iflag)
			{
				System.out.print("warning: iflgret != iflag. " + serr.toString() + "\n");
			}
			/*
			 *  get the name of the planet p
			 */
			 Planet planet;
			if (p != 11)
			{
			snam = FJConstants.planetLongNames[p];
			
			planet = new Planet(snam, p);
			planet.setLongitude(x2[0]);//longitude
			planet.setLatitude(x2[1]);//latitude
			planet.setVelocity(x2[3]);//velocity in longitude
			}
			else //creates Ketu
			{
				//System.out.println("Creating Ketu");
				snam = FJConstants.planetLongNames[p];
				planet = new Planet(snam, p);
				double rahuLong = planets.getPlanet("Rahu").getLongitude();
				double ketuLong = (rahuLong+180)%360;
				planet.setLongitude(ketuLong);
				planet.setLatitude(planets.getPlanet("Rahu").getLatitude());
				planet.setVelocity(planets.getPlanet("Rahu").getVelocity());
			}
				
				
			
			//System.out.println("Planet Name = " + planet.getPlanetName());
			//System.out.println("Planet Number = " + planet.getPlanetNumber());
			//System.out.println("Longitude of " + planet.getPlanetName() + " is " + planet.getPlanetData().getPLongitude());
			
			planets.setPlanet(planet);

		}
		///TESTING ONLY
		/*AllPlanetsToXML write = new AllPlanetsToXML();
		write.writeAllPlanetsToXML(planets, "PlanetTest.xml");
		AllPlanetsHandler aph = new AllPlanetsHandler();
		XMLCenter xmlCenter = new XMLCenter();
		xmlCenter.setFileName("PlanetTest.xml");
		xmlCenter.setHandler(aph);
		xmlCenter.readXML();
		AllPlanets testRead = aph.getAllPlanets();
		write.writeAllPlanetsToXML(testRead, "PlanetReadWriteTest.xml");*/
		
		
		return planets;


	}
	private HouseData calculateAscendant()
	{
		double[] cusp = new double[13];
		double[] ascmc = new double[10];
		///eventually these arrays should probably be cloned...
		
		lon = getDecimalLongitude();
		lat = getDecimalLatitude();
		
		sw.swe_houses(tjd, iflag, lat, lon, 'O', cusp, ascmc);//the actual calculation
		System.out.println("Ascendant = " + FJUtility.zodiacDMS(ascmc[0]));
		//System.out.println("Midheaven = " + ascmc[1]);
		HouseData houseData = new HouseData(ascmc, cusp);
		///TESTING ONLY
		/*HouseDataToXML house = new HouseDataToXML();
		house.writeHouseDataToXML(houseData, "HouseTest.xml");
		HouseDataHandler hdh = new HouseDataHandler();
		XMLCenter xmlCenter = new XMLCenter();
		xmlCenter.setFileName("HouseTest.xml");
		xmlCenter.setHandler(hdh);
		xmlCenter.readXML();
		HouseData testRead = hdh.getHouseData();
		house.writeHouseDataToXML(testRead, "HouseDataRWTest.xml");*/
		///TESTING ONLY
		return houseData;
	}

public SweDate getSunrise()//This is a test version
{
	lon = getDecimalLongitude();
	lat = getDecimalLatitude();
	double[] lonLatH = {lon,lat,0};
        DblObj  sunrise = new DblObj();
	StringBuffer star=new StringBuffer();
        sw.swe_rise_trans(te,SweConst.SE_SUN, star,SweConst.SEFLG_SWIEPH,SweConst.SE_CALC_RISE + SweConst.SE_BIT_DISC_CENTER + SweConst.SE_BIT_NO_REFRACTION,lonLatH,0.0,0.0, sunrise,serr);//calculates disc center, no refraction.
        SweDate sr = new SweDate(sunrise.val);
	int sunHour = (int)sr.getHour();
	double rem1 = sr.getHour() - (double)sunHour;
	double sun2 = rem1 * 60.0;
	int sunMin = (int)sun2;
	double rem2 = sun2 - (double)sunMin;
	double sun3 = rem2 * 60.0;
	int sunSec = (int)sun3;
	String sunRise = (sunHour + ":" + sunMin + ":" + sunSec);
	System.out.println("Sunrise at " + sunRise + " GMT");
	return sr;

} 

public SweDate getSunset()//This is a test version
{
	lon = getDecimalLongitude();
	lat = getDecimalLatitude();
	double[] lonLatH = {lon,lat,0};
        DblObj  sunset = new DblObj();
	StringBuffer star=new StringBuffer();
        sw.swe_rise_trans(te,SweConst.SE_SUN, star,SweConst.SEFLG_SWIEPH,SweConst.SE_CALC_SET + SweConst.SE_BIT_DISC_CENTER + SweConst.SE_BIT_NO_REFRACTION,lonLatH,0.0,0.0, sunset,serr);//calculates disc center, no refraction.
        SweDate sr = new SweDate(sunset.val);
	int sunHour = (int)sr.getHour();
	double rem1 = sr.getHour() - (double)sunHour;
	double sun2 = rem1 * 60.0;
	int sunMin = (int)sun2;
	double rem2 = sun2 - (double)sunMin;
	double sun3 = rem2 * 60.0;
	int sunSec = (int)sun3;
	String sunSet = (sunHour + ":" + sunMin + ":" + sunSec);
	System.out.println("Sunset at " + sunSet + " GMT");
	return sr;

} 
	
}

@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

