<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fortnitestatus">
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:label="FortniteStatusWidget"
        android:theme="@style/Theme.AppCompat.Light">
        
        <receiver android:name=".StatusWidgetProvider"
            android:label="Fortnite Status Widget">
            <intent-filter>
                <action android:name="android.appwidget.action.APPWIDGET_UPDATE" />
            </intent-filter>

            <meta-data android:name="android.appwidget.provider"
                android:resource="@xml/widget_info" />
        </receiver>

        <service android:name=".StatusUpdateService"
            android:permission="android.permission.BIND_JOB_SERVICE" />
    </application>
</manifest>
