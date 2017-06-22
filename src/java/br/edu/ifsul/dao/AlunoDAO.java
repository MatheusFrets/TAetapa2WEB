/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Matheus
 * @email omatheusfreitas@gmail.com
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class AlunoDAO<T> extends DAOGenerico<Aluno> implements Serializable {

    public AlunoDAO(){
        super();        
        super.classePersistente = Aluno.class;
    }
    
    @Override
    public Aluno getObjectById(Integer id) throws Exception {
        Aluno obj = (Aluno) em.find(classePersistente, id);
        obj.getEnderecos().size(); // inicializa a lista de enderecos do usuário
        return obj;
    }
    
    public Aluno localizaPorNomeAluno(String aluno){
        Aluno obj = (Aluno) em.createQuery("from Aluno where upper(aluno) = :aluno").
                setParameter("aluno", aluno.toUpperCase()).getSingleResult();
        obj.getEnderecos().size();
        return obj;
    }
    
}
