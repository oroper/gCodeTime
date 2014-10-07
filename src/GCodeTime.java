import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GCodeTime extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int height = 100, width = 300;
	private JFrame g;
	private JLabel msg = new JLabel("provami", JLabel.CENTER );
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GCodeTime();
		
	}

	public void setMsg(String s){
		
		msg.setText(s);
		
		revalidate();
		
		
	}
	
	
	public GCodeTime (){		
		super("GCodeTime");
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.lightGray);
		
		g = this;
		
		JLabel init = new JLabel("Segli un file.", JLabel.CENTER);
		
		add(init, BorderLayout.NORTH);

        JButton openBtn = new JButton("Open");
        
        

        openBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JFileChooser openFile = new JFileChooser(/*"/home/nevio/Dropbox/scuola/grafica/tirocinio/provefori"*/);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("GCode", "gcode");
                openFile.setFileFilter(filter);
                
                int returnVal = openFile.showOpenDialog(null);
                
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                   try {
					new Calcola((GCodeTime) g, openFile.getSelectedFile());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                }
            }
            
        });

        add(openBtn, BorderLayout.SOUTH);
        
        add(msg);
        
        setLocationRelativeTo(null); // posiziona la finestra in centro

        setVisible(true);
		
	}
}
