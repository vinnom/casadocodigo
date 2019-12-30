package br.com.casadocodigo.loja.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

@Component
public class UsuarioValidation implements Validator{

  @Autowired
	private UsuarioDAO dao;

  @Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senhaConfirma", "field.required");

		Usuario usuario = (Usuario) target;
		System.out.println(usuario.getSenha());
		System.out.println(usuario.getSenhaConfirma());
		if(dao.existe(usuario)) {
      errors.rejectValue("email", "usuario.existe");
    }
		if(usuario.getSenha().length() < 5) {
			errors.rejectValue("senha", "senha.tamanho");
		} else if (!usuario.senhasConferem(usuario.getSenha(), usuario.getSenhaConfirma())) {
		  errors.rejectValue("senhaConfirma", "senha.nao.confere");
    }
	}
}
