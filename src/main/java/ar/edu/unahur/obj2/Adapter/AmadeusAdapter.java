package ar.edu.unahur.obj2.Adapter;

import ar.edu.unahur.obj2.Adapter.Proveedor;
import ar.edu.unahur.obj2.Boleto;
import ar.edu.unahur.obj2.Pasajero;
import ar.edu.unahur.obj2.Vuelo;
import ar.edu.unahur.obj2.proveedores.Amadeus;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;

public class AmadeusAdapter implements Proveedor {

    private Amadeus amadeus;

    public AmadeusAdapter(Amadeus amadeus) {
        this.amadeus = amadeus;
    }

    @Override
    public List<Vuelo> buscarVuelos(DateTime fecha, String origen, String destino) {

        return amadeus.executeSearch(fecha,origen,destino);
    }

    @Override
    public Boleto reservar(Vuelo vuelo, Set<Pasajero> pasajeros) {
        return amadeus.executeBook(vuelo, pasajeros);
    }
}
