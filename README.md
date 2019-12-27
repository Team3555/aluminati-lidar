# AluminatiLidar

AluminatiLidar is a template for vision processing: The AluminatiLidar.java file can be customized by the user for a wide range of vision processing tasks.  AluminatiLidar should work well with very little modification (such as setting IP addresses and adjusting HSV and contour filters).

When all of the components are compiled/added to the source tree, the directory structure should look like this:
```
AluminatiLidar
              |-jni
              |-jre
              |-AluminatiLidar.sh
              |-AluminatiLidar.jar
```

Remember to modify the file permissions of the scripts to make them executable.

# Features
 - Highly customizable
 - Target info over UDP
 - Fast garbage collection (usually under 10 ms)
 - All bytecode is translated to machine language immediately at runtime
 - Runs as a real-time process on Linux
 
# Default program
The default AluminatiLidar has an untuned HSV threshold and contour filters and attempts to track one target and send the target info to 10.35.55.2.

# LIDAR systems
The only currently supported LIDAR system is the RPLIDAR A1.

Supported cameras:
```
PS3 Eye Camera (187 FPS, The maximum FPS is 187 not 120 as it says in the documentation)
Microsoft Lifecam HD-3000 (30 FPS)
```
The default program is setup for the PS3 camera, but it can easily be changed to work with the Microsoft Lifecam.

# Filesystem corruption
Since the vision system will be turned off just by cutting the power, it is important the the filesystem does not become corrupt.  The best way to do this is to make the filesystem read-only, but there needs to be a way to switch back and forth between read-only mode and read-write mode so that new programs can be deployed.  The scripts folder contains a script for the initial setup of a read-only filesystem.

To set the filesystem to rw again, run this:
```
 sudo mount -o remount,rw /
```
To set the filesystem to ro run this command:
```
 sudo mount -o remount,ro /
```

# Target info
The template contains a very basic UDP protocol to transfer the data to the robot, but the user is free to implement any protocol.  AluminatiLidar currently does not include any WPILib functions mainly for simplicity.  So, network tables may be more difficult to get working.

# File locations
The root of this project should be copied to /home/pi on a Raspberry Pi.  The executable should be at /home/pi/AluminatiLidar/AluminatiLidar.jar.  To run AluminatiLidar at startup, create a systemd service to run the startup script (/home/pi/AluminatiLidar/AluminatiLidar.sh).  The Booster program should have a similar setup in the /home/pi/Booster folder.

# Note about the JVM
This project currently does not contain a JVM.  It is necessary to add one before running the vision system.  We recommend using the Bellsoft JRE 13.  It should be placed so that the java executable is at /home/pi/AluminatiLidar/jre/bin/java.

Download the JRE for the Raspberry Pi from this link: https://download.bell-sw.com/java/13.0.1/bellsoft-jre13.0.1-linux-arm32-vfp-hflt.tar.gz

# Setup steps (using premade image)
1. Flash premade image to an SD card.
2. Connect the Raspberry Pi to your computer with ethernet, and boot from the SD card.
3. Using the hostname aluminatilidar.local, log in with SSH and SCP (username: pi, password: AluminatiLidar).
4. Set the filesystem to read-write.
5. Upload your custom vision program based on the the code in /home/pi/AluminatiLidar/src to /home/pi/AluminatiLidar/AluminatiLidar.jar.
6. Set the filesystem to read-only, and reboot.

# Setup steps (from scratch)
1. Flash Raspbian Buster to an SD card.
2. Add empty SSH file to "boot" partition.
3. Disable wifi and bluetooth (see other section).
4. Connect the Raspberry Pi to your computer with ethernet (bridged to Internet), and boot from the SD card.
5. Using the hostname raspberrypi.local, log in with SSH and SCP (username: pi, password: raspberry).
6. Upload the repository to the home directory so that the root of this project is located at /home/pi.
7. Upload a Java 13 JRE (Bellsoft JRE recommended) to /home/pi/AluminatiLidar/jre.
8. Upload your custom vision program to /home/pi/AluminatiLidar/AluminatiLidar.jar
9. Navigate to /home/pi/scripts, and run the following commands:
```
chmod +x *.sh
./install-deps.sh
./install-lidar-service.sh
sudo chmod -x /etc/rc.local
sudo ./read-only-fs.sh
```
10. Reboot

# Library
The lib/src directory contains Java code for communicating with AluminatiLidar from the robot code.  The code is simple and should be able to be replicated in other languages (C++) with little difficulty.

# Disabling wifi and bluetooth
FRC requires that all wireless communcation be disabled on the Raspberry Pi to use it in competition.  Add these lines to /boot/config.txt before the line [pi4] to disable both wifi and bluetooth.
```
dtoverlay=disable-wifi
dtoverlay=disable-bt
```
https://raspberrypi.stackexchange.com/questions/43720/disable-wifi-wlan0-on-pi-3

# Notice
The scripts have been collected from various projects and are released under different licenses.  Here is a list of links to these projects:

 - https://github.com/Drewsif/PiShrink
 - https://gist.github.com/willprice/abe456f5f74aa95d7e0bb81d5a710b60
 - https://github.com/adafruit/Raspberry-Pi-Installer-Scripts/blob/master/read-only-fs.sh
