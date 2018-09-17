/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.construction.project;

/**
 *
 * @author Dell
 */
public class Token {

    String classPart;
    String valuePart;
    int lineNumber;

    public Token(String cp, String vp, int ln) {
        this.classPart = cp;
        this.valuePart = vp;
        this.lineNumber = ln;
    }
}
