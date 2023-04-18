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
public class ListaPersonas{
    private Nodo principio;
    private int largo;
    
     public ListaPersonas() {
        this.principio = null;
        this.largo = 0;
     }

    public Nodo getPrincipio() {
        return principio;
    }

    public void setPrincipio(Nodo principio) {
        this.principio = principio;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
    
    //Ingresa un valor al inicio de la lista
    public void ingresarAlPrincipio (Persona nuevo){
        Nodo info = new Nodo(nuevo);
        if (getPrincipio() == null){
            setPrincipio(info);
        }else{
            info.setSiguiente(principio);
            setPrincipio(info);
        }
        largo++;
    }
    
    //Metodo que devuelve el numero que almacena un nodo dado su indice
    public Nodo obtenerPersona(int indice){
        Nodo nodoObjetivo = principio;
        for (int i=0; i<indice;i++){
            nodoObjetivo = nodoObjetivo.getSiguiente();
        }
        return nodoObjetivo;
    }
    
     public Nodo encontrarPersona(String referencia) {
        Nodo aux = principio;
        boolean encontrado = false;
        while (aux != null && encontrado != true) {
            if (referencia.equals(aux.getpersona().getNombre())
                    || referencia.equals(aux.getpersona().getNombre())) {
                return aux;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return null;
    }
    
}
