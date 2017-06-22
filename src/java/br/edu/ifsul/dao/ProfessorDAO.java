/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Professor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Matheus
 * @email omatheusfreitas@gmail.com
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class ProfessorDAO<T> extends DAOGenerico<Professor> implements Serializable {

    public ProfessorDAO(){
        super();
        super.classePersistente = Professor.class;
    }
            
  
}