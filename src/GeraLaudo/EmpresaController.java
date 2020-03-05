package GeraLaudo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import util.Conexao;
import util.Constante;

public class EmpresaController {

	// bloco de variáveis dados da EMPRESA (primeira tela)
	@FXML
	private TextField txtRazao;
	@FXML
	private TextField txtCnpj;
	@FXML
	private TextField txtInscEstad;
	@FXML
	private TextField txtEndRua;
	@FXML
	private TextField txtEndNumero;
	@FXML
	private TextField txtEndComplemento;
	@FXML
	private TextField txtEndBairro;
	@FXML
	private TextField txtCidade;
	@FXML
	private TextField txtUF;
	@FXML
	private TextField txtCEP;
	@FXML
	private TextField txtContato;
	@FXML
	private TextField txtCPF;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtFAX;
	@FXML
	private TextField txtEmail;

	public void cadastraEmpresa() {
		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//			loader.load();
//			SampleController controller = loader.getController();
//			if(controller.txtNrLaudo.getText().equals("")) {
//				mostraAlert("Informe o número do laudo!");
//			}else {	
				int numeroLaudo = Integer.parseInt(Constante.nrLaudo);
				Empresa e = new Empresa();
				e.setRazao(txtRazao.getText());
				e.setCnpj(txtCnpj.getText());
				e.setInscricaoEstadual(txtInscEstad.getText());
				e.setRua(txtEndRua.getText());
				e.setNroEnd(txtEndNumero.getText());
				if (txtEndComplemento.getText().isEmpty()) {
					e.setComplemento("");
				} else {
					e.setComplemento(txtEndComplemento.getText());
				}
				e.setBairro(txtEndBairro.getText());
				e.setCidade(txtCidade.getText());
				e.setUf(txtUF.getText());
				e.setCep(txtCEP.getText());
				e.setContato(txtContato.getText());
				e.setCpfContato(txtCPF.getText());
				e.setTelefone(txtTelefone.getText());
				e.setFax(txtFAX.getText());
				e.setEmail(txtEmail.getText());
	
				Connection conn = Conexao.getConexao();
				String sql = "Insert into empresa (nrlaudo, razao, cnpj, inscricao_estadual, rua, numero, complemento, bairro, cidade, uf, cep, contato, cpf_contato, telefone, fax, email)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, numeroLaudo);
				ps.setString(2, e.getRazao());
				ps.setString(3, e.getCnpj());
				ps.setString(4, e.getInscricaoEstadual());
				ps.setString(5, e.getRua());
				ps.setString(6, e.getNroEnd());
				ps.setString(7, e.getComplemento());
				ps.setString(8, e.getBairro());
				ps.setString(9, e.getCidade());
				ps.setString(10, e.getUf());
				ps.setString(11, e.getCep());
				ps.setString(12, e.getContato());
				ps.setString(13, e.getCpfContato());
				ps.setString(14, e.getTelefone());
				ps.setString(15, e.getFax());
				ps.setString(16, e.getEmail());
				ps.executeUpdate();
				conn.close();
			//}	
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

	/*
	 * método para capturar os dados do BD e preencher os campos do gerador (não
	 * funcionando pois não atualiza visualmente, os dados estão sendo corretamente
	 * capturados
	 */
	public void carregaEmpresa(String nrLaudo) {
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from empresa where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				txtRazao.setText(rs.getString("razao"));
				txtCnpj.setText(rs.getString("cnpj"));
				txtInscEstad.setText(rs.getString("inscricao_estadual"));
				txtEndRua.setText(rs.getString("rua"));
				txtEndNumero.setText(rs.getString("numero"));
				txtEndBairro.setText(rs.getString("bairro"));
				txtEndComplemento.setText(rs.getString("complemento"));
				txtCidade.setText(rs.getString("cidade"));
				txtUF.setText(rs.getString("uf"));
				txtCEP.setText(rs.getString("cep"));
				txtContato.setText(rs.getString("contato"));
				txtCPF.setText(rs.getString("cpf_contato"));
				txtTelefone.setText(rs.getString("telefone"));
				txtFAX.setText(rs.getString("fax"));
				txtEmail.setText(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String txtEmpresa(String nrLaudo) {
		try {
			Connection conn = Conexao.getConexao();
			String sql = "Select * from empresa where nrlaudo = " + nrLaudo;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				txtRazao.setText(rs.getString("razao"));
				txtCnpj.setText(rs.getString("cnpj"));
				txtInscEstad.setText(rs.getString("inscricao_estadual"));
				txtEndRua.setText(rs.getString("rua"));
				txtEndNumero.setText(rs.getString("numero"));
				txtEndBairro.setText(rs.getString("bairro"));
				txtEndComplemento.setText(rs.getString("complemento"));
				txtCidade.setText(rs.getString("cidade"));
				txtUF.setText(rs.getString("uf"));
				txtCEP.setText(rs.getString("cep"));
				txtContato.setText(rs.getString("contato"));
				txtCPF.setText(rs.getString("cpf_contato"));
				txtTelefone.setText(rs.getString("telefone"));
				txtFAX.setText(rs.getString("fax"));
				txtEmail.setText(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DADOS DA EMPRESA: " + txtRazao.getText() + " | " + txtCnpj.getText() + " | " + txtInscEstad.getText()
				+ " | " + txtEndRua.getText() + " | " + txtEndNumero.getText() + " | " + txtEndBairro.getText() + " | "
				+ txtEndComplemento.getText() + " | " + txtCidade.getText() + " | " + txtUF.getText() + " | "
				+ txtCEP.getText() + " | " + txtContato.getText() + " | " + txtCPF.getText() + " | "
				+ txtTelefone.getText() + " | " + txtFAX.getText() + " | " + txtEmail.getText() + "\n";

	}
}