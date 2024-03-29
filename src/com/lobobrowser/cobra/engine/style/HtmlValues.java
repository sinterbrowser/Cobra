/*
 GNU LESSER GENERAL PUBLIC LICENSE
 Copyright (C) 2006 The Lobo Project

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 Contact info: lobochief@users.sourceforge.net
 */

package com.lobobrowser.cobra.engine.style;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lobobrowser.util.Urls;
import org.lobobrowser.util.gui.ColorFactory;
import org.w3c.dom.css.CSS2Properties;

public class HtmlValues {
  public static final Map<String, FontInfo> SYSTEM_FONTS = new HashMap<>();
  private static final Logger logger = Logger.getLogger(HtmlValues.class.getName());
  public static final float DEFAULT_FONT_SIZE = 14.0f;
  public static final int DEFAULT_FONT_SIZE_INT = (int) DEFAULT_FONT_SIZE;
  public static final Float DEFAULT_FONT_SIZE_BOX = new Float(DEFAULT_FONT_SIZE);
  public static final int DEFAULT_BORDER_WIDTH = 2;

  public static final int BORDER_STYLE_NONE = 0;
  public static final int BORDER_STYLE_HIDDEN = 1;
  public static final int BORDER_STYLE_DOTTED = 2;
  public static final int BORDER_STYLE_DASHED = 3;
  public static final int BORDER_STYLE_SOLID = 4;
  public static final int BORDER_STYLE_DOUBLE = 5;
  public static final int BORDER_STYLE_GROOVE = 6;
  public static final int BORDER_STYLE_RIDGE = 7;
  public static final int BORDER_STYLE_INSET = 8;
  public static final int BORDER_STYLE_OUTSET = 9;

  static {
    final FontInfo systemFont = new FontInfo();
    SYSTEM_FONTS.put("caption", systemFont);
    SYSTEM_FONTS.put("icon", systemFont);
    SYSTEM_FONTS.put("menu", systemFont);
    SYSTEM_FONTS.put("message-box", systemFont);
    SYSTEM_FONTS.put("small-caption", systemFont);
    SYSTEM_FONTS.put("status-bar", systemFont);
  }

  private HtmlValues() {
  }

  public static boolean isBorderStyle(final String token) {
    final String tokenTL = token.toLowerCase();
    return tokenTL.equals("solid") || tokenTL.equals("dashed") || tokenTL.equals("dotted") || tokenTL.equals("double")
        || tokenTL.equals("none") || tokenTL.equals("hidden") || tokenTL.equals("groove") || tokenTL.equals("ridge")
        || tokenTL.equals("inset") || tokenTL.equals("outset");
  }

  public static HtmlInsets getMarginInsets(final CSS2Properties cssProperties, final RenderState renderState) {
    HtmlInsets insets = null;
    final String topText = cssProperties.getMarginTop();
    insets = updateTopInset(insets, topText, renderState);
    final String leftText = cssProperties.getMarginLeft();
    insets = updateLeftInset(insets, leftText, renderState);
    final String bottomText = cssProperties.getMarginBottom();
    insets = updateBottomInset(insets, bottomText, renderState);
    final String rightText = cssProperties.getMarginRight();
    insets = updateRightInset(insets, rightText, renderState);
    return insets;
  }

  public static HtmlInsets getPaddingInsets(final CSS2Properties cssProperties, final RenderState renderState) {
    HtmlInsets insets = null;
    final String topText = cssProperties.getPaddingTop();
    insets = updateTopInset(insets, topText, renderState);
    final String leftText = cssProperties.getPaddingLeft();
    insets = updateLeftInset(insets, leftText, renderState);
    final String bottomText = cssProperties.getPaddingBottom();
    insets = updateBottomInset(insets, bottomText, renderState);
    final String rightText = cssProperties.getPaddingRight();
    insets = updateRightInset(insets, rightText, renderState);
    return insets;
  }

