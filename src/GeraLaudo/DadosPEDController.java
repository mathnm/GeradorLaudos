package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import util.Conexao;
import util.Constante;

public class DadosPEDController {

	// bloco de variáveis com dados do sistema PED (sexta tela)
	@FXML
	private TextField txtPedRazao;
	@FXML
	private TextField txtPedCnpj;
	@FXML
	private TextField txtPedNome;
	@FXML
	private TextField txtPedExecutavel;
	@FXML
	private TextField txtPedMd5;
	@FXML
	private TextField txtPedFuncao;
	@FXML
	private RadioButton rdTipoPed;
	@FXML
	private RadioButton rdTipoPedNfe;

	public void cadastraPed() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().isEmpty()) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Ped p = new Ped();
				p.setRazao(txtPedRazao.getText());
				p.setCnpj(txtPedCnpj.getText());
				p.setNome(txtPedNome.getText());
				p.setExecutavel(txtPedExecutavel.getText());
				p.setMd5(txtPedMd5.getText());
				p.setFuncao(txtPedFuncao.getText());
				if (rdTipoPed.isSelected()) {
					p.setTipo("PED");
				} else {
					p.setTipo("NF-e");
				}

				Connection conn = Conexao.getConexao();
				String sql = "Insert into ped (nrlaudo, desenvolvedor, cnpj, nome, executavel, md5_exe, funcao, tipo)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, p.getRazao());
				ps.setString(3, p.getCnpj());
				ps.setString(4, p.getNome());
				ps.setString(5, p.getExecutavel());
				ps.setString(6, p.getMd5());
				ps.setString(7, p.getFuncao());
				ps.setString(8, p.getTipo());
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
	

	public String txtPed(String nrLaudo) {
		String dadosPed = "";
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from ped where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dadosPed += rs.getString("desenvolvedor") + " | " + rs.getString("cnpj") + " | "
						+ rs.getString("executavel") + " | " + rs.getString("md5_exe") + " | " + rs.getString("funcao")
						+ " | Tipo: " + rs.getString("tipo") + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "SISTEMA PED: " + dadosPed;
	}

}