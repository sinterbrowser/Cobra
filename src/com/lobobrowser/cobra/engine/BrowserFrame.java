package com.lobobrowser.cobra.engine;

import java.awt.Component;
import java.net.URL;

import com.lobobrowser.cobra.dom.Document;

/**
 * The <code>BrowserFrame</code> interface represents a browser frame. A simple
 * implementation of this interface is provided in
 * {@link org.lobobrowser.html.test.SimpleBrowserFrame}.
 */
public interface BrowserFrame {
  /**
   * Gets the component that renders the frame. This can be a
   * {@link org.lobobrowser.html.gui.HtmlPanel}.
   */
  public Component getComponent();

  /**
   * Loads a URL in the frame.
   */
  public void loadURL(URL url);

  /**
   * Gets the content document.
   */
  public Document getContentDocument();

  /**
   * Gets the {@link HtmlRendererContext} of the frame.
   */
  public HtmlRendererContext getHtmlRendererContext();

  /**
   * Sets the default margin insets of the browser frame.
   *
   * @param insets
   *          The margin insets.
   */
  public void setDefaultMarginInsets(java.awt.Insets insets);

  /**
   * Sets the default horizontal overflow of the browser frame.
   *
   * @param overflowX
   *          See constants in {@link org.lobobrowser.html.style.RenderState}.
   */
  public void setDefaultOverflowX(int overflowX);

  /**
   * Sets the default vertical overflow of the browser frame.
   *
   * @param overflowY
   *          See constants in {@link org.lobobrowser.html.style.RenderState}.
   */
  public void setDefaultOverflowY(int overflowY);
}
