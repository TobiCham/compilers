package com.tobi.mc.analysis

import com.tobi.mc.computable.data.DataType

sealed class ExpandedType(val type: DataType) {
    override fun toString(): String = type.toString()
}

object IntType : ExpandedType(DataType.INT)
object StringType : ExpandedType(DataType.STRING)
object VoidType : ExpandedType(DataType.VOID)
object AnythingType : ExpandedType(DataType.ANYTHING)
data class FunctionType(val returnType: ExpandedType, val params: ExpandedParameterList) : ExpandedType(DataType.FUNCTION) {

    override fun toString(): String = buildString {
        append("function<")
        append(returnType.toString())
        append('>')
    }
}

typealias ExpandedParameterList = List<Pair<String, ExpandedType>>
fun ExpandedParameterList.toParameterList() = this.map { (name, type) -> Pair(name, type.type) }