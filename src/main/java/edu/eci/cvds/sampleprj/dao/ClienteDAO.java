package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.exceptions.PersistenceException;
import java.util.List;

public interface ClienteDAO {

   public void save(Cliente cl) throws PersistenceException;

   public Cliente load(long id) throws PersistenceException;
   
   public List<Cliente> load() throws PersistenceException;

}