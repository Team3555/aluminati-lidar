/**
 * Copyright (c) 2019 Team 3555
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.aluminati3555.aluminatilidar.lidar;

import java.util.ArrayList;

/**
 * This class interprets the data from the LIDAR sensor
 * 
 * @author Caleb Heydon
 */
public class LIDARBuffer {
	private static final int BUFFER_SIZE = 8192;

	private int[] data;
	private ArrayList<Point> points;

	/**
	 * Returns the integer array as a buffer
	 * 
	 * @return
	 */
	public int[] getData() {
		return data;
	}

	/**
	 * Returns the list of points
	 * 
	 * @return
	 */
	public ArrayList<Point> getPoints() {
		synchronized (points) {
			return points;
		}
	}

	/**
	 * Decodes the data from the buffer
	 */
	public void update() {
		synchronized (points) {
			points.clear();

			for (int i = 0; i < data.length; i += 3) {
				Point point = new Point(data[i], data[i + 1], data[i + 2]);

				if (point.distance != 0) {
					points.add(point);
				}
			}
		}
	}

	public LIDARBuffer() {
		data = new int[BUFFER_SIZE];
		points = new ArrayList<Point>();
	}

	public static class Point {
		public int angle;
		public int distance;
		public int quality;

		public Point(int angle, int distance, int quality) {
			this.angle = angle;
			this.distance = distance;
			this.quality = quality;
		}
	}
}
