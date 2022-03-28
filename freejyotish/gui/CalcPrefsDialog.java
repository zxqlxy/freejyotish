head	1.5;
access;
symbols;
locks; strict;
comment	@# @;


1.5
date	2003.05.23.07.55.32;	author michaeltaft;	state Exp;
branches;
next	1.4;

1.4
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
branches;
next	1.3;

1.3
date	2003.05.22.16.33.32;	author michaeltaft;	state Exp;
branches;
next	1.2;

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


1.5
log
@House preferences work.
@
text
@package freejyotish.gui;

import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;

import javax.swing.*;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.layout.FormLayout;

import freejyotish.FreeJyotish;
import freejyotish.main.IChartCenter;
import freejyotish.main.calc.CalculationPreferences;
import freejyotish.util.DefaultFormBuilder;
import freejyotish.util.I18NManager;

public class CalcPrefsDialog extends JDialog
{

	private static final String MEAN_NODE = "mean"; //$NON-NLS-1$
	private static final String TRUE_NODE = "true"; //$NON-NLS-1$
	private static final String SIDEREAL_MODE = "sidereal"; //$NON-NLS-1$
	private static final String TROPICAL_MODE = "tropical"; //$NON-NLS-1$
	private static final String NODE_PREF_KEY = "node"; //$NON-NLS-1$
	private static final String MODE_PREF_KEY = "mode"; //$NON-NLS-1$
	private static final String AYANAMSA_PREF_KEY = "ayanamsa"; //$NON-NLS-1$
	private static final String HOUSE_SYSTEM_PREF_KEY = "houseType";//$NON-NLS-1$

	private JButton okButton, cancelButton;
	private JComboBox ayanamasaCombo, houseTypeCombo;
	private CalculationPreferences calcPrefs;
	private JRadioButton tropicalRadioButton, siderealRadioButton, trueRadioButton, meanRadioButton;
	private IChartCenter chartCenter;
	private Preferences preferences;

	public CalcPrefsDialog(IChartCenter center, FJSplit parentFrame)
	{
		super(parentFrame);
		chartCenter = center;
		preferences = Preferences.userNodeForPackage(FreeJyotish.class);
		setTitle(I18NManager.getString("calcprefsdialog.title")); //$NON-NLS-1$
		initComponents();

		FormLayout layout = new FormLayout("pref,6dlu,pref:grow,3dlu,pref", ""); //$NON-NLS-1$ //$NON-NLS-2$
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.appendSeparator(I18NManager.getString("calcprefsdialog.separator.title")); //$NON-NLS-1$
		builder.append(I18NManager.getString("calcprefsdialog.zodiactype.label"), siderealRadioButton, tropicalRadioButton); //$NON-NLS-1$
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.ayanamsa.label")); //$NON-NLS-1$
		builder.append(createAyanamasaCombo(), 3);
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.nodetype.label"), trueRadioButton, meanRadioButton); //$NON-NLS-1$
		builder.nextLine();
		builder.append(I18NManager.getString("calcprefsdialog.housetype.label")); //$NON-NLS-1$
		builder.append(createHouseTypeCombo(), 3);
		builder.nextLine();
		builder.appendSeparator();
		builder.append(buildButtonPanel(), 5);
		getContentPane().add(builder.getPanel());
		pack();
		setLocationRelativeTo(parentFrame);

	}

	void initComponents()
	{
		tropicalRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mode.tropical")); //$NON-NLS-1$
		siderealRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mode.sidereal")); //$NON-NLS-1$
		ButtonGroup zodiacTypeGroup = new ButtonGroup();
		zodiacTypeGroup.add(tropicalRadioButton);
		zodiacTypeGroup.add(siderealRadioButton);

		trueRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.node.true")); //$NON-NLS-1$
		meanRadioButton = new JRadioButton(I18NManager.getString("calcprefsdialog.mean.true")); //$NON-NLS-1$
		ButtonGroup nodeTypeGroup = new ButtonGroup();
		nodeTypeGroup.add(trueRadioButton);
		nodeTypeGroup.add(meanRadioButton);

		okButton = new JButton(new SavePreferencesAction());
		cancelButton = new JButton(new CancelAction());

	}

