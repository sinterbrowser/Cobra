package com.lobobrowser.cobra.engine.renderer;

abstract class BaseRenderable implements Renderable {
  private int ordinal = 0;

  public int getOrdinal() {
    return this.ordinal;
  }

  public int getZIndex() {
    return 0;
  }

  public void setOrdinal(final int ordinal) {
    this.ordinal = ordinal;
  }
}
