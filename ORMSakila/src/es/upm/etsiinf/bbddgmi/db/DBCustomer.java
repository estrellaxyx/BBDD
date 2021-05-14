package es.upm.etsiinf.bbddgmi.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import es.upm.etsiinf.bbddgmi.modelo.Customer;

public class DBCustomer {

	public static Customer getCostumerById(int customerId) {
		Customer result = null; // Objeto a devolver

		String sql = "SELECT * FROM customer WHERE customer_id = ?;";

		try {
			PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(sql);
			pst.setInt(1, customerId);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				File image = null;
				InputStream is = rs.getBinaryStream("image");

				if (is != null) {
					image = new File("descarga" + rs.getInt("customer_id") + ".jpg");
					FileOutputStream fos = new FileOutputStream(image);
					System.out.println("Escribiendo en: " + image.getAbsolutePath());
					byte[] buffer = new byte[1024];

					while (is.read(buffer) > 0) {
						fos.write(buffer);
					}
				}

				result = new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("email"), rs.getInt("active"), DBAddress.getAddressById(rs.getInt("address_id")),
						rs.getObject("create_date", LocalDateTime.class), image);
			}

			//rs.close();
			//pst.close();
			//DBConnectionManager.closeConnection();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void updateCustomerImage(Customer c) {
		try {
			PreparedStatement pst = DBConnectionManager.getConnection()
					.prepareStatement("UPDATE customer SET image = ? WHERE customer_id = ?;");

			if (c.getImage() == null) {
				pst.setObject(1, null);
			} else {
				FileInputStream fis = new FileInputStream(c.getImage());
				pst.setBinaryStream(1, fis, c.getImage().length());
			}
			pst.setInt(2, c.getCustomerId());

			int numRows = pst.executeUpdate(); // Devuelvo el n�mero de filas afectadas

			if (numRows < 1) {
				System.out.println("No se ha guardado la imagen para el cliente " + c.getCustomerId());
			}

			pst.close();
			DBConnectionManager.closeConnection();

		} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
