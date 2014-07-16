/**
 * MappingFrame.java
 *
 * This file was generated by MapForce 2012r2sp1.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */


package com.mapforce;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import com.altova.types.*;


public class MappingFrame extends JFrame implements com.altova.TraceTarget {
	
	JPanel			contentPane;
	TitledBorder	titledBorder1;
	Box 		jHeader = new Box(BoxLayout.X_AXIS);
	Box		jHeaderInfo = new Box(BoxLayout.Y_AXIS);
	Box 		jButtonPane = new Box(BoxLayout.X_AXIS);
	JScrollPane	jScrollPaneStructures	= new JScrollPane();
	JPanel		jPanelStructures		= new JPanel();
	JLabel		jIconLabel				= new JLabel();
	JLabel		jInfoLabel1				= new JLabel();
	JLabel		jInfoLabel1a			= new JLabel();
	JLabel		jInfoLabel2				= new JLabel();
	JLabel		jInfoLabel3				= new JLabel();
	JButton		jStartButton			= new JButton();
	JPanel		jPanel1					= new JPanel();
	JScrollPane	jTraceScrollPane		= new JScrollPane();
	JTextArea	jTraceTextArea			= new JTextArea();


	JLabel jorderexport_334_5578868232Label0 = new JLabel();
	JTextField jorderexport_334_5578868232TextField0 = new JTextField();

	JLabel jText_file2Label1 = new JLabel();
	JTextField jText_file2TextField1 = new JTextField();


	public MappingFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		// Application Header
		jInfoLabel1.setText("THIS APPLICATION WAS GENERATED BY MapForce 2012r2sp1");
		jInfoLabel1a.setText("   - And was edited by David Anderson :-)");
		jInfoLabel2.setForeground(Color.blue);
		jInfoLabel2.setText("http://www.altova.com/mapforce");
		jInfoLabel3.setText("Please check the input and output files, and press the Start button...");
		jHeaderInfo.add(jInfoLabel1,0);
		jHeaderInfo.add(jInfoLabel1a,1);
		jHeaderInfo.add(jInfoLabel2,2);
		jHeaderInfo.add(jInfoLabel3,3);
		
		jIconLabel.setIcon(new ImageIcon(MappingFrame.class.getResource("mapforce.png")));
		jIconLabel.setText("");
				
		jHeader.add(jIconLabel);
		jHeader.add(Box.createHorizontalStrut(15));
		jHeader.add(jHeaderInfo);
		jHeader.add(Box.createGlue());
		
		jStartButton.setFont(new java.awt.Font("Dialog", 0, 11));
		jStartButton.setText("Start");
		jStartButton.addActionListener(new MappingFrame_jStartButton_actionAdapter(this));
		jButtonPane.add(Box.createHorizontalStrut(5));
		jButtonPane.add(jStartButton);
		jButtonPane.add(Box.createGlue());
				
		jScrollPaneStructures.setBorder(BorderFactory.createLineBorder(Color.black));
		jScrollPaneStructures.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPaneStructures.setAutoscrolls(true);
		jPanelStructures.setLayout(null);
		fillScrollPane();
		jScrollPaneStructures.getViewport().add(jPanelStructures, null);
		
		jTraceTextArea.setBackground(Color.white);
		jTraceTextArea.setForeground(Color.black);
		jTraceTextArea.setToolTipText("");
		jTraceTextArea.setText("");
		jTraceTextArea.setRows(20);
		
		jTraceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jTraceScrollPane.setViewportBorder(null);
		jTraceScrollPane.setAutoscrolls(true);
		jTraceScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		jTraceScrollPane.setDebugGraphicsOptions(0);
		jTraceScrollPane.setToolTipText("");
		jTraceScrollPane.setVerifyInputWhenFocusTarget(true);
		jTraceScrollPane.getViewport().add(jTraceTextArea, null);
		jTraceScrollPane.setPreferredSize(new Dimension(0, 200));
				
