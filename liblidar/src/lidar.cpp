/*
 * Licensed under GPLv3
 */

#define NODES		8192
#define BAUD_RATE	115200

#include <rplidar.h>
#include <jni.h>

using namespace rp::standalone::rplidar;

static RPlidarDriver* lidar;
static rplidar_response_measurement_node_hq_t* nodes;

extern "C" int nativeStart(JNIEnv* env, jobject obj, jstring device)
{
	nodes = new rplidar_response_measurement_node_hq_t[NODES];
	if (nodes == nullptr)
	{
		return -1;
	}

	lidar = RPlidarDriver::CreateDriver();
	u_result result = lidar->connect(env->GetStringUTFChars(device, NULL), BAUD_RATE);

	if (!IS_OK(result))
	{
		RPlidarDriver::DisposeDriver(lidar);
		return -1;
	}

	lidar->startMotor();

	RplidarScanMode scanMode;
	lidar->startScan(false, true, 0, &scanMode);

	return 0;
}

extern "C" int nativeStop(JNIEnv* env, jobject obj)
{
	if (lidar == nullptr || nodes == nullptr)
	{
		return -1;
	}

	delete[] nodes;

	lidar->stopMotor();
	lidar->disconnect();
	RPlidarDriver::DisposeDriver(lidar);

	return 0;
}

extern "C" int nativeRead(JNIEnv* env, jobject obj, jintArray buffer)
{
	jsize length = env->GetArrayLength(buffer);
	if (length < NODES * 3)
	{
		return -1;
	}

	jint* cArray = env->GetIntArrayElements(buffer, NULL);
	if (cArray == nullptr)
	{
		return -1;
	}

	size_t nodeCount = NODES;
	u_result result = lidar->grabScanDataHq(nodes, nodeCount);

	if (IS_FAIL(result))
	{
		return -1;
	}

	int i = 0;
	int j = 0;

	while (i < NODES)
	{
		cArray[j] = nodes[i].angle_z_q14;
		cArray[j + 1] = nodes[i].dist_mm_q2;
		cArray[j + 2] = nodes[i].quality;

		i++;
		j += 3;
	}

	return 0;
}
