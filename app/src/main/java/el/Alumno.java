
package el;

/**
 * Representa la entidad Alumno.
 * 
 * @author melin
 */
public class Alumno {

    // 1. Atributos (declarados al inicio)
    private int idAlumno;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;

    // 2. Constructores
    public Alumno() {
    }

    public Alumno(int idAlumno, String nombre, String apellido, int edad, String correo) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad =edad;
        this.correo = correo;
    }

    // 3. Métodos Getters y Setters
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return idAlumno;
        
    }

}
   