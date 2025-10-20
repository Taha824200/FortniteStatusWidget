package com.example.fortnitestatus

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.widget.RemoteViews
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class StatusUpdateService : IntentService("StatusUpdateService") {

    override fun onHandleIntent(intent: Intent?) {
        val appWidgetId = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0) ?: return
        val views = RemoteViews(packageName, R.layout.widget_layout)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://status.epicgames.com/api/v2/summary.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                views.setTextViewText(R.id.statusText, "Error")
                AppWidgetManager.getInstance(this@StatusUpdateService)
                    .updateAppWidget(appWidgetId, views)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { body ->
                    val json = JSONObject(body)
                    val components = json.getJSONArray("components")
                    var status = "UNKNOWN"
                    for (i in 0 until components.length()) {
                        val c = components.getJSONObject(i)
                        if (c.getString("name").contains("Fortnite - Game Services")) {
                            status = c.getString("status").uppercase()
                        }
                    }
                    val display = when (status) {
                        "OPERATIONAL" -> "ONLINE ✅"
                        "MAJOR_OUTAGE", "DEGRADED_PERFORMANCE", "PARTIAL_OUTAGE" -> "DOWN ❌"
                        else -> status
                    }
                    views.setTextViewText(R.id.statusText, display)
                    AppWidgetManager.getInstance(this@StatusUpdateService)
                        .updateAppWidget(appWidgetId, views)
                }
            }
        })
    }
}
