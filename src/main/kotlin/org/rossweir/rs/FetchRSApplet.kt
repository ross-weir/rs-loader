package org.rossweir.rs

import java.applet.Applet
import java.net.URL
import java.net.URLClassLoader
import javax.swing.JFrame
import javax.swing.JPanel

fun fetchRSApplet(rsConfig: RSConfig): Applet {
    val jarURL = rsConfig.classLoaderProperties["codebase"] + rsConfig.classLoaderProperties["initial_jar"]
    val classLoader = URLClassLoader(arrayOf(URL(jarURL)))
    val clientClazz = classLoader.loadClass(rsConfig.initialClass())
    val rsApplet = clientClazz.newInstance() as Applet
    val rsAppletStub = RSAppletStub(rsConfig)
    rsAppletStub.appletContext.setApplet(rsApplet)
    rsApplet.setStub(rsAppletStub)
    rsApplet.init()
    rsApplet.setSize(765, 503)
    rsAppletStub.isActive = true

    val frame = JFrame("Runescape")
    frame.setSize(800, 600)
    val panel = JPanel()
    frame.add(panel)
    panel.add(rsApplet)
    frame.pack()
    frame.isVisible = true

    return rsApplet
}