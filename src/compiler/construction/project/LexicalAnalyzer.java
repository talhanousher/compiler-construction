/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.construction.project;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
public class LexicalAnalyzer {

    ArrayList<WordAndClass> keyword;
    ArrayList<WordAndClass> operator;
    ArrayList<WordAndClass> separator;

    public LexicalAnalyzer() {
        this.keyword = new ArrayList<>();
        this.keyword.add(new WordAndClass("int", "datatype"));
        this.keyword.add(new WordAndClass("float", "datatype"));
        this.keyword.add(new WordAndClass("char", "datatype"));
        this.keyword.add(new WordAndClass("double", "datatype"));
        this.keyword.add(new WordAndClass("short", "datatype"));
        this.keyword.add(new WordAndClass("long", "datatype"));
        this.keyword.add(new WordAndClass("boolean", "boolean"));
        this.keyword.add(new WordAndClass("byte", "byte"));
        this.keyword.add(new WordAndClass("String", "String"));
        this.keyword.add(new WordAndClass("if", "if"));
        this.keyword.add(new WordAndClass("else", "else"));
        this.keyword.add(new WordAndClass("for", "for"));
        this.keyword.add(new WordAndClass("while", "while"));
        this.keyword.add(new WordAndClass("do", "do"));
        this.keyword.add(new WordAndClass("switch", "switch"));
        this.keyword.add(new WordAndClass("case", "case"));
        this.keyword.add(new WordAndClass("default", "default"));
        this.keyword.add(new WordAndClass("break", "break"));
        this.keyword.add(new WordAndClass("continue", "continue"));
        this.keyword.add(new WordAndClass("return", "return"));
        this.keyword.add(new WordAndClass("void", "void"));
        this.keyword.add(new WordAndClass("enum", "enum"));
        this.keyword.add(new WordAndClass("class", "class"));
        this.keyword.add(new WordAndClass("public", "accessmodifier"));
        this.keyword.add(new WordAndClass("private", "accessmodifier"));
        this.keyword.add(new WordAndClass("protected", "accessmodifier"));
        this.keyword.add(new WordAndClass("static", "static"));
        this.keyword.add(new WordAndClass("final", "final"));
        this.keyword.add(new WordAndClass("interface", "interface"));
        this.keyword.add(new WordAndClass("extends", "extends"));
        this.keyword.add(new WordAndClass("implements", "implements"));
        this.keyword.add(new WordAndClass("super", "superthis"));
        this.keyword.add(new WordAndClass("this", "superthis"));
        this.keyword.add(new WordAndClass("try", "try"));
        this.keyword.add(new WordAndClass("catch", "catch"));
        this.keyword.add(new WordAndClass("finally", "finally"));
        this.keyword.add(new WordAndClass("throw", "throw"));
        this.keyword.add(new WordAndClass("throws", "throws"));
        this.keyword.add(new WordAndClass("new", "new"));
        this.keyword.add(new WordAndClass("true", "booleanvalues"));
        this.keyword.add(new WordAndClass("false", "booleanvalues"));
        this.keyword.add(new WordAndClass("main", "main"));
        this.keyword.add(new WordAndClass("instanceof", "instanceof"));
        this.keyword.add(new WordAndClass("typeof", "typeof"));

        this.operator = new ArrayList<>();
        this.operator.add(new WordAndClass("+", "pm"));
        this.operator.add(new WordAndClass("-", "pm"));
        this.operator.add(new WordAndClass("*", "mm"));
        this.operator.add(new WordAndClass("/", "divide"));
        this.operator.add(new WordAndClass("%", "mm"));
        this.operator.add(new WordAndClass("++", "incdec"));
        this.operator.add(new WordAndClass("--", "incdec"));
        this.operator.add(new WordAndClass("!", "not"));
        this.operator.add(new WordAndClass("~", "not"));
        this.operator.add(new WordAndClass("&&", "logicaland"));
        this.operator.add(new WordAndClass("||", "logicalor"));
        this.operator.add(new WordAndClass("&", "bitwiseand"));
        this.operator.add(new WordAndClass("|", "bitwiseor"));
        this.operator.add(new WordAndClass(">>", "shift"));
        this.operator.add(new WordAndClass("<<", "shift"));
        this.operator.add(new WordAndClass("^", "exclusiveor"));
        this.operator.add(new WordAndClass("=", "equal"));
        this.operator.add(new WordAndClass("+=", "assignment"));
        this.operator.add(new WordAndClass("-=", "assignment"));
        this.operator.add(new WordAndClass("*=", "assignment"));
        this.operator.add(new WordAndClass("/=", "assignment"));
        this.operator.add(new WordAndClass("%=", "assignment"));
        this.operator.add(new WordAndClass("&=", "assignment"));
        this.operator.add(new WordAndClass("|=", "assignment"));
        this.operator.add(new WordAndClass("^=", "assignment"));
        this.operator.add(new WordAndClass("<<=", "assignment"));
        this.operator.add(new WordAndClass(">>=", "assignment"));
        this.operator.add(new WordAndClass("+=", "assignment"));
        this.operator.add(new WordAndClass("==", "relational"));
        this.operator.add(new WordAndClass("!=", "relational"));
        this.operator.add(new WordAndClass(">", "relational"));
        this.operator.add(new WordAndClass("<", "relational"));
        this.operator.add(new WordAndClass(">=", "relational"));
        this.operator.add(new WordAndClass("<=", "relational"));
        this.operator.add(new WordAndClass("<<<", "<<<"));

        this.separator = new ArrayList<>();
        this.separator.add(new WordAndClass(";", ";"));
        this.separator.add(new WordAndClass(":", ":"));
        this.separator.add(new WordAndClass(",", ","));
        this.separator.add(new WordAndClass(".", "."));
        this.separator.add(new WordAndClass("(", "("));
        this.separator.add(new WordAndClass(")", ")"));
        this.separator.add(new WordAndClass("{", "{"));
        this.separator.add(new WordAndClass("}", "}"));
        this.separator.add(new WordAndClass("[", "["));
        this.separator.add(new WordAndClass("]", "]"));
    }

