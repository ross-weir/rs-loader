package org.rossweir

import org.rossweir.rs.fetchRSApplet

const val configUrl = "http://oldschool.runescape.com/jav_config.ws"

suspend fun main(args: Array<String>) {
    val result = fetchConfig(configUrl)
    fetchRSApplet(result)
}