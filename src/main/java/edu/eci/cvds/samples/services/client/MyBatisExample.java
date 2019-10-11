/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    //private static ServiciosAlquiler fabrica = new ServiciosAlquiler();
    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static ServiciosAlquiler testDeServicio = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }

        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        pruebasClientes();
        pruebasItems();

    }

    public static void pruebasClientes() {
        
        try {            
            //Primer prueba consultando al cliente 90
            System.out.println(">>>>>>>>PRUEBA DE CONSULTAR CLIENTE MEDIANTE CAPA LOGICA<<<<<<<<\n");
            System.out.println(testDeServicio.consultarCliente((long) 90).toString()+"\n\n");
            //Segunda prueba consultando los items de un cliente
            System.out.println(">>>>>>>>PRUEBA DE CONSULTAR LOS ITEMS DE UN CLIENTE<<<<<<<<\n");
            System.out.println(testDeServicio.consultarItemsCliente((long) 69).toString()+"\n\n");
            //Tercera prueba agregando un nuevo cliente CLIENTE YA AGREGADO
            //Cliente cli  = new Cliente("Bill Clinton",98347,"5436753","la casa blanca","leguinskyBJ@mail.com",false, null);            
            //testDeServicio.registrarCliente(cli);
            

            
        } catch (ExcepcionServiciosAlquiler ex) {
            Logger.getLogger(MyBatisExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void pruebasItems(){
        try{
            System.out.println(">>>>>>>>PRUEBA DE CONSULTAR ITEM MEDIANTE CAPA LOGICA<<<<<<<<\n");
            System.out.println("ESTO FALLA Y SEGUIRA FALLANDO******************************************************************");
            System.out.println(testDeServicio.consultarItem(666)+"\n\n");
            
            /**
            
            System.out.println(">>>>>>>>PRUEBA DE AGREGAR ITEM<<<<<<<<\n");
            testDeServicio.registrarItem(generarItem());
            
            System.out.println(testDeServicio.consultarItem(115)+"\n\n");
            */
            
            
        } catch (ExcepcionServiciosAlquiler ex){
            
        }
        
    }
    public static Item generarItem(){        
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
