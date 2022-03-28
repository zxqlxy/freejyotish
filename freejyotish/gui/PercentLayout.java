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
@/*
  Copyright 1996 by Sean Lickfold and Daniel Arbuckle
  A LayoutManager for sanity.
*/

package freejyotish.gui;

import java.awt.*;
import java.util.Vector;

public class PercentLayout implements LayoutManager
{
  private Vector rects, comps;

  public PercentLayout()
  {
    rects = new Vector();
    comps = new Vector();
  }

  public void addLayoutComponent(String name, Component comp)
  {
    // First one
    int i = name.indexOf(" ");
    if (i == -1)
      return;
    int x = Integer.valueOf(name.substring(0, i)).intValue();
    if (x < 0 || x > 100)
      return;

    // Second one
    int i2 = name.indexOf(" ", i+1);
    if (i2 == -1)
      return;
    int y = Integer.valueOf(name.substring(i+1, i2)).intValue();
    if (y < 0 || y > 100)
      return;

    // Third one
    int i3 = name.indexOf(" ", i2+1);
    if (i3 == -1)
      return;
    int x2 = Integer.valueOf(name.substring(i2+1, i3)).intValue();
    if (x2 < 0 || x2 > 100)
      return;

    // Fourth one
    int y2 = Integer.valueOf(name.substring(i3 + 1, name.length())).intValue();
    if (y2 < 0 || y2 > 100)
      return;

    rects.addElement(new Rectangle(x, y, x2, y2));
    comps.addElement(comp);
  }

  public void removeLayoutComponent(Component comp)
  {
    int i = comps.indexOf(comp);
    try
    {
      comps.removeElementAt(i);
      rects.removeElementAt(i);
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.err.println("Internal Syncronyzation Error :" + e);
    }
  }

  public Dimension preferredLayoutSize(Container parent)
  {
    // Just sets the preferred size to the largest preferred size of the components
    int xsize = 0;
    int ysize = 0;
    Dimension d;
    for (int i = 0;  i < comps.size(); i++)
    {
      d = ((Component) comps.elementAt(i)).getPreferredSize();
      if(d.width > xsize) xsize = d.width;
      if(d.height > ysize) ysize = d.height;
    }
    Insets insets = parent.getInsets();
    return new Dimension(xsize + insets.left + insets.right,
                         ysize + insets.top + insets.bottom);
  }

  public Dimension minimumLayoutSize(Container parent)
  {
    // Just sets the minimum size to the largest minimum size of the components
    int xsize = 0;
    int ysize = 0;
    Dimension d;
    for (int i = 0;  i < comps.size(); i++)
    {
      d = ((Component) comps.elementAt(i)).getMinimumSize();
      if(d.width > xsize) xsize = d.width;
      if(d.height > ysize) ysize = d.height;
    }
    Insets insets = parent.getInsets();
    return new Dimension(xsize + insets.left + insets.right,
                         ysize + insets.top + insets.bottom);
  }

  public void layoutContainer(Container comp)
  {
    Rectangle r;
    Insets insets = comp.getInsets();
    int w = comp.getSize().width - (insets.left + insets.right);
    int h = comp.getSize().height - (insets.top + insets.bottom);
    int x = insets.left;
    int y = insets.top;
    for (int i = 0; i < comps.size(); i++)
    {
      r = (Rectangle)rects.elementAt(i);
      ((Component)comps.elementAt(i)).setBounds(
        x+((w*r.x)/100),
        y+((h*r.y)/100),
        (x+((w*r.width)/100))-(x+((w*r.x)/100)),
        (y+((h*r.height)/100))-(y+((h*r.y)/100))
        );
    }
  }
}

@
