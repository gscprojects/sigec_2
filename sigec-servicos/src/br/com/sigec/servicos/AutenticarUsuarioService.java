package br.com.sigec.servicos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.sigc.servicos.dao.AutenticacaoDAO;


/* WEB SERVICE REST PARA AUTENTICACAO DE USUARIOS */
@Path("/autenticacaoservice")
public class AutenticarUsuarioService {

	// METODO DO WEB SERVICE RESPONSAVEL PELA AUTENTICACAO DO USUARIO
	// PARAMETROS DE ENTRADA EMAIL E SENHA
	
	@Path("{autenticar}")
	@GET
	@Produces("application/json")
	public Response autenticarUsuario(
			@QueryParam("email") String email,
			@QueryParam("senha") String senha
			) throws JSONException {
		
		// OBJETO JSON PARA RETORNO DO WEBSERVICE
		JSONObject jsonObject = new JSONObject();
		
		// CRIA INSTANCIA DAO PARA VALIDAR AS INFOS COM A BASE DE DADOS MYSQL
		AutenticacaoDAO dao = new AutenticacaoDAO();
		
		// RETORNA O NOME DO USUARIO CASO ESTE SEJA AUTENTICADO
		String retorno = dao.autenticar(email, senha);
		String result = "0";
		
		
		if(retorno != "" && retorno != "0"){
			
			// SET O NOME DO USUARIO AUTENTICADO NO OBJETO JSON
			jsonObject.put("nome", retorno);
			
			// VERIFICA SE O EMAIL PERTENCE A UM DOS ADMINISTRADORES DO CURSO
			if (email.equals("maria@gmail.com") || email.equals("gabriel@gmail.com") ){
				
				jsonObject.put("tipo", "Adminstrador(a)");
			
			// CASO NAO SEJA ADMINISTRADOR O TIPO DE USUARIO É ALUNO
			}else{
			
				jsonObject.put("tipo", "Aluno(a)");
				jsonObject.put("email", email);
				jsonObject.put("senha", senha);
				
				
			}
			result = jsonObject.toString();	
		}
		
		// RETORNO DA CHAMADA DO WEB SERVICE REST PARA O APLICATIVO CLIENTE
		return Response.status(200).entity(result).build();
	}
	
}
