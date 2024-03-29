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
 * Created on Apr 15, 2005
 */
package com.lobobrowser.cobra.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author J. H. S.
 */
public class MonitoredInputStream extends InputStream {
  private final InputStream delegate;
  private int progress = 0;
  private final long minProgressEventGap;
  public final EventDispatch evtProgress = new EventDispatch();

  public MonitoredInputStream(final InputStream delegate, final int minProgressEventGap) {
    this.delegate = delegate;
    this.minProgressEventGap = minProgressEventGap;
  }

  public MonitoredInputStream(final InputStream delegate) {
    this(delegate, 200);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.InputStream#available()
   */
  @Override
  public int available() throws IOException {
    return this.delegate.available();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.InputStream#close()
   */
  @Override
  public void close() throws IOException {
    this.delegate.close();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.InputStream#markSupported()
   */
  @Override
  public boolean markSupported() {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.InputStream#read()
   */
  @Override
  public int read() throws IOException {
    final int b = this.delegate.read();
    if (b != -1) {
      this.progress++;
    }
    return b;
  }

  private long lastEvenPosted = 0;

  /*
   * (non-Javadoc)
   *
   * @see java.io.InputStream#read(byte[], int, int)
   */
  @Override
  public int read(final byte[] arg0, final int arg1, final int arg2) throws IOException {
    final int numRead = this.delegate.read(arg0, arg1, arg2);
    if (numRead != -1) {
      this.progress += numRead;
      final long currentTime = System.currentTimeMillis();
      if ((currentTime - this.lastEvenPosted) > this.minProgressEventGap) {
        this.evtProgress.fireEvent(new InputProgressEvent(this, this.progress));
        this.lastEvenPosted = currentTime;
      }
    }
    return numRead;
  }
}
