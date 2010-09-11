/**
 *  The Units of Measure API, UCUM codes, UCUM table (regardless of format),
 *  and UCUM Specification are copyright © 1999-2010,
 *  Regenstrief Institute, Inc. and the Unified Codes for Units of Measures
 *  (UCUM) Organization. All rights reserved.
 *
 * See TermsOfUse for details.
 */
package org.unitsofmeasure.util;

import java.io.IOException;
import java.text.ParsePosition;

import org.unitsofmeasure.Unit;

/**
 * <p>
 * This interface provides methods for implementing bundles to parse/format
 * units.
 * </p>
 * 
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author <a href="mailto:jcp@catmedia.us">Werner Keil</a>
 * @version 1.2 ($Revision$), $Date$
 * 
 */
public interface UnitFormat {

    /**
     * Formats the specified unit.
     * 
     * @param unit
     *            the unit to format.
     * @param appendable
     *            the appendable destination.
     * @return the appendable destination passed in with formatted text
     *         appended.
     * @throws IOException
     *             if an error occurs.
     */
    Appendable format(Unit<?> unit, Appendable appendable) throws IOException;

    /**
     * Parses a portion of the specified {@link CharSequence} from the specified
     * position to produce a unit. If there is no unit to parse the unitary unit
     * (dimensionless) is returned.
     * 
     * @param csq
     *            the <code>CharSequence</code> to parse.
     * @param cursor
     *            the cursor holding the current parsing index or <code>
     *        null</code>
     *            to parse the whole character sequence.
     * @return the unit parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException
     *             if any problem occurs while parsing the specified character
     *             sequence (e.g. illegal syntax).
     */
    Unit<?> parse(CharSequence csq, ParsePosition cursor)
	    throws IllegalArgumentException;
}