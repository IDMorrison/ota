package ar.edu.unahur.obj2.proveedores;

import ar.edu.unahur.obj2.Vuelo;
import org.joda.time.DateTime;

import java.util.List;

public class AmadeusAdapter implements Proveedor {

    @Override
    public List<Vuelo> buscarVuelo(DateTime fecha, String origen, String destino) {
        return null;
    }
}