  public static HtmlInsets getBorderInsets(final Insets borderStyles, final CSS2Properties cssProperties, final RenderState renderState) {
    HtmlInsets insets = null;
    if (borderStyles.top != HtmlValues.BORDER_STYLE_NONE) {
      final String topText = cssProperties.getBorderTopWidth();
      insets = updateTopInset(insets, topText, renderState);
    }
    if (borderStyles.left != HtmlValues.BORDER_STYLE_NONE) {
      final String leftText = cssProperties.getBorderLeftWidth();
      insets = updateLeftInset(insets, leftText, renderState);
    }
    if (borderStyles.bottom != HtmlValues.BORDER_STYLE_NONE) {
      final String bottomText = cssProperties.getBorderBottomWidth();
      insets = updateBottomInset(insets, bottomText, renderState);
    }
    if (borderStyles.right != HtmlValues.BORDER_STYLE_NONE) {
      final String rightText = cssProperties.getBorderRightWidth();
      insets = updateRightInset(insets, rightText, renderState);
    }
    return insets;
  }

  /**
   * Populates {@link BorderInfo.insets}.
   *
   * @param binfo
   *          A BorderInfo with its styles already populated.
   * @param cssProperties
   *          The CSS properties object.
   * @param renderState
   *          The current render state.
   */
  public static void populateBorderInsets(final BorderInfo binfo, final CSS2Properties cssProperties, final RenderState renderState) {
    HtmlInsets insets = null;
    if (binfo.topStyle != HtmlValues.BORDER_STYLE_NONE) {
      final String topText = cssProperties.getBorderTopWidth();
      insets = updateTopInset(insets, topText, renderState);
    }
    if (binfo.leftStyle != HtmlValues.BORDER_STYLE_NONE) {
      final String leftText = cssProperties.getBorderLeftWidth();
      insets = updateLeftInset(insets, leftText, renderState);
    }
    if (binfo.bottomStyle != HtmlValues.BORDER_STYLE_NONE) {
      final String bottomText = cssProperties.getBorderBottomWidth();
      insets = updateBottomInset(insets, bottomText, renderState);
    }
    if (binfo.rightStyle != HtmlValues.BORDER_STYLE_NONE) {
      final String rightText = cssProperties.getBorderRightWidth();
      insets = updateRightInset(insets, rightText, renderState);
    }
    binfo.insets = insets;
  }

  /* Not used by anyone
  private static int getBorderWidth(final String sizeText, final int borderStyle, final RenderState renderState) {
    if (borderStyle == BORDER_STYLE_NONE) {
      return 0;
    } else {
      if (sizeText == null || sizeText.length() == 0) {
        return DEFAULT_BORDER_WIDTH;
      }
      return HtmlValues.getPixelSize(sizeText, renderState, DEFAULT_BORDER_WIDTH);
    }
  }*/

  private static HtmlInsets updateTopInset(HtmlInsets insets, String sizeText, final RenderState renderState) {
    if (sizeText == null) {
      return insets;
    }
    sizeText = sizeText.trim();
    if (sizeText.length() == 0) {
      return insets;
    }
    if (insets == null) {
      insets = new HtmlInsets();
    }
    if ("auto".equalsIgnoreCase(sizeText)) {
      insets.topType = HtmlInsets.TYPE_AUTO;
    } else if (sizeText.endsWith("%")) {
      insets.topType = HtmlInsets.TYPE_PERCENT;
      try {
        insets.top = Integer.parseInt(sizeText.substring(0, sizeText.length() - 1));
      } catch (final NumberFormatException nfe) {
        insets.top = 0;
      }
    } else {
      insets.topType = HtmlInsets.TYPE_PIXELS;
      insets.top = HtmlValues.getPixelSize(sizeText, renderState, 0);
    }
    return insets;
  }

