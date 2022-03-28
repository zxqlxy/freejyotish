head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.18.11.59.54;	author santoshs;	state Exp;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@Basic I18N support for FJ in the calc prefs dialog.
@
text
@package freejyotish.gui;

import javax.swing.table.AbstractTableModel;

import freejyotish.main.*;

public class BasicTableModel extends AbstractTableModel implements ChartCenterEventListener
{

	private IChartCenter chartCenter;
	private AllPlanets allPlanets;
	private HouseData houseData;

	private Object[] infoNames = { "Planet", "Longitude", "Dir", "Nakshatra", "Latitude", "Velocity" };

	private Object[][] allData;

	public BasicTableModel(IChartCenter center)
	{
		chartCenter = center;
		chartCenter.addChartCenterEventListener(this);
		allPlanets = new AllPlanets();
		houseData = new HouseData();
		//allPlanets = c_center.getCurrentChart().getPlanetInfo();
		//houseData = c_center.getCurrentChart().getHouseData();
		loadModel();

	}
	private void loadModel()
	{
		allData = new String[allPlanets.getSize()][6];

		//allData[0][0] = FJUtility.zodiacDMS(houseData.getAscendant());

		for (int i = 0; i < allPlanets.getSize(); i++)
		{
			allData[i][0] = allPlanets.getPlanet(FJConstants.planetLongNames[i]).getPlanetName();
			allData[i][1] = FJUtility.zodiacDMS(allPlanets.getPlanet(FJConstants.planetLongNames[i]).getLongitude());
			allData[i][2] = allPlanets.getPlanet(FJConstants.planetLongNames[i]).getRetrograde();
			allData[i][3] = allPlanets.getPlanet(FJConstants.planetLongNames[i]).getNakshatraAndPada();
			allData[i][4] = FJUtility.toDMS(allPlanets.getPlanet(FJConstants.planetLongNames[i]).getLatitude());
			allData[i][5] = allPlanets.getPlanet(FJConstants.planetLongNames[i]).getVelocityString();
		}

	}

	public int getColumnCount()
	{
		return infoNames.length;
	}

	public int getRowCount()
	{
		return allPlanets.getSize();
	}

	public String getColumnName(int col)
	{

		return (String) infoNames[col];
	}

	public Object getValueAt(int row, int col)
	{
		return allData[row][col];
	}

	public void chartCenterChanged(ChartCenterEvent e)
	{
		if (chartCenter.getCurrentChart() != null)
		{
			allPlanets = chartCenter.getCurrentChart().getPlanetInfo();
			houseData = chartCenter.getCurrentChart().getHouseData();
			loadModel();

		} else
			allData = new String[allPlanets.getSize()][6];
		this.fireTableDataChanged();

	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d10 3
a12 3
private IChartCenter c_center;
private AllPlanets allPlanets;
private HouseData houseData;
d14 1
a14 1
private	Object[] infoNames = {(Object) "Planet", (Object) "Longitude", 	(Object) "Dir", (Object) "Nakshatra", (Object) "Latitude", (Object)"Velocity"};
d16 1
a16 1
private Object[][] allData;
d18 14
d33 1
d35 9
a43 14
public BasicTableModel(IChartCenter center)
{
	c_center = center;
	c_center.addChartCenterEventListener(this);
	allPlanets = new AllPlanets();
	houseData = new HouseData();
	//allPlanets = c_center.getCurrentChart().getPlanetInfo();
	//houseData = c_center.getCurrentChart().getHouseData();
	loadModel();
	
}
private void loadModel()
{
	allData = new String[allPlanets.getSize()][6];
d45 3
a47 4
	
	//allData[0][0] = FJUtility.zodiacDMS(houseData.getAscendant());
	
	for (int i=0; i<allPlanets.getSize(); i++)
d49 1
a49 6
		allData[i][0] = (Object)allPlanets.getPlanet(FJConstants.planetLongNames[i]).getPlanetName();
		allData[i][1] = (Object)FJUtility.zodiacDMS(allPlanets.getPlanet(FJConstants.planetLongNames[i]).getLongitude());
		allData[i][2] = (Object)allPlanets.getPlanet(FJConstants.planetLongNames[i]).getRetrograde();
		allData[i][3] = (Object)allPlanets.getPlanet(FJConstants.planetLongNames[i]).getNakshatraAndPada();
		allData[i][4] =  (Object)FJUtility.toDMS(allPlanets.getPlanet(FJConstants.planetLongNames[i]).getLatitude());
		allData[i][5] = (Object)allPlanets.getPlanet(FJConstants.planetLongNames[i]).getVelocityString();
d52 4
a55 1
}
d57 2
d60 2
a61 3
        public int getColumnCount() {
            return infoNames.length;
        }
d63 4
a66 3
        public int getRowCount() {
            return allPlanets.getSize();
        }
a67 10
        public String getColumnName(int col) {

             return (String)infoNames[col];
        }

        public Object getValueAt(int row, int col) {
            return allData[row][col];
        }
	
	
d70 1
a70 1
		if (c_center.getCurrentChart() != null)
d72 6
a77 6
		allPlanets = c_center.getCurrentChart().getPlanetInfo();
		houseData = c_center.getCurrentChart().getHouseData();
		loadModel();
		
		}
		else allData = new String[allPlanets.getSize()][6];
@

