/*
 *
 * @package com.lobobrowser.cobra.util.gui
 * @license http://lobobrowser.com/about/license
 * @copyright (c) 2015 Lobo Browser Team
 *
 */
package com.lobobrowser.cobra.util.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;


public class CenterLayout implements LayoutManager {
  public void addLayoutComponent(final String arg0, final Component arg1) {
  }

  public void removeLayoutComponent(final Component arg0) {
  }

  public Dimension preferredLayoutSize(final Container arg0) {
    final java.awt.Insets insets = arg0.getInsets();
    final int count = arg0.getComponentCount();
    if (count > 0) {
      final Dimension d = arg0.getComponent(0).getPreferredSize();
      return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
    } else {
      return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
   */
  public Dimension minimumLayoutSize(final Container arg0) {
    final java.awt.Insets insets = arg0.getInsets();
    final int count = arg0.getComponentCount();
    if (count > 0) {
      final Dimension d = arg0.getComponent(0).getMinimumSize();
      return new Dimension(d.width + insets.left + insets.right, d.height + insets.top + insets.bottom);
    } else {
      return new Dimension(insets.left + insets.right, insets.top + insets.bottom);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
   */
  public void layoutContainer(final Container container) {
    final int count = container.getComponentCount();
    if (count > 0) {
      final Component child = container.getComponent(0);
      final java.awt.Insets insets = container.getInsets();
      final int availWidth = container.getWidth() - insets.left - insets.right;
      final int availHeight = container.getHeight() - insets.top - insets.bottom;
      final Dimension preferredSize = child.getPreferredSize();
      final double preferredWidth = preferredSize.getWidth();
      final double preferredHeight = preferredSize.getHeight();
      int width;
      int height;
      int x;
      int y;
      if (preferredWidth < availWidth) {
        x = (int) Math.round(insets.left + ((availWidth - preferredWidth) / 2));
        width = (int) Math.round(preferredWidth);
      } else {
        x = insets.left;
        width = availWidth;
      }
      if (preferredHeight < availHeight) {
        y = (int) Math.round(insets.top + ((availHeight - preferredHeight) / 2));
        height = (int) Math.round(preferredHeight);
      } else {
        y = insets.top;
        height = availHeight;
      }
      child.setBounds(x, y, width, height);
    }
  }

  private static CenterLayout instance = new CenterLayout();

  public static CenterLayout getInstance() {
    return instance;
  }
}