  private static HtmlInsets updateLeftInset(HtmlInsets insets, String sizeText, final RenderState renderState) {
    if (sizeText == null) {
      return insets;
    }
    sizeText = sizeText.trim();
    if (sizeText.length() == 0) {
      return insets;
    }
    if (insets == null) {
      insets = new HtmlInsets();
    }
    if ("auto".equalsIgnoreCase(sizeText)) {
      insets.leftType = HtmlInsets.TYPE_AUTO;
    } else if (sizeText.endsWith("%")) {
      insets.leftType = HtmlInsets.TYPE_PERCENT;
      try {
        insets.left = Integer.parseInt(sizeText.substring(0, sizeText.length() - 1));
      } catch (final NumberFormatException nfe) {
        insets.left = 0;
      }
    } else {
      insets.leftType = HtmlInsets.TYPE_PIXELS;
      insets.left = HtmlValues.getPixelSize(sizeText, renderState, 0);
    }
    return insets;
  }

  private static HtmlInsets updateBottomInset(HtmlInsets insets, String sizeText, final RenderState renderState) {
    if (sizeText == null) {
      return insets;
    }
    sizeText = sizeText.trim();
    if (sizeText.length() == 0) {
      return insets;
    }
    if (insets == null) {
      insets = new HtmlInsets();
    }
    if ("auto".equalsIgnoreCase(sizeText)) {
      insets.bottomType = HtmlInsets.TYPE_AUTO;
    } else if (sizeText.endsWith("%")) {
      insets.bottomType = HtmlInsets.TYPE_PERCENT;
      try {
        insets.bottom = Integer.parseInt(sizeText.substring(0, sizeText.length() - 1));
      } catch (final NumberFormatException nfe) {
        insets.bottom = 0;
      }
    } else {
      insets.bottomType = HtmlInsets.TYPE_PIXELS;
      insets.bottom = HtmlValues.getPixelSize(sizeText, renderState, 0);
    }
    return insets;
  }

  private static HtmlInsets updateRightInset(HtmlInsets insets, String sizeText, final RenderState renderState) {
    if (sizeText == null) {
      return insets;
    }
    sizeText = sizeText.trim();
    if (sizeText.length() == 0) {
      return insets;
    }
    if (insets == null) {
      insets = new HtmlInsets();
    }
    if ("auto".equalsIgnoreCase(sizeText)) {
      insets.rightType = HtmlInsets.TYPE_AUTO;
    } else if (sizeText.endsWith("%")) {
      insets.rightType = HtmlInsets.TYPE_PERCENT;
      try {
        insets.right = Integer.parseInt(sizeText.substring(0, sizeText.length() - 1));
      } catch (final NumberFormatException nfe) {
        insets.right = 0;
      }
    } else {
      insets.rightType = HtmlInsets.TYPE_PIXELS;
      insets.right = HtmlValues.getPixelSize(sizeText, renderState, 0);
    }
    return insets;
  }

  public static Insets getInsets(final String insetsSpec, final RenderState renderState, final boolean negativeOK) {
    final int[] insetsArray = new int[4];
    int size = 0;
    final StringTokenizer tok = new StringTokenizer(insetsSpec);
    if (tok.hasMoreTokens()) {
      String token = tok.nextToken();
      insetsArray[0] = getPixelSize(token, renderState, 0);
      if (negativeOK || (insetsArray[0] >= 0)) {
        size = 1;
        if (tok.hasMoreTokens()) {
          token = tok.nextToken();
          insetsArray[1] = getPixelSize(token, renderState, 0);
          if (negativeOK || (insetsArray[1] >= 0)) {
            size = 2;
            if (tok.hasMoreTokens()) {
              token = tok.nextToken();
              insetsArray[2] = getPixelSize(token, renderState, 0);
              if (negativeOK || (insetsArray[2] >= 0)) {
                size = 3;
                if (tok.hasMoreTokens()) {
                  token = tok.nextToken();
                  insetsArray[3] = getPixelSize(token, renderState, 0);
                  size = 4;
                  if (negativeOK || (insetsArray[3] >= 0)) {
                    // nop
                  } else {
                    insetsArray[3] = 0;
                  }
                }
              } else {
                size = 4;
                insetsArray[2] = 0;
              }
            }
          } else {
            size = 4;
            insetsArray[1] = 0;
          }
        }
      } else {
        size = 1;
        insetsArray[0] = 0;
      }
    }
    if (size == 4) {
      return new Insets(insetsArray[0], insetsArray[3], insetsArray[2], insetsArray[1]);
    } else if (size == 1) {
      final int val = insetsArray[0];
      return new Insets(val, val, val, val);
    } else if (size == 2) {
      return new Insets(insetsArray[0], insetsArray[1], insetsArray[0], insetsArray[1]);
    } else if (size == 3) {
      return new Insets(insetsArray[0], insetsArray[1], insetsArray[2], insetsArray[1]);
    } else {
      return null;
    }
  }

