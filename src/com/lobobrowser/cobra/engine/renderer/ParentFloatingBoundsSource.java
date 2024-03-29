/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2006 The XAMJ Project

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
package com.lobobrowser.cobra.engine.renderer;

import org.lobobrowser.util.Objects;

public class ParentFloatingBoundsSource implements FloatingBoundsSource {
  private final int blockShiftRight;
  private final int expectedBlockWidth;
  private final int newX;
  private final int newY;
  private final FloatingBounds floatBounds;

  public ParentFloatingBoundsSource(final int blockShiftRight, final int expectedWidth, final int newX, final int newY,
      final FloatingBounds floatBounds) {
    super();
    this.blockShiftRight = blockShiftRight;
    this.expectedBlockWidth = expectedWidth;
    this.newX = newX;
    this.newY = newY;
    this.floatBounds = floatBounds;
  }

  public FloatingBounds getChildBlockFloatingBounds(final int apparentBlockWidth) {
    final int actualRightShift = this.blockShiftRight + (this.expectedBlockWidth - apparentBlockWidth);
    return new ShiftedFloatingBounds(this.floatBounds, -this.newX, -actualRightShift, -this.newY);
  }

  @Override
  public boolean equals(final Object obj) {
    // Important for layout caching.
    if (!(obj instanceof ParentFloatingBoundsSource)) {
      return false;
    }
    final ParentFloatingBoundsSource other = (ParentFloatingBoundsSource) obj;
    return (this.blockShiftRight == other.blockShiftRight) && (this.expectedBlockWidth == other.expectedBlockWidth)
        && (this.newX == other.newX)
        && (this.newY == other.newY) && Objects.equals(this.floatBounds, other.floatBounds);

  }

  @Override
  public int hashCode() {
    return this.newX ^ this.newY ^ this.blockShiftRight ^ this.expectedBlockWidth;
  }
}
