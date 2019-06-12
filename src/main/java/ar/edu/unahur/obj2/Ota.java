package ar.edu.unahur.obj2;

import ar.edu.unahur.obj2.Adapter.Proveedor;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

public class Ota {

    private DistribuidorDeTrafico distribuidorDeTrafico;

    public Ota(DistribuidorDeTrafico distribuidorDeTrafico) {
        this.distribuidorDeTrafico = distribuidorDeTrafico;
    }

    public List<Vuelo> buscarVuelos(DateTime fecha, String origen, String destino) {
        Proveedor proveedor = distribuidorDeTrafico.proveedor();

        return proveedor.buscarVuelos(fecha, origen, destino);
    }

    public Boleto reservar(Vuelo vuelo, Set<Pasajero> pasajeros) {
        Proveedor proveedor = distribuidorDeTrafico.proveedor();
        return proveedor.reservar(vuelo, pasajeros);
    }
}
