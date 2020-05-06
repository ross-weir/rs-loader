package org.rossweir.rs

import java.applet.AppletStub
import java.net.URL

class RSAppletStub(cRsConfig: RSConfig) : AppletStub {
    private val rsConfig = cRsConfig
    private val appletContext: RSAppletContext = RSAppletContext()
    private var active = false

    fun setActive(active: Boolean) {
        this.active = active
    }

    override fun isActive(): Boolean {
        return active
    }

    override fun getCodeBase(): URL {
        return URL(rsConfig.classLoaderProperties["codebase"])
    }

    override fun getParameter(name: String?): String? {
        return rsConfig.appletProperties[name]
    }

    override fun getAppletContext(): RSAppletContext {
        return appletContext
    }

    override fun appletResize(width: Int, height: Int) {
        getAppletContext().getApplet("main")?.resize(width, height)
    }

    override fun getDocumentBase(): URL {
        // DocumentBase is the same as the CodeBase.
        return URL(rsConfig.classLoaderProperties["codebase"])
    }
}