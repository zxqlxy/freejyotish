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
@/*

*

* $Id: FJSplit.java,v 1.1 2003/05/17 08:18:58 santoshs Exp $

*

*/



package freejyotish.gui;



import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.*;

import freejyotish.main.*;
import freejyotish.main.calc.VargaData;
import freejyotish.store.IFJStorage;



/**

 * @@author Uday Mashalkar

 */

public class FJSplit extends JFrame implements ChartCenterEventListener

, ActionListener {

    private IChartCenter c_center;

    private FJListPanel listPanel;

    private BasicTableModel planetInfoModel;

    private BasicTablePanel planetInfoPanel;

    	private FJNativeDisplay nativePanel;
	
	//private FJHouseDataDisplay houseDataPanel;

    private VimPanel dashaPanel;

    private HoroscopeCanvas horo;

	private JMenuBar menuBar;

	private JMenu fileMenu, viewMenu, optionsMenu, helpMenu;

	private JMenuItem testItem, newItem, exitItem, recalcItem, styleItem

    , prefsItem, helpItem, aboutItem, debugItem;

	private JRadioButtonMenuItem northStyle, southStyle, calcPrefs;

	private ButtonGroup styleGroup;

    private Horoscope t1, t2;

    private int horoStyle;

    private Chart testChart1;

    private HoroPanel horoPanel;

    private FJGUIDefs fjDefs;

    private IFJStorage storage;

    private static SimpleInputDialog input;

    private DebugWindow debug;

    private OutputStream out;



    public FJSplit( IChartCenter center, FJSplash jw, IFJStorage store ) {

        out = null;

        try {

            out = new FileOutputStream( "fj.log", false );
	    PrintStream pstr = new PrintStream( out );
	    System.setOut( pstr );
	    System.setErr( pstr );

        } catch( Exception e ) {

            System.out.println( "Error : " + e.getMessage() );
        }



        fjDefs = FJGUIDefs.getInstance();
	jw.setMessage( "Initializing fonts..." );
	c_center = center;

        c_center.addChartCenterEventListener( this );

	storage = store;

        setSize( 800, 700 );

	setTitle( "FreeJyotish Open-Source Vedic Astrology" );

        addWindowListener(

            new WindowAdapter() {

                public void windowClosing( WindowEvent e ) {

                    System.exit( 0 );

                }

        });

        getContentPane().setLayout( new BorderLayout() );

        

        JPanel eastPanel = new JPanel();

        eastPanel.setLayout( new BoxLayout( eastPanel, BoxLayout.Y_AXIS ) );

        //listPanel = new FJListPanel( c_center );

        //jw.setMessage( "Loading availabel charts..." );

        //eastPanel.add( listPanel );

        //eastPanel.add( Box.createVerticalStrut( 5 ) );

       // nativePanel = new FJNativeDisplay( c_center );

        //eastPanel.add( nativePanel );

       // eastPanel.add( Box.createVerticalStrut( 5 ) );

        dashaPanel = new VimPanel( c_center );

        jw.setMessage( "Loading dasha panel..." );

        eastPanel.add( dashaPanel );

        eastPanel.setBorder( BorderFactory.createEtchedBorder() );

        getContentPane().add( eastPanel, BorderLayout.EAST );



        planetInfoModel = new BasicTableModel( c_center );

        planetInfoPanel = new BasicTablePanel( planetInfoModel );

        jw.setMessage( "Loading planet information..." );

        /*

        horo = new HoroscopeCanvas( new NorthHoroscope( 0, 0, 275, c_center )

        , c_center );

        */

        horoPanel = new HoroPanel( c_center );

       // JSplitPane centerPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT, horoPanel, planetInfoPanel );
       
       FJTabbedPanel tabPanel = new FJTabbedPanel(c_center);

        
	
	JSplitPane centerPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT

        , horoPanel, tabPanel );

        centerPane.setDividerLocation( 350 );

        centerPane.setOneTouchExpandable( true );

        getContentPane().add( centerPane, BorderLayout.CENTER );

        

        addMenus();



        ImageIcon imgIco = new ImageIcon( "images/TithiMoon.gif" );

        setIconImage( imgIco.getImage() );

        jw.hide();

        jw = null;

    }

    

	public void actionPerformed( ActionEvent ae ) {

		if ( ae.getSource() == newItem ) {

            //FJInputDialog input = new FJInputDialog();

            if( input == null ) {

                input = new SimpleInputDialog( this, c_center, 0 );

            }

			input.setModal(true);

            input.centerDialog( this );

			input.showDialog();

			//save or save & calculate new chart

            if( input.isCancelled() ) return;

            BirthData nInfo = input.getNativeInfo();

            if( nInfo != null ) {

                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		c_center.createChart( nInfo );
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }

			getContentPane().repaint();

		}



		if (ae.getSource() == testItem)	{

			System.out.println("Testing calculation of chart " + listPanel.getSelected().toString());

			c_center.createChart( listPanel.getSelected() );

			getContentPane().repaint();



		}

		

		if (ae.getSource() == prefsItem) {

			CalcPrefsDialog cpd = new CalcPrefsDialog(c_center,this );

			cpd.showDialog();

		}

		

		if (ae.getSource() == exitItem)	{

			System.out.println("FREEJYOTISH is exiting.");

            if( out != null ) {

                System.out.println( "Closed" );

                try {

                    out.close();

                } catch( Exception e ) {

                }

            }

			System.exit(0);

		}

		

		if (ae.getSource() == northStyle) {

            if( c_center.getCurrentChart() == null ) return;

			horoStyle = 0;

			//VargaData vg = new VargaData(c_center.getCurrentChart().getPlanetInfo(), c_center.getCurrentChart().getAscendant());
			VargaData vg = c_center.getCurrentChart().getVargaData();
			t1 = new NorthHoroscope(10, 10, 275, vg, FJConstants.RASI, c_center);

            t2 = new NorthHoroscope(10, 10, 275, vg, FJConstants.RASI, c_center);

			horoPanel.setHoroscope( t1, t2 );

			getContentPane().repaint();

		}

		

		if (ae.getSource() == southStyle) {

            if( c_center.getCurrentChart() == null ) return;

			horoStyle = 1;

			//VargaData vg = new VargaData(c_center.getCurrentChart().getPlanetInfo(), c_center.getCurrentChart().getAscendant());

			VargaData vg = c_center.getCurrentChart().getVargaData();
			t1 = new SouthHoroscope(10, 10, 275, vg, FJConstants.RASI, c_center);

            t2 = new SouthHoroscope(10, 10, 275, vg, FJConstants.RASI, c_center);

            horoPanel.setHoroscope( t1, t2 );

			getContentPane().repaint();

			

		}

		

		if (ae.getSource() == aboutItem)	{

			JOptionPane.showMessageDialog(this,"FREEJYOTISH \n" + "Open-source Vedic Astrology Program \n" + "pre-Alpha test version \n" + "Spring 2003 \n" + "http://jyotish.sourceforge.net/");



		}

		

		if (ae.getSource() == debugItem)	{

			debug = new DebugWindow();

			debug.show();

		}

	}



	public void chartCenterChanged( ChartCenterEvent e ) {

		  if( c_center.getCurrentChart() == null ) return;

          System.out.println( "here" );

        BirthData nInfo = c_center.getCurrentChart().getNativeInfo2();

		//loadChart( nInfo.toString() );

		getContentPane().repaint();	

        

	}



	private void loadChart(String chartName)	{

		testChart1 = c_center.getChart(chartName);

		Vimshottari vimsh = new Vimshottari( testChart1.getPlanetInfo().getPlanet(FJConstants.planetLongNames[1]).getLongitude(), 1962, 11, 26);

		String[] dashaArray = vimsh.getDashaString();

		StringBuffer dashaBuffer = new StringBuffer();

		for (int i = 0; i < dashaArray.length; i++)

		{

			dashaBuffer.append(dashaArray[i] + '\n');

            if( (i+1)%9 == 0 ) {

                dashaBuffer.append( "-------------------" + '\n' );

            }

		}

		dashaPanel.setVimText(dashaBuffer.toString());

	    getContentPane().repaint();

	}

    

    private void addMenus() {

        Font f = fjDefs.getNormalFont();

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");

        fileMenu.setFont( f );

        fileMenu.setMnemonic( 'F' );

		viewMenu = new JMenu("View");

        viewMenu.setFont( f );

        viewMenu.setMnemonic( 'V' );

		debugItem = new JMenuItem("Debug Window");

	debugItem.setFont(f);

	debugItem.setMnemonic('d');

		optionsMenu = new JMenu("Options");

        optionsMenu.setFont( f );

        optionsMenu.setMnemonic( 'O' );

		helpMenu = new JMenu("Help");

        helpMenu.setFont( f );

        helpMenu.setMnemonic( 'H' );

	aboutItem = new JMenuItem("About");

	aboutItem.setFont( f );

        aboutItem.setMnemonic( 'a' );

		exitItem = new JMenuItem("Exit");

        exitItem.setFont( f );

        exitItem.setMnemonic( 'x' );

        exitItem.setAccelerator( KeyStroke.getKeyStroke( 'Q'

        , InputEvent.CTRL_MASK ) );

		testItem = new JMenuItem("Test");

        testItem.setFont( f );

        testItem.setMnemonic( 'T' );

		recalcItem = new JMenuItem("Recalc");

        recalcItem.setFont( f );

        recalcItem.setMnemonic( 'R' );

		newItem = new JMenuItem("New");

        newItem.setAccelerator( KeyStroke.getKeyStroke( 'N'

        , InputEvent.CTRL_MASK ) );

        newItem.setFont( f );

        newItem.setMnemonic( 'N' );

		styleItem = new JMenuItem("Chart Style");

        styleItem.setFont( f );

        

		prefsItem = new JMenuItem("Calculation Preferences");

        prefsItem.setFont( f );

        prefsItem.setMnemonic( 'P' );

		



		styleGroup = new ButtonGroup();

		northStyle = new JRadioButtonMenuItem("North-style Chart");

        northStyle.setFont( f );

        northStyle.setMnemonic( 'N' );

		southStyle = new JRadioButtonMenuItem("South-style Chart");

        southStyle.setFont( f );

        southStyle.setMnemonic( 'S' );

		northStyle.setSelected(true);

		northStyle.addActionListener(this);

		southStyle.addActionListener(this);

		styleGroup.add(northStyle);

		styleGroup.add(southStyle);



		newItem.addActionListener(this);

		testItem.addActionListener(this);

		recalcItem.addActionListener(this);

		exitItem.addActionListener(this);

		prefsItem.addActionListener(this);

		aboutItem.addActionListener(this);

		debugItem.addActionListener(this);

        

		fileMenu.add(newItem);

		fileMenu.addSeparator();

		//fileMenu.add(testItem);

		//fileMenu.add(recalcItem);

		//fileMenu.addSeparator();

		fileMenu.add(exitItem);

		viewMenu.add(debugItem);

		optionsMenu.add(northStyle);

		optionsMenu.add(southStyle);

		optionsMenu.addSeparator();

		optionsMenu.add(prefsItem);

		helpMenu.add(aboutItem);

		

		menuBar.add(fileMenu);

		menuBar.add(viewMenu);

		menuBar.add(optionsMenu);

		menuBar.add(helpMenu);

        

		setJMenuBar(menuBar);

    }

}

@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d5 1
a5 1
* $Id: FJSplit.java,v 1.24 2003/05/15 03:45:23 michaeltaft Exp $
d271 1
a271 1
			CalcPrefsDialog cpd = new CalcPrefsDialog(c_center );
@

