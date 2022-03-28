head	1.3;
access;
symbols;
locks; strict;
comment	@# @;


1.3
date	2003.05.23.07.55.32;	author michaeltaft;	state Exp;
branches;
next	1.2;

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


1.3
log
@House preferences work.
@
text
@package freejyotish.main;

/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 14, 2002
 */
 
import java.util.TimeZone;
public class FJConstants
{
	/**
	 *  Constructor for the FJConstants object
	 */
	public FJConstants() { }
    /*
    static {
        timeZones = TimeZone.getDefault.getAvailableIDs();
    }
    */

	//Sex of native
	/**
	 *  Native is of undetermined sex
	 */
	public static final int NONE = 0;
	/**
	 *  Native is male
	 */
	public static final int MALE = 1;
	/**
	 *  Native is female
	 */
	public static final int FEMALE = 2;

	//Time Zones
	/**
	 *  Description of the Field
	 */
	public static final String[] timeZones = TimeZone.getAvailableIDs();

	/**
	 *  Description of the Field
	 */
	public static final String[] DSToffsets = {"0", "1", "2"};

	//Names of Signs
	/**
	 *  Description of the Field
	 */
	public static final int ARIES = 1;
	/**
	 *  Description of the Field
	 */
	public static final int TAURUS = 2;
	/**
	 *  Description of the Field
	 */
	public static final int GEMINI = 3;
	/**
	 *  Description of the Field
	 */
	public static final int CANCER = 4;
	/**
	 *  Description of the Field
	 */
	public static final int LEO = 5;
	/**
	 *  Description of the Field
	 */
	public static final int VIRGO = 6;
	/**
	 *  Description of the Field
	 */
	public static final int LIBRA = 7;
	/**
	 *  Description of the Field
	 */
	public static final int SCORPIO = 8;
	/**
	 *  Description of the Field
	 */
	public static final int SAGITTARIUS = 9;
	/**
	 *  Description of the Field
	 */
	public static final int CAPRICORN = 10;
	/**
	 *  Description of the Field
	 */
	public static final int AQUARIUS = 11;
	/**
	 *  Description of the Field
	 */
	public static final int PISCES = 12;

	public static final Object[] signNames = {(Object)"Ari", (Object)"Tau", (Object)"Gem", (Object)"Can", (Object)"Leo", (Object)"Vir", (Object)"Lib", (Object)"Sco", (Object)"Sag", (Object)"Cap", (Object)"Aqu", (Object)"Pis"};

	//Names of Planets
	/**
	 *  Description of the Field
	 */
	public static final int SUN = 0;
	/**
	 *  Description of the Field
	 */
	public static final int MOON = 1;
	/**
	 *  Description of the Field
	 */
	public static final int MERCURY = 2;
	/**
	 *  Description of the Field
	 */
	public static final int VENUS = 3;
	//public static final int EARTH = 4;//this is used for heliocentric calculations
	/**
	 *  Description of the Field
	 */
	public static final int MARS = 4;
	/**
	 *  Description of the Field
	 */
	public static final int JUPITER = 5;
	/**
	 *  Description of the Field
	 */
	public static final int SATURN = 6;
	/**
	 *  Description of the Field
	 */
	public static final int URANUS = 7;
	/**
	 *  Description of the Field
	 */
	public static final int NEPTUNE = 8;
	/**
	 *  Description of the Field
	 */
	public static final int PLUTO = 9;
	/**
	 *  Description of the Field
	 */
	public static final int MEAN_RAHU = 10;
	/**
	 *  Description of the Field
	 */
	public static final int TRUE_RAHU = 11;

	///////////////Natural Planetary Relationship//////////////
	// 0 = neutral
	// 1 = friend
	// -1 = enemy
	// 9 = self
	public static final int[] sunRelations = {9,1,1,0,1,-1,-1};
	public static final int[] moonRelations = {1,9,0,1,0,0,0};
	public static final int[] marsRelations = {1,1,9,-1,1,0,0};
	public static final int[] mercuryRelations = {1,-1,0,9,0,1,0};
	public static final int[] jupiterRelations = {1,1,1,-1,9,-1,0};
	public static final int[] venusRelations = {-1,-1,0,1,0,9,1};
	public static final int[] saturnRelations = {-1,-1,-1,1,0,1,9}; 
	/**
	 *  Description of the Field
	 */
	public static final double navamsaDeg = 3 + (1/3);
	/**
	 *  Description of the Field
	 */
	public static final String planetShortNames[] = {"SU", "MO", "ME", "VE", "MA", "JU", "SA", "UR", "NE", "PL", "RA", "KE"};
	/**
	 *  Description of the Field
	 */
	public static final String planetLongNames[] = {"Sun", "Moon", "Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto", "Rahu", "Ketu"};
	
	public static final String astakavargaLongNames[] = {"Sun", "Moon", "Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Sarva"};
	
	
	/**
	 *  Description of the Field
	 */
	public static final String zodiacShortNames[] = {"Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis"};
	/**
	 *  Description of the Field
	 */
	public static final String zodiacFullNames[] = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
	/**
	 *  Description of the Field
	 */
	public static final double nakshatraDeg = (13.0 + 1.0/3.0);
	/**
	 *  Description of the Field
	 */
	public static final String nakshatra[] = {"Ashwini", "Bharani", "Krittika", "Rohini", "Mrigshira", "Ardra", "Punarvasu", "Pushya", "Ashlesha", "Magha", "Purva Phalguni", "Uttara Phalguni", "Hasta", "Chitra", "Swati", "Vishakha", "Anuradha", "Jyestha", "Mula", "Purva Ashadha", "Uttara Ashadha", "Shravana", "Dhanishta", "Satabhisha", "Purva Bhadrapada", "Uttara Bhadrapada", "Revati", "Ashwini"};

