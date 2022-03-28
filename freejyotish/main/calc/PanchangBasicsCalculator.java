head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
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
@Cleaning up old calculation preferences stuff.
@
text
@package freejyotish.main.calc;
import java.util.GregorianCalendar;
import swisseph.DblObj;
import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;
import freejyotish.main.Planet;
/*
*	Created by Michael W. Taft, 25 March, 2003
*/
public class PanchangBasicsCalculator {
	private static String yoga, tithi, karana;
	private static double diff,
		sum,
		location_lon,
		location_lat,
		sunLon,
		moonLon;
	private static PanchangBasics panch_basics;
	//private static SwissEph sw;
	public static PanchangBasics calculatePanchangBasics(
		SwissEph sw,
		double tjd_ut,
		double location_lon,
		double location_lat,
		Planet sun,
		Planet moon) {
		panch_basics = new PanchangBasics();
		sunLon = sun.getLongitude();
		moonLon = moon.getLongitude();
		diff = getDiff(sunLon, moonLon);
		sum = getSum(sunLon, moonLon);
		panch_basics.setYoga(calculateYoga(sum));
		panch_basics.setTithi(calculateTithi(diff));
		panch_basics.setKarana(calculateKarana(diff));
		panch_basics.setSunrise(
			calculateSunrise(sw, location_lon, location_lat, tjd_ut));
		panch_basics.setSunset(
			calculateSunset(sw, location_lon, location_lat, tjd_ut));
		panch_basics.setAyanamsa(calculateAyanamsa(sw, tjd_ut));
		return panch_basics;
	}
	private static double getDiff(double sunLon, double moonLon) {
		double diff = moonLon - sunLon;
		if (diff < 0)
			diff = diff + 360;
		return diff;
	}
	private static double getSum(double sunLon, double moonLon) {
		double sum = moonLon + sunLon;
		return sum % 360;
	}
	public static String calculateTithi(double diff) {
		int ti = (int) (diff / 12);
		//classical calculation involves adding a one here; stripped because of array positions
		String[] tithiNames =
			{
				"1. Pratipat",
				"2. Dvitiya",
				"3. Tritiya",
				"4. Chaturthi",
				"5. Panchami",
				"6. Shashti",
				"7. Saptami",
				"8. Ashtami",
				"9. Navami",
				"10. Dashami",
				"11. Ekadashi",
				"12. Dvadashi",
				"13. Trayodashi",
				"14. Chaturdashi",
				"15. Purnima",
				"1. Pratipat",
				"2. Dvitiya",
				"3. Tritiya",
				"4. Chaturthi",
				"5. Panchami",
				"6. Shashti",
				"7. Saptami",
				"8. Ashtami",
				"9. Navami",
				"10. Dashami",
				"11. Ekadashi",
				"12. Dvadashi",
				"13. Trayodashi",
				"14. Chaturdashi",
				"15. Amavasya" };
		if (ti < 15)
			tithi = tithiNames[ti] + ", " + "Shukla paksha";
		else if (15 <= ti)
			tithi = tithiNames[ti] + ", " + "Krishna paksha";
		return tithi;
	}
	public static String calculateKarana(double diff) {
		int ka = (int) (diff / 6);
		//classical calculation involves adding a one here; stripped because of array positions
		String[] karanaNames =
			{
				"1. Kinstughna",
				"2. Bava",
				"3. Balava",
				"4. Kaulava",
				"5. Taitila",
				"6. Gara",
				"7. Vanija",
				"8. Vishti",
				"9. Bava",
				"10. Balava",
				"11. Kaulava",
				"12. Taitila",
				"13. Gara",
				"14. Vanija",
				"15. Vishti",
				"16. Bava",
				"17. Balava",
				"18. Kaulava",
				"19. Taitila",
				"20. Gara",
				"21. Vanija",
				"22. Vishti",
				"23. Bava",
				"24. Balava",
				"24. Kaulava",
				"26. Taitila",
				"27. Gara",
				"28. Vanija",
				"29. Vishti",
				"30. Bava",
				"31. Balava",
				"32. Kaulava",
				"33. Taitila",
				"34. Gara",
				"35. Vanija",
				"36. Vishti",
				"37. Bava",
				"38. Balava",
				"39. Kaulava",
				"40. Taitila",
				"41. Gara",
				"42. Vanija",
				"43. Vishti",
				"44. Bava",
				"45. Balava",
				"46. Kaulava",
				"47. Taitila",
				"48. Gara",
				"49. Vanija",
				"50. Vishti",
				"51. Bava",
				"52. Balava",
				"53. Kaulava",
				"54. Taitila",
				"55. Gara",
				"56. Vanija",
				"57. Vishti",
				"58. Shakuni",
				"Chatushpada",
				"Naga" };
		return karanaNames[ka];
	}
	public static String calculateYoga(double sum) {
		int yo = (int) (sum / (13 + 1 / 3));
		String[] yogaNames =
			{
				"1. Vishkambha",
				"2. Priti",
				"3. Ayushman",
				"4. Saubhagya",
				"5. Shobhana",
				"6. Atiganda",
				"7. Sukarma",
				"8. Dhriti",
				"9. Shula",
				"10. Ganda",
				"11. Vriddhi",
				"12. Dhruva",
				"13. Vyaghata",
				"14. Harshana",
				"15. Vajra",
				"16. Siddhi",
				"17. Vyatipat",
				"18. Variyana",
				"19. Parigha",
				"20. Shiva",
				"21. Siddha",
				"22. Sadhya",
				"23. Shubha",
				"24. Shukla",
				"25. Brahma",
				"26. Indra",
				"27. Vaidhriti" };
		return yogaNames[yo];
	}
	public static GregorianCalendar calculateSunrise(
		SwissEph sw,
		double lon,
		double lat,
		double tjd_ut) {
		double[] lonLatH = { lon, lat, 0 };
		DblObj sunrise = new DblObj();
		StringBuffer star = new StringBuffer();
		sw.swe_rise_trans(
			tjd_ut,
			SweConst.SE_SUN,
			star,
			SweConst.SEFLG_SWIEPH,
			SweConst.SE_CALC_RISE
				+ SweConst.SE_BIT_DISC_CENTER
				+ SweConst.SE_BIT_NO_REFRACTION,
			lonLatH,
			0.0,
			0.0,
			sunrise,
			star);
		//calculates disc center, no refraction.
		SweDate sr = new SweDate(sunrise.val);
		int sunHour = (int) sr.getHour();
		double rem1 = sr.getHour() - (double) sunHour;
		double sun2 = rem1 * 60.0;
		int sunMin = (int) sun2;
		double rem2 = sun2 - (double) sunMin;
		double sun3 = rem2 * 60.0;
		int sunSec = (int) sun3;
		GregorianCalendar sunriseGreg =
			new GregorianCalendar(
				(int) sr.getYear(),
				(int) sr.getMonth(),
				(int) sr.getDay(),
				sunHour,
				sunMin,
				sunSec);
		System.out.println(
			"[PanchangBasics]: Sunrise = "
				+ (int) sr.getYear()
				+ ":"
				+ (int) sr.getMonth()
				+ ":"
				+ (int) sr.getDay()
				+ ":"
				+ sunHour
				+ ":"
				+ sunMin
				+ ":"
				+ sunSec);
		return sunriseGreg;
	}
	public static GregorianCalendar calculateSunset(
		SwissEph sw,
		double lon,
		double lat,
		double tjd_ut) {
		double[] lonLatH = { lon, lat, 0 };
		DblObj sunset = new DblObj();
		StringBuffer star = new StringBuffer();
		sw.swe_rise_trans(
			tjd_ut,
			SweConst.SE_SUN,
			star,
			SweConst.SEFLG_SWIEPH,
			SweConst.SE_CALC_SET
				+ SweConst.SE_BIT_DISC_CENTER
				+ SweConst.SE_BIT_NO_REFRACTION,
			lonLatH,
			0.0,
			0.0,
			sunset,
			star);
		//calculates disc center, no refraction.
		SweDate ss = new SweDate(sunset.val);
		int sunHour = (int) ss.getHour();
		double rem1 = ss.getHour() - (double) sunHour;
		double sun2 = rem1 * 60.0;
		int sunMin = (int) sun2;
		double rem2 = sun2 - (double) sunMin;
		double sun3 = rem2 * 60.0;
		int sunSec = (int) sun3;
		GregorianCalendar sunsetGreg =
			new GregorianCalendar(
				(int) ss.getYear(),
				(int) ss.getMonth(),
				(int) ss.getDay(),
				sunHour,
				sunMin,
				sunSec);
		System.out.println(
			"[PanchangBasics]: Sunset = "
				+ (int) ss.getYear()
				+ ":"
				+ (int) ss.getMonth()
				+ ":"
				+ (int) ss.getDay()
				+ ":"
				+ sunHour
				+ ":"
				+ sunMin
				+ ":"
				+ sunSec);
		return sunsetGreg; ///TESTING ONLY
	}
	public static double calculateAyanamsa(SwissEph sw, double tjd_ut) {
		return sw.swe_get_ayanamsa_ut(tjd_ut);
	}
}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a1 1

