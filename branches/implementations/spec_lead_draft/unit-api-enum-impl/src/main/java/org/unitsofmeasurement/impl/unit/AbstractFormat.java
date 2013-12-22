/**
 *
 */
package org.unitsofmeasurement.impl.unit;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;

import javax.measure.Unit;
import javax.measure.function.UnitFormat;


/**
 * <p>
 * This class provides the interface for formatting and parsing {@linkplain org.unitsofmeasurement.unit.Unit
 * units}.
 * </p>
 *
 * <p>
 * The {@linkplain #getStandard standard} instance (UCUM) recognizes all metric units
 * and the 20 SI prefixes used to form decimal multiples and some customory
 * units (see <a href="http://org.unitsofmeasure">UCUM</a> specification). For
 * example:
 *
 * <pre><code>
 *        UnitFormat.getStandard().valueOf("kW").equals(KILO(WATT))
 *        UnitFormat.getStandard().valueOf("[ft_i]").equals(METRE.multiply(3048).divide(10000))
 *        UnitFormat.getInstance(Locale.USA).valueOf("ft").equals(METRE.multiply(3048).divide(10000))
 * [/code]
 * </p>
 *
 * <p>
 * OSGi bundles should use {@link org.unitsofmeasurement.unit.UnitFormat} to
 * parse/format {@linkplain #getStandard() standard} (UCUM) units.
 * </p>
 *
 * @author <a href="mailto:werner.keil@gmail.com">Werner Keil</a>
 * @version 1.2 ($Revision: 18019 $), $Date: 2011-07-23 18:25:49 +0530 (Sat, 23 Jul 2011) $
 * @see <a href="http://org.unitsofmeasure">Unified Code of Measure (UCUM)</a>
 */
abstract class AbstractFormat extends Format implements UnitFormat {

    /** The serialVersionUID */
    private static final long serialVersionUID = 7765623276257908888L;



    /**
     * Returns the standard unit format (UCUM). OSGi bundles should use
     * {@link org.unitsofmeasurement.unit.UnitFormat} to parse/format standard
     * (UCUM) units.
     *
     * @return the standard format.
     */
    public static UnitFormat getStandard() {
        return SimpleFormat.getStandardInstance();
    }

    /**
     * Returns the unit format for the default locale.
     *
     * @return the locale format.
     */
    public static UnitFormat getInstance() {
        return SimpleFormat.getInstance();
    }

    /**
     * Returns the unit format for the specified locale.
     *
     * @param locale
     *            the locale for which the format is returned.
     * @return the format for the specified locale.
     */
    public static UnitFormat getInstance(Locale locale) {
        return SimpleFormat.getInstance();
    }

    /**
     * Base constructor.
     */
    protected AbstractFormat() {
    }

    /**
     * Formats the specified unit.
     *
     * @param unit
     *            the unit to format.
     * @param appendable
     *            the appendable destination.
     * @return The appendable destination passed in as {@code appendable}, with
     *         formatted text appended.
     * @throws java.io.IOException
     *             if an error occurs.
     */
    public abstract Appendable format(Unit<?> unit, Appendable appendable)
        throws IOException;

    /**
     * Parses a portion of the specified <code>CharSequence</code> from the
     * specified position to produce a unit. If there is no unit to parse the
     * unitary unit (dimensionless) is returned.
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
    public abstract Unit<?> parse(CharSequence csq, ParsePosition cursor)
        throws IllegalArgumentException;

    /**
     * Parses the specified character sequence to produce a unit (convenience
     * method). If the specified sequence is empty, the unitary unit
     * (dimensionless) is returned.
     *
     * @param csq
     *            the <code>CharSequence</code> to parse.
     * @return the unit parsed from the specified character sub-sequence.
     * @throws IllegalArgumentException
     *             if any problem occurs while parsing the specified character
     *             sequence (e.g. illegal syntax).
     */
    public final Unit<?> parse(CharSequence csq)
        throws IllegalArgumentException {
    return parse(csq, null);
    }

    @Override
    public final StringBuffer format(Object obj, final StringBuffer toAppendTo,
        FieldPosition pos) {
    if (!(obj instanceof Unit<?>))
        throw new IllegalArgumentException("obj: Not an instance of Unit"); //$NON-NLS-1$
    if ((toAppendTo == null) || (pos == null))
        throw new NullPointerException(); // Format contract.
    try {
        return (StringBuffer) format((Unit<?>) obj, (Appendable) toAppendTo);
    } catch (IOException ex) {
        throw new Error(ex); // Cannot happen.
    }
    }

    @Override
    public final Unit<?> parseObject(String source, ParsePosition pos) {
    try {
        return parse(source, pos);
    } catch (IllegalArgumentException e) {
        return null; // Unfortunately the message why the parsing failed
    } // is lost; but we have to follow the Format spec.

    }

}