package ar.edu.unahur.obj2;

import ar.edu.unahur.obj2.Adapter.AmadeusAdapter;
import ar.edu.unahur.obj2.Adapter.Proveedor;
import ar.edu.unahur.obj2.Adapter.SabreAdapter;
import ar.edu.unahur.obj2.Adapter.WorldspanAdapter;
import ar.edu.unahur.obj2.proveedores.Amadeus;
import ar.edu.unahur.obj2.proveedores.Sabre;
import ar.edu.unahur.obj2.proveedores.Worldspan;
import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.*;

public class OtaTest {

    Amadeus amadeus;
    Sabre sabre;
    Worldspan worldspan;

    AmadeusAdapter amadeusAdapter;
    SabreAdapter sabreAdapter;
    WorldspanAdapter worldspanAdapter;

    @BeforeTest
    public void beforeTest(){
        amadeus = new Amadeus();
        sabre = new Sabre();
        worldspan = new Worldspan();

        amadeusAdapter = new AmadeusAdapter(amadeus);
        sabreAdapter = new SabreAdapter(sabre);
        worldspanAdapter = new WorldspanAdapter(worldspan);
    }

    @org.testng.annotations.Test
    public void testBuscarVuelos() {

        List<Proveedor> proveedores = Stream.of(sabreAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");

        // Pido vuelos a traves de la plataforma Ota (El unico proveedor cargado es Sabre).
        List<Vuelo> vuelo1 = ota.buscarVuelos(fecha, "BUE", "MIA");

        // Pido vuelo directamente al proveedor Sabre sin usar adaptadores.
        List<Vuelo> vuelo3 = sabre.buscar(fecha, "BUE","MIA");

        //Comparo ambos vuelos.
        Assert.assertEquals(vuelo1, vuelo3);
    }


    @org.testng.annotations.Test
    public void testReservar() {

        List<Proveedor> proveedores = Stream.of(amadeusAdapter, sabreAdapter)
                .collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");

        Vuelo elegido =  vuelos.get(0);

        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Juan", "Pérez", 40)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );

        Assert.assertEquals(boleto.getVuelo(), elegido);
    }
}