package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.sampleprj.dao.PersistenceException;

public interface TipoItemDAO {

   public void save(TipoItem it) throws PersistenceException;

   public TipoItem load(int id) throws PersistenceException;

}