package bomberman;

public class DatosUsuario {

		private String nombre;
		private String password;
		
		public DatosUsuario() {
			
		}
	
		public boolean probarPassword() {
			
			nombre = pantallaIngreso.textUsuario.getText();
			password = pantallaIngreso.textPassword.getText();
			
			if ( nombre.equals("Lucas") && password.equals("lucaslucas") )
			
				return true;
			
			return false;
			
		} 
	
}
