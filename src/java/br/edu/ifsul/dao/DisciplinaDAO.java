/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Disciplina;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Matheus
 * @email omatheusfreitas@gmail.com
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class DisciplinaDAO<T> extends DAOGenerico<Disciplina> implements Serializable{

    public DisciplinaDAO() {
        super();
        super.classePersistente = Disciplina.class;
        ordem = "nome";
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }

    @Override   
    public List<Disciplina> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            if (ordem.equals("id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery("select id from " + classePersistente.getSimpleName()
                + where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
}
