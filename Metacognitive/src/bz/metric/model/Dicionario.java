/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bz.metric.model;

/**
 *
 * @author bzsantos
 */
public class Dicionario {

    private int id_dicionario;
    private int hierarquia;
    private String palavra;
    private String sinonimo;
    private String idioma;

    public int getId_dicionario() {
        return id_dicionario;
    }

    public void setId_dicionario(int id_dicionario) {
        this.id_dicionario = id_dicionario;
    }

    public int getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(int hierarquia) {
        this.hierarquia = hierarquia;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSinonimo() {
        return sinonimo;
    }

    public void setSinonimo(String sinonimo) {
        this.sinonimo = sinonimo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    

}
