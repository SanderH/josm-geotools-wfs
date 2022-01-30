package org.openstreetmap.josm.plugins.geotools_wfs;

import java.net.URI;
import java.net.URL;

import org.geotools.util.Converter;
import org.geotools.util.ConverterFactory;
import org.geotools.util.factory.Hints;

/**
 * Converter factory which can convert from URL or URI to String.
 *
 * <p>
 * Handles the following conversions:
 *
 * <ul>
 * <li>URL -> String
 * <li>URI -> String
 * </ul>
 *
 * @author gertjan
 */
public class URtoStringConverterFactory implements ConverterFactory {
    public static final Converter URLToString = new Converter() {
        @SuppressWarnings("unchecked")
        public <T> T convert(Object source, Class<T> target) throws Exception {
            URL url = (URL) source;
            return (T) url.toString();
        }
    };

    public static final Converter URIToString = new Converter() {
        @SuppressWarnings("unchecked")
        public <T> T convert(Object source, Class<T> target) throws Exception {
            URI uri = (URI) source;
            return (T) uri.toString();
        }
    };

    public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
        if (URL.class.equals(source) && String.class.equals(target)) {
            return URLToString;
        }
        if (URI.class.equals(source) && String.class.equals(target)) {
            return URIToString;
        }
        return null;
    }
}
