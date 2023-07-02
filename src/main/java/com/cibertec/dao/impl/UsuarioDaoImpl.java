package com.cibertec.dao.impl;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	
	private static final String REGISTRO_OK = "Se registró correctamente el usuario";
	private static final String REGISTRO_ERROR = "No se registró el usuario";
	
	private static final String ACTUALIZA_OK = "Se actualizó correctamente el usuario";
	private static final String ACTUALIZA_ERROR = "No se actualizó el usuario";
	
	private static final String ELIMINA_OK = "Se eliminó correctamente el usuario";
	private static final String ELIMINA_ERROR = "No se eliminó el usuario";
			
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsuarioPU");
	EntityManager manager = factory.createEntityManager();
	
	
	public String registrarUsuario(Usuario usuario) {
		String mensaje = REGISTRO_ERROR;
		
		if(!Objects.isNull(usuario)) {
			manager.getTransaction().begin();
			manager.persist(usuario);//envia los datos a ejecucion nivel sql
			manager.getTransaction().commit(); //ejecuta
			mensaje = REGISTRO_OK;
			manager.close();//finaliza el registro
		}
		return mensaje;
	}
	
	private Usuario buscarUsuarioId(int id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario buscarUsuario(int id) {
		return buscarUsuario(id);
	}

	public List<Usuario> listarUsuarios() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();//especificar la query a realizar
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> registro = criteriaQuery.from(Usuario.class);
		CriteriaQuery<Usuario> todos = criteriaQuery.select(registro);
		TypedQuery<Usuario> lista = manager.createQuery(todos);

		return lista.getResultList();
	}

	public String actualizarUsuario(Usuario usuario) {
		String mensaje = ACTUALIZA_ERROR;
		Usuario objeto = buscarUsuarioId(usuario.getId());
		if(!Objects.isNull(objeto)) {
			manager.getTransaction().begin();
			objeto.setNombre(usuario.getNombre());
			objeto.setClave(usuario.getClave());
			objeto.setEstado(usuario.getEstado());	
			manager.getTransaction().commit(); //ejecuta			
			manager.close();//finaliza el actualizado
			mensaje = ACTUALIZA_OK;						
		}
		return mensaje;
	}

	public String eliminarUsuario(int id) {
		String mensaje = ELIMINA_ERROR;
		Usuario objeto = buscarUsuarioId(id);
		if(!Objects.isNull(objeto)) {
			manager.getTransaction().begin();
			manager.remove(objeto);
			manager.getTransaction().commit(); //ejecuta			
			manager.close();//finaliza la eliminacion
			mensaje = ELIMINA_OK;						
		}
		return mensaje;
	}

}
