package com.example.unpa;

import bll.AlumnoService;
import dcl.DbUtil;                   // Import de DbUtil desde su paquete
import el.Alumno;
import java.sql.Connection;          // Import de Connection
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// Si Alumno y AlumnoService están en otro paquete, descomenta y ajusta:
// import com.example.unpa.model.Alumno;
// import com.example.unpa.service.AlumnoService;

public class TestUniversidad {
    private Connection dbCon;
    
    public TestUniversidad(){
        this.Initialize();
    }
    
    public void Initialize(){
        try {
            dbCon = DbUtil.getInstance().getConnection();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void queryToDataBase(){
        List<Alumno> alumnos = new ArrayList<Alumno>();
        
        AlumnoService alumnoService = new AlumnoService();
        alumnos = alumnoService.queryToDataBase(dbCon, 0);
        
        for (Alumno alumno : alumnos) {
            System.out.println("Matricula: " + alumno.getId() + " Nombre: " + alumno.getNombre());
        }
    }
    
    public void insertToDataBase(){
        Alumno alumno = new Alumno();
        alumno.setNombre("Arturo");
        alumno.setApellido("Verdon Contreras");
        alumno.setEdad(23);
        alumno.setCorreo("arturoverdon@gmail.com");
        
        AlumnoService alumnoService = new AlumnoService();
        int aux = alumnoService.insertToDataBase(dbCon,alumno);
        
        if(aux==1){
            System.out.println("el registro fue exitoso");
            
            
        }else{
            System.out.println("el registro fallo");
        }
       
    }
    
     public void updateToDateBase(){
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        alumno.setNombre("Arturo");
        alumno.setApellido("Verdon Contreras");
        alumno.setEdad(23);
        alumno.setCorreo("arturoverdon@gmail.com");
        
        AlumnoService alumnoService = new AlumnoService();
        int aux = alumnoService.insertToDataBase(dbCon,alumno);
        
        if(aux==1){
            System.out.println("la actualizacion fue exitoso");
            
            
        }else{
            System.out.println("la actualizacion fallo");
        }
    }
        
        public void deleteDataBase(){
        
        AlumnoService alumnoService = new AlumnoService();
        int aux = alumnoService.deleteToDataBase(dbCon,0);
        
        if(aux==1){
            System.out.println("la eeliminacion fue exitoso");
            
            
        }else{
            System.out.println("la eliminacion fallo");
        }
    }

    void deleteToDataBase(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}