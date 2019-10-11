package edu.eci.cvds.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() throws ExcepcionServiciosAlquiler {
        serviciosAlquiler = ServiciosAlquilerFactory.getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    
    @Test
    public void agregarItem() {
        Item itemAgregado = generarItem();
        boolean bandera = true;
        try {
            serviciosAlquiler.registrarItem(itemAgregado);
            Item itemConsultado = serviciosAlquiler.consultarItem(115);
            System.out.println("itemConsultadOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
            System.out.println(itemConsultado);
        } catch (ExcepcionServiciosAlquiler e) {
            bandera = false;
        }
        
        Assert.assertTrue(bandera);
    }
    
    @Test
    public void consultarItem(){
        int idItem = 115;
        try{
            Item itemConsultado = serviciosAlquiler.consultarItem(idItem);
            System.out.println(itemConsultado.getNombre()+"AWESOMEEEEE!!!!!!!!!!!");
            Assert.assertEquals(itemConsultado.getNombre(),"Cloud Treasure" );
        }
        catch(ExcepcionServiciosAlquiler e){
        }
    }



/**
    @Test
    public void clienteIronMan() {
        int documento = 69;
        try {
            Cliente cliente = serviciosAlquiler.consultarCliente(documento);            
            System.out.println("aca esta IRONMANNNNNNNNNNNNNN");
            System.out.println(serviciosAlquiler.consultarClientes());
            Assert.assertEquals(cliente.getNombre(),"ironman" );
        } catch (ExcepcionServiciosAlquiler e) {
            
        }        
        
    }
    */ 
    




//Apoyo para las pruebas.
    public Item generarItem(){        
        TipoItem tipoDeItemAgregado = new TipoItem(15,"Tipo de Item volador");
        Date fecha = convertDate("2012-02-22");
        Item itemAgregado = new Item(tipoDeItemAgregado, 115, "Cloud Treasure", "Tesoro en las alturas vuela a velocidades impresionantes", fecha, 20000, "Renta Express", "Carreras");
        return itemAgregado;
    }
    
    public static Date convertDate(String fecha) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }
    
    
    
}
