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

        List<Proveedor> proveedores = Stream.of(worldspanAdapter).collect(Collectors.toList());

        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico(proveedores);

        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");

        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");
        List<Vuelo> vuelos1 = ota.buscarVuelos(fecha, "BUE", "NYC");

        Assert.assertEquals(vuelos1, vuelos);




    }

    /*
    @org.testng.annotations.Test
    public void testReservar() {
        DistribuidorDeTrafico distribuidorDeTrafico = new DistribuidorDeTrafico();
        Ota ota = new Ota(distribuidorDeTrafico);

        DateTime fecha = new DateTime("2019-12-13");


        List<Vuelo> vuelos = ota.buscarVuelos(fecha, "BUE", "MIA");

        Vuelo elegido =  vuelos.get(0);
        Set<Pasajero> pasajeros = Stream.of(new Pasajero("Juan", "Pérez", 40)).collect(Collectors.toSet());

        Boleto boleto = ota.reservar(elegido, pasajeros );

        assertEquals(boleto.getVuelo(), elegido);


    }*/
}