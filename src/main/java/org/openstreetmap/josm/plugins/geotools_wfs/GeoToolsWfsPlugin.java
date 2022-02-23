//License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.geotools_wfs;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.tools.Logging;

/**
 * GeoTools WFS plugin, extending the Geotools plug-in with WFS support.
 */
public class GeoToolsWfsPlugin extends Plugin {

	/**
	 * Constructs a new {@code GeoToolsPlugin}.
	 * 
	 * @param info plugin information
	 */
	public GeoToolsWfsPlugin(PluginInformation info) {
		super(info);
		initGeoToolsWfs();
		checkEPSG();
	}

	private void initGeoToolsWfs() {
		// Force Axis order. Fix #8248
		// See https://docs.geotools.org/stable/userguide/library/referencing/order.html
		System.setProperty("org.geotools.referencing.forceXY", "true");
	}

	private void checkEPSG() {
		try {
			CRS.decode("EPSG:4326");
		} catch (NoSuchAuthorityCodeException e) {
			Logging.error("geotools_wfs: error in EPSG database initialization. NoSuchAuthorityCodeException: "
					+ e.getMessage());
		} catch (FactoryException e) {
			Logging.error("geotools_wfs: error in EPSG database initialization. FactoryException: " + e.getMessage());
		}
	}
}