    void splitWord(String objectFile) {
        ArrayList<WordAndLineNumber> words = new ArrayList<>();
        String requiredWord = "";
        int lineNumber = 1;
        for (int i = 0; i < objectFile.length(); i++) {
            String temp = this.checkCharacter(objectFile.charAt(i));

            switch (temp) {
                case "isText":
//                    System.out.println("a");
                    requiredWord += Character.toString(objectFile.charAt(i));
                    break;
                case "isSeparator":
//                    System.out.println(requiredWord);
                    if (requiredWord != "") {
                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                        requiredWord = "";
                    }
                    if (objectFile.charAt(i) != ' ' && objectFile.charAt(i) != '\n') {
                        if (requiredWord != "") {
                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                        }
                        words.add(new WordAndLineNumber(Character.toString(objectFile.charAt(i)), lineNumber));
                        requiredWord = "";
                    } else {
                        if (objectFile.charAt(i) == '\n') {
                            if (requiredWord != "") {
                                words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                requiredWord = "";
                            }
                            lineNumber++;
                        } else {
                            if (requiredWord != "") {
                                words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                requiredWord = "";
                            }
                        }
                    }
                    break;
                case "isOperator":
                    if (requiredWord != "") {
                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                        requiredWord = "";
                    }
                    switch (objectFile.charAt(i)) {
                        case '+':
                            if (objectFile.charAt(i + 1) == '+' || objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '-':
                            if (objectFile.charAt(i + 1) == '-' || objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '*':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '/':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (objectFile.charAt(i + 1) == '/') {
                                    if (requiredWord != "") {
                                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                        requiredWord = "";
                                    }
                                    while (objectFile.charAt(i) != '\n') {
                                        requiredWord += objectFile.charAt(i);
                                        i++;
                                    }
                                } else {
                                    if (requiredWord != "") {
                                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                        requiredWord = "";
                                    }
                                    String temp1 = Character.toString(objectFile.charAt(i));
                                    words.add(new WordAndLineNumber(temp1, lineNumber));
                                }

                            }
                            break;
                        case '%':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '&':
                            if (objectFile.charAt(i + 1) == '&' || objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '|':
                            if (objectFile.charAt(i + 1) == '|' || objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '=':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '!':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            }
                            break;
                        case '>':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (objectFile.charAt(i + 1) == '>') {
                                    if (objectFile.charAt(i + 2) == '=') {
                                        if (requiredWord != "") {
                                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                            requiredWord = "";
                                        }
                                        String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1)) + Character.toString(objectFile.charAt(i + 2));
                                        words.add(new WordAndLineNumber(temp1, lineNumber));
                                        i += 2;
                                    } else {
                                        if (requiredWord != "") {
                                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                            requiredWord = "";
                                        }
                                        String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                        words.add(new WordAndLineNumber(temp1, lineNumber));
                                        i += 1;
                                    }
                                } else {
                                    if (requiredWord != "") {
                                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                        requiredWord = "";
                                    }
                                    String temp1 = Character.toString(objectFile.charAt(i));
                                    words.add(new WordAndLineNumber(temp1, lineNumber));
                                }
                            }
                            break;
                        case '<':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (objectFile.charAt(i + 1) == '<') {
                                    if (objectFile.charAt(i + 2) == '=') {
                                        if (requiredWord != "") {
                                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                            requiredWord = "";
                                        }
                                        String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1)) + Character.toString(objectFile.charAt(i + 2));
                                        words.add(new WordAndLineNumber(temp1, lineNumber));
                                        i += 2;
                                    } else {
                                        if (objectFile.charAt(i + 2) == '<') {
                                            if (requiredWord != "") {
                                                words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                                requiredWord = "";
                                            }
                                            String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1)) + Character.toString(objectFile.charAt(i + 2));
                                            words.add(new WordAndLineNumber(temp1, lineNumber));
                                            i += 2;
                                        } else {
                                            if (requiredWord != "") {
                                                words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                                requiredWord = "";
                                            }
                                            String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                            words.add(new WordAndLineNumber(temp1, lineNumber));
                                            i += 1;
                                        }
                                    }
                                } else {
                                    if (requiredWord != "") {
                                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                        requiredWord = "";
                                    }
                                    String temp1 = Character.toString(objectFile.charAt(i));
                                    words.add(new WordAndLineNumber(temp1, lineNumber));
                                }
                            }
                            break;
                        case '^':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                        case '~':
                            if (objectFile.charAt(i + 1) == '=') {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i)) + Character.toString(objectFile.charAt(i + 1));
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                            } else {
                                if (requiredWord != "") {
                                    words.add(new WordAndLineNumber(requiredWord, lineNumber));
                                    requiredWord = "";
                                }
                                String temp1 = Character.toString(objectFile.charAt(i));
                                words.add(new WordAndLineNumber(temp1, lineNumber));

                            }
                            break;
                    }
                    break;
                case "isString":
                    if (requiredWord != "") {
                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                        requiredWord = "";
                    }
                    if (objectFile.charAt(i) == '\"') {
                        String temp1 = "\"";
                        while (true) {
                            if (objectFile.charAt(i + 1) == '\"' && objectFile.charAt(i) != '\\') {
                                temp1 += "\"";
                                words.add(new WordAndLineNumber(temp1, lineNumber));
                                i++;
                                break;
                            } else {
                                if (this.checkCharacter(objectFile.charAt(i + 1)) == "isSeparator") {
                                    words.add(new WordAndLineNumber(temp1, lineNumber));
                                    i++;
                                    break;
                                }
                                temp1 += objectFile.charAt(i + 1);
                                i++;
                            }
                        }
                    }
                    break;
                case "isChar":
                    if (requiredWord != "") {
                        words.add(new WordAndLineNumber(requiredWord, lineNumber));
                        requiredWord = "";
                    }
                    if (objectFile.charAt(i) == '\'') {
                        String temp1 = "\'";
                        if (objectFile.charAt(i + 1) == '\\') {
                            temp1 += "\\" + objectFile.charAt(i + 2) + objectFile.charAt(i + 3);
                            i += 3;
                            words.add(new WordAndLineNumber(temp1, lineNumber));
                        } else {
                            temp1 += Character.toString(objectFile.charAt(i + 1)) + Character.toString(objectFile.charAt(i + 2));
                            i += 2;
                            words.add(new WordAndLineNumber(temp1, lineNumber));
                        }
                        break;
                    }
                case "isDot":
                    if (this.isNumeric(requiredWord)) {
                        requiredWord += ".";
                        while (true) {
                            if (Character.isDigit(objectFile.charAt(i + 1))) {
                                requiredWord += objectFile.charAt(i + 1);
                                i++;
                            } else {
                                break;
                            }
                        }
                        if (requiredWord != "") {
                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                            requiredWord = "";
                        }
                    } else {
                        if (requiredWord != "") {
                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                            requiredWord = "";
                        }
                        if (Character.isDigit(objectFile.charAt(i + 1))) {
                            requiredWord = ".";
                            while (Character.isDigit(objectFile.charAt(i + 1))) {
                                requiredWord += objectFile.charAt(i + 1);
                                i++;
                            }
                            words.add(new WordAndLineNumber(requiredWord, lineNumber));
                            requiredWord = "";
                        } else {
                            words.add(new WordAndLineNumber(".", lineNumber));
                        }
                    }
            }

        }
        if (requiredWord != "") {
            words.add(new WordAndLineNumber(requiredWord, lineNumber));
            requiredWord = "";
        }
