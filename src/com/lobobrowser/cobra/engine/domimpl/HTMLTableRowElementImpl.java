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
 * Created on Dec 4, 2005
 */
package com.lobobrowser.cobra.engine.domimpl;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableRowElement;

public class HTMLTableRowElementImpl extends HTMLElementImpl implements HTMLTableRowElement {
  public HTMLTableRowElementImpl(final String name) {
    super(name, true);
  }

  public HTMLTableRowElementImpl() {
    super("TR", true);
  }

  public int getRowIndex() {
    final NodeImpl parent = (NodeImpl) this.getParentNode();
    if (parent == null) {
      return -1;
    }
    try {
      parent.visit(new NodeVisitor() {
        private int count = 0;

        public void visit(final Node node) {
          if (node instanceof HTMLTableRowElementImpl) {
            if (HTMLTableRowElementImpl.this == node) {
              throw new StopVisitorException(new Integer(this.count));
            }
            this.count++;
          }
        }
      });
    } catch (final StopVisitorException sve) {
      return ((Integer) sve.getTag()).intValue();
    }
    return -1;
  }

  public int getSectionRowIndex() {
    // TODO Auto-generated method stub
    return 0;
  }

  public HTMLCollection getCells() {
    final NodeFilter filter = new NodeFilter() {
      public boolean accept(final Node node) {
        return node instanceof HTMLTableCellElementImpl;
      }
    };
    return new DescendentHTMLCollection(this, filter, this.treeLock, false);
  }

  public String getAlign() {
    return this.getAttribute("align");
  }

  public void setAlign(final String align) {
    this.setAttribute("align", align);
  }

  public String getBgColor() {
    return this.getAttribute("bgcolor");
  }

  public void setBgColor(final String bgColor) {
    this.setAttribute("bgcolor", bgColor);
  }

  public String getCh() {
    return this.getAttribute("ch");
  }

  public void setCh(final String ch) {
    this.setAttribute("ch", ch);
  }

  public String getChOff() {
    return this.getAttribute("choff");
  }

  public void setChOff(final String chOff) {
    this.setAttribute("choff", chOff);
  }

  public String getVAlign() {
    return this.getAttribute("valign");
  }

  public void setVAlign(final String vAlign) {
    this.setAttribute("valign", vAlign);
  }

  /**
   * Inserts a TH element at the specified index.
   * <p>
   * Note: This method is non-standard.
   *
   * @param index
   *          The cell index to insert at.
   * @return The element that was inserted.
   * @throws DOMException
   *           When the index is out of range.
   */
  public HTMLElement insertHeader(final int index) throws DOMException {
    return this.insertCell(index, "TH");
  }

  public HTMLElement insertCell(final int index) throws DOMException {
    return this.insertCell(index, "TD");
  }

  private HTMLElement insertCell(final int index, final String tagName) throws DOMException {
    final org.w3c.dom.Document doc = this.document;
    if (doc == null) {
      throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, "Orphan element");
    }
    final HTMLElement cellElement = (HTMLElement) doc.createElement(tagName);
    synchronized (this.treeLock) {
      if (index == -1) {
        this.appendChild(cellElement);
        return cellElement;
      }
      final ArrayList<Node> nl = this.nodeList;
      if (nl != null) {
        final int size = nl.size();
        int trcount = 0;
        for (int i = 0; i < size; i++) {
          final Node node = nl.get(i);
          if (node instanceof HTMLTableCellElement) {
            if (trcount == index) {
              this.insertAt(cellElement, i);
              return cellElement;
            }
            trcount++;
          }
        }
      } else {
        this.appendChild(cellElement);
        return cellElement;
      }
    }
    throw new DOMException(DOMException.INDEX_SIZE_ERR, "Index out of range");
  }

  public void deleteCell(final int index) throws DOMException {
    synchronized (this.treeLock) {
      final ArrayList<Node> nl = this.nodeList;
      if (nl != null) {
        final int size = nl.size();
        int trcount = 0;
        for (int i = 0; i < size; i++) {
          final Node node = nl.get(i);
          if (node instanceof HTMLTableCellElement) {
            if (trcount == index) {
              this.removeChildAt(index);
            }
            trcount++;
          }
        }
      }
    }
    throw new DOMException(DOMException.INDEX_SIZE_ERR, "Index out of range");
  }
}
