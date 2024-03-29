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
package com.lobobrowser.cobra.engine.domimpl;

import org.lobobrowser.html.style.HeadingRenderState;
import org.lobobrowser.html.style.RenderState;
import org.w3c.dom.html.HTMLHeadingElement;

public class HTMLHeadingElementImpl extends HTMLAbstractUIElement implements HTMLHeadingElement {
  public HTMLHeadingElementImpl(final String name) {
    super(name);
  }

  public String getAlign() {
    return this.getAttribute("align");
  }

  public void setAlign(final String align) {
    this.setAttribute("align", align);
  }

  @Override
  protected RenderState createRenderState(final RenderState prevRenderState) {
    // (can't put a RenderState in the middle - messes up "em" sizes).
    // prevRenderState = new FontSizeRenderState(prevRenderState, fontSize,
    // java.awt.Font.BOLD);
    return new HeadingRenderState(prevRenderState, this);
  }

  @Override
  protected void appendInnerTextImpl(final StringBuffer buffer) {
    final int length = buffer.length();
    int lineBreaks;
    if (length == 0) {
      lineBreaks = 2;
    } else {
      int start = length - 4;
      if (start < 0) {
        start = 0;
      }
      lineBreaks = 0;
      for (int i = start; i < length; i++) {
        final char ch = buffer.charAt(i);
        if (ch == '\n') {
          lineBreaks++;
        }
      }
    }
    for (int i = 0; i < (2 - lineBreaks); i++) {
      buffer.append("\r\n");
    }
    super.appendInnerTextImpl(buffer);
    buffer.append("\r\n\r\n");
  }

}
