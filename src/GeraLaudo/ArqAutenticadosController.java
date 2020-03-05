package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class ArqAutenticadosController {

	// bloco de variáveis com dados MD5 (sétima tela)
	@FXML
	private TextArea txtArqAutenticados;
	@FXML
	private TextField txtArquivo;
	@FXML
	private TextField txtMd5Cupom;
	@FXML
	private TextField txtExecutavel;
	@FXML
	private TextField txtMd5Executavel;

	public void gravaArquivos() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {			
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Connection conn = Conexao.getConexao();
				String sql = "Insert into arquivo (nrlaudo, md5_arquivos, arqtxt, md5cupom, exe_principal, md5_principal)"
					+ " values (?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, txtArqAutenticados.getText());
				ps.setString(3, txtArquivo.getText());
				ps.setString(4, txtMd5Cupom.getText());
				ps.setString(5, txtExecutavel.getText());
				ps.setString(6, txtMd5Executavel.getText());
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
	
	public String txtArquivos(String nrLaudo) {
		String dadosArquivos = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from arquivo where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosArquivos += rs.getString("md5_arquivos") + " | Txt: " + rs.getString("arqtxt") + " | MD5 Cupom: "
						+ rs.getString("md5cupom") + " | Executável principal: " + rs.getString("exe_principal")
						+ " | MD5 Executável: " + rs.getString("md5_principal") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ARQIVOS AUTENTICADOS: " + dadosArquivos;
	}

}