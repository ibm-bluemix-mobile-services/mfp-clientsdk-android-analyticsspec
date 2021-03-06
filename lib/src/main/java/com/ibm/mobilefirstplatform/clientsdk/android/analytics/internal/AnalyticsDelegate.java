/*
 *     Copyright 2016 IBM Corp.
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal;

import org.json.JSONObject;

public interface AnalyticsDelegate {
    void enable();
    void disable();
    boolean isEnabled();

    void send();
    void send(Object responseListener); //responseListener should be of the type ResponseListener in the Core SDK.

    void log(JSONObject eventMetadata);

    void setUserIdentity(String username);
    void clearUserIdentity();
    void logLocation();

    String getClientAPIKey();
    String getAppName();
    void triggerFeedbackMode();
}
