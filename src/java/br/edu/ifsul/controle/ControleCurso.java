/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.modelo.Curso;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
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
@Named(value = "controleCurso")
@SessionScoped
public class ControleCurso implements Serializable{


    @EJB
    private CursoDAO<Curso> dao;
    private Curso objeto;
    private Boolean editando;
    @EJB
    private DisciplinaDAO<Disciplina> daoDisciplina;
    

    public ControleCurso() {
        editando = false;
        
    }

    public String listar(){
    editando = false;
    return "/privado/curso/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Curso();
    }
    
    public void alterar(Integer id){
        
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto:" + Util.getMensagemErro(e));
        }
    }
    public void excluir(Integer id){
        
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto:" + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto:" + Util.getMensagemErro(e));
        }
    }
    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisciplina;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisciplina = daoDisciplina;
    }

    
    
}

