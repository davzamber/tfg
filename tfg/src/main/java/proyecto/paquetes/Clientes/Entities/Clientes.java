package proyecto.paquetes.Clientes.Entities;


import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id","nombreCliente","direccionCliente","CIF","telefono","email","observaciones"})
public class Clientes {
	private Integer id;
    private String nombreCliente;
    private String direccionCliente;
    private String CIF;
    private String telefono;
    private String email;
    private String observaciones;
	
    public Clientes() {	}

	public Clientes(Integer id, String nombreCliente, String direccionCliente, String CIF, String telefono, String email, String observaciones) {

		this.id = id;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.CIF = CIF;
        this.telefono = telefono;
        this.email = email;
        this.observaciones = observaciones;
        
	}

		public Integer getId() {
		return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
			}
	 
	    public String getNombreCliente() {
	        return nombreCliente;
	    }
	 
	    public void setNombreCliente(String nombreCliente) {
	        this.nombreCliente = nombreCliente;
	    }
	 
	    public String getDireccionCliente() {
	        return direccionCliente;
	    }
	    
	    public void setDireccionCliente(String direccionCliente) {
	        this.direccionCliente = direccionCliente;
	    }
	 
	    public String getCIF() {
	        return CIF;
	    }
	    
	    public void setCIF(String CIF) {
	        this.CIF = CIF;
	    }
	 
	 
	    public String getTelefono() {
	        return telefono;
	    }
	 
	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }
	 
	    public String getEmail() {
	        return email;
	    }
	 
	    public void setEmail(String email) {
	        this.email = email;
	    }
	 
	    public String getObservaciones() {
	        return observaciones;
	    }
	 
	    public void setObservaciones(String observaciones) {
	        this.observaciones = observaciones;
	    }

	@Override
	public String toString() {
		return "Clientes [" + (id != null ? "id=" + id + ", " : "")
				+ (nombreCliente != null ? "nombreCliente=" + nombreCliente + ", " : "")
				+ (direccionCliente != null ? "direccionCliente=" + direccionCliente + ", " : "")
				+ (CIF != null ? "CIF=" + CIF + ", " : "")
				+ (telefono != null ? "telefono=" + telefono + ", " : "")
				+ (email != null ? "email=" + email : "") + "]";
    }

}



