package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import util.Conexao;
import util.Constante;

public class InconsistenciasController {

	// inconsistências encontradas durante o teste
	@FXML
	private TextArea txtInconsistencias;

	public void gravaInconsistencia() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Connection conn = Conexao.getConexao();
				String sql = "Insert into inconsistencia (nrlaudo, inconsistencias) values (?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, txtInconsistencias.getText());
				ps.executeUpdate();
				conn.close();
//			}	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void mostraAlert(String msg) {
		Alert a = new Alert(AlertType.WARNING);
		a.setHeaderText(null);
		a.setContentText(msg);
		a.show();
	}

	public String txtInconsistencia(String nrLaudo) {
		String dadosInconsistencias = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from inconsistencia where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosInconsistencias += rs.getString("inconsistencias");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "INCONSISTÊNCIAS: " + dadosInconsistencias;
	}

}