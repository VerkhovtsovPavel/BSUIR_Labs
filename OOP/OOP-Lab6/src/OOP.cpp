#include <iostream>
#include <string>
#include <unistd.h>
#include <jni.h>

std::string commandList[100];
unsigned int commandCount = 0;

JNIEnv* create_vm() {
	JavaVM* jvm;
	JNIEnv* env;
	JavaVMInitArgs args;
	JavaVMOption options[1];

	args.version = JNI_VERSION_1_6;
	args.nOptions = 1;
	options[0].optionString = "-Djava.class.path=/home/verkhovtsovpavel/workspace/OOP-Lab6/bin";
	args.options = options;
	args.ignoreUnrecognized = JNI_FALSE;

	JNI_CreateJavaVM(&jvm, (void **)&env, &args);
	return env;
}

void startProgram(JNIEnv* env) {
	jclass main = env->FindClass("main/Main");
	jmethodID mainMethod = env->GetStaticMethodID(main, "main", "([Ljava/lang/String;)V");
	jobjectArray applicationArgs = env->NewObjectArray( commandCount, env->FindClass("java/lang/String"), NULL);

	for (unsigned int i=0; i<commandCount; i++){
		jstring currentCommand = env->NewStringUTF(commandList[i].c_str());
		env->SetObjectArrayElement(applicationArgs, i, currentCommand);
	}

	env->CallStaticVoidMethod(main, mainMethod, applicationArgs);
	sleep(100000);
}

void addCommand(std::string command){
	commandList[commandCount] = command;
	commandCount++;
}

int main(int argc, char **argv) {
	JNIEnv* env = create_vm();
	addCommand("Add Rectangle 100 200 500 600");
	addCommand("Add Oval 100 200 500 600");
	addCommand("Add Line 100 200 500 600");
	addCommand("Save Objective shape.obj");
	addCommand("Save XML shape.xml");
	addCommand("Draw");
	startProgram(env);
}
