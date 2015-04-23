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


public class WrapperLayout implements LayoutManager {
  /*
   * (non-Javadoc)
   *
   * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String,
   * java.awt.Component)
   */
  public void addLayoutComponent(final String arg0, final Component arg1) {
  }

  /*
   * (non-Javadoc)
   *
   * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
   */
  public void removeLayoutComponent(final Component arg0) {
  }

  /*
   * (non-Javadoc)
   *
   * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
   */
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
  public void layoutContainer(final Container arg0) {
    final int count = arg0.getComponentCount();
    if (count > 0) {
      final Component child = arg0.getComponent(0);
      final java.awt.Insets insets = arg0.getInsets();
      child.setBounds(insets.left, insets.top, arg0.getWidth() - insets.left - insets.right, arg0.getHeight() - insets.top - insets.bottom);
    }
  }

  private static WrapperLayout instance = new WrapperLayout();

  public static WrapperLayout getInstance() {
    return instance;
  }
}
