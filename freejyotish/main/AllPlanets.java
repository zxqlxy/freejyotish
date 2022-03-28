head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.main;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 15, 2002
 */
public class AllPlanets implements Serializable
{

	private HashMap allPlanets;


	/**
	 *  Constructor for the AllPlanets object
	 */
	public AllPlanets()
	{
		allPlanets = new HashMap();
	}


	/**
	 *  Sets the planet attribute of the AllPlanets object
	 *
	 *@@param  planet  The new planet value
	 */
	public void setPlanet(Planet planet)
	{
		allPlanets.put(planet.getPlanetName(), planet);
	}


	/**
	 *  Gets the planet attribute of the AllPlanets object
	 *
	 *@@param  name  Description of the Parameter
	 *@@return       The planet value
	 */
	public Planet getPlanet(String name)
	{
		//System.out.println("Planet is: " + ((Planet) allPlanets.get((Object)name)).getPlanetName());
		return (Planet) allPlanets.get((Object) name);
	}

	public int getSize()
	{
		return allPlanets.size();

	}

	public Iterator getAllPlanets()
	{
		return allPlanets.values().iterator();
		
	}
	
	public Collection getPlanets()
	{
		return allPlanets.values();
	}


}

@
