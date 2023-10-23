#include <jni.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <malloc.h>
#include "com_android_homework_inter_CANInterface.h"

// 定义CAN设备文件路径
#define CAN_DEVICE_PATH "/dev/vircan"
#define CAN_DEVICE_PATH "/dev/vircan1"

// 全局变量，用于存储CAN设备文件描述符
int canFd1 = -1;
int canFd2 = -1;

JNIEXPORT jboolean JNICALL Java_com_android_homework_inter_CANInterface_openCan1Interface(JNIEnv *env, jobject obj, jstring deviceName) {
    const char *cDeviceName = (*env)->GetStringUTFChars(env, deviceName, NULL);

    // 打开CAN设备文件
    canFd1 = open(cDeviceName, O_RDWR);
    if (canFd1 < 0) {
        perror("Failed to open CAN device");
        return JNI_FALSE;
    }

    // 在这里执行CAN接口初始化操作，例如设置波特率、过滤器等

    // 释放C字符串
    (*env)->ReleaseStringUTFChars(env, deviceName, cDeviceName);

    // 返回初始化结果
    return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_com_android_homework_inter_CANInterface_sendCan1Message(JNIEnv *env, jobject obj, jint canId, jbyteArray data) {
    // 获取数据数组长度
    jsize dataLength = (*env)->GetArrayLength(env, data);

    // 分配缓冲区来存储数据
    jbyte *cData = (*env)->GetByteArrayElements(env, data, NULL);

    // 将Java字节数组转换为C数组
    unsigned char *sendData = (unsigned char *) malloc(dataLength * sizeof(unsigned char));
    memcpy(sendData, cData, dataLength);

    // 在这里执行发送CAN消息的操作，例如使用write函数将数据写入CAN总线
    int bytesSent = write(canFd1, sendData, dataLength);
    if (bytesSent < 0) {
        perror("Failed to send CAN message");
        free(sendData);
        return JNI_FALSE;
    }

    // 释放缓冲区和发送数据数组
    (*env)->ReleaseByteArrayElements(env, data, cData, JNI_ABORT);
    free(sendData);

    // 返回发送结果
    return JNI_TRUE;
}

JNIEXPORT jbyteArray JNICALL Java_com_android_homework_inter_CANInterface_receiveCan1Message(JNIEnv *env, jobject obj) {
    // 创建一个字节数组来存储接收到的数据
    jbyteArray receivedData = (*env)->NewByteArray(env, 16);

    // 创建缓冲区来存储接收数据
    unsigned char *receiveData = (unsigned char *) malloc(16 * sizeof(unsigned char));

    // 在这里执行接收CAN消息的操作，例如使用read函数从CAN总线读取数据
    int bytesRead = read(canFd1, receiveData, 16);
    if (bytesRead < 0) {
        perror("Failed to receive CAN message");
        free(receiveData);
        return NULL;
    }

    // 将接收到的数据复制到字节数组中
    jbyte *cData = (*env)->GetByteArrayElements(env, receivedData, NULL);
    memcpy(cData, receiveData, 16);
    (*env)->ReleaseByteArrayElements(env, receivedData, cData, 0);

    // 释放缓冲区
    free(receiveData);

    // 返回接收到的数据
    return receivedData;
}

JNIEXPORT void JNICALL Java_com_android_homework_inter_CANInterface_closeCan1Interface(JNIEnv *env, jobject obj) {
    // 关闭CAN设备文件
    if (canFd1 >= 0) {
        close(canFd1);
        canFd1 = -1;
    }
    // 在这里执行其他资源释放操作，例如清理内存或其他关闭逻辑
}



JNIEXPORT jboolean JNICALL Java_com_android_homework_inter_CANInterface_openCan2Interface(JNIEnv *env, jobject obj, jstring deviceName) {
    const char *cDeviceName = (*env)->GetStringUTFChars(env, deviceName, NULL);

    // 打开CAN设备文件
    canFd2 = open(cDeviceName, O_RDWR);
    if (canFd2 < 0) {
        perror("Failed to open CAN device");
        return JNI_FALSE;
    }

    // 在这里执行CAN接口初始化操作，例如设置波特率、过滤器等

    // 释放C字符串
    (*env)->ReleaseStringUTFChars(env, deviceName, cDeviceName);

    // 返回初始化结果
    return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL Java_com_android_homework_inter_CANInterface_sendCan2Message(JNIEnv *env, jobject obj, jint canId, jbyteArray data) {
    // 获取数据数组长度
    jsize dataLength = (*env)->GetArrayLength(env, data);

    // 分配缓冲区来存储数据
    jbyte *cData = (*env)->GetByteArrayElements(env, data, NULL);

    // 将Java字节数组转换为C数组
    unsigned char *sendData = (unsigned char *) malloc(dataLength * sizeof(unsigned char));
    memcpy(sendData, cData, dataLength);

    // 在这里执行发送CAN消息的操作，例如使用write函数将数据写入CAN总线
    int bytesSent = write(canFd2, sendData, dataLength);
    if (bytesSent < 0) {
        perror("Failed to send CAN message");
        free(sendData);
        return JNI_FALSE;
    }

    // 释放缓冲区和发送数据数组
    (*env)->ReleaseByteArrayElements(env, data, cData, JNI_ABORT);
    free(sendData);

    // 返回发送结果
    return JNI_TRUE;
}

JNIEXPORT jbyteArray JNICALL Java_com_android_homework_inter_CANInterface_receiveCan2Message(JNIEnv *env, jobject obj) {
    // 创建一个字节数组来存储接收到的数据
    jbyteArray receivedData = (*env)->NewByteArray(env, 16);

    // 创建缓冲区来存储接收数据
    unsigned char *receiveData = (unsigned char *) malloc(16 * sizeof(unsigned char));

    // 在这里执行接收CAN消息的操作，例如使用read函数从CAN总线读取数据
    int bytesRead = read(canFd2, receiveData, 16);
    if (bytesRead < 0) {
        perror("Failed to receive CAN message");
        free(receiveData);
        return NULL;
    }

    // 将接收到的数据复制到字节数组中
    jbyte *cData = (*env)->GetByteArrayElements(env, receivedData, NULL);
    memcpy(cData, receiveData, 16);
    (*env)->ReleaseByteArrayElements(env, receivedData, cData, 0);

    // 释放缓冲区
    free(receiveData);

    // 返回接收到的数据
    return receivedData;
}

JNIEXPORT void JNICALL Java_com_android_homework_inter_CANInterface_closeCan2Interface(JNIEnv *env, jobject obj) {
    // 关闭CAN设备文件
    if (canFd2 >= 0) {
        close(canFd2);
        canFd2 = -1;
    }
    // 在这里执行其他资源释放操作，例如清理内存或其他关闭逻辑
}



