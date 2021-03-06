/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.lutron.internal.radiora.protocol;

/**
 * Feedback that gives the state of all zones
 * <p>
 * <b>Syntax:</b>
 *
 * <pre>
 * {@code
 * ZMP,<Zone States>
 * }
 * </pre>
 *
 * <b>Example:</b>
 * <p>
 * Zones 2 and 9 are ON, all others are OFF, and Zones 31 and 32 are unassigned.
 *
 * <pre>
 * ZMP,010000001000000000000000000000XX
 * </pre>
 *
 * @author Jeff Lauterbach - Initial Contribution
 *
 */
public class ZoneMapFeedback extends RadioRAFeedback {

    private String zoneStates; // 32 bit String of (0,1,X)

    public ZoneMapFeedback(String msg) {
        String[] params = parse(msg, 1);

        zoneStates = params[1];
    }

    public String getZoneStates() {
        return zoneStates;
    }

    public char getZoneValue(int zone) {
        if (zone < 1 || zone > zoneStates.length()) {
            return 'X';
        }

        return zoneStates.charAt(zone - 1);
    }
}