a2 1

a7 1

d11 1
a11 3

public class PanchangBasicsCalculator
{
d13 6
a18 1
	private static double diff, sum, location_lon, location_lat, sunLon, moonLon;
d21 7
a27 4

	public static PanchangBasics calculatePanchangBasics(SwissEph sw, double tjd_ut, double location_lon, double location_lat, Planet sun, Planet moon)
	{
		
d36 4
a39 2
		panch_basics.setSunrise(calculateSunrise(sw, location_lon, location_lat, tjd_ut));
		panch_basics.setSunset(calculateSunset(sw, location_lon, location_lat, tjd_ut));
d43 1
a43 3
	
	private static double getDiff(double sunLon, double moonLon)
	{
d45 2
a46 1
		if (diff<0)diff = diff + 360;
d49 1
a49 3
	
	private static double getSum(double sunLon, double moonLon)
	{
d51 1
a51 1
		return sum%360;	
d53 163
a215 42

public static String calculateTithi(double diff)
{
    int ti = (int)(diff/12);//classical calculation involves adding a one here; stripped because of array positions
    
    String[] tithiNames = {"1. Pratipat","2. Dvitiya","3. Tritiya","4. Chaturthi", "5. Panchami","6. Shashti","7. Saptami","8. Ashtami","9. Navami","10. Dashami", "11. Ekadashi","12. Dvadashi","13. Trayodashi","14. Chaturdashi","15. Purnima",
    "1. Pratipat","2. Dvitiya","3. Tritiya","4. Chaturthi","5. Panchami","6. Shashti", "7. Saptami","8. Ashtami","9. Navami","10. Dashami","11. Ekadashi","12. Dvadashi","13. Trayodashi","14. Chaturdashi","15. Amavasya"};
    
    if (ti<15)tithi = tithiNames[ti] + ", " + "Shukla paksha";
    else if (15<=ti)tithi = tithiNames[ti] + ", " + "Krishna paksha";
    return tithi;
}

public static String calculateKarana(double diff)
{
    int ka = (int)(diff/6);//classical calculation involves adding a one here; stripped because of array positions
    String[] karanaNames = {"1. Kinstughna", "2. Bava", "3. Balava","4. Kaulava","5. Taitila","6. Gara","7. Vanija","8. Vishti",
    "9. Bava", "10. Balava","11. Kaulava","12. Taitila","13. Gara","14. Vanija","15. Vishti","16. Bava", "17. Balava","18. Kaulava","19. Taitila",
    "20. Gara","21. Vanija","22. Vishti","23. Bava", "24. Balava","24. Kaulava","26. Taitila","27. Gara","28. Vanija","29. Vishti", "30. Bava",

    "31. Balava","32. Kaulava","33. Taitila","34. Gara","35. Vanija","36. Vishti","37. Bava","38. Balava","39. Kaulava","40. Taitila","41. Gara",
    "42. Vanija","43. Vishti", "44. Bava", "45. Balava","46. Kaulava","47. Taitila","48. Gara","49. Vanija","50. Vishti","51. Bava","52. Balava",
    "53. Kaulava","54. Taitila","55. Gara","56. Vanija","57. Vishti","58. Shakuni","Chatushpada","Naga"};
    return karanaNames[ka];
}

public static String calculateYoga(double sum)
{
    
    int yo = (int)(sum/(13 + 1/3));
    String[] yogaNames = {"1. Vishkambha","2. Priti","3. Ayushman","4. Saubhagya", "5. Shobhana","6. Atiganda","7. Sukarma","8. Dhriti","9. Shula","10. Ganda","11. Vriddhi", "12. Dhruva","13. Vyaghata","14. Harshana","15. Vajra","16. Siddhi","17. Vyatipat","18. Variyana","19. Parigha","20. Shiva","21. Siddha","22. Sadhya","23. Shubha","24. Shukla",
    "25. Brahma","26. Indra","27. Vaidhriti"};
    return yogaNames[yo];

}

public static GregorianCalendar calculateSunrise(SwissEph sw, double lon, double lat, double tjd_ut)
	{
		double[] lonLatH = {lon,lat,0};
		DblObj  sunrise = new DblObj();
		StringBuffer star=new StringBuffer();
		sw.swe_rise_trans(tjd_ut,SweConst.SE_SUN, star,SweConst.SEFLG_SWIEPH,SweConst.SE_CALC_RISE + SweConst.SE_BIT_DISC_CENTER + SweConst.SE_BIT_NO_REFRACTION,lonLatH,0.0,0.0, sunrise,star);//calculates disc center, no refraction.
d217 2
a218 2
		int sunHour = (int)sr.getHour();
		double rem1 = sr.getHour() - (double)sunHour;
d220 2
a221 2
		int sunMin = (int)sun2;
		double rem2 = sun2 - (double)sunMin;
d223 22
a244 3
		int sunSec = (int)sun3;
		GregorianCalendar sunriseGreg = new GregorianCalendar((int)sr.getYear(), (int)sr.getMonth(), (int)sr.getDay(), sunHour, sunMin, sunSec);
		System.out.println("[PanchangBasics]: Sunrise = " + (int)sr.getYear() + ":" +  (int)sr.getMonth() + ":" + (int)sr.getDay() + ":" + sunHour+ ":" + sunMin+ ":" + sunSec);
d247 22
a268 7
	
public static GregorianCalendar calculateSunset(SwissEph sw, double lon, double lat, double tjd_ut)
	{
		double[] lonLatH = {lon,lat,0};
		DblObj  sunset = new DblObj();
		StringBuffer star=new StringBuffer();
		sw.swe_rise_trans(tjd_ut,SweConst.SE_SUN, star,SweConst.SEFLG_SWIEPH,SweConst.SE_CALC_SET + SweConst.SE_BIT_DISC_CENTER + SweConst.SE_BIT_NO_REFRACTION,lonLatH,0.0,0.0, sunset,star);//calculates disc center, no refraction.
d270 2
a271 2
		int sunHour = (int)ss.getHour();
		double rem1 = ss.getHour() - (double)sunHour;
d273 2
a274 2
		int sunMin = (int)sun2;
		double rem2 = sun2 - (double)sunMin;
d276 27
a302 14
		int sunSec = (int)sun3;
		GregorianCalendar sunsetGreg = new GregorianCalendar((int)ss.getYear(), (int)ss.getMonth(), (int)ss.getDay(), sunHour, sunMin, sunSec);
		System.out.println("[PanchangBasics]: Sunset = " + (int)ss.getYear() + ":" +  (int)ss.getMonth() + ":" + (int)ss.getDay() + ":" + sunHour+ ":" + sunMin+ ":" + sunSec);
		return sunsetGreg;///TESTING ONLY
	}
	
public static double calculateAyanamsa(SwissEph sw, double tjd_ut)
{
	return sw.swe_get_ayanamsa_ut(tjd_ut);
	
}



@

