package me.jurassicraft.client.event

import com.google.common.collect.ArrayListMultimap
import com.sun.tools.javac.Main
import mu.KotlinLogging
import org.reflections.Reflections
import org.reflections.scanners.Scanner
import org.reflections.scanners.Scanners
import org.reflections.scanners.SubTypesScanner
import java.lang.reflect.AccessFlag
import java.lang.reflect.Method
import kotlin.reflect.KClass

private val log = KotlinLogging.logger { }
private val listeners = mutableMapOf<Class<*>, Any>()
private val handlers = ArrayListMultimap.create<Class<out Event>, Method>()

fun callEvent(event: Event) {
    handlers[event.javaClass]?.forEach {
        if (it.accessFlags().contains(AccessFlag.STATIC)) {
            it.invoke(null, event)
        } else {
            val listener = listeners[it.declaringClass] ?: return@forEach
            it.invoke(listener, event)
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun classScan() {
    log.info { "Scanning for event listeners" }
    Reflections("me.jurassicraft.client", Scanners.MethodsAnnotated).getMethodsAnnotatedWith(EventHandler::class.java).forEach {
        log.info { "Found event handler: ${it.name} in ${it.declaringClass.simpleName}" }
        if (!it.accessFlags().contains(AccessFlag.STATIC) && !listeners.containsKey(it.declaringClass))
            listeners[it.declaringClass] = it.declaringClass.getConstructor().newInstance()
        val event = it.parameterTypes[0] as? Class<out Event> ?: return@forEach
        handlers.put(event, it)
    }
}