  /**
   * Gets a number for 1 to 7.
   *
   * @param oldHtmlSpec
   *          A number from 1 to 7 or +1, etc.
   */
  public static final int getFontNumberOldStyle(String oldHtmlSpec, final RenderState renderState) {
    oldHtmlSpec = oldHtmlSpec.trim();
    int tentative;
    try {
      if (oldHtmlSpec.startsWith("+")) {
        tentative = renderState.getFontBase() + Integer.parseInt(oldHtmlSpec.substring(1));
      } else if (oldHtmlSpec.startsWith("-")) {
        tentative = renderState.getFontBase() + Integer.parseInt(oldHtmlSpec);
      } else {
        tentative = Integer.parseInt(oldHtmlSpec);
      }
      if (tentative < 1) {
        tentative = 1;
      } else if (tentative > 7) {
        tentative = 7;
      }
    } catch (final NumberFormatException nfe) {
      // ignore
      tentative = 3;
    }
    return tentative;
  }

  public static final float getFontSize(final int fontNumber) {
    switch (fontNumber) {
    case 1:
      return 10.0f;
    case 2:
      return 11.0f;
    case 3:
      return 13.0f;
    case 4:
      return 16.0f;
    case 5:
      return 21.0f;
    case 6:
      return 29.0f;
    case 7:
      return 42.0f;
    default:
      return 63.0f;
    }
  }

  public static final String getFontSizeSpec(final int fontNumber) {
    switch (fontNumber) {
    case 1:
      return "10px";
    case 2:
      return "11px";
    case 3:
      return "13px";
    case 4:
      return "16px";
    case 5:
      return "21px";
    case 6:
      return "29px";
    case 7:
      return "42px";
    default:
      return "63px";
    }
  }

  public static final float getFontSize(final String spec, final RenderState parentRenderState) {
    final String specTL = spec.toLowerCase();
    if (specTL.endsWith("em")) {
      if (parentRenderState == null) {
        return DEFAULT_FONT_SIZE;
      }
      final Font font = parentRenderState.getFont();
      final String pxText = specTL.substring(0, specTL.length() - 2);
      double value;
      try {
        value = Double.parseDouble(pxText);
      } catch (final NumberFormatException nfe) {
        return DEFAULT_FONT_SIZE;
      }
      return (int) Math.round(font.getSize() * value);
    } else if (specTL.endsWith("px") || specTL.endsWith("pt") || specTL.endsWith("cm") || specTL.endsWith("pc") || specTL.endsWith("cm")
        || specTL.endsWith("mm") || specTL.endsWith("ex")) {
      final int pixelSize = getPixelSize(spec, parentRenderState, DEFAULT_FONT_SIZE_INT);
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      // Normally the factor below should be 72, but
      // the font-size concept in HTML is handled differently.
      return (pixelSize * 96) / dpi;
    } else if (specTL.endsWith("%")) {
      final String value = specTL.substring(0, specTL.length() - 1);
      try {
        final double valued = Double.parseDouble(value);
        final double parentFontSize = parentRenderState == null ? 14.0 : parentRenderState.getFont().getSize();
        return (float) ((parentFontSize * valued) / 100.0);
      } catch (final NumberFormatException nfe) {
        return DEFAULT_FONT_SIZE;
      }
    } else if ("small".equals(specTL)) {
      return 12.0f;
    } else if ("medium".equals(specTL)) {
      return 14.0f;
    } else if ("large".equals(specTL)) {
      return 20.0f;
    } else if ("x-small".equals(specTL)) {
      return 11.0f;
    } else if ("xx-small".equals(specTL)) {
      return 10.0f;
    } else if ("x-large".equals(specTL)) {
      return 26.0f;
    } else if ("xx-large".equals(specTL)) {
      return 40.0f;
    } else if ("larger".equals(specTL)) {
      final int parentFontSize = parentRenderState == null ? DEFAULT_FONT_SIZE_INT : parentRenderState.getFont().getSize();
      return parentFontSize * 1.2f;
    } else if ("smaller".equals(specTL)) {
      final int parentFontSize = parentRenderState == null ? DEFAULT_FONT_SIZE_INT : parentRenderState.getFont().getSize();
      return parentFontSize / 1.2f;
    } else {
      return getPixelSize(spec, parentRenderState, DEFAULT_FONT_SIZE_INT);
    }
  }

