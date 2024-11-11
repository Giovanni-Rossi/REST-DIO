package com.academia.ginastica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.academia.ginastica.dao.*;
import com.academia.ginastica.dao.IAlunoDAO;
import com.academia.ginastica.domain.Aluno;

 
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IAlunoDAO dao;

    @Override
    public UserDetails loadUserByUsername(String CPF)
            throws UsernameNotFoundException {
        Aluno aluno = dao.findByCPF(CPF);

        if (aluno != null) {
            //return new alunoDetails(cliente);
        }


        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
