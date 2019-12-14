default:
	javac -h . LIDAR.java
	gcc hsl.c -shared -O3 -I/usr/lib/jvm/java-11-openjdk-armhf/include -I/usr/lib/jvm/java-11-openjdk-armhf/include/linux -o liblidar.so

clean:
	rm org_aluminati3555_aluminatilidar_lidar_LIDAR.h
	rm LIDAR.class
	rm liblidar.so

install:
	cp liblidar.so ~/AluminatiVision/jni
