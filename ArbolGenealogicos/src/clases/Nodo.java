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
public class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona) {
        this.persona = persona;
        this.siguiente = null;
    }

    public Persona getpersona() {
        return persona;
    }

    public void setpersona(Persona persona) {
        this.persona = persona;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