	//Chart styles
	/**
	 *  North Indian-style chart
	 */
	public static final int NORTH = 0;
	/**
	 *  South Indian-style chart
	 */
	public static final int SOUTH = 1;

	//Vargas
	public static final int RASI = 0;

	/**
	 *  Description of the Field
	 */
	public static final int HORA = 1;
	//v2
	/**
	 *  Description of the Field
	 */
	public static final int DREKKANA = 2;
	//v3
	/**
	 *  Description of the Field
	 */
	public static final int CHATURTAMSA = 3;
	//v4
	/**
	 *  Description of the Field
	 */
	public static final int PANCHAMSA = 4;
	//v5
	/**
	 *  Description of the Field
	 */
	public static final int ASHTAMSA = 5;
	//v8
	/**
	 *  Description of the Field
	 */
	public static final int SAPTAMSA = 6;
	//v7
	/**
	 *  Description of the Field
	 */
	public static final int NAVAMSA = 7;
	//v9
	/**
	 *  Description of the Field
	 */
	public static final int DASAMSA = 8;
	//v10
	/**
	 *  Description of the Field
	 */
	public static final int DWADASAMSA = 9;
	//v12
	/**
	 *  Description of the Field
	 */
	public static final int SHODASAMSA = 10;
	//v16
	/**
	 *  Description of the Field
	 */
	public static final int VIMSHAMSA = 11;
	//v20
	/**
	 *  Description of the Field
	 */
	public static final int SIDDHAMSA = 12;
	//v24
	/**
	 *  Description of the Field
	 */
	public static final int BHAMSA = 13;
	//v27
	/**
	 *  Description of the Field
	 */
	public static final int TRIMSHAMSA = 14;
	//v30
	/**
	 *  Description of the Field
	 */
	public static final int KHAVEDAMSA = 15;
	//v40
	/**
	 *  Description of the Field
	 */
	public static final int AKSHVEDASAMSA = 16;
	//v45
	/**
	 *  Description of the Field
	 */
	public static final int SHASTIAMSA = 17;
	//v60
    public static final String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        
	public static final Object[] vargaNames = {(Object)"Rasi", (Object)"Hora", (Object)"Drekkana", (Object)"Chaturtamsa", (Object)"Panchamsa", (Object)"Saptamsa", (Object)"Ashtamsa", (Object)"Navamsa", (Object)"Dasamsa", (Object)"Dwadasamsa", (Object)"Shodasamsa", (Object)"Vimshamsa", (Object)"Siddhamsa", (Object)"Bhamsa", (Object)"Trimshamsa", (Object)"Khavedamsa", (Object)"Akshvedasamsa", (Object)"Shastiamsa"}; 

	public static final Object[] ayanamsaNames = {(Object) "Fagan/Bradley", (Object)"Lahiri", (Object)"Deluce", (Object)"Raman", (Object)"Ushashashi", (Object)"Krishnamurti", (Object)"Dhwaj_Khul", (Object)"Yukteshwar", (Object)"JN_BHASIN"};
	
	public static final String[] ayanamsaStrings = { "Fagan/Bradley", "Lahiri", "Deluce", "Raman", "Ushashashi", "Krishnamurti", "Dhwaj_Khul", "Yukteshwar", "JN_Bhasin"};
	
	
	public static final String[] ascmcValues = {"ascendant", "midheaven", "armc", "vertex", "equitorial_ac", "coascendant_koch", "coascendant_munkasey", "polar_ac", "reserved", "reserved"};
	
	public static final String[] houseSystemStrings = {"Shripati", "Placidus", "Koch", "Alcabitus", "Regiomontanus", "Campanus"};
	
	public static final char[] houseSystemChars = {'O', 'P', 'K', 'A', 'R', 'C'}; //These chars must be keyed to the Strings
	
	
	///These constants specifically for the AstroGadget font.
	public static final String[] planetGlyphs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
	///These constants specifically for the AstroGadget font.
	public static final String[] signGlyphs = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
	
	///These constants specifically for the Astro font.
	public static final String[] simpleSignGlyphs = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
	///These constants specifically for the Astro font.
	public static final String[] simplePlanetGlyphs = {"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "<", ">"};
	
	
	public static final String[] stateAbbr_USA = {"AL", "AK", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};
	
	public static final String[] stateAbbr_CANADA = {"AB", "BC", "MB", "NB", "NF", "NT", "NS", "NU", "ON", "PE", "QC", "SK", "YT"};
}

@


1.2
log
@Cleaning up old calculation preferences stuff.
@
text
@d305 1
a305 1
	public static final String[] houseSystemStrings = {"ShriPati", "Placidus", "Koch", "Alcabitus", "Regiomontanus", "Campanus", "Equal"};
d307 1
a307 1
	public static final char[] houseSystemChars = {'O', 'P', 'K', 'A', 'R', 'C', 'E'}; //These chars must be keyed to the Strings
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d41 1
a41 1
	public static final String[] timeZones = TimeZone.getDefault().getAvailableIDs();
@

