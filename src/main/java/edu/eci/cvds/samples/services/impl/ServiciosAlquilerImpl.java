package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.exceptions.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ClienteDAO clienteDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) {
       return 0; //itemDAO.load(itemId).getTarifaxDia();
       
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
       try{
           return clienteDAO.load(docu);
       }
        catch (edu.eci.cvds.exceptions.PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los clientes", ex);
        }

   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try{
           return clienteDAO.load(idcliente).getRentados();
       }
       catch(edu.eci.cvds.exceptions.PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar los items del cliente "+idcliente,ex);
       }

   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try{
           return clienteDAO.load();
       }
       catch (edu.eci.cvds.exceptions.PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar los clientes",ex);
       }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles(){
       try {
           return itemDAO.itemsDisponibles();
       } catch (PersistenceException ex) {
           Logger.getLogger(ServiciosAlquilerImpl.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
      
      try {
          return itemDAO.multaAlquiler(iditem,fechaDevolucion);
      }
      catch (PersistenceException ex){
          throw new ExcepcionServiciosAlquiler("Error al consultar el alquiler del item "+iditem, ex);
       }
       
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try{
           return itemDAO.load(id).getTipo();           
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item"+id,ex);
       }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
       try{
           clienteDAO.saveItemAgregadoACliente(date,docu,item,numdias);
       }
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar el alquiler del cliente", ex);
       }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try{
           clienteDAO.save(c);
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al registrar al cliente", ex);
       }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(iditem).getTarifaxDia()*numdias;
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al consultar el alquiler del item "+iditem, ex);
       }
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
           itemDAO.load(id).setTarifaxDia(tarifa);
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item "+id, ex);
       }
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try{
           itemDAO.save(i);
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al registrar el item", ex);
       }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try{
           clienteDAO.load(docu).setVetado(estado);
       }
       catch (PersistenceException ex){
           throw new ExcepcionServiciosAlquiler("Error al vetar al cliente con numero de documento "+docu, ex);
       }
   }
}