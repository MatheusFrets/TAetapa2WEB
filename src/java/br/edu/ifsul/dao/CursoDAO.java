/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Curso;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Matheus
 * @email omatheusfreitas@gmail.com
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class CursoDAO<T> extends DAOGenerico<Curso> implements Serializable {

    public CursoDAO() {
        super();
        super.setClassePersistente(Curso.class);
        super.setOrdem("nome");        
    }
 
    @Override
    public Curso getObjectById(Integer id) throws Exception {
        Curso obj = (Curso) super.getEm().find(super.getClassePersistente(), id);
        obj.getDisciplinas().size();
        return obj;
    }       
    
}
