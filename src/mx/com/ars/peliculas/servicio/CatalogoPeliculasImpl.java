package mx.com.ars.peliculas.servicio;

import mx.com.ars.peliculas.datos.*;
import mx.com.ars.peliculas.domain.Pelicula;
import mx.com.ars.peliculas.excepciones.AccesoDatosEx;
import mx.com.ars.peliculas.excepciones.LecturaDatosEx;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas{
    
    private final IAccesoDatos datos;
    
    public CatalogoPeliculasImpl(){
        this.datos = new AccesoDatosImpl();
    }


    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula = new Pelicula(nombrePelicula);
        boolean anexar = false;
        try {
            anexar = datos.existe(NOMBRE_ARCHIVO);
            datos.escribir(pelicula, NOMBRE_ARCHIVO, anexar);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso de datos.");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarPeliculas() {
        try {
            var peliculas = this.datos.listar(NOMBRE_ARCHIVO);
            for(var pelicula: peliculas){
                System.out.println("Nombre de la pelicula " + pelicula);
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Error de acceso datos.");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPeliculas(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_ARCHIVO, buscar);
            
        } catch (LecturaDatosEx ex) {
            System.out.println("Error de acceso de datos.");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado:" + resultado);
    }

    @Override
    public void iniciarCatalogoPeliculas() {
        try {
            if(datos.existe(NOMBRE_ARCHIVO)){
                datos.borrar(NOMBRE_ARCHIVO);
                datos.crear(NOMBRE_ARCHIVO);
            } else{
                datos.crear(NOMBRE_ARCHIVO);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al iniciar datos de catalogo de peliculas.");
            ex.printStackTrace(System.out);
        }
    }
    
}
