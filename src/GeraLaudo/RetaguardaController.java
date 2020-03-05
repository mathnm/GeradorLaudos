package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class RetaguardaController {

	// bloco de variáveis com dados do sistema de retaguarda (sexta tela)
	@FXML
	private TextField txtSgRazao;
	@FXML
	private TextField txtSgCnpj;
	@FXML
	private TextField txtSgNome;
	@FXML
	private TextField txtSgExecutavel;
	@FXML
	private TextField txtSgMd5;
	@FXML
	private TextField txtSgRequisitos;

	public void cadastraRetaguarda() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Retaguarda r = new Retaguarda();
				r.setRazao(txtSgRazao.getText());
				r.setCnpj(txtSgCnpj.getText());
				r.setNome(txtSgNome.getText());
				r.setExecutavel(txtSgExecutavel.getText());
				r.setMd5(txtSgMd5.getText());
				r.setRequisitos(txtSgRequisitos.getText());
	
				Connection conn = Conexao.getConexao();
				String sql = "Insert into retaguarda (nrlaudo, nome, desenvolvedor, cnpj, executavel, md5_exe, requisitos)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, r.getNome());
				ps.setString(3, r.getRazao());
				ps.setString(4, r.getCnpj());
				ps.setString(5, r.getExecutavel());
				ps.setString(6, r.getMd5());
				ps.setString(7, r.getRequisitos());
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
	public String txtRetaguarda(String nrLaudo) {
		String dadosRetaguarda = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from retaguarda where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosRetaguarda += rs.getString("nome") + " | " + rs.getString("desenvolvedor") + " | "
						+ rs.getString("cnpj") + " | " + rs.getString("executavel") + " | " + rs.getString("md5_exe")
						+ " | " + rs.getString("requisitos") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SISTEMA DE RETAGUARDA: " + dadosRetaguarda;
	}

}