package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class ECFsController {

	// bloco de variáveis com dados das ECF utilizáveis pelo PAF (quinta tela)
	@FXML
	private CheckBox ckBematech;
	@FXML
	private CheckBox ckDaruma;
	@FXML
	private CheckBox ckElgin;
	@FXML
	private CheckBox ckEpson;
	@FXML
	private CheckBox ckSweda;
	@FXML
	private CheckBox ckZPM;
	@FXML
	private TextField txtEcfMarca;
	@FXML
	private TextField txtEcfModelo;

	public String bematech, daruma, elgin, epson, sweda, zpm;

	public void cadastraEcf() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Ecf e = new Ecf();
				e.setMarca(txtEcfMarca.getText());
				e.setModelo(txtEcfModelo.getText());
				if (ckBematech.isSelected()) {
					bematech = "Sim";
				} else {
					bematech = "Não";
				}
				if (ckDaruma.isSelected()) {
					daruma = "Sim";
				} else {
					daruma = "Não";
				}
				if (ckElgin.isSelected()) {
					elgin = "Sim";
				} else {
					elgin = "Não";
				}
				if (ckEpson.isSelected()) {
					epson = "Sim";
				} else {
					epson = "Não";
				}
				if (ckSweda.isSelected()) {
					sweda = "Sim";
				} else {
					sweda = "Não";
				}
				if (ckZPM.isSelected()) {
					zpm = "Sim";
				} else {
					zpm = "Não";
				}
		
				Connection conn = Conexao.getConexao();
				String sql = "Insert into ecf (nrlaudo, bematech, daruma, elgin, epson, sweda, zpm, marca, modelo)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, bematech);
				ps.setString(3, daruma);
				ps.setString(4, elgin);
				ps.setString(5, epson);
				ps.setString(6, sweda);
				ps.setString(7, zpm);
				ps.setString(8, e.getMarca());
				ps.setString(9, e.getModelo());
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

	public String txtEcf(String nrLaudo) {
		String dadosEcf = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from ecf where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosEcf += "Bematech: " + rs.getString("bematech") + " | Daruma: " + rs.getString("daruma")
						+ " | Elgin: " + rs.getString("elgin") + " | Epson: " + rs.getString("epson") + " | Sweda: "
						+ rs.getString("sweda") + " | ZPM: " + rs.getString("zpm") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ECFs: " + dadosEcf;
	}

}