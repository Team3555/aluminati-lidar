default:
	javac -h . LIDAR.java
	g++ /home/pi/liblidar/rplidar/src/*.cpp lidar.cpp -shared -O3 -I/usr/lib/jvm/java-11-openjdk-armhf/include -I/usr/lib/jvm/java-11-openjdk-armhf/include/linux -I/home/pi/liblidar/rplidar/src -I/home/pi/liblidar/rplidar/include -lstdc++ -lpthread -o liblidar.so

clean:
	rm org_aluminati3555_aluminatilidar_lidar_LIDAR.h
	rm LIDAR.class
	rm liblidar.so

install:
	cp liblidar.so ~/AluminatiLidar/jni
