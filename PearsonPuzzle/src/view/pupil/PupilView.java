package view.pupil;

import java.awt.BorderLayout;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import view.JView;
import model.Model;
import controller.Controller;

/**
 * Definiert die Schüler Perspektive der grafischen Oberfläche
 * 
 * @author workspace
 */
public class PupilView extends JView{
	private ListSelectionModel listSelectionModel;
	private JTextArea projectDescription;
	private JButton enter;
	private JButton saveButton;
	public PupilView(Model model){
		// Instanzierung der Variablen
		super(model);
		projectDescription = new JTextArea("Wähle ein Projekt aus");
		enter = new JButton("Projekt öffnen");
		saveButton = new JButton("Übernehmen");	
		// Bei Konstruktion wird Ansicht "Projektliste" aufgerufen
		menu = new MenuPupil(frame);
		setupProjectList();
	}
	
	/**
	 * Projekt Liste wird gezeichnet.
	 */
	private void setupProjectList(){
		DefaultListModel <String> projectListModel = makeDefaultListModel(model.getProjects());
		JList <String> projectList = new JList<String>(projectListModel);
		projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		new JScrollPane(projectList);
		listSelectionModel = projectList.getSelectionModel();
		projectList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		projectList.setFixedCellHeight(20);
		projectList.setFixedCellWidth(200);
		//projectList.setPreferredSize(new Dimension(250,250));		
		mainPanel.add(projectList, BorderLayout.LINE_START);
		// Zeilen werden umgebrichen und Wortgrenzen beachtet
		projectDescription.setLineWrap(true);
		projectDescription.setWrapStyleWord(true);
		projectDescription.setEditable(false);
		projectDescription.setSize(200, 200);
		mainPanel.add(projectDescription, BorderLayout.EAST);
		mainPanel.add(enter, BorderLayout.SOUTH);
	}
	
	/**
	 * Wird vom Controller asugeführt, um Listener, Handler und <br>
	 * Controller hinzuzufügen
	 */
	public void addController(Controller controller){		
		listSelectionModel.addListSelectionListener(controller);
		
		enter.addActionListener(controller);
		enter.setActionCommand("openProject");
		
		saveButton.addActionListener(controller);
		saveButton.setActionCommand("saveChanges");
		
		menu.addActionListener(controller);
	}
	
	@Override
	public void update() {
		projectDescription.setText(model.getProjectDescription());
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}