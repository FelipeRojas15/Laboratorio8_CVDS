package edu.eci.cvds.sampleprj.dao.mybatis;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.SQLException;

public class MyBATISTipoItemDAO implements TipoItemDAO{

  @Inject
  private TipoItemMapper tipoItemMapper;    

  @Override
  public void save(TipoItem ti) throws PersistenceException{
        try{            
            tipoItemMapper.addTipoItem(ti);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al registrar el cliente ", e);
  }
  }

  @Override
  public TipoItem load(int id) throws PersistenceException {
  try{
      return tipoItemMapper.getTipoItem(id);
  }
  catch(org.apache.ibatis.exceptions.PersistenceException e){
      throw new PersistenceException("Error al consultar el cliente "+id,e);
  }


  }

  }