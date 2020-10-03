package br.edu.mariaregina.agenda_dmos5.data;

import java.util.LinkedList;
import java.util.List;

import br.edu.mariaregina.agenda_dmos5.model.Contato;

public class ContatoDAO {
    private List<Contato> mContatoList;
    private static ContatoDAO sContatoDAO = null;

    private ContatoDAO(){
        mContatoList = new LinkedList<>();
    }

    public static ContatoDAO getInstante(){
        if(sContatoDAO == null){
            sContatoDAO = new ContatoDAO();
        }
        return sContatoDAO;
    }

    public void add(Contato contato) throws NullPointerException{
        if(contato == null){
            throw new NullPointerException("Contato inválido");
        }

        mContatoList.add(contato);
    }

    public List<Contato> all(){
        return mContatoList;
    }

}


