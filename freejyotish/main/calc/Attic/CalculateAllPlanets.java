head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.07.34.07;	author michaeltaft;	state dead;
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
@Files no longer required.
@
text
@package freejyotish.main.calc;
import swisseph.*;
import freejyotish.main.*;

/*
*	Created 5 April, 2003 by Michael W. Taft
*
*/



public class CalculateAllPlanets
{
	
	private static AllPlanets allPlanets;
	//private SwissEph sw;
	//private double te;///REQUIRES EPHEMERIS TIME (SEE SWISSEPH DOCS)
	
	private static String snam = null;
	private static String retro = "";
	private static StringBuffer serr = new StringBuffer();
	private static double x2[] = new double[6];
	private static long iflgret;
	private static int iflag = SweConst.SEFLG_SIDEREAL + SweConst.SEFLG_NONUT + SweConst.SEFLG_SPEED;
	
	/*public CalculateAllPlanets(SwissEph swiss, double eTime)
	{
		sw = swiss;
		te = eTime;
		
	}*/
	
	public static AllPlanets calculateAllPlanets(SwissEph sw, double te)
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
				
			
			planets.setPlanet(planet);

		}
		
		return planets;
	
	}
	/*private static HouseData calculateAscendant(SwissEph sw, double tjd)
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
		return houseData;
	}*/


}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

