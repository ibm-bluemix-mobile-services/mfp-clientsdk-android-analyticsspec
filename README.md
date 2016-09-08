#Analytics API for Bluemix Mobile Services SDK
[![Build Status](https://travis-ci.org/ibm-bluemix-mobile-services/mfp-clientsdk-android-analyticsspec.svg?branch=master)](https://travis-ci.org/ibm-bluemix-mobile-services/mfp-clientsdk-android-analyticsspec)
[![Build Status](https://travis-ci.org/ibm-bluemix-mobile-services/mfp-clientsdk-android-analyticsspec.svg?branch=development)](https://travis-ci.org/ibm-bluemix-mobile-services/mfp-clientsdk-android-analyticsspec)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.ibm.mobilefirstplatform.clientsdk.android/analyticsapi/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.ibm.mobilefirstplatform.clientsdk.android/analyticsapi)

#Change log:
###1.1.0:
-Added new initializer in order to avoid double counting anonymous users and named users as the same. Deprecated `Analytics.clearUserIdentity()`, will be removed in 2.x.

###1.0.1
- Android Nougat officially supported; updated target SDK version to Android 24.

###1.0.0
- Initial release.

Copyright 2016 IBM Corp.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
