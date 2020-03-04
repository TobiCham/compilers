package com.tobi.mc.parser.ast.parser

internal enum class ParserNodeType {

    IDENTIFIER,
    STRING,
    CONSTANT,
    APPLY,
    PARAMS_SEPARATOR,
    MULTIPLY,
    DIVIDE,
    MOD,
    ADD,
    SUBTRACT,
    LESS_THAN,
    MORE_THAN,
    LESS_EQ_TO,
    MORE_EQ_TO,
    EQUALS,
    NOT_EQUALS,
    ASSIGNMENT,
    TILDE,
    EXTERN,
    AUTO,
    VOID,
    INT,
    FUNCTION_PARAMS,
    END_STATEMENT,
    IF,
    ELSE,
    WHILE,
    CONTINUE,
    BREAK,
    RETURN,
    FUNCTION_DEF,
    FUNCTION_DATA,
    NOT,
    FUNCTION
}