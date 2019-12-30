package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService {

  @PersistenceContext
  private EntityManager manager;

  public Usuario loadUserByUsername(String email) {
    Usuario usuario = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
        .setParameter("email", email).getSingleResult();

    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
    }

    return usuario;
  }

  public void gravar(Usuario usuario) {
    manager.persist(usuario);
  }

  public boolean existe(Usuario usuario) {
    List<Usuario> usuarios = listar();

    for (Usuario usuarioAux : usuarios) {
      if (usuarioAux.equals(usuario)) {
        return true;
      }
    }
    return false;
  }

  public List<Usuario> listar() {
    return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
  }
}