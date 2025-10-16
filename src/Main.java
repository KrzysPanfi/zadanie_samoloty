import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

        public class Main {
            public static int num = 0;
            private static JFrame frame;
            public static int charindx = 0;

            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
            }



            public JButton getjButton(DefaultTableModel model,ArrayList<Aircraft>planes ) {
                JButton button = new JButton("Uruchom wątki");
                button.addActionListener(e -> {
                    for(int i=0;i<planes.size();i++) {

                        String s="";
                        Aircraft aircraft = planes.get(i);
                       MyRunnable thread = new MyRunnable(aircraft.getModel(),model);
                        ExecutorService executorService = Executors.newFixedThreadPool(planes.size());
                        executorService.execute(thread);
                    }
                });
                return button;
            }

            public JButton getjButton3(JTextField modelplane,JTextField capacityfield,JTextField speedfield,JRadioButton jRadioButton,JRadioButton jRadioButton1, DefaultTableModel model,ArrayList<Aircraft>planes ) {
                JButton button = new JButton("Dodaj samolot");
                button.addActionListener(e -> {
                  Vector v = new Vector();
                  String mod=modelplane.getText();
                  int capacity=Integer.parseInt(capacityfield.getText());
                  int speed=Integer.parseInt(speedfield.getText());

                  if(jRadioButton.isSelected()) {
                      CargoPlane cargoPlane=new CargoPlane(mod,capacity,speed);
                      planes.add(cargoPlane);
                      v.add(mod);
                      v.add("CargoPlane");
                      v.add("  ");
                      v.add(" ");
                  }
                  if(jRadioButton1.isSelected()) {
                      PassangerPlane passangerPlane=new PassangerPlane(mod,capacity,speed);
                      planes.add(passangerPlane);
                      v.add(mod);
                      v.add("PassangerPlane");
                      v.add(" ");
                      v.add(" ");
                  }
               // MyRunnable thread= new MyRunnable(textField.getText(),model);


                  model.addRow(v);
                });
                return button;
            }




            private  JButton getjButton1(DefaultTableModel model,ArrayList<Aircraft>planes ) {
                JButton button = new JButton("Dodaj samolot");
                button.addActionListener(e -> {
                    JFrame frame1 = new JFrame("Input");
                    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame1.setSize(500, 500);
                    frame1.setLayout(new BorderLayout(8, 8));

                    JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JRadioButton jRadioButton1 = new JRadioButton("Cargo Plane");
                    JRadioButton jRadioButton2 = new JRadioButton("PassangerPlane");
                    ButtonGroup buttonGroup = new ButtonGroup();
                    buttonGroup.add(jRadioButton1);
                    buttonGroup.add(jRadioButton2);

                    frame1.add(basicPanel, BorderLayout.NORTH);
                    JTextField textField = new JTextField(15);
                    JTextField textField1 = new JTextField(15);
                    JTextField textField2 = new JTextField(15);
                    JButton button1 = getjButton3(textField,textField1,textField2,jRadioButton1,jRadioButton2, model,planes);
                    JButton button2 = getjButton2(frame1);
                    JLabel label = new JLabel("Podaj nazwę kraju:");
                    basicPanel.add(label);
                    Box box = Box.createVerticalBox();
                    box.add(button1);
                    box.add(button2);
                    box.add(textField);
                    box.add(textField1);
                    box.add(textField2);
                    box.add(jRadioButton1);
                    box.add(jRadioButton2);
                    basicPanel.add(box);
                  //  basicPanel.add(textField);
                   // basicPanel.add(button1);
                  //  basicPanel.add(button2);
                   // basicPanel.add(jRadioButton1);
                   // basicPanel.add(jRadioButton2);
                 //   basicPanel.add(textField1);
                  //  basicPanel.add(textField2);
                    frame1.setVisible(true);
                });
                return button;
            }

            private static JButton getjButton2(JFrame frame) {
                JButton button = new JButton("Cancel");
                button.addActionListener(e -> frame.dispose());
                return button;
            }


            private void createAndShowGUI() {
                frame = new JFrame("AirportApp");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setLayout(new BorderLayout(8, 8));
                JPanel basicPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel, BorderLayout.EAST);
                DefaultTableModel model = new DefaultTableModel();
                ArrayList<Aircraft> planes = new ArrayList<>();


                model.addColumn("Model");
                model.addColumn("Typ");
                model.addColumn("Status");
                model.addColumn("Akcja");



                JButton btn = getjButton(model,planes);
                Box box = Box.createVerticalBox();
                basicPanel.add(btn);
                JButton btn1 = getjButton1(model,planes);
                // JButton btn1 = getjButton1(model, threads, label);
                box.add(btn1);
                box.add(btn);
                basicPanel.add(box);
                frame.add(basicPanel, BorderLayout.EAST);
                JTable table = new JTable();
                table.setModel(model);
                JScrollPane tableScroll = new JScrollPane(table);
                table.setRowHeight(100);
                frame.add(tableScroll, BorderLayout.CENTER);
                frame.setVisible(true);
                JPanel basicPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                frame.add(basicPanel1, BorderLayout.NORTH);
            }

            static class MyRunnable implements Runnable {
                private final String threadName;
                private final DefaultTableModel model;


                public MyRunnable(String name, DefaultTableModel model) {
                    this.threadName = name;
                    this.model = model;
                }

                public DefaultTableModel getModel() {
                    return model;
                }
                public String getThreadName(){
                    return threadName;
                }
                @Override
                public void run() {
                    for (int k = 1; k <= 10; k++) {
                        int row=0;
                        int col=0;
                        for (int i = 0; i < model.getRowCount(); i++) {
                            for (int j = 0; j < model.getColumnCount(); j++) {
                                if (model.getValueAt(i, j).equals(threadName)) {
                                    row=i;
                                    col=j+2;
                                }
                            }
                        }
                        model.setValueAt(k, row, col);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            System.out.println(threadName + " przerwany!");
                        }
                    }
                }
            }
        }


