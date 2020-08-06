import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//required for border
import javax.swing.BorderFactory;
import javax.swing.border.Border;

//required for file IO
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;

//required for exception
import java.io.IOException;

//required decimal format
import java.text.DecimalFormat;


//Cake Panel
class CakePanel extends JPanel implements ActionListener, ItemListener{
    DecimalFormat df = new DecimalFormat ("0.00");
    //list all UI components for the panel
    JLabel line1;
    JLabel cmenu;
    JLabel line2;
    JLabel topping;
    JLabel choc;
    JLabel cher;
    JLabel wcream;
    JLabel psize;
    JLabel small;
    JLabel medium;
    JLabel big;
    JLabel line3;
    JLabel order;
    JLabel toppingorder;
    JRadioButton bs;
    JRadioButton bm;
    JRadioButton bb;
    JLabel sizeorder;
    JCheckBox cb1;
    JCheckBox cb2;
    JCheckBox cb3;    
    JLabel line4;
    JLabel total;
    JLabel line5;
    JButton submit;
    JButton clear;
    JLabel lbloutput; 
    JScrollPane jsp;
    JLabel quantity;
    JTextField user;
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

    //global variable  
    String output="";
    String list_selection="";
    String rb_selection="";
    String filePath="data.txt"; //in the same directory
    int ttl=0, mult=0;
    int q;

