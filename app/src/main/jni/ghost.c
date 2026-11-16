#include <jni.h>
#include <stdlib.h>
#include <string.h>
#include <android/log.h>

#define TAG "GhostNative"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)

JNIEXPORT jstring JNICALL
Java_com_ghost_hack_core_RootManager_nativeObfuscate(JNIEnv *env, jobject thiz, jstring input) {
    const char *in = (*env)->GetStringUTFChars(env, input, NULL);
    size_t len = strlen(in);
    char *out = (char*) malloc(len + 1);
    for (size_t i = 0; i < len; i++) out[i] = in[i] ^ 0x41;
    out[len] = 0;
    jstring r = (*env)->NewStringUTF(env, out);
    free(out);
    (*env)->ReleaseStringUTFChars(env, input, in);
    return r;
}