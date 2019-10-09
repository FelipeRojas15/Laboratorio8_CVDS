package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.exceptions.PersistenceException;
import java.sql.Date;
import java.util.List;

public interface ItemDAO {

   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;
   
   public List<Item> itemsDisponibles() throws PersistenceException;
   
  

    public long multaAlquiler(int iditem, Date fechaDevolucion) throws PersistenceException ;

}