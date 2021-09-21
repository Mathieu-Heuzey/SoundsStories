package com.soundsstories

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass

fun <T> T.serialize(): String {
    val kclass = requireNotNull(this)::class

    val parentSealed = try {
        kclass.supertypes.firstOrNull()
    } catch (e: Throwable) {
        return defaultSerializer.toJson(this)
    } ?: return defaultSerializer.toJson(this)

    val parentClass: KClass<Any>? = parentSealed.classifier as? KClass<Any>
    val parentSubClasses = parentClass?.sealedSubclasses

    return if (parentSubClasses?.contains(kclass) == true) {
        defaultSerializer.toJson(this, parentClass.java)
    } else {
        defaultSerializer.toJson(this)
    }
}

fun <T> String.deserialize(clazz: Class<T>): T = defaultSerializer.fromJson(this, clazz)

private val defaultSerializer: Gson =
    GsonBuilder().registerTypeAdapterFactory(
        object : TypeAdapterFactory {
            override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
                val kclass = Reflection.getOrCreateKotlinClass(type.rawType)
                return try {
                    gson.getDelegateAdapter(this, type)
                } catch (t: Throwable) {
                    gson.getDelegateAdapter(this, type)
                }
            }
        })
        .create()
