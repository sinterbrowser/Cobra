package com.lobobrowser.cobra.engine.domimpl;

import org.w3c.dom.html.HTMLAppletElement;

public class HTMLAppletElementImpl extends HTMLAbstractUIElement implements HTMLAppletElement {
  public HTMLAppletElementImpl(final String name) {
    super(name);
  }

  public String getAlign() {
    return this.getAttribute("align");
  }

  public String getAlt() {
    return this.getAttribute("alt");
  }

  public String getArchive() {
    return this.getAttribute("archive");
  }

  public String getCode() {
    return this.getAttribute("code");
  }

  public String getCodeBase() {
    return this.getAttribute("codebase");
  }

  public String getHeight() {
    return this.getAttribute("height");
  }

  public String getHspace() {
    return this.getAttribute("hspace");
  }

  public String getName() {
    return this.getAttribute("name");
  }

  public String getObject() {
    return this.getAttribute("object");
  }

  public String getVspace() {
    return this.getAttribute("vspace");
  }

  public String getWidth() {
    return this.getAttribute("width");
  }

  public void setAlign(final String align) {
    this.setAttribute("align", align);
  }

  public void setAlt(final String alt) {
    this.setAttribute("alt", alt);
  }

  public void setArchive(final String archive) {
    this.setAttribute("archive", archive);
  }

  public void setCode(final String code) {
    this.setAttribute("code", code);
  }

  public void setCodeBase(final String codeBase) {
    this.setAttribute("codebase", codeBase);
  }

  public void setHeight(final String height) {
    this.setAttribute("height", height);
  }

  public void setHspace(final String hspace) {
    this.setAttribute("hspace", hspace);
  }

  public void setName(final String name) {
    this.setAttribute("name", name);
  }

  public void setObject(final String object) {
    this.setAttribute("object", object);
  }

  public void setVspace(final String vspace) {
    this.setAttribute("vspace", vspace);
  }

  public void setWidth(final String width) {
    this.setAttribute("width", width);
  }
}
