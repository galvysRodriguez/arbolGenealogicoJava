/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Rodriguez
 */
public class Arbol {

    private Persona raiz;

    public Arbol(Persona raiz) {
        this.raiz = raiz;
    }

    public Persona getRaiz() {
        return raiz;
    }

    public void setRaiz(Persona raiz) {
        this.raiz = raiz;
    }

    public Persona SubirNivelPadre(Persona nodo, int nivel) {
        if (nivel != 0) {
            return SubirNivelPadre(nodo.getPadre(), nivel - 1);
        }
        return nodo;
    }

    public Persona SubirNivelMadre(Persona nodo, int nivel) {
        if (nivel != 0) {
            return SubirNivelMadre(nodo.getMadre(), nivel - 1);
        }
        return nodo;
    }



    public String obtenerEsposa() {
        if (raiz.getPareja() == null) {
            return "";
        }
        return raiz.getPareja().getNombre();
    }

    public String[] abuelos() {
        String[] abuelos = new String[2];
        Persona abueloP = SubirNivelPadre(raiz, 2);
        Persona abuelaM = SubirNivelMadre(raiz, 2);
        abuelos[0] = abueloP.getNombre() + " y " + abueloP.getPareja().getNombre();
        abuelos[1] = abuelaM.getPareja().getNombre() + " y " + abuelaM.getNombre();

        return abuelos;
    }

    public Persona abueloPaterno() {
        return SubirNivelPadre(raiz, 2);
    }

    public Persona abuelaMaterno() {
        return SubirNivelMadre(raiz, 2);
    }

    public Persona abueloMaterno() {
        Persona aux = SubirNivelMadre(raiz, 1);
        Persona abueloMaterno = SubirNivelPadre(aux, 1);
        return abueloMaterno;
    }

    public Persona abuelaPaterno() {
        Persona aux = SubirNivelPadre(raiz, 1);
        Persona abuelaPaterno = SubirNivelMadre(aux, 1);
        return abuelaPaterno;
    }

    public Persona buscarPersona(Persona padre, Persona valor) {
        if (padre == null) {
            return null;
        }
        if (padre.equals(valor)) {
            return padre;
        }

        if (padre.getHijos().encontrarPersona(valor.getNombre()) != null) {
            Persona aux = buscarPersona(padre.getHijos().encontrarPersona(valor.getNombre()).getpersona(), valor);
            return aux;
        }
        return null;
    }

    public Persona buscarPersona(String nombre) {
        return buscarEnArbol(raiz, nombre);
    }

    public Persona buscarEnArbol(Persona nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getNombre().equals(nombre)) {
            return nodo;
        }
        ListaPersonas hijos = nodo.getHijos();
        for (int i = 0; i < hijos.getLargo(); i++) {
            Persona personaEncontrada = buscarEnArbol(hijos.obtenerPersona(i).getpersona(), nombre);
            if (personaEncontrada != null) {
                return personaEncontrada;
            }
        }
        return null;
    }

    public ListaPersonas encontrarPersonas() {
        ListaPersonas personas = new ListaPersonas();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < raiz.cantidadHijos()) {
            personas.ingresarAlPrincipio(raiz.getHijos().obtenerPersona(i).getpersona());
            while (j < raiz.getHijos().obtenerPersona(i).getpersona().cantidadHijos()) {
                personas.ingresarAlPrincipio(raiz.getHijos().obtenerPersona(i).getpersona().getHijos().obtenerPersona(j).getpersona());
                while (k < raiz.getHijos().obtenerPersona(i).getpersona().getHijos().obtenerPersona(j).getpersona().cantidadHijos()) {
                    personas.ingresarAlPrincipio(raiz.getHijos().obtenerPersona(i).getpersona().getHijos().obtenerPersona(j).getpersona().getHijos().obtenerPersona(k).getpersona());
                    k++;
                }
                j++;
            }
            i++;
        }
        return personas;
    }
}
