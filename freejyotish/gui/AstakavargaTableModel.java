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

import freejyotish.main.ChartCenterEvent;
import freejyotish.main.ChartCenterEventListener;
import freejyotish.main.FJConstants;
import freejyotish.main.IChartCenter;
import freejyotish.main.calc.Astakavarga;

public class AstakavargaTableModel extends AbstractTableModel implements ChartCenterEventListener
{

	private IChartCenter chartCenter;
	private Astakavarga avData;

	private Object[] infoNames = { "Planet", "Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis" };

	private Object[][] allData;

	public AstakavargaTableModel(IChartCenter center)
	{
		chartCenter = center;
		chartCenter.addChartCenterEventListener(this);
		avData = new Astakavarga();

	}
	private void loadModel()
	{
		allData = new String[8][13];

		for (int i = 0; i < 8; i++)
		{
			allData[i][0] = (Object) FJConstants.astakavargaLongNames[i];
			for (int j = 1; j < 13; j++)
			{
				allData[i][j] = (Object) ("" + avData.getAstakavarga()[i][j - 1]);
			}
		}

	}

	public int getColumnCount()
	{
		return infoNames.length;
	}

	public int getRowCount()
	{
		return 8;
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
			avData = chartCenter.getCurrentChart().getAstakavarga();

			loadModel();

		} else
			allData = new String[8][6];
		this.fireTableDataChanged();

	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d14 2
a15 2
private IChartCenter c_center;
private Astakavarga avData;
d17 1
a17 1
private	Object[] infoNames = {(Object)"Planet", (Object) "Ari", (Object) "Tau", 	(Object) "Gem", (Object) "Can", (Object) "Leo", (Object)"Vir", (Object) "Lib", (Object) "Sco", 	(Object) "Sag", (Object) "Cap", (Object) "Aqu", (Object)"Pis"};
d19 1
a19 1
private Object[][] allData;
d21 5
d27 4
d32 1
a32 18
public AstakavargaTableModel(IChartCenter center)
{
	c_center = center;
	c_center.addChartCenterEventListener(this);
	avData = new Astakavarga();
	
}
private void loadModel()
{
	allData = new String[8][13];

	
	
	
	for (int i=0; i<8; i++)
	{
		allData[i][0] = (Object)FJConstants.astakavargaLongNames[i];
		for (int j=1; j<13; j++)
d34 5
a38 1
		allData[i][j] = (Object)("" + avData.getAstakavarga()[i][j-1]);
d40 1
d43 4
a46 1
}
d48 4
d53 2
a54 3
        public int getColumnCount() {
            return infoNames.length;
        }
d56 2
a57 3
        public int getRowCount() {
            return 8;
        }
d59 4
a62 1
        public String getColumnName(int col) {
a63 8
             return (String)infoNames[col];
        }

        public Object getValueAt(int row, int col) {
            return allData[row][col];
        }
	
	
d66 1
a66 1
		if (c_center.getCurrentChart() != null)
d68 6
a73 6
		avData = c_center.getCurrentChart().getAstakavarga();
		
		loadModel();
		
		}
		else allData = new String[8][6];
@

