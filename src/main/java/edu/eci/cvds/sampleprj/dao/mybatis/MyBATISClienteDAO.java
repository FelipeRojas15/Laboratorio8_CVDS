package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.TipoItem;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;
    @Override
    public void saveItemAgregadoACliente(Date date, long docu, Item item, int numdias) throws PersistenceException {
        Calendar cal = Calendar.getInstance();                                 
        cal.setTime(date);
        cal.add(Calendar.DATE, numdias);        
        try{
            clienteMapper.agregarItemRentadoACliente((int)docu, item.getId(), date, cal.getTime());
            
        }
        
        catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el prestamo al cliente ", e);
        }
    }

    @Override
    public void save(Cliente cl) throws PersistenceException {
        try {
            clienteMapper.agregarCliente(cl);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al registrar el cliente ", e);
        }
    }
    
    

    @Override
    public Cliente load(long id) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente(id);
        } catch (org.apache.ibatis.exceptions.PersistenceException e) {
            throw new PersistenceException("Error al consultar el cliente " + id, e);
        }

    }

    @Override
    public List<Cliente> load() throws PersistenceException {
        try{            
            return clienteMapper.consultarClientes();
        }
        catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente ", e);
        }
    }

    

}
