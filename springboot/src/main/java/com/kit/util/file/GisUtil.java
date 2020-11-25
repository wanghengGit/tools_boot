package com.kit.util.file;

import lombok.Data;

import java.awt.image.BufferedImage;

public class GisUtil {

	public static Location getCoordinatesByGlobe(float latitude, float longitude, int mapLatitudeMin,
			int mapLatitudeMax, int mapLongitudeMin, int mapLongitudeMax, BufferedImage mapImage) {

		/**
		 * Work out minimum and maximums,clamp inside map bounds
		 */
		latitude = Math.max(mapLatitudeMin, Math.min(mapLatitudeMax, latitude));
		longitude = Math.max(mapLongitudeMin, Math.min(mapLongitudeMax, longitude));

		/**
		 * We need the distance from 0 or minimum long/lat
		 */
		float adjLon = longitude - mapLongitudeMin;
		float adjLat = latitude - mapLatitudeMin;

		float mapLongWidth = mapLongitudeMax - mapLongitudeMin;
		float mapLatHeight = mapLatitudeMax - mapLatitudeMin;

		float mapWidth = mapImage.getWidth();
		float mapHeight = mapImage.getHeight();

		float longPixelRatio = mapWidth / mapLongWidth;
		float latPixelRatio = mapHeight / mapLatHeight;

		int x = Math.round(adjLon * longPixelRatio) - 3;// these are offsets for
														// the target icon that
														// shows.. eedit laterrr
														// @oz
		int y = Math.round(adjLat * latPixelRatio) + 3; //

		// turn it up
		y = (int) (mapHeight - y);

		return new Location(x, y);
	}

	@Data
	public static class Location {
		private int x;
		private int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
