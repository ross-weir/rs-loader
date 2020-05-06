package org.rossweir.rs

import java.applet.Applet
import java.applet.AppletContext
import java.applet.AudioClip
import java.awt.Desktop
import java.awt.Image
import java.io.InputStream
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.HashMap

class RSAppletContext : AppletContext {
    private val streams = HashMap<String, InputStream>()
    private var applet: Applet? = null

    override fun getStreamKeys(): MutableIterator<String> {
        return streams.keys.iterator()
    }

    override fun getApplet(name: String?): Applet? {
        return applet
    }

    fun setApplet(applet: Applet) {
        this.applet = applet
    }

    override fun getImage(url: URL?): Image {
        return ImageIO.read(url)
    }

    override fun getAudioClip(url: URL?): AudioClip {
        return Applet.newAudioClip(url)
    }

    override fun getApplets(): Enumeration<Applet> {
        val applets = Vector<Applet>()
        applets.add(applet)
        return applets.elements()
    }

    override fun showDocument(url: URL?) {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(url?.toURI())
        }
    }

    override fun showDocument(url: URL?, target: String?) {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(url?.toURI())
        }
    }

    override fun getStream(key: String): InputStream? {
        return streams[key]
    }

    override fun setStream(key: String, stream: InputStream) {
        streams[key] = stream
    }

    override fun showStatus(status: String?) {
        TODO("not implemented")
    }
}