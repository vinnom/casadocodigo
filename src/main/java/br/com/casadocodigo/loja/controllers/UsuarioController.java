package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioDAO usuarioDao;

  @Autowired
  private RoleDAO roleDao;

  @Autowired
  private UsuarioValidation validaUsuario;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(validaUsuario);
  }

  @RequestMapping("/form")
  public ModelAndView form(Usuario usuario) {
    ModelAndView modelAndView = new ModelAndView("usuarios/form");
    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
      return form(usuario);
    }

    String senhaCripto = passwordEncoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCripto);
    usuarioDao.gravar(usuario);

    String sucesso = "Usuário " + usuario.getNome() + " cadastrado com sucesso!";
    redirectAttributes.addFlashAttribute("sucesso", sucesso);

    return new ModelAndView("redirect:/usuarios");
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView listar() {
    List<Usuario> usuarios = usuarioDao.listar();
    ModelAndView modelAndView = new ModelAndView("usuarios/lista");
    modelAndView.addObject("usuarios", usuarios);
    return modelAndView;
  }

  @RequestMapping(value = "/editar", method = RequestMethod.GET)
  public ModelAndView editarForm(@RequestParam String email) {
    ModelAndView mav = new ModelAndView("/usuarios/editar");

    Usuario u = usuarioDao.loadUserByUsername(email);
    mav.addObject("u", u);

    List<Role> listaRoles = roleDao.listar();
    mav.addObject("listaRoles", listaRoles);
    mav.addObject("roles", u.getRoles());

    return mav;
  }
  
  @RequestMapping(value = "/atualizar" , method = RequestMethod.POST)
  public ModelAndView atualizar(String email, @RequestParam ArrayList<Role> roles, 
      RedirectAttributes redirectAttributes) {
    
    Usuario usuario = usuarioDao.loadUserByUsername(email);
    
    usuarioDao.atualizar(usuario, roles);
    
    String sucesso = "Permissões alteradas com sucesso!";
    redirectAttributes.addFlashAttribute("sucesso", sucesso);
    
    return new ModelAndView("redirect:/usuarios");
  }
}