#!/bin/sh

LD_LIBRARY_PATH=/home/pi/AluminatiLidar/jni chrt --rr 99 /home/pi/AluminatiLidar/jre/bin/java -Xcomp -Xmx128M -XX:+UseG1GC -XX:MaxGCPauseMillis=10 -XX:InitiatingHeapOccupancyPercent=50 -XX:+PrintGCDetails -jar /home/pi/AluminatiLidar/AluminatiLidar.jar
