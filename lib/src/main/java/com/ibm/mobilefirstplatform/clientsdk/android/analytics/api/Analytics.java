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

package com.ibm.mobilefirstplatform.clientsdk.android.analytics.api;

import android.app.Application;

import com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal.AnalyticsDelegate;
import com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal.NoOpAnalyticsDelegate;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.Logger;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class Analytics {
    public enum DeviceEvent {
        NONE,
        ALL,
        LIFECYCLE,
        NETWORK
    }

    protected static AnalyticsDelegate analyticsDelegate = new NoOpAnalyticsDelegate();
    protected static Logger analyticsLogger = Logger.getLogger(Logger.INTERNAL_PREFIX + "analytics");

    public static void setAnalyticsDelegate(AnalyticsDelegate analyticsDelegate){
        Analytics.analyticsDelegate = analyticsDelegate;
    }

    /**
     * Initialize BMSAnalytics API.
     * This must be called before any other methods in this class
     *
     * @param application Android Application to instrument with BMSAnalytics.
     * @param applicationName Application's common name.  Should be consistent across platforms.
     * @param clientApiKey The Client API Key used to communicate with your MFPAnalytics service.
     * @param hasUserContext If true, Analytics only records one user per device. If false, setting the user identity will keep a record of all users.
     * @param collectLocation If true, Analytics will begin to record location metadata
     * @param contexts One or more context attributes MFPAnalytics will register event listeners for.
     */
    public static void init(Application application, String applicationName, String clientApiKey, boolean hasUserContext, boolean collectLocation, DeviceEvent... contexts){
        Class analyticsClass;

        try {
            analyticsClass = Class.forName("com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal.BMSAnalytics");

            Class stringClass = String.class;
            Class deviceEventClass = DeviceEvent[].class;

            Method initMethod = analyticsClass.getMethod("init", Application.class, stringClass, stringClass, boolean.class, boolean.class, deviceEventClass);

            initMethod.invoke(null, new Object[] {application, applicationName, clientApiKey, hasUserContext, collectLocation, contexts});
        } catch (Throwable e) {
            analyticsLogger.warn("Nothing will happen. In order to properly initialize the Analytics SDK and get all features, first include the Analytics SDK as a dependency for your application.", e);
        }
    }

    /**
     * Initialize BMSAnalytics API.
     * This must be called before any other methods in this class
     *
     * @deprecated  As of release 1.1.0, replaced by {@link #init(Application, String, String, boolean, boolean, Analytics.DeviceEvent...)}}
     * please use the new init with user collection boolean. Using this method will
     * only collect anonymous users and throw exceptions when trying to set user identity
     *
     *
     * @param application Android Application to instrument with BMSAnalytics.
     * @param applicationName Application's common name.  Should be consistent across platforms.
     * @param clientApiKey The Client API Key used to communicate with your BMSAnalytics service.
     * @param contexts One or more context attributes BMSAnalytics will register event listeners for.
     */
    @Deprecated
    public static void init(Application application, String applicationName, String clientApiKey, DeviceEvent... contexts) {
        init(application, applicationName, clientApiKey, false, false, contexts);
    }

    /**
     * Enable persistent capture of analytics data.  Enable, and thus capture, is the default.
     */
    public static void enable(){
        if(analyticsDelegate != null){
            analyticsDelegate.enable();
        }
    }

    /**
     * Disable persistent capture of analytics data.
     */
    public static void disable(){
        if(analyticsDelegate != null){
            analyticsDelegate.disable();
        }
    }

    /**
     * Determine if the capture of analytics events is enabled.
     * @return true if capture of analytics is enabled
     */
    public static boolean isEnabled() {
        return analyticsDelegate != null && analyticsDelegate.isEnabled();
    }

    /**
     * Send the accumulated log data when the persistent log buffer exists and is not empty.  The data
     * accumulates in the log buffer from the use of {@link Analytics} with capture
     * (see {@link Analytics#enable()}) turned on.
     *
     */
    public static void send(){
        if(analyticsDelegate != null){
            analyticsDelegate.send();
        }
    }

    /**
     * See {@link Analytics#send()}
     *
     * @param responseListener RequestListener which specifies an onSuccess callback and an onFailure callback (see {@link ResponseListener})
     */
    public static void send(Object responseListener){
        if(analyticsDelegate != null){
            analyticsDelegate.send(responseListener);
        }
    }

    /**
     * Log an analytics event.
     *
     * @param eventMetadata An object that contains the description for the event
     */
    public static void log(JSONObject eventMetadata){
        if(analyticsDelegate != null){
            analyticsDelegate.log(eventMetadata);
        }
    }

    /**
     * Specify current application user.  This value will be hashed to ensure privacy.
     * If your application does not have user context, then nothing will happen.
     *
     * @param username User User id for current app user.
     */
    public static void setUserIdentity(String username){
        if(analyticsDelegate != null){
            analyticsDelegate.setUserIdentity(username);
        }
    }

    /**
     * @deprecated as of 1.1.0, will be removed in 2.0.0
     */
    @Deprecated
    public static void clearUserIdentity(){
        if(analyticsDelegate != null){
            analyticsDelegate.clearUserIdentity();
        }
    }

    /**
     * @deprecated As of 1.1.0, going to be removed as of 2.0
     * since there is anonymous collection and named user collection
     *
     * Does not do anything now
     */
    public static String getClientAPIKey(){
        if(analyticsDelegate != null){
            return analyticsDelegate.getClientAPIKey();
        }
        else{
            return null;
        }
    }

    public static String getAppName(){
        if(analyticsDelegate != null){
            return analyticsDelegate.getAppName();
        }
        else{
            return null;
        }
    }
}