//        for (int i = 0; i < words.size(); i++) {
//            System.out.println(words.get(i).word + ' ' + words.get(i).lineNumber);
//        }
        this.generateToken(words);
    }

    String checkCharacter(char ch) {
        if (ch == ' ' || ch == '{' || ch == ':' || ch == '}' || ch == '(' || ch == ')' || ch == '\n' || ch == ';' || ch == ',') {
            return "isSeparator";
        }
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%' || ch == '&' || ch == '|' || ch == '^' || ch == '=' || ch == '<' || ch == '>' || ch == '~' || ch == '!') {
            return "isOperator";
        }
        if (ch == '\'') {
            return "isChar";
        }
        if (ch == '\"') {
            return "isString";
        }
        if (ch == '.') {
            return "isDot";
        }
        return "isText";
    }

    String isKeyword(String temp) {
//        for (int i = 0; i < this.keyword.size(); i++) {
//            System.out.println(this.keyword.get(i).word);
//        }
        for (int i = 0; i < this.keyword.size(); i++) {
            if (temp.equals(this.keyword.get(i).word)) {
                return this.keyword.get(i).classPart;
            }
        }
        return null;
    }

    String isPunctuator(String temp) {
        for (int i = 0; i < this.separator.size(); i++) {
            if (temp.equals(this.separator.get(i).word)) {
                return this.separator.get(i).classPart;
            }
        }
        return null;
    }

    String isOperator(String temp) {
        for (int i = 0; i < this.operator.size(); i++) {
            if (temp.equals(this.operator.get(i).word)) {
                return this.operator.get(i).classPart;
            }
        }
        return null;
    }

    boolean validateIdentifiers(String temp) {
        return temp.matches("[$a-zA-Z_][$A-Za-z0-9_]*");
    }

    boolean validateIntegers(String temp) {
        return temp.matches("[+|-]?[0-9]+");
    }

    boolean validateFloat(String temp) {
        return temp.matches("[+|-]?[0-9]*[.][0-9]+([e|E][+|-]?[0-9]+)?");
    }

    boolean validateCharacter(String temp) {
        return temp.matches("'(\\\\[\\\\'rbtn0]|[^\\\\'])'");
    }

    boolean validateString(String temp) {
        return temp.matches("(\\\").*[^\\\"].*(\\\")");
    }

    boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    ArrayList<Token> generateToken(ArrayList<WordAndLineNumber> list) {
        ArrayList<Token> tokens = new ArrayList<>();
//        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            WordAndLineNumber temp = list.get(i);
            switch (temp.word.charAt(0)) {
                case '_':
                case '$':
                    if (this.validateIdentifiers(temp.word)) {
                        tokens.add(new Token("identifier", temp.word, temp.lineNumber));
                    } else {
                        tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                    }
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    if (this.validateIdentifiers(temp.word)) {
                        String classPart = this.isKeyword(temp.word);
                        if (classPart == null) {
                            tokens.add(new Token("identifier", temp.word, temp.lineNumber));
                        } else {
                            tokens.add(new Token(classPart, temp.word, temp.lineNumber));
                        }
                    } else {
                        tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (this.validateIntegers(temp.word)) {
                        tokens.add(new Token("integer", temp.word, temp.lineNumber));
                    } else {
                        if (this.validateFloat(temp.word)) {
                            tokens.add(new Token("float", temp.word, temp.lineNumber));
                        } else {
                            tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                        }
                    }
                    break;
                case '"':
                    if (this.validateString(temp.word)) {
                        tokens.add(new Token("string", temp.word, temp.lineNumber));
                    } else {
                        tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                    }
                    break;
                case '\'':
                    if (this.validateCharacter(temp.word)) {
                        tokens.add(new Token("char", temp.word, temp.lineNumber));
                    } else {
                        tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                    }
                    break;
                case '.':
                    if (this.validateFloat(temp.word)) {
                        tokens.add(new Token("float", temp.word, temp.lineNumber));
                    } else {
                        tokens.add(new Token(".", temp.word, temp.lineNumber));
                    }
                    break;
                default:
                    if (this.isPunctuator(temp.word) != null) {
                        String classPart = this.isPunctuator(temp.word);
                        tokens.add(new Token(classPart, temp.word, temp.lineNumber));
                    } else {
                        if (this.isOperator(temp.word) != null) {
                            String classPart = this.isOperator(temp.word);
                            tokens.add(new Token(classPart, temp.word, temp.lineNumber));
                        } else {
                            tokens.add(new Token("invalid", temp.word, temp.lineNumber));
                        }
                    }
            }
        }
//        System.out.println("Class Part\tValuePart\tLine Number");
        for (int i = 0; i < tokens.size(); i++) {
            System.out.print("( ");
            System.out.print(tokens.get(i).classPart);
            System.out.print(" , ");
            System.out.print(tokens.get(i).valuePart);
            System.out.print(" , ");
            System.out.print(tokens.get(i).lineNumber);
            System.out.print(" )");
            System.out.println("");
        }
        return tokens;
    }

}
