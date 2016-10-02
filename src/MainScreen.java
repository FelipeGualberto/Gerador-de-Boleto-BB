import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainScreen {

	private JFrame frame;
	private JTextField textNumeroDoc;
	private JTextField textValorDoc;
	private JTextField textInstrucoes;
	private JTextField textSacado;
	private JDatePickerImpl datePicker_vencimento;
	private JDatePickerImpl datePicker_dataDocumento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createMainScreen();
		//---------------------------------------------------------------------------------------------------------//		
		textNumeroDoc = new JTextField();
		textNumeroDoc.setText("0");
		textNumeroDoc.setToolTipText("Numero de Documento");
		textNumeroDoc.setBounds(267, 95, 207, 24);
		frame.getContentPane().add(textNumeroDoc);
		textNumeroDoc.setColumns(10);

		JLabel lblNumeroDocumento = new JLabel("N\u00FAmero do Documento");
		lblNumeroDocumento.setBounds(267, 70, 130, 14);
		frame.getContentPane().add(lblNumeroDocumento);
		//---------------------------------------------------------------------------------------------------------//		
		JLabel lblDataDoDocumento = new JLabel("Data do Documento");
		lblDataDoDocumento.setBounds(10, 70, 130, 14);
		frame.getContentPane().add(lblDataDoDocumento);

		JPanel panel_dataDocumento = new JPanel();
		panel_dataDocumento.setBackground(Color.WHITE);
		panel_dataDocumento.setBounds(10, 89, 207, 33);
		datePicker_dataDocumento = createDatePickerDataDocumento();
		panel_dataDocumento.add(datePicker_dataDocumento);
		frame.getContentPane().add(panel_dataDocumento);
		//---------------------------------------------------------------------------------------------------------//		


		JLabel labelDataDocumento = new JLabel("Vencimento");
		labelDataDocumento.setBounds(10, 129, 130, 14);
		frame.getContentPane().add(labelDataDocumento);

		JPanel panel_vencimento = new JPanel();
		panel_vencimento.setBounds(10, 154, 207, 33);
		panel_vencimento.setBackground(Color.WHITE);
		datePicker_vencimento = createDatePickerVencimento();
		panel_vencimento.add(datePicker_vencimento);
		frame.getContentPane().add(panel_vencimento);

		textValorDoc = new JTextField();
		textValorDoc.setText("0");
		textValorDoc.setToolTipText("Valor do Documento");
		textValorDoc.setColumns(10);
		textValorDoc.setBounds(267, 163, 207, 24);
		frame.getContentPane().add(textValorDoc);

		JLabel labelValorDocumento = new JLabel("Valor do Documento");
		labelValorDocumento.setBounds(267, 138, 130, 14);
		frame.getContentPane().add(labelValorDocumento);

		textInstrucoes = new JTextField();
		textInstrucoes.setText(" ");
		textInstrucoes.setToolTipText("Instru\u00E7\u00F5es");
		textInstrucoes.setColumns(10);
		textInstrucoes.setBounds(10, 223, 464, 62);
		frame.getContentPane().add(textInstrucoes);

		JLabel lblInstrues = new JLabel("Instru\u00E7\u00F5es:");
		lblInstrues.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstrues.setBounds(210, 198, 130, 14);
		frame.getContentPane().add(lblInstrues);

		textSacado = new JTextField();
		textSacado.setText(" ");
		textSacado.setToolTipText("Sacado");
		textSacado.setColumns(10);
		textSacado.setBounds(10, 318, 464, 62);
		frame.getContentPane().add(textSacado);

		JLabel lblSacado = new JLabel("Sacado:");
		lblSacado.setHorizontalAlignment(SwingConstants.LEFT);
		lblSacado.setBounds(210, 293, 130, 14);
		frame.getContentPane().add(lblSacado);

		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createPDF();
			}
		});
		btnNewButton.setBounds(194, 391, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 484, 54);
		frame.getContentPane().add(panel);

		JLabel lbl_Main = new JLabel("GERADOR DE BOLETO");
		lbl_Main.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lbl_Main);
		//---------------------------------------------------------------------------------------------------------//
	}

	private JDatePickerImpl createDatePickerDataDocumento(){
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		return datePicker;
	}

	private JDatePickerImpl createDatePickerVencimento(){ // Método igual ao de cima, criado apenas para evitar problemas quando vai para aba de design.
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		return datePicker;
	}
	private void createMainScreen(){
		frame = new JFrame();
		ImageIcon img = new ImageIcon("C:/Users/Felipe/workspace/PrinterBoleto/pdf.png");
		frame.setIconImage(img.getImage());
		frame.setTitle("Gerador de Boleto");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 500, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void createPDF(){
		String data = datePicker_dataDocumento.getJFormattedTextField().getText();
		String vencimento = datePicker_vencimento.getJFormattedTextField().getText();
		String instrucoes = textInstrucoes.getText();
		String numeroDocumento = textNumeroDoc.getText();
		String sacado = textSacado.getText();
		String valorDocumento = textValorDoc.getText();

		try {
			String path = new File(".").getCanonicalPath();
			PDDocument doc = new PDDocument();
			try
			{
				PDPage page = new PDPage();
				PDFont font = PDType1Font.HELVETICA_BOLD;
				doc.addPage(page);				
				createStream(doc, page, font,data, 100, 700);
				createStream(doc, page, font,numeroDocumento, 140, 700);
				createStream(doc, page, font,vencimento, 450, 740);
				createStream(doc, page, font,valorDocumento, 450, 680);
				createStream(doc, page, font,instrucoes, 100, 630);
				createStream(doc, page, font,sacado, 100, 580);
				
				
				
				doc.save("boleto.pdf");
				Desktop.getDesktop().open(new File(path +"//" +"boleto.pdf"));
			}
			finally
			{
				doc.close();
			}
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createStream(PDDocument doc,PDPage page,PDFont font, String text, int x, int y) throws IOException{
		PDPageContentStream contents = new PDPageContentStream(doc, page,PDPageContentStream.AppendMode.APPEND,false);
		contents.beginText();
		contents.setFont(font, 12);
		contents.newLineAtOffset(x, y);
		contents.showText(text);
		contents.endText();
		contents.close();
	}
}