		contentPane = (JPanel)this.getContentPane();
		titledBorder1 = new TitledBorder("");
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		this.setSize(new Dimension(603, 511));
		this.setTitle("Mapforce Application");
		contentPane.add(jHeader, 0);
		contentPane.add(jScrollPaneStructures, 1);
		contentPane.add(jButtonPane, 2);
		contentPane.add(jTraceScrollPane, 3);
	}

	protected void fillScrollPane() {

		jorderexport_334_5578868232Label0.setText("Source file " + "FilenameGoesHere" + ":");
		jorderexport_334_5578868232Label0.setBounds(new Rectangle(15, 10, 438, 23));
		jPanelStructures.add(jorderexport_334_5578868232Label0, null);
		jorderexport_334_5578868232TextField0.setText("DirectoryGoesHere");
		jorderexport_334_5578868232TextField0.setBounds(new Rectangle(15, 35, 438, 23));
		jorderexport_334_5578868232TextField0.setEditable(false);
		jPanelStructures.add(jorderexport_334_5578868232TextField0, null);

//		jText_file2Label1.setText("Instance of Text file file:");
//		jText_file2Label1.setBounds(new Rectangle(15, 60, 438, 23));
//		jPanelStructures.add(jText_file2Label1, null);
//		jText_file2TextField1.setText("E:/PROJECTS/Enterprise Connect to Web Services/Output Text File.txt");
//		jText_file2TextField1.setBounds(new Rectangle(15, 85, 438, 23));
//		jText_file2TextField1.setEditable(false);
//		jPanelStructures.add(jText_file2TextField1, null);

		jPanelStructures.setLayout(null);
		jPanelStructures.setPreferredSize(new Dimension(85, 160));
//		jPanelStructures.setSize(new Dimension(85, 50));
//		jPanelStructures.setMinimumSize(new Dimension(85, 100));
//		jPanelStructures.setMaximumSize(new Dimension(85, 500));
	}

	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

    void jStartButton_actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jStartButton)) {
			jStartButton.setEnabled(false);
			jTraceTextArea.removeAll();
			jTraceTextArea.append("Started...\n");
			com.altova.TraceTarget ttc = this;
			//temp
			String directory = "NULL in jStartButton_actionPerformed";

			try {

			MappingMapToText_file MappingMapToText_fileObject = new MappingMapToText_file();




			MappingMapToText_fileObject.registerTraceTarget(ttc);
	

			// run mapping
			//
			// you have different options to provide mapping input and output:
			//
			// files using file names (available for XML, text, and Excel):
			//   com.altova.io.FileInput(String filename)
			//   com.altova.io.FileOutput(String filename)
			//
			// streams (available for XML, text, and Excel):
			//   com.altova.io.StreamInput(java.io.InputStream stream)
			//   com.altova.io.StreamOutput(java.io.OutputStream stream)
			//
			// strings (available for XML and text):
			//   com.altova.io.StringInput(String xmlcontent)
			//   com.altova.io.StringOutput()	(call getContent() after run() to get StringBuffer with content)
			//
			// Java IO reader/writer (available for XML and text):
			//   com.altova.io.ReaderInput(java.io.Reader reader)
			//   com.altova.io.WriterOutput(java.io.Writer writer)
			//
			// DOM documents (for XML only):
			//   com.altova.io.DocumentInput(org.w3c.dom.Document document)
			//   com.altova.io.DocumentOutput(org.w3c.dom.Document document)
			// 
			// By default, run will close all inputs and outputs. If you do not want this,
			// call the following function:
			// MappingMapToText_fileObject.setCloseObjectsAfterRun(false);

			{
				com.altova.io.Input orderexport_334_5578868232Source = com.altova.io.StreamInput.createInput(directory);

				MappingMapToText_fileObject.run(
						orderexport_334_5578868232Source);
			}



				jTraceTextArea.append("Finished\n");
			} catch (Exception ex) {
				jTraceTextArea.append("ERROR: " + ex.getMessage());
			}

			jStartButton.setEnabled(true);
		}
    }


	public void writeTrace(String info) {
		jTraceTextArea.append(info);
	}
}

class MappingFrame_jStartButton_actionAdapter
	implements java.awt.event.ActionListener {
	MappingFrame adaptee;

	MappingFrame_jStartButton_actionAdapter(MappingFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jStartButton_actionPerformed(e);
	}
}

