/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.EnderecoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Endereco;
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
@Named(value = "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable{
    @EJB
    private AlunoDAO<Aluno> dao;
    private Aluno objeto;
    private Boolean editando;
    @EJB
    private EnderecoDAO<Endereco> daoEndereco;
    private Endereco endereco;
    private Boolean editandoEndereco;
    
    public ControleAluno() {
        editando = false;
        editandoEndereco = false;
        
    }

    public String listar(){
    editando = false;
    editandoEndereco = false;
    return "/privado/aluno/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        editandoEndereco = false;
        objeto = new Aluno();
    }
    
    public void alterar(Integer id){
        
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoEndereco = false;
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
    
    public void novaEndereco(){
        editandoEndereco = true;
    }
    
    public void salvarEndereco(){
        if(!objeto.getEnderecos().contains(endereco)){
            objeto.getEnderecos().add(endereco);
            Util.mensagemInformacao("Endereco adicionado com sucesso!");
        }else{
            Util.mensagemErro("Aluno ja possui este esdereco !");
        }
        editandoEndereco = false;
    }
    
    
    public void removerEndereco(Endereco obj){
        objeto.getEnderecos().remove(obj);
        Util.mensagemInformacao("Endereco removido com sucesso !");
    }
    
    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }


    public EnderecoDAO<Endereco> getDaoEndereco() {
        return daoEndereco;
    }

    public void setDaoEndereco(EnderecoDAO<Endereco> daoEndereco) {
        this.daoEndereco = daoEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getEditandoEndereco() {
        return editandoEndereco;
    }

    public void setEditandoEndereco(Boolean editandoEndereco) {
        this.editandoEndereco = editandoEndereco;
    }
    
    

}
