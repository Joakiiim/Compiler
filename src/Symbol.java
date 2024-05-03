/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
public class Symbol {

    private String ID;
    private String token;
    private String valor;
    private String D1;
    private String D2;
    private String ptr;
    private String ambito;
    private String tipoDato;
    private int valorInt;
    private double valorDouble;
    private String valorString;

    public Symbol(String ID, String token, String valor, String D1, String D2, String ptr, String ambito, String tipoDato) {
        this.ID = ID;
        this.token = token;
        this.valor = valor;
        this.D1 = D1;
        this.D2 = D2;
        this.ptr = ptr;
        this.ambito = ambito;
        this.tipoDato = tipoDato;
    }

    public Symbol(String ID, String token, String D1, String D2, String ptr, String ambito, String tipoDato, int valorInt) {
        this.ID = ID;
        this.token = token;
        this.D1 = D1;
        this.D2 = D2;
        this.ptr = ptr;
        this.ambito = ambito;
        this.tipoDato = tipoDato;
        this.valorInt = valorInt;
    }

    public Symbol(String ID, String token, String valor, String D1, String D2, String ptr, String ambito,double valorDouble, String tipoDato) {
        this.ID = ID;
        this.token = token;
        this.valor = valor;
        this.D1 = D1;
        this.D2 = D2;
        this.ptr = ptr;
        this.ambito = ambito;
        this.tipoDato = tipoDato;
        this.valorDouble = valorDouble;
    }

    public Symbol(String ID, String token, String valor, String D1, String D2, String ptr, String ambito, String tipoDato, String valorString) {
        this.ID = ID;
        this.token = token;
        this.valor = valor;
        this.D1 = D1;
        this.D2 = D2;
        this.ptr = ptr;
        this.ambito = ambito;
        this.tipoDato = tipoDato;
        this.valorString = valorString;
    }

    public int getValorInt() {
        return valorInt;
    }

    public void setValorInt(int valorInt) {
        this.valorInt = valorInt;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getD1() {
        return D1;
    }

    public void setD1(String D1) {
        this.D1 = D1;
    }

    public String getD2() {
        return D2;
    }

    public void setD2(String D2) {
        this.D2 = D2;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public double getValorDouble() {
        return valorDouble;
    }

    public void setValorDouble(double valorDouble) {
        this.valorDouble = valorDouble;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    @Override
    public String toString() {
        return "Symbol{" + "ID=" + ID + ", token=" + token + ", valor=" + valor + ", D1=" + D1 + ", D2=" + D2 + ", ptr=" + ptr + ", ambito=" + ambito + ", tipoDato=" + tipoDato + ", valorInt=" + valorInt + ", valorDouble=" + valorDouble + ", valorString=" + valorString + '}';
    }
    

}
