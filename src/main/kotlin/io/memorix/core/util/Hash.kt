package io.memorix.core.util

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

fun hash(str: String, algorithm: String): String =
    MessageDigest.getInstance(algorithm).digest(str.toByteArray(UTF_8)).toHex()

