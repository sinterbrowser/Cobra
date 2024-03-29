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
 * Created on May 21, 2005
 */
package com.lobobrowser.cobra.engine.renderer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import org.lobobrowser.html.domimpl.ModelNode;
import org.lobobrowser.html.style.RenderState;

final class RBlank extends BaseBoundableRenderable {
  // TODO: Is there a need for RBlank's at all?
  public final int ascentPlusLeading;
  private final FontMetrics fontMetrics;

  public RBlank(final ModelNode me, final FontMetrics fm, final RenderableContainer container, final int ascentPlusLeading,
      final int width, final int height) {
    super(container, me);
    this.fontMetrics = fm;
    this.ascentPlusLeading = ascentPlusLeading;
    // Dimensions set when constructed.
    this.width = width;
    this.height = height;
  }

  @Override
  protected void invalidateLayoutLocal() {
  }

  public boolean onMouseClick(final java.awt.event.MouseEvent event, final int x, final int y) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onMouseClick(me, event, x, y);
    } else {
      return true;
    }
  }

  public boolean onDoubleClick(final java.awt.event.MouseEvent event, final int x, final int y) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onDoubleClick(me, event, x, y);
    } else {
      return true;
    }
  }

  public boolean onMousePressed(final java.awt.event.MouseEvent event, final int x, final int y) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onMouseDown(me, event, x, y);
    } else {
      return true;
    }
  }

  public boolean onMouseReleased(final java.awt.event.MouseEvent event, final int x, final int y) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onMouseUp(me, event, x, y);
    } else {
      return true;
    }
  }

  public boolean onMouseDisarmed(final java.awt.event.MouseEvent event) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onMouseDisarmed(me, event);
    } else {
      return true;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * net.sourceforge.xamj.domimpl.markup.Renderable#paint(java.awt.Graphics)
   */
  public final void paint(final Graphics g) {
    final RenderState rs = this.modelNode.getRenderState();

    if ((rs != null) && (rs.getVisibility() != RenderState.VISIBILITY_VISIBLE)) {
      // Just don't paint it.
      return;
    }

    final Color bkg = rs.getTextBackgroundColor();
    if (bkg != null) {
      final Color oldColor = g.getColor();
      try {
        g.setColor(bkg);
        g.fillRect(0, 0, this.width, this.height);
      } finally {
        g.setColor(oldColor);
      }
    }
    final int td = rs.getTextDecorationMask();
    if (td != 0) {
      if ((td & RenderState.MASK_TEXTDECORATION_UNDERLINE) != 0) {
        final int lineOffset = this.ascentPlusLeading + 2;
        g.drawLine(0, lineOffset, this.width, lineOffset);
      }
      if ((td & RenderState.MASK_TEXTDECORATION_LINE_THROUGH) != 0) {
        final FontMetrics fm = this.fontMetrics;
        final int lineOffset = fm.getLeading() + ((fm.getAscent() + fm.getDescent()) / 2);
        g.drawLine(0, lineOffset, this.width, lineOffset);
      }
      if ((td & RenderState.MASK_TEXTDECORATION_OVERLINE) != 0) {
        final int lineOffset = this.fontMetrics.getLeading();
        g.drawLine(0, lineOffset, this.width, lineOffset);
      }
      if ((td & RenderState.MASK_TEXTDECORATION_BLINK) != 0) {
        // TODO
      }
    }
    final Color over = rs.getOverlayColor();
    if (over != null) {
      final Color oldColor = g.getColor();
      try {
        g.setColor(over);
        g.fillRect(0, 0, width, height);
      } finally {
        g.setColor(oldColor);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.xamjwg.html.renderer.BoundableRenderable#paintSelection(java.awt.Graphics
   * , boolean, org.xamjwg.html.renderer.RenderablePoint,
   * org.xamjwg.html.renderer.RenderablePoint)
   */
  public boolean paintSelection(final Graphics g, final boolean inSelection, final RenderableSpot startPoint, final RenderableSpot endPoint) {
    if ((this == startPoint.renderable) || (this == endPoint.renderable)) {
      if (inSelection) {
        return false;
      }
    } else if (!inSelection) {
      return false;
    }
    g.setColor(SELECTION_COLOR);
    g.setXORMode(SELECTION_XOR);
    g.fillRect(0, 0, this.width, this.height);
    g.setPaintMode();
    return true;
  }

  public boolean extractSelectionText(final StringBuffer buffer, final boolean inSelection, final RenderableSpot startPoint,
      final RenderableSpot endPoint) {
    if ((this == startPoint.renderable) || (this == endPoint.renderable)) {
      if (inSelection) {
        return false;
      }
    } else if (!inSelection) {
      return false;
    }
    buffer.append(' ');
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.xamjwg.html.renderer.BoundableRenderable#getRenderable(int, int)
   */
  public RenderableSpot getLowestRenderableSpot(final int x, final int y) {
    return new RenderableSpot(this, x, y);
  }

  public boolean isContainedByNode() {
    return true;
  }

  public boolean onRightClick(final MouseEvent event, final int x, final int y) {
    final ModelNode me = this.modelNode;
    if (me != null) {
      return HtmlController.getInstance().onContextMenu(me, event, x, y);
    } else {
      return true;
    }
  }
}
