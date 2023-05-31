package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.table.DefaultTableModel;

import controller.HospedeController;
import controller.ReservaController;
import model.Hospede;
import model.Reserva;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private ReservaController reservaController;
	private HospedeController hospedeController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Buscar() throws SQLException {
		this.reservaController = new ReservaController();
		this.hospedeController = new HospedeController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String entrada = txtBuscar.getText();
				try {
					switch (validarInput(entrada)) {
					case 0:
						LimparReservas();
						preencherTabelaReservas();
						LimparHospedes();
						preencherTabelaHospedes();
						break;
					case 1:
						LimparReservas();
						resultadoPorId(entrada);
						break;
					case 2:
						LimparHospedes();
						resultadoPorSobrenome(entrada);
						break;
					}
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 1) {

					int linhaSelecionada = tbHospedes.getSelectedRow();
					DefaultTableModel modeloHospede = (DefaultTableModel) tbHospedes.getModel();

					int id = Integer.parseInt(modeloHospede.getValueAt(linhaSelecionada, 0).toString());
					String nome = modeloHospede.getValueAt(linhaSelecionada, 1).toString();
					String sobrenome = modeloHospede.getValueAt(linhaSelecionada, 2).toString();
					String nacionalidade = modeloHospede.getValueAt(linhaSelecionada, 4).toString();
					String telefone = modeloHospede.getValueAt(linhaSelecionada, 5).toString();
					try {
						hospedeController.atualizar(id, nome, sobrenome, nacionalidade, telefone);
						JOptionPane.showMessageDialog(contentPane, "Dados do hopsede foram editados com sucesso");
					} catch (SQLException e1) {
						new RuntimeException(e1);
						JOptionPane.showMessageDialog(contentPane, "Houve alguma coisa de errado a o editar o hospede, lembre-se de apertar enter depois de editar os dados");
					}
				} else {
					
					int linhaSelecionada = tbReservas.getSelectedRow();
					DefaultTableModel modeloReserva = (DefaultTableModel) tbReservas.getModel();

					int id = Integer.parseInt(modeloReserva.getValueAt(linhaSelecionada, 0).toString());
					java.sql.Date dataEntrada = (Date) modeloReserva.getValueAt(linhaSelecionada, 1);
					java.sql.Date dataSaida = (Date) modeloReserva.getValueAt(linhaSelecionada, 2);
					String valor = modeloReserva.getValueAt(linhaSelecionada, 4).toString();
					String formaPagamento = modeloReserva.getValueAt(linhaSelecionada, 5).toString();
					try {
						reservaController.atualizar(id, dataEntrada, dataSaida, valor, formaPagamento);
						JOptionPane.showMessageDialog(contentPane, "Dados da reserva foram editados com sucesso");
					} catch (SQLException e1) {
						new RuntimeException(e1);
						JOptionPane.showMessageDialog(contentPane, "Houve alguma coisa de errado a o editar o hospede, lembre-se de apertar enter depois de editar os dados");
					}

				}

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MenuEvent e) {
				if (panel.getSelectedIndex() == 1) {
					
					int linhaSelecionada = tbHospedes.getSelectedRow();
					try {
						hospedeController.deletar(linhaSelecionada);
						JOptionPane.showMessageDialog(contentPane, "Hospede deletado com sucesso");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(contentPane, "Houve algum erro a o deletar o hospede");
						throw new RuntimeException(e1);
					}
				} else {
					int linhaSelecionada = tbReservas.getSelectedRow();
					try {
						reservaController.deletar(linhaSelecionada);
						JOptionPane.showMessageDialog(contentPane, "Reserva deletada com sucesso");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(contentPane, "Houve algum erro a o deletar a reserva");
						throw new RuntimeException(e1);
					}
				}
			}
		});
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private List<Reserva> buscarReservas() throws SQLException {
		return this.reservaController.buscar();
	}

	private void preencherTabelaReservas() throws SQLException {
		List<Reserva> reservaLista = buscarReservas();

		for (Reserva reserva : reservaLista) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(),
					reserva.getValor(), reserva.getFormaPagamento() });
		}
	}

	private List<Reserva> buscarReservaPorId(String id) throws SQLException {
		return this.reservaController.buscarId(id);
	}

	private List<Reserva> resultadoPorId(String id) throws SQLException {
		List<Reserva> reservaLista = buscarReservaPorId(id);

		for (Reserva reserva : reservaLista) {
			modelo.addRow(new Object[] { reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(),
					reserva.getFormaPagamento(), reserva.getValor() });
		}
		return reservaLista;
	}

	private List<Hospede> buscarHospedes() throws SQLException {
		return ((HospedeController) this.hospedeController).buscar();
	}

	private List<Hospede> preencherTabelaHospedes() throws SQLException {
		List<Hospede> hospedeLista = buscarHospedes();

		for (Hospede hospede : hospedeLista) {
			modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
					hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
					hospede.getIdReserva() });
		}
		return hospedeLista;
	}

	private List<Hospede> buscarHospedePorSobrenome(String sobrenome) throws SQLException {
		return ((HospedeController) this.hospedeController).buscarSobrenome(sobrenome);
	}

	private List<Hospede> resultadoPorSobrenome(String sobrenome) throws SQLException {
		List<Hospede> hospedeLista = buscarHospedePorSobrenome(sobrenome);

		for (Hospede hospede : hospedeLista) {
			modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
					hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
					hospede.getIdReserva() });
		}
		return hospedeLista;
	}

	public int validarInput(String entrada) {
		char[] caractrs = entrada.toCharArray();

		for (char c : caractrs) {
			if (!Character.isLetter(c)) {
				return 1;
			} else {
				return 2;
			}
		}
		return 0;
	}

	private void LimparReservas() {
		modelo.getDataVector().clear();
	}

	private void LimparHospedes() {
		modeloHospedes.getDataVector().clear();
	}
}
