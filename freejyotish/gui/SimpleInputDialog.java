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

import freejyotish.main.FJConstants;
import freejyotish.main.BirthData;
import freejyotish.main.IChartCenter;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DecimalFormat;

public class SimpleInputDialog extends FJBaseDialog implements FocusListener
, ActionListener
{
	private JTextField firstName, lastName;
	private JFormattedTextField yearField;
    private JComboBox monthField, dateField;
    private JSpinner hourField, minField, secField;
	private JComboBox zoneField, dstField;
	private JSpinner lonDeg, lonMin, lonSec, latDeg, latMin, latSec;
	private JTextField cityField, stateField, countryField;
    private JRadioButton eRadio, wRadio, nRadio, sRadio;
    private JButton okButton, cancelButton;
	private Calendar tempCal;
	private BirthData ni;
    private static boolean cancelled;
    private IChartCenter c_center;
	
    public SimpleInputDialog( Frame parent, IChartCenter center, int dialogType )
    {
	
        super( parent, "Birth Data" );
	c_center = center;
        setSize( 450, 450 );
        getContentPane().setLayout( new BorderLayout() );
        Font f = FJGUIDefs.getInstance().getNormalFont();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );
        mainPanel.add( createNamePanel( f ) );
        mainPanel.add( createTimePanel( f ) );
        mainPanel.add( createGeoPanel( f ) );
        mainPanel.setBorder( BorderFactory.createEtchedBorder() );
        getContentPane().add( mainPanel, BorderLayout.CENTER );
        getContentPane().add( getOKCancelPanel(), BorderLayout.SOUTH );
	if (dialogType == 0)loadDefaultData();
	else if (dialogType == 1) loadEditData();
    }
       
       
	
	private JPanel createNamePanel( Font f )
	{
		JPanel panel = new JPanel();
		panel.setLayout( new FlowLayout() );
		JLabel firstNameLabel = new JLabel( "First:" );
		JLabel lastNameLabel = new JLabel( "Last:" );
		firstName = new JTextField( 10 );
		lastName = new JTextField( 10 );
        firstNameLabel.setFont( f );
        lastNameLabel.setFont( f );
        firstName.setFont( f );
        lastName.setFont( f );
		panel.add( firstNameLabel );
		panel.add( firstName );
		panel.add( lastNameLabel );
		panel.add( lastName );
		panel.setBorder( BorderFactory.createTitledBorder( "Name:" ) );
		return panel;
	}
    
    private JPanel createTimePanel( Font f ) {
        JPanel jp = new JPanel();
        jp.setLayout( new BoxLayout( jp, BoxLayout.Y_AXIS ) );
        
        JPanel jp1 = new JPanel();
        jp1.setLayout( new FlowLayout() );
        JLabel ly = new JLabel( "Year: " );
        ly.setFont( f );
        jp1.add( ly );
        yearField = new JFormattedTextField( new DecimalFormat( "0000" ) );
        yearField.setFont( f );
        yearField.addFocusListener( this );
        jp1.add( yearField );
        JLabel lm = new JLabel( "Month: " );
        lm.setFont( f );
        jp1.add( lm );
        monthField = new JComboBox( FJConstants.monthNames );
        monthField.setFont( f );
        monthField.setBackground( Color.white );
        monthField.addActionListener( this );
        jp1.add( monthField );
        JLabel ld = new JLabel( "Date: " );
        ld.setFont( f );
        jp1.add( ld );
        dateField = new JComboBox();
        dateField.setFont( f );
        dateField.setBackground( Color.white );
        jp1.add( dateField );
        
        JPanel jp2 = new JPanel();
        jp2.setLayout( new FlowLayout() );
        JLabel lh = new JLabel( "Hour: " );
        lh.setFont( f );
        jp2.add( lh );
        hourField = new JSpinner( new SpinnerNumberModel( 0, 0, 24, 1 ) );
        hourField.setFont( f );
        hourField.setBackground( Color.white );
        jp2.add( hourField );
        JLabel lmin = new JLabel( "Minute: " );
        lmin.setFont( f );
        jp2.add( lmin );
        minField = new JSpinner( new SpinnerNumberModel( 0, 0, 59, 1 ) );
        minField.setFont( f );
        minField.setBackground( Color.white );
        jp2.add( minField );
        JLabel ls = new JLabel( "Second: " );
        ls.setFont( f );
        jp2.add( ls );
        secField = new JSpinner( new SpinnerNumberModel( 0, 0, 59, 1 ) );
        secField.setFont( f );
        secField.setBackground( Color.white );
        jp2.add( secField );
        
        JPanel jp3 = new JPanel();
        jp3.setLayout( new FlowLayout() );
        JLabel ltz = new JLabel( "Time Zone: " );
        ltz.setFont( f );
        jp3.add( ltz );
        zoneField = new JComboBox( FJConstants.timeZones );
        zoneField.setFont( f );
        zoneField.setBackground( Color.white );
        jp3.add( zoneField );
        JLabel ldst = new JLabel( "Daylight Saving: " );
        ldst.setFont( f );
        jp3.add( ldst );
        dstField = new JComboBox( FJConstants.DSToffsets );
        dstField.setFont( f );
        dstField.setBackground( Color.white );
        jp3.add( dstField );
        
        
        jp.add( jp1 );
        jp.add( jp2 );
        jp.add( jp3 );
        jp.setBorder( BorderFactory.createTitledBorder( "Date & Time: " ) );
        return jp;
    }
    
    private JPanel createGeoPanel( Font f ) {
        JPanel jp = new JPanel();
        jp.setLayout( new BoxLayout( jp, BoxLayout.Y_AXIS ) );
        
        JPanel jp1 = new JPanel();
        jp1.setLayout( new FlowLayout() );
        JLabel lcity = new JLabel( "City: " );
        lcity.setFont( f );
        jp1.add( lcity );
        cityField = new JTextField( 7 );
        cityField.setFont( f );
        jp1.add( cityField );
        JLabel lst = new JLabel( "State: " );
        lst.setFont( f );
        jp1.add( lst );
        stateField = new JTextField( 5 );
        stateField.setFont( f );
        jp1.add( stateField );
        JLabel lcou = new JLabel( "Country: " );
        lcou.setFont( f );
        jp1.add( lcou );
        countryField = new JTextField( 3 );
        countryField.setFont( f );
        jp1.add( countryField );
        
        JPanel jp2 = new JPanel();
        jp2.setLayout( new FlowLayout() );
        JLabel jld = new JLabel( "Longitude Deg.: " );
        jld.setFont( f );
        jp2.add( jld );
        lonDeg = new JSpinner( new SpinnerNumberModel( 0, 0, 180, 1 ) );
        lonDeg.setFont( f );
        lonDeg.setBackground( Color.white );
        jp2.add( lonDeg );
        JPanel mini1 = new JPanel();
        mini1.setLayout( new BoxLayout( mini1, BoxLayout.Y_AXIS ) );
        ButtonGroup ewGrp = new ButtonGroup();
        eRadio = new JRadioButton( "E", true );
        eRadio.setFont( f );
        wRadio = new JRadioButton( "W", false );
        wRadio.setFont( f );
        ewGrp.add( eRadio );
        ewGrp.add( wRadio );
        mini1.add( eRadio );
        mini1.add( wRadio );
        jp2.add( mini1 );
        JLabel jlm = new JLabel( "Min.: " );
        jlm.setFont( f );
        jp2.add( jlm );
        lonMin = new JSpinner( new SpinnerNumberModel( 0, 0, 60, 1 ) );
        lonMin.setFont( f );
        lonMin.setBackground( Color.white );
        jp2.add( lonMin );
        JLabel jls = new JLabel( "Sec.: " );
        jls.setFont( f );
        jp2.add( jls );
        lonSec = new JSpinner( new SpinnerNumberModel( 0, 0, 60, 1 ) );
        lonSec.setFont( f );
        lonSec.setBackground( Color.white );
        jp2.add( lonSec );
        
        JPanel jp3 = new JPanel();
        jp3.setLayout( new FlowLayout() );
        JLabel jlad = new JLabel( "Latitude Deg.: " );
        jlad.setFont( f );
        jp3.add( jlad );
        latDeg = new JSpinner( new SpinnerNumberModel( 0, 0, 90, 1 ) );
        latDeg.setFont( f );
        latDeg.setBackground( Color.white );
        jp3.add( latDeg );
        JPanel mini2 = new JPanel();
        mini2.setLayout( new BoxLayout( mini2, BoxLayout.Y_AXIS ) );
        ButtonGroup nsGrp = new ButtonGroup();
        nRadio = new JRadioButton( "N", true );
        nRadio.setFont( f );
        sRadio = new JRadioButton( "S", false );
        sRadio.setFont( f );
        nsGrp.add( nRadio );
        nsGrp.add( sRadio );
        mini2.add( nRadio );
        mini2.add( sRadio );
        jp3.add( mini2 );
        JLabel jlam = new JLabel( "Min.: " );
        jlam.setFont( f );
        jp3.add( jlam );
        latMin = new JSpinner( new SpinnerNumberModel( 0, 0, 60, 1 ) );
        latMin.setFont( f );
        latMin.setBackground( Color.white );
        jp3.add( latMin );
        JLabel jlas = new JLabel( "Sec.: " );
        jlas.setFont( f );
        jp3.add( jlas );
        latSec = new JSpinner( new SpinnerNumberModel( 0, 0, 60, 1 ) );
        latSec.setFont( f );
        latSec.setBackground( Color.white );
        jp3.add( latSec );


        jp.add( jp1 );
        jp.add( jp2 );
        jp.add( jp3 );
        jp.setBorder( BorderFactory.createTitledBorder( "Geographical: " ) );
        
        return jp;
    }
    
    private JPanel getOKCancelPanel() {
        JPanel jp = new JPanel();
        jp.setLayout( new FlowLayout() );
        okButton = new JButton( "OK" );
        okButton.setFont( FJGUIDefs.getInstance().getNormalFont() );
        okButton.setMnemonic( 'O' );
        okButton.addActionListener( this );
        jp.add( okButton );
        
        cancelButton = new JButton( "Cancel" );
        cancelButton.setFont( FJGUIDefs.getInstance().getNormalFont() );
        cancelButton.setMnemonic( 'O' );
        cancelButton.addActionListener( this );
        jp.add( cancelButton );
        jp.setBorder( BorderFactory.createEtchedBorder() );
        return jp;
    }
    
    
    private void loadDefaultData() {
        System.out.println( "here" );
        tempCal = Calendar.getInstance();
        yearField.setValue( new Integer( tempCal.get( Calendar.YEAR ) ) );
        monthField.setSelectedIndex( tempCal.get( Calendar.MONTH ) );
        populateDates( tempCal.get( Calendar.YEAR ), tempCal.get(
        Calendar.MONTH ) );
        tempCal = Calendar.getInstance();
        dateField.setSelectedIndex( tempCal.get( Calendar.DATE )-1 );
        hourField.setValue( new Integer( tempCal.get( Calendar.HOUR_OF_DAY ) ) );
        minField.setValue( new Integer( tempCal.get( Calendar.MINUTE ) ) );
        secField.setValue( new Integer( tempCal.get( Calendar.SECOND ) ) );
        zoneField.setSelectedItem( TimeZone.getDefault().getID() );
        dstField.setSelectedIndex( tempCal.get( Calendar.DST_OFFSET )/(1000*60*60) );
        System.out.println( "offset: " + TimeZone.getDefault().getRawOffset()
        /(1000*60*60) + " , " + tempCal.get( Calendar.DAY_OF_MONTH ));
        
    }
    
    private void loadEditData()
    {
	 BirthData editNative = c_center.getCurrentChart().getNativeInfo2();
	 firstName.setText(editNative.getFirstName());
	 lastName.setText(editNative.getLastName());
	 yearField.setValue(new Integer(editNative.getBirthYear()));
	 monthField.setSelectedIndex(editNative.getBirthMonth());
	 dateField.setSelectedIndex(editNative.getBirthDate());
	 hourField.setValue(new Integer(editNative.getBirthHour()));
	 minField.setValue(new Integer(editNative.getBirthMinute()));
	 secField.setValue(new Integer(editNative.getBirthSecond()));
	 //zoneField.setSelectedItem
	 dstField.setSelectedIndex(editNative.getDSTOffset());
	    
	    
	    
    }
    
    public void focusGained( FocusEvent fe ) {}
    public void focusLost( FocusEvent fe ) {
        Object obj = fe.getSource();
        if( obj == yearField ) {
            int yr = 0;
            int mon = 0;
            try {
                yr = Integer.parseInt( yearField.getText() );
                mon = monthField.getSelectedIndex();
                populateDates( yr, mon );
            } catch( NumberFormatException nfe ) {
                yearField.setText( "" );
            }
        }
    }
    
    public void actionPerformed( ActionEvent ae ) {
        Object obj = ae.getSource();
        if( obj == monthField ) {
            int yr = 0;
            int mon = 0;
            try {
                yr = Integer.parseInt( yearField.getText() );
                mon = monthField.getSelectedIndex();
                populateDates( yr, mon );
            } catch( NumberFormatException nfe ) {
                yearField.setText( "" );
            }
        }
        if( obj == okButton ) okClicked();
        if( obj == cancelButton ) cancelClicked();

    }
    
    private void populateDates( int yr, int mon ) {
        tempCal = Calendar.getInstance();
        tempCal.set( Calendar.YEAR, yr );
        tempCal.set( Calendar.MONTH, mon );
        tempCal.set( Calendar.DATE, 1 );
        int days = tempCal.getActualMaximum( Calendar.DATE );
        dateField.removeAllItems();
        for( int i=1; i<=days; i++ ) {
            dateField.addItem( new Integer( i ) );
        }
        dateField.setSelectedIndex( 0 );
    }
    private void okClicked() {
        cancelled = false;
        setNativeInfo();
        setVisible( false );
        //dispose();
    }
    
    private void cancelClicked() {
        cancelled = true;
        setVisible( false );
        //dispose();
	}
    
    public boolean isCancelled() {
        return cancelled;
    }
       
       public void showDialog()
       {
	       setVisible(true);
       }
       public BirthData getNativeInfo()
       {
	       if(ni != null)
	       {
		   return ni;    
	       }
	     else return new BirthData();  
       }
       
       public void setNativeInfo()
       {
	       ni = new BirthData();
	       ni.setFirstName( firstName.getText().trim() );
	       ni.setLastName( lastName.getText().trim() );
	       
	       ni.setBirthCity( cityField.getText().trim() );
	       ni.setBirthState( stateField.getText().trim() );
	       ni.setBirthCountry( countryField.getText().trim() );
           int x = 1;
           if( wRadio.isSelected() ) x = -1;
	       ni.setLongitude_Deg( ( ( Integer ) lonDeg.getValue() ).intValue() * x );
           ni.setLongitude_Min( ( ( Integer ) lonMin.getValue() ).intValue() );
           x = 1;
           if( sRadio.isSelected() ) x = -1;
           ni.setLatitude_Deg( ( ( Integer ) latDeg.getValue() ).intValue() * x );
           ni.setLatitude_Min( ( ( Integer ) latMin.getValue() ).intValue() );
	       ni.setBirthYear(  Integer.valueOf(yearField.getText() ).intValue());
           ni.setBirthMonth( ( new Integer( monthField.getSelectedIndex()+1 ) ).intValue() );
           ni.setBirthDate( ( new Integer( dateField.getSelectedIndex()+1 ) ).intValue() );
	    ni.setBirthHour( (  (Integer ) hourField.getValue() ).intValue() );
           ni.setBirthMinute(  ( ( Integer ) minField.getValue() ).intValue() );
           ni.setBirthSecond(  ( ( Integer ) secField.getValue() ).intValue() );
	       TimeZone tz = TimeZone.getTimeZone( ( String )
           zoneField.getSelectedItem() );
           double offset = tz.getRawOffset()/(float)( 1000*60*60 );
	       ni.setTimeZoneOffset( offset );
           ni.setDSTOffset( ( new Integer( dstField.getSelectedIndex() ) ).intValue() ); 
	       System.out.println("Name is: " + ni.getFirstName() + " " + ni.getLastName());
	       System.out.println("BirthPlace is: " + ni.getBirthCity() + ", " + ni.getBirthState() + ", " + ni.getBirthCountry());
	       System.out.println("Logitude is: " + ni.getLongitude_Deg() + " deg " + ni.getLongitude_Min() + " min");
	       System.out.println("Latitude is: " + ni.getLatitude_Deg() + " deg " + ni.getLatitude_Min() + " min");
	       System.out.println("Birth Time/Date is: " + ni.getBirthDate() + "/" + ni.getBirthMonth() + "/" + ni.getBirthYear() + " at " + ni.getBirthHour() + ":" + ni.getBirthMinute() + ":00 time zone:" + ni.getTimeZoneOffset() + " with " + ni.getDSTOffset() + " dst offset");
       }
       
}
@
