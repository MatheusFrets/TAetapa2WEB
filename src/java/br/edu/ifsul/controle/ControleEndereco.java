/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EnderecoDAO;
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
@Named(value = "controleEndereco")
@SessionScoped
public class ControleEndereco implements Serializable {
    @EJB
    private EnderecoDAO<Endereco> dao;
    private Endereco objeto;
    private Boolean editando;
    private Boolean novoObjeto;
    
    public ControleEndereco(){        
        editando = false;
        novoObjeto = false;
    }
    
    public String listar(){
        editando = false;
        novoObjeto = false;
        return "/privado/endereco/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Endereco();
        novoObjeto = true;
    }
    
    public void alterar(String rua){
      try {
            objeto = dao.getObjectById(rua); 
            editando = true;
            novoObjeto = false;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }                
        
    }
    
    public void excluir(String rua){
       try {
            objeto = dao.getObjectById(rua);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {                  
            if (novoObjeto){                
                dao.persist(objeto);            
            } else {                
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");  
            editando = false;        
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir: "+Util.getMensagemErro(e));            
        }                
    }
    
    public Endereco getObjeto() {
        return objeto;
    }

    public void setObjeto(Endereco objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public EnderecoDAO<Endereco> getDao() {
        return dao;
    }

    public void setDao(EnderecoDAO<Endereco> dao) {
        this.dao = dao;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }
}
