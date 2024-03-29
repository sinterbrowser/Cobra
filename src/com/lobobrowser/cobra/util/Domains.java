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
 * Created on Jun 2, 2005
 */
package com.lobobrowser.cobra.util;

/**
 * @author J. H. S.
 */
public class Domains {
  /*
  private static final Collection<String> gTLDs;

  static {
    gTLDs = new HashSet<>();
    gTLDs.add(".com");
    gTLDs.add(".edu");
    gTLDs.add(".gov");
    gTLDs.add(".int");
    gTLDs.add(".mil");
    gTLDs.add(".net");
    gTLDs.add(".org");
    gTLDs.add(".biz");
    gTLDs.add(".info");
    gTLDs.add(".name");
    gTLDs.add(".pro");
    gTLDs.add(".aero");
    gTLDs.add(".coop");
    gTLDs.add(".museum");
    // TODO: New gTLDs?
  }*/

  /**
   *
   */
  private Domains() {
    super();
  }

  /** @deprecated use the method in DomainValidation.java */
  /*
  public static boolean isValidCookieDomain(String domain, final String hostName) {
    String plainDomain;
    if (!domain.startsWith(".")) {
      // Valid domains must start with a dot according to RFC 2109, but
      // RFC 2965 specifies a dot is prepended in the Set-Cookie2 header.
      plainDomain = domain;
      domain = "." + domain;
    } else {
      plainDomain = domain.substring(1);
    }
    final String plainDomainTL = plainDomain.toLowerCase();
    final String hostNameTL = hostName.toLowerCase();
    if (!hostNameTL.endsWith(plainDomainTL)) {
      return false;
    }
    final int lastDotIdx = domain.lastIndexOf('.');
    if (lastDotIdx == -1) {
      return false;
    }
    final String suffix = domain.substring(lastDotIdx).toLowerCase();
    if (gTLDs.contains(suffix)) {
      return Strings.countChars(domain, '.') >= 2;
    } else {
      return Strings.countChars(domain, '.') >= 3;
    }
  }*/

  /*
   *
   * @param host
   *          A host name in lower case.
   * @return
   /
  public static boolean endsWithGTLD(final String host) {
    final Iterator<String> i = gTLDs.iterator();
    while (i.hasNext()) {
      final String ending = i.next();
      if (host.endsWith(ending)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isLikelyHostName(final String name) {
    final String nameTL = name.toLowerCase();
    if (nameTL.startsWith("www.")) {
      return true;
    }
    if (endsWithGTLD(name)) {
      return true;
    }
    final int lastDotIdx = nameTL.lastIndexOf('.');
    if (lastDotIdx == -1) {
      return false;
    }
    // Check for country code.
    return lastDotIdx == nameTL.length() - 3;
  }

  public static Collection<String> getPossibleDomains(final String hostName) {
    final Collection<String> domains = new LinkedList<>();
    domains.add(hostName);
    final int dotIdx = hostName.indexOf('.', 1);
    if (dotIdx == -1) {
      return domains;
    }
    final String testDomain = hostName.substring(dotIdx);
    if (!Domains.isValidCookieDomain(testDomain, hostName)) {
      return domains;
    }
    domains.addAll(Domains.getPossibleDomains(testDomain.substring(1)));
    return domains;
  }*/
}
