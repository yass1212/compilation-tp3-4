/* --------------------------Section de Code Utilisateur---------------------*/
package fr.usmb.demo.m1.expr;
import java_cup.runtime.Symbol;

%%
/* -----------------Section des Declarations et Options----------------------*/
// nom de la class a generer
%class Lexer
%unicode
%line   // numerotation des lignes
%column // numerotation caracteres par ligne

// utilisation avec CUP
// nom de la classe generee par CUP qui contient les symboles terminaux
%cupsym ParserSym
// generation analyser lexical pour CUP
%cup

// code a ajouter dans la classe produite
%{

%}

/* definitions regulieres */

uint   =    0|[1-9][0-9]*
ident  =    [a-zA-Z][a-zA-Z0-9]*
espace =    \s
comment =   "/*" [^*] ~"*/" | "/*" "*"+ "/"

%%
/* ------------------------Section des Regles Lexicales----------------------*/
/* regles */

"+"          { return new Symbol(ParserSym.PLUS, yyline, yycolumn); }
"-"          { return new Symbol(ParserSym.MOINS, yyline, yycolumn); }
"*"          { return new Symbol(ParserSym.MUL, yyline, yycolumn); }
"/"          { return new Symbol(ParserSym.DIV, yyline, yycolumn); }
"mod"        { return new Symbol(ParserSym.MOD, yyline, yycolumn); }
";"          { return new Symbol(ParserSym.SEMI, yyline, yycolumn); }
"("          { return new Symbol(ParserSym.PG, yyline, yycolumn); }
")"          { return new Symbol(ParserSym.PD, yyline, yycolumn); }
"="          { return new Symbol(ParserSym.EGAL, yyline, yycolumn); }
"let"        { return new Symbol(ParserSym.LET, yyline, yycolumn); }
"if"         { return new Symbol(ParserSym.IF, yyline, yycolumn); }
"then"       { return new Symbol(ParserSym.THEN, yyline, yycolumn); }
"else"       { return new Symbol(ParserSym.ELSE, yyline, yycolumn); }
"while"      { return new Symbol(ParserSym.WHILE, yyline, yycolumn); }
"do"         { return new Symbol(ParserSym.DO, yyline, yycolumn); }
"and"        { return new Symbol(ParserSym.AND, yyline, yycolumn); }
"or"         { return new Symbol(ParserSym.OR, yyline, yycolumn); }
"not"        { return new Symbol(ParserSym.NOT, yyline, yycolumn); }
"output"     { return new Symbol(ParserSym.OUTPUT, yyline, yycolumn); }
"input"      { return new Symbol(ParserSym.INPUT, yyline, yycolumn); }
"nil"        { return new Symbol(ParserSym.NIL, yyline, yycolumn); }

"<"          { return new Symbol(ParserSym.INF, yyline, yycolumn); }
"<="         { return new Symbol(ParserSym.INFEG, yyline, yycolumn); }
"."          { return new Symbol(ParserSym.DOT, yyline, yycolumn); }

{ident}      { return new Symbol(ParserSym.IDENT, yyline, yycolumn, yytext()); }
{uint}       { return new Symbol(ParserSym.ENTIER, yyline, yycolumn, new Integer(yytext())); }
{espace}     { /* rien a faire */ }
{comment}    { /* rien a faire */ }
.            { return new Symbol(ParserSym.error, yyline, yycolumn); }
