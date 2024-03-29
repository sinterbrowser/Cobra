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
package com.lobobrowser.cobra.engine.renderer;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.lobobrowser.html.domimpl.HTMLBaseInputElement;

public class InputFileControl extends BaseInputControl {
  private final JTextField textField = new JTextField();
  private final JButton browseButton = new JButton();

  public InputFileControl(final HTMLBaseInputElement modelNode) {
    super(modelNode);
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    final JButton browseButton = this.browseButton;
    browseButton.setAction(new BrowseAction());
    browseButton.setText("Browse");
    final java.awt.Dimension ps = this.textField.getPreferredSize();
    this.textField.setPreferredSize(new java.awt.Dimension(128, ps.height));
    this.textField.setEditable(false);
    this.add(this.textField);
    this.add(Box.createHorizontalStrut(4));
    this.add(browseButton);
  }

  @Override
  public String getValue() {
    // This is the way browsers behave, even
    // though this value is not submitted.
    return this.textField.getText();
  }

  @Override
  public void setDisabled(final boolean disabled) {
    this.browseButton.setEnabled(!disabled);
  }

  @Override
  public void setValue(final String value) {
    // nop - security
  }

  private File fileValue;

  private void setFileValue(final File file) {
    this.fileValue = file;
    if (file == null) {
      this.textField.setText("");
    } else {
      this.textField.setText(file.getAbsolutePath());
    }
  }

  @Override
  public File getFileValue() {
    return this.fileValue;
  }

  public void resetInput() {
    this.setFileValue(null);
  }

  private class BrowseAction extends AbstractAction {
    public void actionPerformed(final ActionEvent e) {
      final JFileChooser chooser = new JFileChooser();
      if (chooser.showOpenDialog(InputFileControl.this) == JFileChooser.APPROVE_OPTION) {
        setFileValue(chooser.getSelectedFile());
      } else {
        setFileValue(null);
      }
    }
  }
}
