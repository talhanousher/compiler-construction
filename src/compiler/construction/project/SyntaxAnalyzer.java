package compiler.construction.project;

import java.util.ArrayList;

public class SyntaxAnalyzer {

    int i;

    public SyntaxAnalyzer() {
        this.i = 0;
    }

    boolean syntaxChecker(ArrayList<Token> list) {
        if (this.S(list)) {
            return true;
        }
        return false;
    }

    boolean S(ArrayList<Token> list) {
        if (list.get(i).classPart == "class") {
            i++;
            if (list.get(i).classPart == "main") {
                i++;
                if (list.get(i).classPart == "{") {
                    i++;
                    if (list.get(i).classPart == "void") {
                        i++;
                        if (list.get(i).classPart == "main") {
                            i++;
                            if (list.get(i).classPart == "(") {
                                i++;
                                if (list.get(i).classPart == ")") {
                                    i++;
                                    if (list.get(i).classPart == "{") {
                                        i++;
                                        if (this.MST(list)) {
                                            if (list.get(i).classPart == "}") {
                                                i++;
                                                if (list.get(i).classPart == "}") {
                                                    i++;
                                                    if (this.CLASS_DEF(list)) {
                                                        return true;
                                                    } else {
                                                        if (list.get(i).classPart == "$") {
                                                            return true;
                                                        } else {
                                                            return false;
                                                        }
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
//        if (list.get(i).classPart == "void") {
//            i++;
//            if (list.get(i).classPart == "main") {
//                i++;
//                if (list.get(i).classPart == "(") {
//                    i++;
//                    if (list.get(i).classPart == ")") {
//                        i++;
//                        if (list.get(i).classPart == "{") {
//                            i++;
//                            if (this.MST(list)) {
//                                if (list.get(i).classPart == "}") {
//                                    i++;
//                                    if (this.CLASS_DEF(list)) {
//                                        return true;
//                                    } else {
//                                        return false;
//                                    }
//                                } else {
//                                    return false;
//                                }
//                            } else {
//                                return false;
//                            }
//                        } else {
//                            return false;
//                        }
//                    } else {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
    }

    boolean MST(ArrayList<Token> list) {
        if (list.get(i).classPart == "datatype"
                || list.get(i).classPart == "if"
                || list.get(i).classPart == "while"
                || list.get(i).classPart == "do"
                || list.get(i).classPart == "for"
                || list.get(i).classPart == "identifier"
                || list.get(i).classPart == "switch") {
            if (this.SST(list)) {
                if (this.MST(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}" || list.get(i).classPart == ";" || list.get(i).classPart == "case" || list.get(i).classPart == "default") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean SST(ArrayList<Token> list) {
        if (list.get(i).classPart == "if"
                || list.get(i).classPart == "while"
                || list.get(i).classPart == "do"
                || list.get(i).classPart == "for"
                || list.get(i).classPart == "datatype"
                || list.get(i).classPart == "identifier"
                || list.get(i).classPart == "switch") {
            if (list.get(i).classPart == "datatype") {
                i++;
                if (this.LComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (this.KComplement(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (list.get(i).classPart == "if") {
                        if (this.IF_ELSE(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (list.get(i).classPart == "while") {
                            if (this.WHILE(list)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            if (list.get(i).classPart == "do") {
                                if (this.DO_WHILE(list)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                if (list.get(i).classPart == "for") {
                                    if (this.FOR(list)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    if (list.get(i).classPart == "switch") {
                                        if (this.SWITCH(list)) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
    }

    boolean KComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "=") {
            i++;
            if (this.PComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "incdec") {
                i++;
                if (list.get(i).classPart == ";") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (list.get(i).classPart == "=") {
                        i++;
                        if (list.get(i).classPart == "new") {
                            i++;
                            if (list.get(i).classPart == "identifier") {
                                i++;
                                if (list.get(i).classPart == "(") {
                                    i++;
                                    if (this.PARAM(list)) {
                                        if (list.get(i).classPart == ")") {
                                            i++;
                                            if (list.get(i).classPart == ";") {
                                                i++;
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (list.get(i).classPart == "(") {
                        i++;
                        if (this.PARAM(list)) {
                            if (list.get(i).classPart == ")") {
                                i++;
                                if (list.get(i).classPart == ";") {
                                    i++;
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        if (list.get(i).classPart == "assignment") {
                            i++;
                            if (this.RIGHT_SIDE(list)) {
                                if (list.get(i).classPart == ";") {
                                    i++;
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            if (list.get(i).classPart == "[") {
                                i++;
                                if (this.OE(list)) {
                                    if (list.get(i).classPart == "]") {
                                        i++;
                                        if (this.M(list)) {
                                            if (this.EA(list)) {
                                                if (this.RIGHT_SIDE(list)) {
                                                    if (list.get(i).classPart == ";") {
                                                        i++;
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                if (this.O1(list)) {
                                    if (this.OComplement(list)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    boolean OComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "(") {
            i++;
            if (this.PARAM(list)) {
                if (list.get(i).classPart == ")") {
                    i++;
                    if (list.get(i).classPart == ";") {
                        i++;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (this.EA(list)) {
                if (this.RIGHT_SIDE(list)) {
                    if (list.get(i).classPart == ";") {
                        i++;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean PComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "new") {
            i++;
            if (list.get(i).classPart == "datatype") {
                i++;
                if (this.ARRAY_DECLARE2(list)) {
                    if (list.get(i).classPart == ";") {
                        i++;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (this.RIGHT_SIDE(list)) {
                if (list.get(i).classPart == ";") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean LComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier"
                || list.get(i).classPart == "[") {
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.INIT(list)) {
                    if (this.LIST(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "[") {
                    i++;
                    if (list.get(i).classPart == "]") {
                        i++;
                        if (this.DIM(list)) {
                            if (list.get(i).classPart == "identifier") {
                                i++;
                                if (this.MComplement(list)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    boolean DIM(ArrayList<Token> list) {
        if (list.get(i).classPart == "[") {
            i++;
            if (list.get(i).classPart == "]") {
                i++;
                if (this.DIM(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "identifier" || list.get(i).classPart == "{") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean MComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == ";") {
            i++;
            return true;
        } else {
            if (list.get(i).classPart == "=") {
                i++;
                if (list.get(i).classPart == "new") {
                    i++;
                    if (list.get(i).classPart == "datatype") {
                        i++;
                        if (this.ARRAY_DECLARE2(list)) {
                            if (list.get(i).classPart == ";") {
                                i++;
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean ARRAY_DECLARE2(ArrayList<Token> list) {
        if (list.get(i).classPart == "[") {
            i++;
            if (this.ARRAY_DECLARE3(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean ARRAY_DECLARE3(ArrayList<Token> list) {
        if (this.OE(list)) {
            if (list.get(i).classPart == "]") {
                i++;
                if (this.AX2(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "]") {
                i++;
                if (this.AX3(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean AX2(ArrayList<Token> list) {
        if (list.get(i).classPart == "[") {
            i++;
            if (this.OE(list)) {
                if (list.get(i).classPart == "]") {
                    i++;
                    if (this.AX2(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == ";") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean AX3(ArrayList<Token> list) {
        if (this.DIM(list)) {
            if (this.ARRAY_VALUES(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean ARRAY_VALUES(ArrayList<Token> list) {
        if (list.get(i).classPart == "{") {
            i++;
            if (this.ARRAY_VALUES_OUT(list)) {
                if (list.get(i).classPart == "}") {
                    i++;
                    if (this.AC(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean ARRAY_VALUES_OUT(ArrayList<Token> list) {
        if (this.ARRAY_VALUES(list)) {
            return true;
        } else {
            if (this.ARRAY_VALUES_1(list)) {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean AC(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (this.NEXT(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == ";" || list.get(i).classPart == " }") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean ARRAY_VALUES_1(ArrayList<Token> list) {
        if (this.EXP(list)) {
            return true;
        } else {
            return false;
        }
    }

    boolean NEXT(ArrayList<Token> list) {
        if (list.get(i).classPart == "{") {
            i++;
            if (this.NEXT_OUT(list)) {
                if (list.get(i).classPart == "}") {
                    i++;
                    if (this.DOT_TEMP(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}" || list.get(i).classPart == ";") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean EXP(ArrayList<Token> list) {
        if (this.OE(list)) {
            if (this.EXP1(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean EXP1(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (this.OE(list)) {
                if (this.EXP1(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean NEXT_OUT(ArrayList<Token> list) {
        if (this.NEXT(list)) {
            return true;
        } else {
            if (this.NEXT_START_1(list)) {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean NEXT_START_1(ArrayList<Token> list) {
        if (this.EXP(list)) {
            return true;
        } else {
            return false;
        }
    }

    boolean DOT_TEMP(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (this.NEXT(list)) {
                if (this.DOT_TEMP(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}" || list.get(i).classPart == ";") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean INIT(ArrayList<Token> list) {
        if (list.get(i).classPart == "=") {
            i++;
            if (this.OE(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == ";"
                    || list.get(i).classPart == ",") {
//                i++;
                return true;
            } else {
                return false;
            }
        }
    }

    boolean LIST(ArrayList<Token> list) {
        if (list.get(i).classPart == ";") {
            i++;
            return true;
        } else {
            if (list.get(i).classPart == ",") {
                i++;
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (this.INIT(list)) {
                        if (this.LIST(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean CONST_ID(ArrayList<Token> list) {
        if (list.get(i).classPart == "integer"
                || list.get(i).classPart == "float"
                || list.get(i).classPart == "string"
                || list.get(i).classPart == "boolean") {
            if (this.CONSTANT(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "identifier") {
                i++;
                return true;
            } else {
                return false;
            }
        }
    }

    boolean CONSTANT(ArrayList<Token> list) {
        if (list.get(i).classPart == "integer"
                || list.get(i).classPart == "float"
                || list.get(i).classPart == "string"
                || list.get(i).classPart == "char"
                || list.get(i).classPart == "boolean") {
            i++;
            return true;
        } else {
            return false;
        }
    }

    boolean OE(ArrayList<Token> list) {
        if (this.AE(list)) {
            if (this.OEComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean AE(ArrayList<Token> list) {
        if (this.ROP(list)) {
            if (this.AEComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean OEComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "logicalor") {
            i++;
            if (this.AE(list)) {
                if (this.OEComplement(list)) {
                    return true;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            if (list.get(i).classPart == ")"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == "("
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean ROP(ArrayList<Token> list) {
        if (this.E(list)) {
            if (this.ROPComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean AEComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "logicaland") {
            i++;
            if (this.ROP(list)) {
                if (this.AEComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "logicalor"
                    || list.get(i).classPart == ")"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean E(ArrayList<Token> list) {
        if (this.T(list)) {
            if (this.EComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean ROPComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "relational") {
            i++;
            if (this.E(list)) {
                if (this.ROPComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "logicaland"
                    || list.get(i).classPart == "logicalor"
                    || list.get(i).classPart == ")"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean T(ArrayList<Token> list) {
        if (this.F(list)) {
            if (this.TComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean EComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "pm") {
            i++;
            if (this.T(list)) {
                if (this.EComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "relational"
                    || list.get(i).classPart == "logicaland"
                    || list.get(i).classPart == "logicalor"
                    || list.get(i).classPart == ")"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean F(ArrayList<Token> list) {
        if (this.CONSTANT(list)) {
            return true;
        } else {
            if (list.get(i).classPart == "(") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == ")") {
                        i++;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (this.NComplement(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    boolean NComplement(ArrayList<Token> list) {
        if (this.O1(list)) {
            return true;
        } else {
            if (list.get(i).classPart == "[") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == "]") {
                        i++;
                        if (this.M(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "mdm"
                        || list.get(i).classPart == "pm"
                        || list.get(i).classPart == "relational"
                        || list.get(i).classPart == "logicaland"
                        || list.get(i).classPart == "logicalor"
                        || list.get(i).classPart == ")"
                        || list.get(i).classPart == "float"
                        || list.get(i).classPart == "integer"
                        || list.get(i).classPart == "boolean"
                        || list.get(i).classPart == "string"
                        || list.get(i).classPart == "char"
                        || list.get(i).classPart == "identifier"
                        || list.get(i).classPart == "("
                        || list.get(i).classPart == ";"
                        || list.get(i).classPart == "]"
                        || list.get(i).classPart == ","
                        || list.get(i).classPart == ":"
                        || list.get(i).classPart == "}") {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    boolean TComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "mdm") {
            i++;
            if (this.F(list)) {
                if (this.TComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "pm"
                    || list.get(i).classPart == "relational"
                    || list.get(i).classPart == "logicaland"
                    || list.get(i).classPart == "logicalor"
                    || list.get(i).classPart == ")"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean O(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            if (this.O1(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean O1(ArrayList<Token> list) {
        if (list.get(i).classPart == ".") {
            i++;
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.O1(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "="
                    || list.get(i).classPart == "assignment"
                    || list.get(i).classPart == "mdm"
                    || list.get(i).classPart == "pm"
                    || list.get(i).classPart == "relational"
                    || list.get(i).classPart == "logiacaland"
                    || list.get(i).classPart == "logicalor"
                    || list.get(i).classPart == ")"
                    || list.get(i).classPart == "integer"
                    || list.get(i).classPart == "float"
                    || list.get(i).classPart == "char"
                    || list.get(i).classPart == "string"
                    || list.get(i).classPart == "boolean"
                    || list.get(i).classPart == "identifier"
                    || list.get(i).classPart == "("
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == "]"
                    || list.get(i).classPart == ","
                    || list.get(i).classPart == ":"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean Arr(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            if (list.get(i).classPart == "[") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == "]") {
                        i++;
                        if (this.M(list)) {
                            return true;
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean M(ArrayList<Token> list) {
        if (list.get(i).classPart == "[") {
            i++;
            if (this.OE(list)) {
                if (list.get(i).classPart == "]") {
                    i++;
                    if (this.M(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "="
                    || list.get(i).classPart == "assignment"
                    || list.get(i).classPart == ";"
                    || list.get(i).classPart == ")") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean IF_ELSE(ArrayList<Token> list) {
        if (list.get(i).classPart == "if") {
            i++;
            if (list.get(i).classPart == "(") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == ")") {
                        i++;
                        if (list.get(i).classPart == "{") {
                            i++;
                            if (this.MST(list)) {
                                if (list.get(i).classPart == "}") {
                                    i++;
                                    if (this.IF_ELSE1(list)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean IF_ELSE1(ArrayList<Token> list) {
        if (list.get(i).classPart == "else") {
            i++;
            if (list.get(i).classPart == "{") {
                i++;
                if (this.MST(list)) {
                    if (list.get(i).classPart == "}") {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "if"
                    || list.get(i).classPart == "while"
                    || list.get(i).classPart == "do"
                    || list.get(i).classPart == "for"
                    || list.get(i).classPart == "datatype"
                    || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean WHILE(ArrayList<Token> list) {
        if (list.get(i).classPart == "while") {
            i++;
            if (list.get(i).classPart == "(") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == ")") {
                        i++;
                        if (this.BODY(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean BODY(ArrayList<Token> list) {
        if (list.get(i).classPart == ";") {
            i++;
            return true;
        } else {
            if (this.SST(list)) {
                return true;
            } else {
                if (list.get(i).classPart == "{") {
                    i++;
                    if (this.MST(list)) {
                        if (list.get(i).classPart == "}") {
                            i++;
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    boolean DO_WHILE(ArrayList<Token> list) {
        if (list.get(i).classPart == "do") {
            i++;
            if (this.BODY(list)) {
                if (list.get(i).classPart == "while") {
                    i++;
                    if (list.get(i).classPart == "(") {
                        i++;
                        if (this.OE(list)) {
                            if (list.get(i).classPart == ")") {
                                i++;
                                if (list.get(i).classPart == ";") {
                                    i++;
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean FOR(ArrayList<Token> list) {
        if (list.get(i).classPart == "for") {
            i++;
            if (list.get(i).classPart == "(") {
                i++;
                if (this.F1(list)) {
                    if (this.F2(list)) {
                        if (list.get(i).classPart == ";") {
                            i++;
                            if (this.F3(list)) {
                                if (list.get(i).classPart == ")") {
                                    i++;
                                    if (this.BODY(list)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean F1(ArrayList<Token> list) {
        if (this.DECL(list)) {
            return true;
        } else {
            if (this.ASSIGN(list)) {
                if (list.get(i).classPart == ";") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == ";") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    boolean DECL(ArrayList<Token> list) {
        if (list.get(i).classPart == "datatype") {
            i++;
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.INIT(list)) {
                    if (this.LIST(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean ASSIGN(ArrayList<Token> list) {
        if (this.LEFT_SIDE(list)) {
            if (this.EA(list)) {
                if (this.RIGHT_SIDE(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean LEFT_SIDE(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            if (this.CComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean CComplement(ArrayList<Token> list) {
        if (this.O1(list)) {
            return true;
        } else {
            if (list.get(i).classPart == "[") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == "]") {
                        i++;
                        if (this.M(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "=" || list.get(i).classPart == "assignment") {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    boolean EA(ArrayList<Token> list) {
        if (list.get(i).classPart == "=") {
            i++;
            return true;
        } else {
            if (list.get(i).classPart == "assignment") {
                i++;
                return true;
            } else {
                return false;
            }
        }
    }

    boolean RIGHT_SIDE(ArrayList<Token> list) {
        if (this.OE(list)) {
            return true;
        } else {
            return false;
        }
    }

    boolean F2(ArrayList<Token> list) {
        if (this.OE(list)) {
            return true;
        } else {
            if (list.get(i).classPart == ";") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean F3(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            if (this.DComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "incdec") {
                i++;
                if (list.get(i).classPart == "identifier") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == ")") {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    boolean DComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "incdec") {
            i++;
            return true;
        } else {
            if (this.CComplement(list)) {
                if (this.EA(list)) {
                    if (this.RIGHT_SIDE(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean SWITCH(ArrayList<Token> list) {
        if (list.get(i).classPart == "switch") {
            i++;
            if (list.get(i).classPart == "(") {
                i++;
                if (this.OE(list)) {
                    if (list.get(i).classPart == ")") {
                        i++;
                        if (list.get(i).classPart == "{") {
                            i++;
                            if (this.CASE(list)) {
                                if (this.DEFAULT(list)) {
                                    if (list.get(i).classPart == "}") {
                                        i++;
                                        if (list.get(i).classPart == ";") {
                                            i++;
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean CASE(ArrayList<Token> list) {
        if (list.get(i).classPart == "case") {
            i++;
            if (this.OE(list)) {
                if (list.get(i).classPart == ":") {
                    i++;
                    if (this.MST(list)) {
                        if (this.CASE(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "default" || list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean DEFAULT(ArrayList<Token> list) {
        if (list.get(i).classPart == "default") {
            i++;
            if (list.get(i).classPart == ":") {
                i++;
                if (this.MST(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}" || list.get(i).classPart == ";") {
                return true;
            } else {
                return true;
            }
        }

    }

    boolean PARAM(ArrayList<Token> list) {
        if (this.OE(list)) {
            if (this.TEMP2(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == ")") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean TEMP2(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (this.OE(list)) {
                if (this.TEMP2(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == ")") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean CLASS_DEF(ArrayList<Token> list) {
        if (this.FINAl_ABSTRACT(list)) {
            if (list.get(i).classPart == "accessmodifier") {
                i++;
                if (list.get(i).classPart == "class") {
                    i++;
                    if (list.get(i).classPart == "identifier") {
                        i++;
                        if (this.EXTENDS(list)) {
                            if (this.IMPLEMENTS(list)) {
                                if (this.CLASS_BODY(list)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "$") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean FINAl_ABSTRACT(ArrayList<Token> list) {
        if (list.get(i).classPart == "final") {
            i++;
            return true;
        } else {
            if (list.get(i).classPart == "abstract") {
                i++;
                return true;
            } else {
                if (list.get(i).classPart == "accessmodifier") {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    boolean EXTENDS(ArrayList<Token> list) {
        if (list.get(i).classPart == "extends") {
            i++;
            if (list.get(i).classPart == "identifier") {
                i++;
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "implements" || list.get(i).classPart == "{") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean IMPLEMENTS(ArrayList<Token> list) {
        if (list.get(i).classPart == "implements") {
            i++;
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.MULTI_IDENTIFIER(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "{") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean MULTI_IDENTIFIER(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.MULTI_IDENTIFIER(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "{") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean CLASS_BODY(ArrayList<Token> list) {
        if (list.get(i).classPart == "{") {
            i++;
            if (this.CLASS_MST(list)) {
                if (list.get(i).classPart == "}") {
                    i++;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "$") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean CLASS_MST(ArrayList<Token> list) {
        if (this.CLASS_SST(list)) {
            if (this.CLASS_MST(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "}") {
                return true;
            } else {
                return false;
            }
        }
    }

    boolean CLASS_SST(ArrayList<Token> list) {
        if (list.get(i).classPart == "final") {
            i++;
            if (this.YComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "abstract") {
                i++;
                if (this.YComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "static") {
                    if (this.UComplement(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (list.get(i).classPart == "accessmodifier") {
                        i++;
                        if (this.XComplement(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (list.get(i).classPart == "identifier") {
                            i++;
                            if (this.ZComplement(list)) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            if (list.get(i).classPart == "datatype") {
                                i++;
                                if (this.AComplement(list)) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                if (list.get(i).classPart == "void") {
                                    i++;
                                    if (list.get(i).classPart == "identifier") {
                                        i++;
                                        if (list.get(i).classPart == "(") {
                                            i++;
                                            if (this.PARAM1(list)) {
                                                if (list.get(i).classPart == ")") {
                                                    i++;
                                                    if (list.get(i).classPart == "{") {
                                                        i++;
                                                        if (this.MST(list)) {
                                                            if (list.get(i).classPart == "}") {
                                                                i++;
                                                                return true;
                                                            } else {
                                                                return false;
                                                            }
                                                        } else {
                                                            return false;
                                                        }
                                                    } else {
                                                        return false;
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean PARAM1(ArrayList<Token> list) {
        if (list.get(i).classPart == "datatype") {
            i++;
            if (this.PARAM1Complement(list)) {
                if (this.PARAM2(list)) {
                    return true;
                }
            }
        }
        if (list.get(i).classPart == "identifier") {
            i++;
            if (this.PARAM2Complement(list)) {
                if (this.PARAM2(list)) {
                    return true;
                }
            }
        }
        if (list.get(i).classPart == ")") {
            return true;
        }
        return false;
    }

    public boolean PARAM1Complement(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            return true;

        }
        if (list.get(i).classPart == "[") {
            i++;
            if (list.get(i).classPart == "]") {
                i++;
                if (this.DIM(list)) {
                    if (list.get(i).classPart == "identifier") {
                        i++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean PARAM2Complement(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            return true;
        }
        if (list.get(i).classPart == "[") {
            i++;
            if (list.get(i).classPart == "]") {
                i++;
                if (this.DIM(list)) {
                    if (list.get(i).classPart == "identifier") {
                        i++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean PARAM2(ArrayList<Token> list) {
        if (list.get(i).classPart == ",") {
            i++;
            if (this.PARAM1(list)) {
                return true;
            }
        } else {
            if (list.get(i).classPart == ")") {
                return true;
            }
        }
        return false;
    }

    boolean UComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "accessmodifier") {
            i++;
            if (this.XComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "identifier") {
                i++;
                if (this.ZComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "datatype") {
                    i++;
                    if (this.AComplement(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (list.get(i).classPart == "void") {
                        i++;
                        if (list.get(i).classPart == "identifier") {
                            i++;
                            if (list.get(i).classPart == "(") {
                                i++;
                                if (this.PARAM1(list)) {
                                    if (list.get(i).classPart == ")") {
                                        i++;
                                        if (list.get(i).classPart == "{") {
                                            i++;
                                            if (this.MST(list)) {
                                                if (list.get(i).classPart == "}") {
                                                    i++;
                                                    return true;
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    boolean YComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "static") {
            i++;
            if (this.RComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "accessmodifier") {
                i++;
                if (this.XComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (this.ZComplement(list)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (list.get(i).classPart == "datatype") {
                        i++;
                        if (this.AComplement(list)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if (list.get(i).classPart == "void") {
                            i++;
                            if (list.get(i).classPart == "identifier") {
                                i++;
                                if (list.get(i).classPart == "(") {
                                    i++;
                                    if (this.PARAM1(list)) {
                                        if (list.get(i).classPart == ")") {
                                            i++;
                                            if (list.get(i).classPart == "{") {
                                                i++;
                                                if (this.MST(list)) {
                                                    if (list.get(i).classPart == "}") {
                                                        i++;
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                } else {
                                                    return false;
                                                }
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
    }

    boolean RComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "accessmodifier") {
            i++;
            if (this.RT(list)) {
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (list.get(i).classPart == "(") {
                        i++;
                        if (this.PARAM1(list)) {
                            if (list.get(i).classPart == ")") {
                                i++;
                                if (list.get(i).classPart == "{") {
                                    i++;
                                    if (this.MST(list)) {
                                        if (list.get(i).classPart == "}") {
                                            i++;
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    boolean XComplement(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier") {
            i++;
            if (this.ZComplement(list)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (list.get(i).classPart == "datatype") {
                i++;
                if (this.AComplement(list)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (list.get(i).classPart == "void") {
                    i++;
                    if (list.get(i).classPart == "identifier") {
                        i++;
                        if (list.get(i).classPart == "(") {
                            i++;
                            if (this.PARAM1(list)) {
                                if (list.get(i).classPart == ")") {
                                    i++;
                                    if (list.get(i).classPart == "{") {
                                        i++;
                                        if (this.MST(list)) {
                                            if (list.get(i).classPart == "}") {
                                                i++;
                                                return true;
                                            } else {
                                                return false;
                                            }
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    boolean ZComplement(ArrayList<Token> list) {
        if (this.KComplement(list)) {
            return true;
        } else {
            if (list.get(i).classPart == "accessmodifier") {
                i++;
                if (list.get(i).classPart == "identifier") {
                    i++;
                    if (list.get(i).classPart == "(") {
                        i++;
                        if (this.PARAM1(list)) {
                            if (list.get(i).classPart == ")") {
                                i++;
                                if (list.get(i).classPart == "{") {
                                    i++;
                                    if (this.MST(list)) {
                                        if (list.get(i).classPart == "}") {
                                            i++;
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean AComplement(ArrayList<Token> list) {
        if (this.LComplement(list)) {
            return true;
        } else {
            if (list.get(i).classPart == "(") {
                i++;
                if (this.PARAM1(list)) {
                    if (list.get(i).classPart == ")") {
                        i++;
                        if (list.get(i).classPart == "{") {
                            i++;
                            if (this.MST(list)) {
                                if (list.get(i).classPart == "}") {
                                    i++;
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    boolean RT(ArrayList<Token> list) {
        if (list.get(i).classPart == "identifier"
                || list.get(i).classPart == "datatype"
                || list.get(i).classPart == "void") {
            i++;
            return true;
        } else {
            return false;
        }
    }

}
