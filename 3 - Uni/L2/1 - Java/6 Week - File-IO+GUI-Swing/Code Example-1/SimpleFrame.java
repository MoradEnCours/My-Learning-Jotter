import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SimpleFrame extends JFrame implements ActionListener {
	
	public SimpleFrame() {
		super("Hello World!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(300, 250);

		JButton button = new JButton("Press me!");
		button.addActionListener(new ButtonListener());
		getContentPane().add(button);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SimpleFrame().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println ("Hello");
	}

}
