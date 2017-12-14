/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.xml.ws.WebServiceRef;
import serviceClient.Player;
import serviceClient.PracticaExamenServiceSOAP_Service;

/**
 *
 * @author Dani
 */
@Named(value = "practicaExamenClienteBean")
@SessionScoped
public class practicaExamenClienteBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_54747/practicaExamenSOAP-war/practicaExamenServiceSOAP.wsdl")
    private PracticaExamenServiceSOAP_Service service;
    private Player player = new Player();

    /**
     * Creates a new instance of practicaExamenClienteBean
     */
    public practicaExamenClienteBean() {
    }

    
    //METODOS DEL SERVIDOR SOAP
    public java.util.List<serviceClient.Player> encontrarJugadores() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        return port.encontrarJugadores();
    }
    
    private Player encontrarJugadorPorID(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        return port.encontrarJugadorPorID(id);
    }
    
    private void edit(serviceClient.Player entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        port.edit(entity);
    }
    
    private void remove(serviceClient.Player entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        port.remove(entity);
    }
    
    private void create(serviceClient.Player entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        port.create(entity);
    }
    
    private int encontrarMaximoIDJugador() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serviceClient.PracticaExamenServiceSOAP port = service.getPracticaExamenServiceSOAPPort();
        return port.encontrarMaximoIDJugador();
    }
    
    //METODOS DE REDIRECCIÓN ENTRE PÁGINAS
    public String irPerfilJugador(int id){
        player = encontrarJugadorPorID(id);
        
        return "perfilJugador";
    }
    
    public String irConfirmarBorrar(int id){
        player = encontrarJugadorPorID(id);
        
        return "confirmacionBorrar";
    }
    
    public String irPortada(){   
        return "index";
    }
    
    public String irCrearJugador(){
        player = new Player();
        return "crearJugador";
    }

    
    //METODOS GET Y SET DE VARIABLES
    public Player getPlayer(){
        return this.player;
    }
    
    public String actualizarPlayer(){
        edit(player);
        return "index";
    }
    
    public String borrarPlayer(){
        remove(player);
        return "index";
    }
    
    public String crearPlayer(){
        player.setId(encontrarMaximoIDJugador());
        
        create(player);
        return "index";
    }

    

    
    

    

    

    
    
    
}
