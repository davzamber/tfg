package proyecto.paquetes.Clientes.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import proyecto.paquetes.Clientes.DBFactory.DBFactory;
import proyecto.paquetes.Clientes.Entities.Clientes;



public class ClientesModel {
    
    Connection conexion = null;

    public ClientesModel() throws SQLException {
	DataSource ds = DBFactory.getMySQLDataSource();
	conexion = ds.getConnection();
    }

    public Clientes read(Integer id) {
	Clientes cliente = null;
	Statement sentencia = null;

	String sql = "SELECT `id`, `nombreCliente`, `direccionCliente`, `CIF`, "
			+ "`telefono`, `email`, `observaciones` " + "FROM clientes "
			+ "WHERE id = " + id;

	try {
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { 
		cliente = new Clientes(
			rs.getInt("id"),
			rs.getString("nombreCliente"),
			rs.getString("direccionCliente"),
			rs.getString("CIF"),
			rs.getString("telefono"),
			rs.getString("email"),
			rs.getString("observaciones"));
			};
	    
	} catch (SQLException e) {
	    System.err.println("Error en read de Clientes: " + e.getMessage());
	    return null;
	}

	return cliente;
    }

    /**
     * 
     * @param cliente
     * @return Devuelve el id del registro recien insertado
     */
    public Integer insert(Clientes cliente) throws  SQLException {
	Integer id = null;
	PreparedStatement ps = null;
	String sql = "INSERT INTO clientes ( "
			+ "`nombreCliente`, `direccionCliente`, `CIF`, "
			+ "`telefono`, `email`, `observaciones`) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	System.out.println(id);

	try {
	    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1, cliente.getNombreCliente());
	    ps.setString(2, cliente.getDireccionCliente());
	    ps.setString(3, cliente.getCIF());
	    ps.setString(4, cliente.getTelefono());
	    ps.setString(5, cliente.getEmail());
	    ps.setString(6, cliente.getObservaciones());

	    if (ps.executeUpdate() > 0) {
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getInt(1);
		}
	    }
	    
	    System.out.println(id);

	} catch (SQLException e) {
	    System.err.println("Error al insertar cliente: " + e.getMessage());
	    throw e;
	}

	return id;
    }

    public Boolean delete(Integer idcliente) throws SQLException {
	Boolean resultado = false;
	
	Statement stmt = conexion.createStatement();
    stmt.execute("SET FOREIGN_KEY_CHECKS=0");
    stmt.close();

	PreparedStatement ps = null;
	String sql = "DELETE FROM clientes where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);

	    ps.setInt(1, idcliente);

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al borrar cliente: " + e.getMessage());
	    throw e;
	}
	
	stmt = conexion.createStatement();
    stmt.execute("SET FOREIGN_KEY_CHECKS=1");
    stmt.close();

	return resultado;
    }

    public Boolean update(Clientes cliente) throws SQLException  {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "UPDATE clientes SET "
		+ "nombreCliente = ?, "
		+ "direccionCliente = ?, "
		+ "CIF = ?, "
		+ "telefono = ?, "
		+ "email = ?, "
		+ "observaciones = ? "
		+ "where id = ?";
	
	System.out.println(sql);
	try {
	    ps = conexion.prepareStatement(sql);
	    ps.setString(1, cliente.getNombreCliente());
	    ps.setString(2, cliente.getDireccionCliente());
	    ps.setString(3, cliente.getCIF());
	    ps.setString(4, cliente.getTelefono());
	    ps.setString(5, cliente.getEmail());
	    ps.setString(6, cliente.getObservaciones());
	    ps.setInt(7, cliente.getId());

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al actualizar cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public ArrayList<Clientes> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<Clientes> clientes = new ArrayList<Clientes>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "
		+ "`nombreCliente` , "
		+ "`direccionCliente`, "
		+ "`CIF`, "
		+ "`telefono`, "
		+ "`email`, "
		+ "`observaciones` " 
		+ " FROM `clientes` ";
	

	try {
	    if (filtro != null)
		sql += " WHERE " + filtro;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si todavía hay un cliente lo añado al array
		clientes.add(new Clientes(
				rs.getInt("id"),
				rs.getString("nombreCliente"),
				rs.getString("direccionCliente"),
				rs.getString("CIF"),
				rs.getString("telefono"),
				rs.getString("email"),
				rs.getString("observaciones")));
				};
	  
	} catch (SQLException e) {
	    System.err.println("Error en leer los clientes: " + e.getMessage());
	    return null;
	}

	return clientes;
    }

}
