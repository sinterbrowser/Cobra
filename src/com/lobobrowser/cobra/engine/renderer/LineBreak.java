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

public class LineBreak {
  public static final int NONE = 0;
  public static final int LEFT = 1;
  public static final int RIGHT = 2;
  public static final int ALL = 3;

  private final int breakType;

  public LineBreak(final int breakType) {
    this.breakType = breakType;
  }

  public int getBreakType() {
    return this.breakType;
  }

  public static int getBreakType(final String clearAttr) {
    if (clearAttr == null) {
      return NONE;
    } else if ("all".equalsIgnoreCase(clearAttr)) {
      return ALL;
    } else if ("left".equalsIgnoreCase(clearAttr)) {
      return LEFT;
    } else if ("right".equalsIgnoreCase(clearAttr)) {
      return RIGHT;
    } else {
      return NONE;
    }
  }
}
