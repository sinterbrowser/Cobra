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
/*
 * Created on Oct 8, 2005
 */
package com.lobobrowser.cobra.engine.domimpl;

import org.w3c.dom.html.HTMLHtmlElement;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement {
  public HTMLHtmlElementImpl() {
    super("HTML", true);
  }

  public HTMLHtmlElementImpl(final String name) {
    super(name, true);
  }

  public String getVersion() {
    return this.getAttribute("version");
  }

  public void setVersion(final String version) {
    this.setAttribute("version", version);
  }
}
