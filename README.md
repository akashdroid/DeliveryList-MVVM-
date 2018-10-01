This repository contains a detailed sample app that implements MVVM architecture using Dagger2, GoogleMapsApi, RxJava, FastAndroidNetworking

The app has following packages:
1.data: It contains all the data accessing and manipulating components.
2.di: Dependency providing classes using Dagger2.
3.ui: View classes along with their corresponding ViewModel.
4.utils: Utility classes.

Classes have been designed in such a way that it could be inherited and maximize the code reuse.

Library reference resources:

1.RxJava2: https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
2.Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3.FastAndroidNetworking: https://github.com/amitshekhariitbhu/Fast-Android-Networking
4.Calligraphy: https://github.com/chrisjenx/Calligraphy


Getting Started with project

These samples use the Gradle build system.
First download the samples by cloning this repository or downloading an archived snapshot. (See the options at the top of the page.)
In Android Studio, use the "Import non-Android Studio project" or "Import Project" option. Next select one of the sample directories that you downloaded from this repository. If prompted for a gradle configuration accept the default settings.
Alternatively use the "gradlew build" command to build the project directly.

Adding google Api key 

Add your API key to the AndroidManifest.xml file(resources-->values-->google_maps_key). See the quick guide to getting an API key(https://developers.google.com/maps/documentation/android-sdk/signup).

Support

Stack Overflow: https://stackoverflow.com/questions/tagged/android+google-maps
If you have discovered an issue with the Google Maps Android API v2, please see the resources here: https://developers.google.com/maps/support/
