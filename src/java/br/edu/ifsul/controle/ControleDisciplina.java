/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.DisciplinaDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Matheus
 * @email omatheusfreitas@gmail.com
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleDisciplina")
@SessionScoped
public class ControleDisciplina implements Serializable{

    @EJB
    private DisciplinaDAO dao;

    public ControleDisciplina() {
    }
    

    public DisciplinaDAO getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO dao) {
        this.dao = dao;
    }
}