	private JComboBox createHouseTypeCombo()
	{
		String[] houseTypes = { I18NManager.getString("calcprefsdialog.housetype.shripati"), I18NManager.getString("calcprefsdialog.housetype.koch"), I18NManager.getString("calcprefsdialog.housetype.placidus"), I18NManager.getString("calcprefsdialog.housetype.alcabitus"), I18NManager.getString("calcprefsdialog.housetype.regiomontanus"),  I18NManager.getString("calcprefsdialog.housetype.campanus") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		houseTypeCombo = new JComboBox(houseTypes);
		return houseTypeCombo;
	}

	private JComboBox createAyanamasaCombo()
	{
		String[] ayanamsaStrings =
			{ I18NManager.getString("calcprefsdialog.ayanamsa.fagan"), I18NManager.getString("calcprefsdialog.ayanamsa.lahiri"), I18NManager.getString("calcprefsdialog.ayanamsa.deluce"), I18NManager.getString("calcprefsdialog.ayanamsa.raman"), I18NManager.getString("calcprefsdialog.ayanamsa.ushashashi"), I18NManager.getString("calcprefsdialog.ayanamsa.krishnamurti"), I18NManager.getString("calcprefsdialog.ayanamsa.dhwajkhul"), I18NManager.getString("calcprefsdialog.ayanamsa.yukteshwar"), I18NManager.getString("calcprefsdialog.ayanamsa.jnbhasin") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
		ayanamasaCombo = new JComboBox(ayanamsaStrings);
		return ayanamasaCombo;
	}

	private JPanel buildButtonPanel()
	{
		ButtonBarBuilder builder = new ButtonBarBuilder();
		builder.addGlue();
		builder.addGridded(okButton);
		builder.addRelatedGap();
		builder.addGridded(cancelButton);
		return builder.getPanel();
	}

	public void showDialog()
	{
		String modePreference = preferences.get(MODE_PREF_KEY, SIDEREAL_MODE);
		if (modePreference.equals(TROPICAL_MODE))
			tropicalRadioButton.setSelected(true);
		else
			siderealRadioButton.setSelected(true);

		int ayanamsaPreference = preferences.getInt(AYANAMSA_PREF_KEY, 0);
		ayanamasaCombo.setSelectedIndex(ayanamsaPreference);

		String nodePreference = preferences.get(NODE_PREF_KEY, TRUE_NODE);

		if (nodePreference.equals(MEAN_NODE))
			meanRadioButton.setSelected(true);
		else
			trueRadioButton.setSelected(true);
			
		String houseSystemPreference = preferences.get(HOUSE_SYSTEM_PREF_KEY, "Shripati");
		houseTypeCombo.setSelectedItem((Object) houseSystemPreference);	

		setVisible(true);
	}

	public void setCalculationPreferences()
	{
		String selectedMode = tropicalRadioButton.isSelected() ? TROPICAL_MODE : SIDEREAL_MODE;
		preferences.put(MODE_PREF_KEY, selectedMode);

		int selectedAyanamasa = ayanamasaCombo.getSelectedIndex();
		preferences.putInt(AYANAMSA_PREF_KEY, selectedAyanamasa);

		String selectedNode = trueRadioButton.isSelected() ? TRUE_NODE : MEAN_NODE;
		preferences.put(NODE_PREF_KEY, selectedNode);
		
		String selectedHouseSystem = (String) houseTypeCombo.getSelectedItem();
		preferences.put(HOUSE_SYSTEM_PREF_KEY, selectedHouseSystem);
		//calcPrefs.setPreference("house_system",  (Object) new String ("" + comboA.getSelectedIndex()));

	}

	class SavePreferencesAction extends AbstractAction
	{
		public SavePreferencesAction()
		{
			super(I18NManager.getString("calcprefsdialog.savepreferencesaction.label")); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Preferences change heard.");
			setCalculationPreferences();
			chartCenter.saveCalculationPreferences();
			setVisible(false);
			chartCenter.createChart(chartCenter.getCurrentChart().getNativeInfo2());
		}
	}

	class CancelAction extends AbstractAction
	{
		public CancelAction()
		{
			super(I18NManager.getString("calcprefsdialog.cancelpreferencesaction.label")); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}

}
@


1.4
log
@Cleaning up old calculation preferences stuff.
@
text
@d27 1
d87 1
a87 1
		String[] houseTypes = { I18NManager.getString("calcprefsdialog.housetype.shripati"), I18NManager.getString("calcprefsdialog.housetype.vedicequal"), I18NManager.getString("calcprefsdialog.housetype.equal"), I18NManager.getString("calcprefsdialog.housetype.koch"), I18NManager.getString("calcprefsdialog.housetype.placidus") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
d127 3
d144 3
a146 1

@


1.3
log
@Getting new Preferences system working.
@
text
@d156 1
a156 1
			chartCenter.saveCalculationPreferences(calcPrefs);
@


1.2
log
@Basic I18N support for FJ in the calc prefs dialog.
@
text
@d154 1
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a2 1
import java.awt.Dimension;
d4 1
a4 1
import java.awt.event.ActionListener;
d6 1
a6 11
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
d8 4
a11 1
import freejyotish.main.FJConstants;
d14 2
d17 2
d20 7
d28 2
a29 6
public class CalcPrefsDialog extends JDialog
{
	
	private Border etch;
	private JButton ok, cancel;
	private JComboBox comboA, comboH;
d31 76
a106 18
	private JRadioButton tropical, sidereal, trueN, meanN;
	private IChartCenter c_center;
	
	
	public CalcPrefsDialog(IChartCenter center)
	{
		c_center = center;
		calcPrefs = c_center.getCalculationPreferences();
		setTitle("Calculation Prefs");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		etch = BorderFactory.createEtchedBorder();
		getContentPane().add(zodiacBox());
		getContentPane().add(ayanamsaBox());
		getContentPane().add(nodeBox());
		getContentPane().add(houseBox());
		getContentPane().add(controlPanel());
	
	
d108 1
a108 1
	
d111 37
a147 5
		
		
		
		
		if (((String)calcPrefs.getPreference("mode")).equals("sidereal"))
d149 1
a149 1
			sidereal.setSelected(true);
d151 2
a152 6
		else tropical.setSelected(true);
		
		int x = Integer.valueOf((String)calcPrefs.getPreference("ayanamsa")).intValue();
		comboA.setSelectedIndex(x);
		
		if (((String)calcPrefs.getPreference("node")).equals("mean"))
d154 4
a157 1
			meanN.setSelected(true);
a158 4
		else trueN.setSelected(true);
		
		setSize(new Dimension(225, 325));
		setVisible(true);
d161 1
a161 1
	public Box zodiacBox()
d163 9
a171 99
		Box box = Box.createVerticalBox();
		box.setPreferredSize(new Dimension(255, 70));
		//box.setBorder(etch);
		JLabel label = new JLabel("Zodiac Type");
		ButtonGroup zodiacButtonGroup = new ButtonGroup();
		tropical = new JRadioButton("Tropical");
		sidereal = new JRadioButton("Sidereal");
		zodiacButtonGroup.add(tropical);
		zodiacButtonGroup.add(sidereal);
		box.add(label);
		box.add(sidereal);
		box.add(tropical);
		return box;
		
	}
	
	public Box ayanamsaBox()
	{
		Box box = Box.createVerticalBox();
		box.setBorder(etch);
		JLabel label = new JLabel("Ayanamsa");
		comboA = new JComboBox(FJConstants.ayanamsaStrings);	
		box.add(label);
		box.add(comboA);
		return box;
	}
	
	public Box nodeBox()
	{
		Box box = Box.createVerticalBox();
		box.setPreferredSize(new Dimension(255, 70));
		//box.setBorder(etch);
		JLabel label = new JLabel("Node Type");
		ButtonGroup nodeButtonGroup = new ButtonGroup();
		trueN = new JRadioButton("True");
		meanN = new JRadioButton("Mean");
		nodeButtonGroup.add(trueN);
		nodeButtonGroup.add(meanN);
		box.add(label);
		box.add(trueN);
		box.add(meanN);
		return box;
		
	}
	
	public Box houseBox()
	{
		Box box = Box.createVerticalBox();
		box.setBorder(etch);
		JLabel label = new JLabel("House Type");
		comboH = new JComboBox(FJConstants.houseSystemStrings);
		
			
		box.add(label);
		box.add(comboH);
		return box;
	}
	
	public JPanel controlPanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(etch);
		ok = new JButton("OK");
		ok.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setCalculationPreferences();	c_center.saveCalculationPreferences(calcPrefs);
					setVisible(false);
					c_center.createChart(c_center.getCurrentChart().getNativeInfo2());
				}
			});
		cancel = new JButton("Cancel");
		cancel.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
				}
			});
		panel.add(ok);
		panel.add(cancel);
		return panel;
		
	}
	
	public void setCalculationPreferences()
	{
		if (tropical.isSelected()) calcPrefs.setPreference("mode", (Object) "tropical");
		else if (sidereal.isSelected()) calcPrefs.setPreference("mode", (Object) "sidereal");
		
		calcPrefs.setPreference("ayanamsa",  (Object) new String ("" + comboA.getSelectedIndex()));
		//calcPrefs.setPreference("house_system",  (Object) new String ("" + comboA.getSelectedIndex()));
		
		if (trueN.isSelected()) calcPrefs.setPreference("node", (Object) "true");
		else if (meanN.isSelected()) calcPrefs.setPreference("node", (Object) "mean");
		 
d173 1
@

