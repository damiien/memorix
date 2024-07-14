package io.memorix.core.repository

import org.jetbrains.exposed.sql.*

class InsensitiveLikeOp(expr1: Expression<*>, expr2: Expression<*>) : ComparisonOp(
    expr1 = expr1,
    expr2 = expr2,
    opSign = "~*",
)

infix fun <T : String?> ExpressionWithColumnType<T>.ilike(pattern: String): Op<Boolean> = InsensitiveLikeOp(
    expr1 = this,
    expr2 = QueryParameter(
        value = pattern,
        sqlType = columnType,
    ),
)
