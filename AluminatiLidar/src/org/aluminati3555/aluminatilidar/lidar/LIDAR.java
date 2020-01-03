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

/**
 * This is the java wrapper for liblidar
 * 
 * @author Caleb Heydon
 */
public class LIDAR {
	// Native
	private static native int nativeStart(String device);

	private static native int nativeStop();

	private static native int nativeRead(int[] buffer);

	/**
	 * Returns the path of the device
	 * 
	 * @param number
	 * @return
	 */
	private static String getDeviceByNumber(int number) {
		return "/dev/ttyUSB" + number;
	}

	// Library
	public static final String LIBRARY_NAME = "lidar";

	/**
	 * Starts the lidar scanner
	 * 
	 * @param device
	 * @throws LIDARException
	 */
	public static synchronized void start(int device) throws LIDARException {
		String deviceName = getDeviceByNumber(device);

		int result = nativeStart(deviceName);
		if (result != 0) {
			throw new LIDARException("Unable to access device");
		}
	}

	/**
	 * Stops the driver
	 * 
	 * @throws LIDARException
	 */
	public static synchronized void stop() throws LIDARException {
		int result = nativeStop();
		if (result != 0) {
			throw new LIDARException("Unable to access device");
		}
	}

	/**
	 * Reads data from the LIDAR sensor
	 * 
	 * @param buffer
	 */
	public static synchronized void read(LIDARBuffer buffer) throws LIDARException {
		for (int i = 0; i < buffer.getData().length; i++) {
			buffer.getData()[i] = 0;
		}
		
		int result = nativeRead(buffer.getData());
		if (result != 0) {
			throw new LIDARException("Unable to access device");
		}
		
		buffer.update();
	}
}