  public static final int getPixelSize(final String spec, final RenderState renderState, final int errorValue, final int availSize) {
    if (spec.endsWith("%")) {
      final String perText = spec.substring(0, spec.length() - 1);
      try {
        final double val = Double.parseDouble(perText);
        return (int) Math.round((availSize * val) / 100.0);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
    } else {
      return getPixelSize(spec, renderState, errorValue);
    }
  }

  public static final int getPixelSize(final String spec, final RenderState renderState, final int errorValue) {
    final String lcSpec = spec.toLowerCase();
    if (lcSpec.endsWith("px")) {
      final String pxText = lcSpec.substring(0, lcSpec.length() - 2);
      try {
        return (int) Math.round(Double.parseDouble(pxText));
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
    } else if (lcSpec.endsWith("em") && (renderState != null)) {
      final Font f = renderState.getFont();
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      // Get fontSize in 1/72 of an inch.
      final int fontSize = f.getSize();
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      // The factor below should normally be 72, but font sizes
      // are calculated differently in HTML.
      final double pixelSize = (fontSize * dpi) / 96;
      return (int) Math.round(pixelSize * val);
    } else if (lcSpec.endsWith("pt")) {
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      final double inches = val / 72;
      return (int) Math.round(dpi * inches);
    } else if (lcSpec.endsWith("pc")) {
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      final double inches = val / 6;
      return (int) Math.round(dpi * inches);
    } else if (lcSpec.endsWith("cm")) {
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      final double inches = val / 2.54;
      return (int) Math.round(dpi * inches);
    } else if (lcSpec.endsWith("mm")) {
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      final int dpi = GraphicsEnvironment.isHeadless() ? 72 : Toolkit.getDefaultToolkit().getScreenResolution();
      final double inches = val / 25.4;
      return (int) Math.round(dpi * inches);
    } else if (lcSpec.endsWith("ex") && (renderState != null)) {
      // Factor below is to try to match size in other browsers.
      // Update: for #157, the factor of 0.47 was too low when testing with reddit homepage (the vote count).
      final double xHeight = renderState.getFontMetrics().getAscent() * 0.6;
      final String valText = lcSpec.substring(0, lcSpec.length() - 2);
      double val;
      try {
        val = Double.parseDouble(valText);
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
      return (int) Math.round(xHeight * val);
    } else {
      final String pxText = lcSpec;
      try {
        return (int) Math.round(Double.parseDouble(pxText));
      } catch (final NumberFormatException nfe) {
        return errorValue;
      }
    }
  }

  public static int getOldSyntaxPixelSize(String spec, final int availSize, final int errorValue) {
    if (spec == null) {
      return errorValue;
    }
    spec = spec.trim();
    try {
      if (spec.endsWith("%")) {
        return (availSize * Integer.parseInt(spec.substring(0, spec.length() - 1))) / 100;
      } else {
        return Integer.parseInt(spec);
      }
    } catch (final NumberFormatException nfe) {
      return errorValue;
    }
  }

  /* This was called from BodyRenderState.getMarginInsets() which has now been commented out
  public static int getOldSyntaxPixelSizeSimple(String spec, final int errorValue) {
    if (spec == null) {
      return errorValue;
    }
    spec = spec.trim();
    try {
      return Integer.parseInt(spec);
    } catch (final NumberFormatException nfe) {
      return errorValue;
    }
  }*/

  public static java.net.URL getURIFromStyleValue(final String fullURLStyleValue) {
    final String start = "url(";
    if (!fullURLStyleValue.toLowerCase().startsWith(start)) {
      return null;
    }
    final int startIdx = start.length();
    final int closingIdx = fullURLStyleValue.lastIndexOf(')');
    if (closingIdx == -1) {
      return null;
    }
    final String quotedUri = fullURLStyleValue.substring(startIdx, closingIdx);
    final String tentativeUri = unquoteAndUnescape(quotedUri);
    try {
      return Urls.createURL(null, tentativeUri);
    } catch (final java.net.MalformedURLException mfu) {
      logger.log(Level.WARNING, "Unable to create URL for URI=[" + tentativeUri + "].", mfu);
      return null;
    }
  }

  public static String unquoteAndUnescape(final String text) {
    final StringBuffer result = new StringBuffer();
    int index = 0;
    final int length = text.length();
    boolean escape = false;
    boolean single = false;
    if (index < length) {
      final char ch = text.charAt(index);
      switch (ch) {
      case '\'':
        single = true;
        break;
      case '"':
        break;
      case '\\':
        escape = true;
        break;
      default:
        result.append(ch);
      }
      index++;
    }
    OUTER: for (; index < length; index++) {
      final char ch = text.charAt(index);
      switch (ch) {
      case '\'':
        if (escape || !single) {
          escape = false;
          result.append(ch);
        } else {
          break OUTER;
        }
        break;
      case '"':
        if (escape || single) {
          escape = false;
          result.append(ch);
        } else {
          break OUTER;
        }
        break;
      case '\\':
        if (escape) {
          escape = false;
          result.append(ch);
        } else {
          escape = true;
        }
        break;
      default:
        if (escape) {
          escape = false;
          result.append('\\');
        }
        result.append(ch);
      }
    }
    return result.toString();
  }

  public static String quoteAndEscape(final String text) {
    final StringBuffer result = new StringBuffer();
    result.append("'");
    int index = 0;
    final int length = text.length();
    while (index < length) {
      final char ch = text.charAt(index);
      switch (ch) {
      case '\'':
        result.append("\\'");
        break;
      case '\\':
        result.append("\\\\");
        break;
      default:
        result.append(ch);
      }
      index++;
    }
    result.append("'");
    return result.toString();
  }

  public static String getColorFromBackground(final String background) {
    final String[] backgroundParts = HtmlValues.splitCssValue(background);
    for (final String token : backgroundParts) {
      if (ColorFactory.getInstance().isColor(token)) {
        return token;
      }
    }
    return null;
  }

  public static boolean isLength(final String token) {
    if (token.endsWith("px") || token.endsWith("pt") || token.endsWith("pc") || token.endsWith("cm") || token.endsWith("mm")
        || token.endsWith("ex") || token.endsWith("em")) {
      return true;
    }
    try {
      Double.parseDouble(token);
      return true;
    } catch (final NumberFormatException nfe) {
      return false;
    }
  }

  public static String[] splitCssValue(final String cssValue) {
    final ArrayList<String> tokens = new ArrayList<>(4);
    final int len = cssValue.length();
    int parenCount = 0;
    StringBuffer currentWord = null;
    for (int i = 0; i < len; i++) {
      final char ch = cssValue.charAt(i);
      switch (ch) {
      case '(':
        parenCount++;
        if (currentWord == null) {
          currentWord = new StringBuffer();
        }
        currentWord.append(ch);
        break;
      case ')':
        parenCount--;
        if (currentWord == null) {
          currentWord = new StringBuffer();
        }
        currentWord.append(ch);
        break;
      case ' ':
      case '\t':
      case '\n':
      case '\r':
        if (parenCount == 0) {
          tokens.add(currentWord.toString());
          currentWord = null;
          break;
        } else {
          // Fall through - no break
        }
      default:
        if (currentWord == null) {
          currentWord = new StringBuffer();
        }
        currentWord.append(ch);
        break;
      }
    }
    if (currentWord != null) {
      tokens.add(currentWord.toString());
    }
    return tokens.toArray(new String[tokens.size()]);
  }

  public static boolean isUrl(final String token) {
    return token.toLowerCase().startsWith("url(");
  }

  public static int getListStyleType(final String token) {
    final String tokenTL = token.toLowerCase();
    if ("none".equals(tokenTL)) {
      return ListStyle.TYPE_NONE;
    } else if ("disc".equals(tokenTL)) {
      return ListStyle.TYPE_DISC;
    } else if ("circle".equals(tokenTL)) {
      return ListStyle.TYPE_CIRCLE;
    } else if ("square".equals(tokenTL)) {
      return ListStyle.TYPE_SQUARE;
    } else if ("decimal".equals(tokenTL)) {
      return ListStyle.TYPE_DECIMAL;
    } else if ("lower-alpha".equals(tokenTL) || "lower-latin".equals(tokenTL)) {
      return ListStyle.TYPE_LOWER_ALPHA;
    } else if ("upper-alpha".equals(tokenTL) || "upper-latin".equals(tokenTL)) {
      return ListStyle.TYPE_UPPER_ALPHA;
    } else {
      // TODO: Many types missing here
      return ListStyle.TYPE_UNSET;
    }
  }

  public static int getListStyleTypeDeprecated(final String token) {
    final String tokenTL = token.toLowerCase();
    if ("disc".equals(tokenTL)) {
      return ListStyle.TYPE_DISC;
    } else if ("circle".equals(tokenTL)) {
      return ListStyle.TYPE_CIRCLE;
    } else if ("square".equals(tokenTL)) {
      return ListStyle.TYPE_SQUARE;
    } else if ("1".equals(tokenTL)) {
      return ListStyle.TYPE_DECIMAL;
    } else if ("a".equals(tokenTL)) {
      return ListStyle.TYPE_LOWER_ALPHA;
    } else if ("A".equals(tokenTL)) {
      return ListStyle.TYPE_UPPER_ALPHA;
    } else {
      // TODO: Missing i, I.
      return ListStyle.TYPE_UNSET;
    }
  }

  public static int getListStylePosition(final String token) {
    final String tokenTL = token.toLowerCase();
    if ("inside".equals(tokenTL)) {
      return ListStyle.POSITION_INSIDE;
    } else if ("outside".equals(tokenTL)) {
      return ListStyle.POSITION_OUTSIDE;
    } else {
      return ListStyle.POSITION_UNSET;
    }
  }

  public static ListStyle getListStyle(final String listStyleText) {
    final ListStyle listStyle = new ListStyle();
    final String[] tokens = HtmlValues.splitCssValue(listStyleText);
    for (final String token : tokens) {
      final int listStyleType = HtmlValues.getListStyleType(token);
      if (listStyleType != ListStyle.TYPE_UNSET) {
        listStyle.type = listStyleType;
      } else if (HtmlValues.isUrl(token)) {
        // TODO: listStyle.image
      } else {
        final int listStylePosition = HtmlValues.getListStylePosition(token);
        if (listStylePosition != ListStyle.POSITION_UNSET) {
          listStyle.position = listStylePosition;
        }
      }
    }
    return listStyle;
  }

  public static boolean isFontStyle(final String token) {
    return "italic".equals(token) || "normal".equals(token) || "oblique".equals(token);
  }

  public static boolean isFontVariant(final String token) {
    return "small-caps".equals(token) || "normal".equals(token);
  }

  public static boolean isFontWeight(final String token) {
    if ("bold".equals(token) || "bolder".equals(token) || "lighter".equals(token)) {
      return true;
    }
    try {
      final int value = Integer.parseInt(token);
      return ((value % 100) == 0) && (value >= 100) && (value <= 900);
    } catch (final NumberFormatException nfe) {
      return false;
    }
  }

  public static BorderInfo getBorderInfo(final CSS2Properties properties, final RenderState renderState) {
    final BorderInfo binfo = new BorderInfo();

    binfo.topStyle = getBorderStyle(properties.getBorderTopStyle());
    binfo.rightStyle = getBorderStyle(properties.getBorderRightStyle());
    binfo.bottomStyle = getBorderStyle(properties.getBorderBottomStyle());
    binfo.leftStyle = getBorderStyle(properties.getBorderLeftStyle());

    final ColorFactory cf = ColorFactory.getInstance();
    final String topColorSpec = properties.getBorderTopColor();
    if (topColorSpec != null) {
      binfo.topColor = cf.getColor(topColorSpec);
    }
    final String rightColorSpec = properties.getBorderRightColor();
    if (rightColorSpec != null) {
      binfo.rightColor = cf.getColor(rightColorSpec);
    }
    final String bottomColorSpec = properties.getBorderBottomColor();
    if (bottomColorSpec != null) {
      binfo.bottomColor = cf.getColor(bottomColorSpec);
    }
    final String leftColorSpec = properties.getBorderLeftColor();
    if (leftColorSpec != null) {
      binfo.leftColor = cf.getColor(leftColorSpec);
    }

    HtmlValues.populateBorderInsets(binfo, properties, renderState);

    return binfo;
  }

  public static Insets getBorderStyles(final CSS2Properties properties) {
    final int topStyle = getBorderStyle(properties.getBorderTopStyle());
    final int rightStyle = getBorderStyle(properties.getBorderRightStyle());
    final int bottomStyle = getBorderStyle(properties.getBorderBottomStyle());
    final int leftStyle = getBorderStyle(properties.getBorderLeftStyle());
    return new Insets(topStyle, leftStyle, bottomStyle, rightStyle);
  }

  private static int getBorderStyle(final String styleText) {
    if ((styleText == null) || (styleText.length() == 0)) {
      return HtmlValues.BORDER_STYLE_NONE;
    }
    final String stl = styleText.toLowerCase();
    if ("solid".equals(stl)) {
      return BORDER_STYLE_SOLID;
    } else if ("dashed".equals(stl)) {
      return BORDER_STYLE_DASHED;
    } else if ("dotted".equals(stl)) {
      return BORDER_STYLE_DOTTED;
    } else if ("none".equals(stl)) {
      return BORDER_STYLE_NONE;
    } else if ("hidden".equals(stl)) {
      return BORDER_STYLE_HIDDEN;
    } else if ("double".equals(stl)) {
      return BORDER_STYLE_DOUBLE;
    } else if ("groove".equals(stl)) {
      return BORDER_STYLE_GROOVE;
    } else if ("ridge".equals(stl)) {
      return BORDER_STYLE_RIDGE;
    } else if ("inset".equals(stl)) {
      return BORDER_STYLE_INSET;
    } else if ("outset".equals(stl)) {
      return BORDER_STYLE_OUTSET;
    } else {
      return BORDER_STYLE_NONE;
    }
  }

  public static boolean isBackgroundRepeat(final String repeat) {
    final String repeatTL = repeat.toLowerCase();
    return repeatTL.indexOf("repeat") != -1;
  }

  public static boolean isBackgroundPosition(final String token) {
    return isLength(token) || token.endsWith("%") || token.equalsIgnoreCase("top") || token.equalsIgnoreCase("center")
        || token.equalsIgnoreCase("bottom") || token.equalsIgnoreCase("left") || token.equalsIgnoreCase("right");
  }
}