    public CakePanel(){  
    
      //adjust size and set layout
      setPreferredSize (new Dimension (600, 700));
      setLayout (null);      

      //JLabel
      line1 = new JLabel ("----------------------------------------------");
      line1.setBounds (20, 15, 210, 25);
      add (line1);
      
      cmenu = new JLabel ("Cake Menu");
      cmenu.setBounds (78, 35, 100, 25);
      add (cmenu);
      
      line2 = new JLabel ("----------------------------------------------");
      line2.setBounds (20, 55, 210, 25);
      add (line2);
      
      topping = new JLabel ("BlackForest with available toppings:");
      topping.setBounds (20, 75, 230, 25);
      add (topping);
      
      choc = new JLabel ("1) Chocolate");
      choc.setBounds (20, 95, 100, 25);
      add (choc);
      
      cher = new JLabel ("2) Cherries");
      cher.setBounds (20, 115, 100, 25);
      add (cher);
      
      wcream = new JLabel ("3) Whipped Cream");
      wcream.setBounds (20, 135, 120, 25);
      add (wcream);
      
      psize = new JLabel ("Price:");
      psize.setBounds (20, 170, 100, 25);
      add (psize);
      
      small = new JLabel ("[1] Small       :RM45.00");
      small.setBounds (20, 190, 130, 25);
      add (small);
      
      medium = new JLabel ("[2] Medium   :RM65.00");
      medium.setBounds (20, 210, 130, 25);
      add (medium);
      
      big = new JLabel ("[3] Big            :RM80.00");
      big.setBounds (20, 230, 130, 25);
      add (big);
      
      order = new JLabel ("Cake Order Details:");
      order.setBounds (20, 270, 130, 25);
      add (order);
      
      line3 = new JLabel ("-------------------------------------------------------------------------------------------");
      line3.setBounds (20, 285, 400, 25);
      add (line3);
      
      toppingorder = new JLabel ("Topping :");
      toppingorder.setBounds (20, 305, 100, 25);
      add (toppingorder);
      
      sizeorder = new JLabel ("Size         :");
      sizeorder.setBounds (20, 330, 100, 25);
      add (sizeorder);
          
      //JCheckbox and item listener
      cb1 = new JCheckBox ("Chocolate");
      cb1.setBounds (75, 305, 100, 25);
      cb1.addItemListener (this);
      add (cb1);
    
      cb2 = new JCheckBox ("Cherries");
      cb2.setBounds (175, 305, 90, 25);
      cb2.addItemListener (this);
      add (cb2);
    
      cb3 = new JCheckBox ("Whipped Cream");
      cb3.setBounds (265, 305, 140, 25);
      cb3.addItemListener (this);
      add (cb3);  
       
      //JRadioButton and action listener
      bs = new JRadioButton ("Small");
      bs.setBounds (75, 330, 100, 25);
      bs.addActionListener (this);
      add (bs);
    
      bm = new JRadioButton ("Medium");
      bm.setBounds (175, 330, 90, 25);
      bm.addActionListener (this);
      add (bm);
    
      bb = new JRadioButton ("Big");
      bb.setBounds (265, 330, 50, 25);
      bb.addActionListener (this);
      add (bb);
      
      //define ButtonGroup
      ButtonGroup bg = new ButtonGroup();
      bg.add (bs);
      bg.add (bm);
      bg.add (bb);
      
      //JTextField for JLabel quantity
      quantity = new JLabel ("Quantity: ");
      quantity.setBounds (20, 355, 100, 25);
      add (quantity);
      
      user = new JTextField (20);
      user.setBounds (75, 355, 100, 25); 
      add (user);

      line4 = new JLabel ("-------------------------------------------------------------------------------------------");
      line4.setBounds (20, 370, 400, 25);
      add (line4);

      total = new JLabel ("Total Price :"); 
      total.setBounds (20, 385, 200, 25);
      add (total);

      line5 = new JLabel ("-------------------------------------------------------------------------------------------");
      line5.setBounds (20, 400, 400, 25);
      add (line5);

      submit = new JButton ("Submit");  
      submit.setBounds (180, 355, 100, 25);
      add (submit);

      clear = new JButton("Clear"); 
      clear.setBounds (280, 355, 100, 25);
      add (clear);

      //handle button submit action listener
      //view the input to output label
      //and write to file
      submit.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){  
             //call method              
             if(printOutput())
                writeInput();
                quantity.setText("Quantity: ");
                total.setText("Total Price: RM" + df.format(mult));   
          } 
       });

      //handle button clear action listener
      clear.addActionListener(new ActionListener(){  
          public void actionPerformed(ActionEvent e){  
             lbloutput.setText("Output"); 
             bg.clearSelection();
             cb1.setSelected(false);
             cb2.setSelected(false);
             cb3.setSelected(false);
             user.setText(" ");
             quantity.setText("Quantity: ");
             total.setText("Total Price: RM"); 
          }  
        });

      lbloutput = new JLabel("Output");
      lbloutput.setBounds (20, 430, 400, 25);
      lbloutput.setBorder(border);
      lbloutput.setVerticalAlignment(JLabel.TOP);
      
      //add output label to scrollpane
      jsp = new JScrollPane(lbloutput);
      jsp.setBounds (20, 430, 400, 200);
      add(jsp);   
  }
  
  //handle radio button selection
   public void actionPerformed(ActionEvent ae) {
      rb_selection = ae.getActionCommand();    	   
   }
   
   //handle item listener for checkbox
   public void itemStateChanged(ItemEvent ie) {
      JCheckBox check = (JCheckBox)ie.getSource();
      list_selection += check.getText() + " ";   
   }
     //method to print output to lbloutput
    public boolean printOutput(){
      
      output = "<html>";
      output += "Thank you for your order<br><br>";   
      output += "Topping: " + list_selection + "<br>";

      q = Integer.parseInt(user.getText());
      if(cb1.isSelected()){
      ttl += 10;
      }if(cb2.isSelected()){
      ttl += 10;
      }if(cb3.isSelected()){
      ttl += 10;
      }

      if(bs.isSelected()){
         mult = ttl + (45*q);
      }else if(bm.isSelected()){
         mult = ttl + (65*q);
      }else if(bb.isSelected()){
         mult = ttl + (80*q);
      }
            
      output += "Size: " + rb_selection + "<br>";
      output += "Quantity: "+ q + "<br>";
      output += "Total: RM" + df.format(mult) + "<br>";
      output += "</html>";          
      lbloutput.setText(output);
      jsp.getViewport().revalidate();
      return true;
    }
    
    //write to file
    public void writeInput(){
      File file = new File(filePath);
		FileWriter fr = null;
		BufferedWriter br = null;
		PrintWriter pr = null;
      String input = rb_selection + ", " + list_selection + ","  +  user.getText() +", RM" + df.format(mult);

      //exception implementation
		try {
			// to append to file, you need to initialize FileWriter using below constructor
			fr = new FileWriter(file, true);
			br = new BufferedWriter(fr);
			pr = new PrintWriter(br);
			pr.println(input);
		} catch (IOException e) {			
         lbloutput.setText(e.toString());
		} finally {
			try {
				pr.close();
				br.close();
				fr.close();
			} catch (IOException e) {
				lbloutput.setText(e.toString());
			}
		}
    }
}
class MenuActionListener implements ActionListener {
   CakePanel fp;
   //receive FormPanel class to this constructor
   public MenuActionListener(CakePanel p){
      fp = p;
   }
    public void actionPerformed(ActionEvent e) {
       BufferedReader reader;
 	   try {
 			reader = new BufferedReader(new FileReader(fp.filePath));
 			String line = reader.readLine();
         String output="<html>";
 			while (line != null) {
 				output += line + "<br>";
 				// read next line
 				line = reader.readLine();
 			}
          output += "<br>";
          fp.lbloutput.setText(output);
 			reader.close();
 		} catch (IOException io) {
 			fp.lbloutput.setText(io.toString());
 		}
 
   }
 }
//run the application using this main
public class CakeApp {  
   public static void main(String[] 	args) {  
      JFrame f = new JFrame("Cake Order System");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      f.getContentPane().add (new CakePanel());
      CakePanel fp = new CakePanel();
      
      JMenuBar mb = new JMenuBar(); 
      // create a menu 
      JMenu x = new JMenu("Menu"); 
      
      // create menuitems 
      JMenuItem m1 = new JMenuItem("View Data"); 
      // attach listener and send FormPanel class
      m1.addActionListener(new MenuActionListener(fp));
      
      JMenuItem m2 = new JMenuItem("Exit");  
      m2.addActionListener((event) -> System.exit(0));
      // add menu items to menu 
      x.add(m1); 
      x.add(m2);
     
      // add menu to menu bar 
      mb.add(x); 
      // add menubar to frame 
      f.setJMenuBar(mb);  
               
      //add panels to frame      
      f.add(fp);
      f.pack();
      f.setVisible(true);
   }  
}