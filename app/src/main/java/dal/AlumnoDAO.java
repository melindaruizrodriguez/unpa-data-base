/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import el.Alumno;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melin
 */
public class AlumnoDAO {
    private Connection dbCon;
    private Alumno alumno;
    
    public AlumnoDAO(Connection _dbCon){
        this.dbCon = _dbCon;
        this.alumno = new Alumno();
    }

    public List<Alumno> queryToDataBase(int matricula){
        List<Alumno> alumnos = new ArrayList<Alumno>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        
        try{
            cstmt = this.dbCon.prepareCall("{call consultar_alumno(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setInt(1, matricula);
            
            boolean results = cstmt.execute();
            int rowsAffected = 0;
            
            while(results || rowsAffected != -1){
                if(results){
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            
            while(rs != null && rs.next()){
                this.alumno = new Alumno();
                this.alumno.setIdAlumno(rs.getInt("idAlumno"));
                this.alumno.setNombre(rs.getString("nombre"));
                this.alumno.setApellido(rs.getString("apellido"));
                this.alumno.setEdad(rs.getInt("edad"));
                this.alumno.setCorreo(rs.getString("correo"));
                
                alumnos.add(alumno);
            }
        } catch(SQLException e){
            System.out.println("FALLO AL OBTENER EL/LOS ALUMNOS DE LA BASE DE DATOS [" + e.toString() + "]");
            return null;
        }
        return alumnos;
    }
    
    public int insertToDataBase(Alumno a){
        CallableStatement cstmt = null;
        int aux = 0;
        
        try{
            cstmt = this.dbCon.prepareCall("{call insertarAlumnos(?,?,?,?)}");
            cstmt.setString("In_Nombre", a.getNombre());
            cstmt.setString("In_Apellido", a.getApellido());
            cstmt.setInt("In_Edad", a.getEdad());
            cstmt.setString("In_Correo", a.getCorreo());
            
            cstmt.execute();
            aux = 1; // Si no lanzó error (como correo duplicado), ¡sí se guardó!
            
        } catch(SQLException e){
            System.out.println("fallo al registrar el/los alumnos de la base de datos[" + e.toString() + "]");
            aux = 0;
        }
        return aux;
    }
    
    public int updateToDataBase(Alumno alumno){
        CallableStatement cstmt = null;
        ResultSet rs = null;
        int aux = 0;
        
        try{
            cstmt = this.dbCon.prepareCall("{call actualizar_alumno(?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setInt("In_Matricula", alumno.getIdAlumno());
            cstmt.setString("In_Nombre", alumno.getNombre());
            cstmt.setString("In_Apellido", alumno.getApellido());
            cstmt.setInt("In_Edad", alumno.getEdad());
            cstmt.setString("In_Correo", alumno.getCorreo());
            
            boolean results = cstmt.execute();
            aux = 1; // Si ejecutó sin lanzar SQLException, la actualización fue exitosa
            int rowsAffected = 0;
            
            while(results || rowsAffected != -1){
                if(results){
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            
            if(rs != null && rs.next()){
                aux = rs.getInt(1);
            }
            return aux;
            
        } catch(SQLException e){
            System.out.println("fallo al actualizar alumnos de la base de datos[" + e.toString() + "]");
        }
        return 0;
    }
    
    public int deleteToDataBase(int matricula){
        CallableStatement cstmt = null;
        ResultSet rs = null;
        int aux = 0;
        
        try{
            cstmt = this.dbCon.prepareCall("{call eliminar_alumno(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt.setInt("In_Matricula", matricula);
            
            boolean results = cstmt.execute();
            int rowsAffected = 0;
            
            while(results || rowsAffected != -1){
                if(results){
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            
            if (rs != null && rs.next()) {
                aux = rs.getInt(1);
            } else if (rowsAffected > 0) {
                aux = 1;
            }
            return aux;
            
        } catch(SQLException e){
            System.out.println("fallo al eliminar alumnos de la base de datos[" + e.toString() + "]");
        }
        return 0;
    }
}