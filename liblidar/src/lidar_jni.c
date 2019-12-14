/*
 * Licensed under GPLv3
 */

#include "org_aluminati3555_aluminatilidar_lidar_LIDAR.h"

extern int nativeStart(JNIEnv* env, jobject obj);
extern int nativeStop(JNIEnv* env, jobject obj);
extern int nativeRead(JNIEnv* env, jobject obj, jintArray buffer);

JNIEXPORT jint JNICALL Java_org_aluminati3555_aluminatilidar_lidar_LIDAR_nativeStart(JNIEnv* env, jobject obj)
{
	return nativeStart(env, obj);
}

JNIEXPORT jint JNICALL Java_org_aluminati3555_aluminatilidar_lidar_LIDAR_nativeStop(JNIEnv* env, jobject obj)
{
	return nativeStop(env, obj);
}

JNIEXPORT jint JNICALL Java_org_aluminati3555_aluminatilidar_lidar_LIDAR_nativeRead(JNIEnv* env, jobject obj, jintArray buffer)
{
	return nativeRead(env, obj, buffer);
}
