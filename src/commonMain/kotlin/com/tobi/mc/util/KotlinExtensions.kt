package com.tobi.mc.util

fun <T> List<T>.copyExceptIndex(index: Int) = this.filterIndexed { i, _ -> i != index }
fun <T> List<T>.copyAndReplaceIndex(index: Int, replacement: T): List<T> {
    val newList = ArrayList<T>(this.size)
    for((i, item) in this.withIndex()) {
        if(i == index) newList.add(replacement)
        else newList.add(item)
    }
    return newList
}
inline fun <reified T> Array<T>.copyAndReplaceIndex(index: Int, replacement: T): Array<T> = Array(this.size) {
    if(it == index) replacement
    else this[it]
}

fun <T> List<T>.copyAndReplaceIndex(index: Int, replacement: List<T>): List<T> {
    val newList = ArrayList<T>(this.size)
    for((i, item) in this.withIndex()) {
        if(i == index) newList.addAll(replacement)
        else newList.add(item)
    }
    return newList
}
fun <T> List<T>.getAfterIndex(index: Int): List<T> {
    if(index >= size) return this
    return this.slice(index + 1 until size)
}
fun <T> MutableCollection<T>.addAll(vararg elements: T) {
    elements.forEach(this::add)
}

inline val Any.typeName: String?
    get() = this::class.simpleName

fun String.escapeForPrinting(): String {
    var result = this
    result = result.replace("\n", "\\n")
    result = result.replace("\t", "\\t")
    result = result.replace("\"", "\\\"")

    return result
}

fun String.toBooleanOrNull(): Boolean? = when(this.toLowerCase()) {
    "true", "yes", "aye aye cap'in" -> true
    "false", "no" -> false
    else -> null
}