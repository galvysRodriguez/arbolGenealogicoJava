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
public class Persona{

    private String nombre;
    private Persona pareja;
    private Persona madre;
    private Persona padre;
    private ListaPersonas hijos;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.pareja = null;
        this.madre = null;
        this.padre = null;
        this.hijos = new ListaPersonas();
    }
    
    public void agregarPersona(Persona persona){
        persona.ingresarAlPrincipio (persona);
    }
    
    public Persona buscarPersona(String nombre) {
        return null;
    // Busca a la persona por su nombre en la lista de personas
    }    
    public void agregarHijo(Persona hijo){
        hijos.ingresarAlPrincipio(hijo);
    }
    
    public int cantidadHijos(){
        return hijos.getLargo();
    }
    

    public ListaPersonas getHijos() {
        return hijos;
    }

    public void setHijos(ListaPersonas hijos) {
        this.hijos = hijos;
    }

    public Persona getMadre() {
        return madre;
    }

    public void setMadre(Persona madre) {
        this.madre = madre;
        madre.getHijos().ingresarAlPrincipio(this);
    }

    public Persona getPadre() {
        return padre;
    }

    public void setPadre(Persona padre) {
        this.padre = padre;
        padre.getHijos().ingresarAlPrincipio(this);
    }

    public Persona getPareja() {
        return pareja;
    }

    public void setPareja(Persona pareja) {
        this.pareja = pareja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void agregar(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ingresarAlPrincipio(Persona persona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
