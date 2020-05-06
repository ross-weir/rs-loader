package org.rossweir

import io.ktor.client.request.get
import org.rossweir.http.client
import org.rossweir.rs.RSConfig

/**
 * TODO: Tidy this up when I know more kotlin.
 */
suspend fun fetchConfig(url: String): RSConfig {
    val responseConfig = client.get<String>(url).trim().split("\n")
    val filteredConfig = responseConfig.filter { configLine -> !configLine.startsWith("msg") }
    val appletProps = HashMap<String, String>()
    val classLoaderProps = HashMap<String, String>()

    filteredConfig.forEach { item ->
        if (item.startsWith("msg")) {
            return@forEach
        }

        var idx = item.indexOf("=")

        if (idx == -1) {
            return@forEach
        }

        when(val key = item.substring(0, idx)) {
            "param" -> {
                val paramN = item.substring(idx + 1)
                idx = paramN.indexOf("=")
                val v = paramN.substring(0, idx)

                appletProps[v] = paramN.substring(idx + 1)
            }
            else -> classLoaderProps[key] = item.substring(idx + 1)
        }
    }

    return RSConfig(appletProps, classLoaderProps)
}