package com.example.spring_jwt_jpa.enumerador;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by viniciuszim on 13/10/18.
 */
public enum TypeUser {

    NENHUM(0, "NENHUM"),
    ADMINISTRATIVO(1, "ADMINISTRATIVO"),
    APP(2, "APP"),
    SITE(3, "SITE");

    private int codigo;
    private String descricao;

    private TypeUser(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static ArrayList<TypeUser> obterLista() {
        ArrayList<TypeUser> list = new ArrayList<TypeUser>(Arrays.asList(TypeUser.values()));
        return list;
    }

    public static TypeUser obterEnum(int id) {
        //Reduzido uma unidade devido o ordinal dos Enums começam com 0 (zero)
        //Aplicado uma condiçao para os status quando nao houver status (antigos)
        if (id > 0) {
            id = id - 1;
        }

        return TypeUser.values()[id];
    }

    public static String obterDescricao(int codigo){
        for(TypeUser cargo: values()){
            if(cargo.getCodigo() == codigo){
                return cargo.getDescricao();
            }
        }
        return null;
    }

}
