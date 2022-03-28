head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;

import freejyotish.main.BirthData;
import freejyotish.main.ChartCenterEvent;
import freejyotish.main.ChartCenterEventListener;
import freejyotish.main.IChartCenter;


//First created 24 February, 2003 by Michael W. Taft


public class FJListPanel extends JPanel implements ChartCenterEventListener
{
	private IChartCenter c_center;
	
	protected JList nativeList;
	
	private Vector elements = new Vector();
	private JScrollPane listPane;
	private Border etch;
	private BirthData ni2;
	protected BirthData selected;
	private SimpleInputDialog input;
	private JLabel chartLabel;
	private JButton sortSwitch;
	private int recordNumber;
	
	
	public FJListPanel(IChartCenter cent)
	{
		c_center = cent;
		setLayout(new BorderLayout());
		etch = BorderFactory.createEtchedBorder();
		c_center.addChartCenterEventListener(this);
		setBorder(etch);
        setBackground( new Color( 0, 153, 153 ) );
        Font fPl = FJGUIDefs.getInstance().getNormalFont();
        Font fB = FJGUIDefs.getInstance().getNormalBoldFont();
		nativeList = new JList();
		loadList();
        nativeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nativeList.setFixedCellWidth(150);
        nativeList.setFont( fPl );
        nativeList.setForeground( Color.DARK_GRAY );
		nativeList.addMouseListener( new FJListMouseListener() );
		listPane = new JScrollPane(nativeList);
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(1,2));
		header.setOpaque(false);
	chartLabel = new JLabel( "Charts" );
        chartLabel.setForeground( Color.WHITE );
        chartLabel.setFont( fB );
	sortSwitch = new JButton("Sort up");
	sortSwitch.setForeground( Color.WHITE );
	sortSwitch.setOpaque(false);
	sortSwitch.setFont( fB );
	/*sortSwitch.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Collections.sort(elements, Collections.reverseOrder());
					
				}
			});*/
	header.add(chartLabel);
	//header.add(sortSwitch);
	
        add(header, BorderLayout.NORTH );
		add( buttonBox( fPl ), BorderLayout.SOUTH);
		add( listPane, BorderLayout.CENTER);
	chartLabel.setText("Charts ( " + recordNumber + " )");	
	}
	
	public void loadList()
	{
		recordNumber = 0;
		elements.clear();
		//Iterator iter = c_center.getAllCharts();
		Iterator iter = c_center.getAllNatives();
		while (iter.hasNext())
			{
				//Chart chart = (Chart) iter.next();
				
				ni2 = (BirthData) iter.next();
				elements.add(ni2);
				recordNumber++;
			}
			Collections.sort(elements, Collections.reverseOrder());
		nativeList.setListData(elements);
		
		if(c_center.getCurrentChart() != null)
		{
		nativeList.setSelectedValue(
		c_center.getCurrentChart().getNativeInfo2(), true );
		}
	}
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		//System.out.println("chartCenterChanged() heard by FJListPanel");
		loadList();
		chartLabel.setText("Charts ( " + recordNumber + " )");
	}
	
	public BirthData getSelected()
	{
		if (selected != null) {return selected;}
		else return new BirthData();//FOR TESTING ONLY - build some decent error code later
	}
	
	class FJListMouseListener extends MouseAdapter
	{
		/**
		 *  Description of the Method
		 *
		 *@@param  evt  Description of the Parameter
		 */
		public void mouseClicked( MouseEvent me )
		{
            if( me.getClickCount() == 2 ) {
                showSelectedChart( me.getPoint(), true );
            }
            /*
			JList src = (JList) evt.getSource();
			Object selectedNative = src.getSelectedValue();
			if (selectedNative == null) return;
			selected = (BirthData) selectedNative;
			c_center.setCurrentChart( new Chart( selected ) );
			System.out.println("Source is " + selected.toString());// FOR TESTING ONLY
            */
			
		}

	}
    
    private void showSelectedChart( Point clickPoint, boolean doubleClicked ) {
        //System.out.println("calc " + selected.toString());
        if( doubleClicked ) {
            int idx = nativeList.locationToIndex( clickPoint );
            selected = ( BirthData ) ( ( ListModel ) nativeList.getModel() ).getElementAt( idx
            );
        } else {
			Object selectedNative = nativeList.getSelectedValue();
			if (selectedNative == null) return;
			selected = (BirthData) selectedNative;
        }
	 this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        c_center.createChart(getSelected());
	this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

private Box buttonBox( Font f )
{
	JButton calc, del, newChart, editChart;
	
		
		Box box = Box.createHorizontalBox();
		
		calc = new JButton("Calc");
        calc.setFont( f );
        calc.setMnemonic( 'C' );
		del = new JButton("Del");
        del.setFont( f );
        del.setMnemonic( 'D' );
		newChart = new JButton("New");
		newChart.setFont(f);
		newChart.setMnemonic( 'N' );
		editChart = new JButton("Edit");
		editChart.setFont(f);
		editChart.setMnemonic( 'E' );
		
			calc.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
                    showSelectedChart( null, false );
				}
			});
		del.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	c_center.deleteChart(selected.toString());
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					//System.out.println("delete " + selected.toString());
				}
			});
			
		newChart.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					 if( input == null ) {

                input = new SimpleInputDialog(null, c_center, 0 );

            }

			input.setModal(true);

            //input.centerDialog( this );

			input.showDialog();

			//save or save & calculate new chart

            if( input.isCancelled() ) return;

            BirthData nInfo = input.getNativeInfo();

            if( nInfo != null ) {

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		c_center.createChart( nInfo );
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }

			repaint();

		}
				
			});
			
			
			
			editChart.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					 if( input == null ) {

                input = new SimpleInputDialog(null, c_center, 1 );

            }

			input.setModal(true);

            //input.centerDialog( this );

			input.showDialog();

			//save or save & calculate new chart

            if( input.isCancelled() ) return;

            BirthData nInfo = input.getNativeInfo();

            if( nInfo != null ) {

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		c_center.createChart( nInfo );
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }

			repaint();

		}
				
			});
			
		
		box.add(calc);
		//box.add(editChart);
		box.add(del);
		box.add(newChart);
		return box;
		
		
}
	
	
}
	
	

		
		
		

	
	
	





@
