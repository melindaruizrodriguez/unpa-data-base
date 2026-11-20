/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bll;

import dal.AlumnoDAO;
import el.Alumno;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author melin
 */
public class AlumnoService {
    public List<Alumno> queryToDataBase(Connection dbCon, int matricula){
        try{
            AlumnoDAO alumnoDAO = new AlumnoDAO(dbCon);
            return alumnoDAO.queryToDataBase(matricula);
        }
        catch(Exception e){
            throw e;
        }
        
    }
    
    public int insertToDataBase( Connection dbCon, Alumno alumno){
        int aux=0;
    
        try{
            AlumnoDAO alumnoDAO = new AlumnoDAO(dbCon);
            aux = alumnoDAO.insertToDataBase(alumno);
            
        }catch(Exception e){
            throw e;
        }
        
        return aux;
    }
    public int updateToDataBase( Connection dbCon, Alumno alumno){
        int aux=0;
    
        try{
            AlumnoDAO alumnoDAO = new AlumnoDAO(dbCon);
            aux = alumnoDAO.updateToDataBase(alumno);
            
        }catch(Exception e){
            throw e;
        }
        
        return aux;
    }
    public int deleteToDataBase( Connection dbCon,int matricula){
        int aux=0;
    
        try{
            AlumnoDAO alumnoDAO = new AlumnoDAO(dbCon);
            aux = alumnoDAO.deleteToDataBase(matricula);
            
        }catch(Exception e){
            throw e;
        }
        
        return aux;
    }
    
    
}
