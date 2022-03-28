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

import java.io.Serializable;
/*
* created 14 Feb, 2003, Michael W. Taft
*
*
*/

public class ChartCalcPrefs implements Serializable
{
	private int ayanamsa;
	private String house_system;
	private boolean sidereal_ON;
	


	public ChartCalcPrefs(int ay, String hs, boolean sd)
	{
		ayanamsa = ay;
		house_system = hs;
		sidereal_ON = sd;
	}
	
	public ChartCalcPrefs()
	{
	}

		public void setAyanamsa(int ayanamsa) {
			this.ayanamsa = ayanamsa;
		}
		public void setHouse_system(String house_system) {
			this.house_system = house_system;
		}
		public void setSidereal_ON(boolean sidereal_ON) {
			this.sidereal_ON = sidereal_ON;
		}
		
		
		public int getAyanamsa() {
			return ayanamsa;
		}
		public String getHouse_system() {
			return house_system;
		}
		public boolean getSidereal_ON() {
			return sidereal_ON;
		}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

