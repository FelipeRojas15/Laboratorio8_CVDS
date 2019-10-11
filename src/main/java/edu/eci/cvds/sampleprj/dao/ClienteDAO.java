package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Item;
import java.util.List;
import java.sql.Date;

public interface ClienteDAO {

   public void save(Cliente cl) throws PersistenceException;

   public Cliente load(long id) throws PersistenceException;
   
   public List<Cliente> load() throws PersistenceException;
   
   public void saveItemAgregadoACliente(long docu, Item item, java.sql.Date fechaini, java.sql.Date fechafin) throws PersistenceException;

}