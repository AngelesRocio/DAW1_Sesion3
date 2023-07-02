package com.cibertec.dao;

import java.util.List;
import com.cibertec.model.Usuario;

public interface UsuarioDao {
	
	public String registrarUsuario(Usuario usuario);
	public Usuario buscarUsuario(int id);
	public List<Usuario> listarUsuarios();
	public String actualizarUsuario(Usuario usuario);
	public String eliminarUsuario(int id);

}
