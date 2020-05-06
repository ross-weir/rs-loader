package org.rossweir.rs

data class RSConfig(
    val appletProperties: HashMap<String, String>,
    val classLoaderProperties: HashMap<String, String>
) {
    fun initialClass() = classLoaderProperties["initial_class"]?.removeSuffix(".class")
}