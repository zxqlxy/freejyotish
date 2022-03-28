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

import freejyotish.main.ChartCenterEvent;
import freejyotish.main.ChartCenterEventListener;
import freejyotish.main.FJConstants;
import freejyotish.main.IChartCenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 *  Description of the Class
 *
 *@@author     Harsh Singh, Michael Taft
 *@@created    February 13, 2003
 */
public class HoroscopeCanvas extends JPanel implements ChartCenterEventListener
{
	private Horoscope horoscope;
	private JComboBox vargaBox, signBox;
	Graphics g;
	private IChartCenter c_center;
	private JLabel chartName;

	/**
	 *  Constructor for the HoroscopeCanvas object
	 *
	 *@@param  hs  Description of the Parameter
	 */
	public HoroscopeCanvas(Horoscope hs, IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		setLayout(new BorderLayout());
		this.setVisible(true);
		this.horoscope = hs;
		add(boxPanel(), BorderLayout.SOUTH);

	}
	
	public HoroscopeCanvas(IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		setLayout(new BorderLayout());
		this.setVisible(true);
		add(boxPanel(), BorderLayout.SOUTH);

	}
	
	
	public void setHoroscope(Horoscope hs)
	{
		this.horoscope = hs;
		repaint();
		
	}

	public void setChartName()
	{
		if(c_center.getCurrentChart() != null)
		chartName.setText(c_center.getCurrentChart().getChartName());
		else chartName.setText("");
	}
	/**
	 *  Description of the Method
	 *
	 *@@return    Description of the Return Value
	 */
	private JPanel boxPanel()
	{
        Font f = FJGUIDefs.getInstance().getNormalFont();
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BorderLayout());
		chartName = new JLabel("");
        chartName.setFont( f );
		vargaBox = new JComboBox(FJConstants.vargaNames);
        vargaBox.setFont( f );
        vargaBox.setBackground( Color.WHITE );
		vargaBox.setSelectedIndex(horoscope.getVargaNumber());
		signBox = new JComboBox(FJConstants.signNames);
        signBox.setFont( f );
        signBox.setBackground( Color.WHITE );
		signBox.setSelectedIndex(horoscope.getAscendant() - 1);

		vargaBox.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JComboBox cb = (JComboBox) e.getSource();
					int vargaChoice = cb.getSelectedIndex();
					horoscope.setVargaNumber(vargaChoice);
					signBox.setSelectedIndex(horoscope.getAscendant() - 1);
					repaint();
                    Container parent = getParent();
                    while( !( parent instanceof JFrame ) ) {
                        parent = parent.getParent();
                    }
                    ( ( JFrame ) parent ).getContentPane().repaint();
				}
			});

		signBox.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JComboBox cb = (JComboBox) e.getSource();
					int signChoice = cb.getSelectedIndex();
					horoscope.setAscendant(signChoice + 1);
					repaint();
                    Container parent = getParent();
                    while( !( parent instanceof JFrame ) ) {
                        parent = parent.getParent();
                    }
                    ( ( JFrame ) parent ).getContentPane().repaint();
				}
			});
		boxPanel.add(chartName, BorderLayout.NORTH);
		boxPanel.add(signBox, BorderLayout.WEST);
		boxPanel.add(vargaBox, BorderLayout.EAST);
		return boxPanel;
	}


	/**
	 *  Description of the Method
	 *
	 *@@param  e  Description of the Parameter
	 */
	public void chartCenterChanged(ChartCenterEvent e)
	{
		System.out.println("chartCenterChanged() heard by HoroscopeCanvas");
		
		
		setChartName();
		
		signBox.setSelectedIndex(horoscope.getAscendant() - 1);
		vargaBox.setSelectedIndex(horoscope.getVargaNumber());
		repaint();
	}


	/**
	 *  Description of the Method
	 *
	 *@@param  g  Description of the Parameter
	 */
	public void paintComponent(Graphics g)
	{
		horoscope.drawHoroscope(g);
	}

}

@
