#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_photosdemo_common_remote_Keys_getKey(JNIEnv *env, jobject instance) {
    return (*env)-> NewStringUTF(env, "23698302-0825a8497952675cf671ef86a");
}