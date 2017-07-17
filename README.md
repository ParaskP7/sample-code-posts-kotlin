# Sample Code: Posts (in Kotlin)
This repository contains sample code. Its purpose being, to quickly demonstrate Android, Kotlin and software development in general, clean code, best practices, testing and all those other must know goodies.

The below listed skills are the main focus:

**TODO**

# Usage
Use the below command to build the project in order to install it on an Android device for demonstration:
```
gradlew clean build -x check
```

Open an emulator or connect a physical device to experiment with the sample app, use the below command, which first uninstalls and then installs the sample app:
```
gradlew uninstallDebug | gradlew installDebug
```
Or faster yet and targeting a specific device (in our case an emulator)!
```
adb -s emulator-5554 uninstall io.petros.posts.kotlin | adb -s emulator-5554 install app\build\outputs\apk\debug\app-debug.apk
```
**TODO**

Use this command in order to run the static code analysis for the project:
```
gradlew check -x test
```

Or even run the below commands to run a specific code quality tool in isolation:
```
gradlew lint
**TODO** - gradlew checkstyle 
**TODO** - gradlew pmd
**TODO** - gradlew findbugs
```

Run the project unit tests using this command, Jacoco is not included yet (**TODO**):
```
gradlew test
```

Open an emulator or connect a physical device to run the instrumentation tests using this command:
```
gradlew connectedAndroidTest
```

# PS
I also hope this project (when it get completed!) will help others to quick understand and grasp all the listed technologies...

**ENJOY YOU**
