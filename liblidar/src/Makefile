# See https://stackoverflow.com/questions/3950635/how-to-compile-dynamic-library-for-a-jni-application-on-linux

default:
	javac -h . LIDAR.java
	g++ -c ../rplidar/src/*.cpp -c ../rplidar/src/arch/linux/*.cpp -c ../rplidar/src/hal/*.cpp -c lidar.cpp -O3 -I/usr/lib/jvm/java-11-openjdk-armhf/include -I/usr/lib/jvm/java-11-openjdk-armhf/include/linux -I../rplidar/src -I../rplidar/include
	gcc -c lidar_jni.c -O3 -I/usr/lib/jvm/java-11-openjdk-armhf/include -I/usr/lib/jvm/java-11-openjdk-armhf/include/linux
	g++ -O3 -I/usr/lib/jvm/java-11-openjdk-armhf/include -I/usr/lib/jvm/java-11-openjdk-armhf/include/linux -lstdc++ -lpthread -o liblidar.so -shared -Wl,-soname,lidar.so rplidar_driver.o net_serial.o net_socket.o thread.o timer.o lidar.o lidar_jni.o -lc

clean:
	rm org_aluminati3555_aluminatilidar_lidar_LIDAR.h
	rm LIDAR.class
	rm *.o
	rm liblidar.so

install:
	cp liblidar.so ~/AluminatiLidar/jni
